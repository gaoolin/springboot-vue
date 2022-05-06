<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <!--添加清除属性clearable-->
      <el-input v-model="search" placeholder="请输入关键字" style="width: 25%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>

    <el-table :data="tableData" border stripe style="width: 100%">  <!--添加斑马纹tripe-->
      <el-table-column prop="id" label="ID" sortable/> <!--添加排序sortable属性-->
      <el-table-column prop="username" label="用户名"/>
      <!--      <el-table-column prop="password" label="密码"/>-->
      <el-table-column prop="nickName" label="昵称"/>
      <el-table-column prop="age" label="年龄"/>
      <el-table-column prop="sex" label="性别"/>
      <el-table-column prop="address" label="地址"/>
      <!--  添加操作列-->
      <el-table-column fixed="right" label="Operations" width="120">
        <template #default="scope">
          <el-button type="text" size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除吗?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger" size="small">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 20]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
      <!--新增按钮的弹窗-->
      <el-dialog v-model="dialogVisible" title="提示" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%"/>
          </el-form-item>
        </el-form>
        <el-form :model="form" label-width="120px">
          <el-form-item label="昵称">
            <el-input v-model="form.nickName" style="width: 80%"/>
          </el-form-item>
        </el-form>
        <el-form :model="form" label-width="120px">
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 80%"/>
          </el-form-item>
        </el-form>
        <el-form :model="form" label-width="120px">
          <el-form-item label="性别">
            <el-radio v-model="form.sex" label="男">男</el-radio>
            <el-radio v-model="form.sex" label="女">女</el-radio>
            <el-radio v-model="form.sex" label="未知">未知</el-radio>

          </el-form-item>
        </el-form>
        <el-form :model="form" label-width="120px">
          <el-form-item label="地址">
            <el-input type="textarea" v-model="form.address" style="width: 80%"/>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 认</el-button>
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: 'HomeView',
  components: {},
  data() {
    return {
      form: {},
      dialogVisible: false,
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      tableData: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get("/api/user", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    add() {
      this.dialogVisible = true
      //  每次点击清空表单域对象
      this.form = {}
    },
    save() {
      if (this.form.id) {
        //更新
        request.put("/api/user", this.form).then((res) => {   //ES语法
          console.log(res)
          if (res.code === "0") {
            this.$message({
              type: "success",
              message: res.extend.update
            })
          } else {
            this.$message({
              type: "error",
              message: res.extend.update
            })
          }
          //刷新表格数据
          this.load()
          //关闭弹窗
          this.dialogVisible = false
        })
      } else {
        //新增
        request.post("/api/user", this.form).then((res) => {   //ES语法
          console.log(res)
          if (res.code === "0") {
            this.$message({
              type: "success",
              message: res.data
            })
          } else {
            this.$message({
              type: "error",
              message: res.data
            })
          }
        })
      }
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row)) //深拷贝：避免弹窗数据填完后，表单数据发生变化。这样表单数据是一个独立对象和表格数据完全隔离开
      this.dialogVisible = true
    },
    handleDelete(rowId) {
      console.log(rowId)
      request.delete("/api/user/" + rowId).then(res => {
        if (res.code === "0") {
          this.$message({
            type: "success",
            message: res.extend.delete
          })
        } else {
          this.$message({
            type: "error",
            message: res.extend.delete
          })
        }
      })
      this.load()
    },
    handleSizeChange(pageSize) {       //改变当前每页的个数触发
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {    //改变当前页码触发函数
      this.currentPage = pageNum
      this.load()
    }
  }
}

</script>
