package com.gl.springbootend.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回类
 */

public class Msg<T> {

    private String code;
    private String msg;
    private T data;
    private Map<String, Object> extend = new HashMap<>();

    public Msg() {
    }

    public Msg(T data) {
        this.data = data;
    }

    public static Msg success(){
        Msg res = new Msg<>();
        res.setCode("0");
        res.setMsg("处理成功");
        return res;
    }

    public static <T> Msg<T> success(T data){
        Msg<T> res = new Msg<>();
        res.setCode("0");
        res.setMsg("处理成功");
        res.setData(data);
        return res;
    }

    public static Msg fail(){
        Msg res = new Msg();
        res.setCode("-1");
        res.setMsg("处理失败");
        return res;
    }

    public Msg add(String key, Object value){
        this.getExtend().put(key, value);
        return this;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
