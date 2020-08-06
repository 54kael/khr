package com.kael.hr.responst;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    private String code;
    private String message;
    private Map<String,Object> data;

    /**
     * 操作成功，不需要返回数据
     */
    public static Result ok() {
        Result result = new Result();
        result.setCode(Statue.SUCCESS.getCode());
        result.setMessage(Statue.SUCCESS.getMessage());
        return result;
    }

    /**
     * 操作成功，并且给data单独设置属性名和值
     * @param attr (data属性名)
     * @param o (data数据)
     */
    public Result data(String attr, Object o) {
        Map<String,Object> map = new HashMap<>();
        map.put(attr,o);
        setData(map);
        return this;
    }

    /**
     * 操作成功，一次性返回data
     * @param map data
     */
    public Result data(Map<String,Object> map) {
        setData(map);
        return this;
    }

    /**
     * 操作失败
     * @param message 失败信息
     */
    public static  Result failure(String message) {
        Result result = new Result();
        result.setCode("50000");
        result.setMessage(message);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
