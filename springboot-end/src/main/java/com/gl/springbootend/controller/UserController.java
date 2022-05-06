package com.gl.springbootend.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gl.springbootend.comm.Msg;
import com.gl.springbootend.entity.User;
import com.gl.springbootend.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    /*
    @RequestBody的作用是把请求传过来的json转换（映射）成JavaBean实例对象
    @PostMapping的作用是接收前端POST请求传过来的数据
     */

    //直接引入mapper，这并不规范。正常步骤是controller中装入service对象，service装入dao对象
    @Resource
    UserMapper userMapper;

    @PostMapping
    public Msg<?> save(@RequestBody User user) {
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Msg.success();
    }

    @GetMapping
    public Msg<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "") String search) {
        Page<User> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(User::getNickName, search);
        }
        Page<User> userPage = userMapper.selectPage(page, wrapper);
        System.out.println("userPage = " + userPage);
        return Msg.success(userPage);
    }

    @PutMapping
    public Msg<?> update(@RequestBody User user) {
        try {
            userMapper.updateById(user);
            return Msg.success().add("update", "更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("update", "更新失败！");
        }
    }

    @DeleteMapping("/{id}")
    public Msg<?> delete(@PathVariable String id) {
        try {
            userMapper.deleteById(id);
            return Msg.success().add("delete", "删除成功！");
        } catch (Exception e) {
            return Msg.success().add("delete", "删除失败！");
        }
    }
}
