package com.bridge.common;

import java.io.Serializable;

import com.google.gson.Gson;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 操作消息提醒
 * 
 * @author 
 */
@ApiModel("ajaxResult")
public class AjaxResult<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";

    public static final String MSG_TAG = "msg";

    public static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type
    {
        /** 成功 */
        SUCCESS(0),
        /** 警告 */
        WARN(301),
        /** 错误 */
        ERROR(500);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }

    /** 状态类型 */
    @ApiModelProperty(value = "状态类型", name = "type")
    private Type type;

    /** 状态码 */
    @ApiModelProperty(value = "状态码", name = "code")
    private int code;

    /** 返回内容 */
    @ApiModelProperty(value = "返回内容", name = "msg")
    private String msg;

    /** 数据对象 */
    @ApiModelProperty(value = "数据对象", name = "data")
    private T data;

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public AjaxResult()
    {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     * 
     * @param type 状态类型
     * @param msg 返回内容
     */
    public AjaxResult(Type type, String msg)
    {
        this.code = type.value;
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     * 
     * @param type 状态类型
     * @param msg 返回内容
     * @param data 数据对象
     */
    public AjaxResult(Type type, String msg, T data)
    {
    	this.code = type.value;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static AjaxResult<String> success()
    {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功数据
     * 
     * @return 成功消息
     */
    public static<T> AjaxResult<T> success(T data)
    {
        return AjaxResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @return 成功消息
     */
    public static AjaxResult<String> success(String msg)
    {
        return AjaxResult.success(msg, null);
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static<T> AjaxResult<T> success(String msg, T data)
    {
        return new AjaxResult<T>(Type.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     * 
     * @param msg 返回内容
     * @return 警告消息
     */
    public static<T> AjaxResult<String> warn(String msg)
    {
        return AjaxResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static<T> AjaxResult<T> warn(String msg, T data)
    {
        return new AjaxResult<T>(Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     * 
     * @return
     */
    public static<T> AjaxResult<String> error()
    {
        return AjaxResult.error("操作失败");
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @return 警告消息
     */
    public static<T> AjaxResult<String> error(String msg)
    {
        return AjaxResult.error(msg, null);
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static<T> AjaxResult<T> error(String msg, T data)
    {
        return new AjaxResult<T>(Type.ERROR, msg, data);
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    @Override
    public String toString() {
        Gson gs = new Gson();
        return  gs.toJson(this);
    }
    
	/*
	 * @Override public String toString() { return new
	 * ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE) .append("code",
	 * getCode()) .append("msg", getMsg()) .append("data", getData()) .toString(); }
	 */
}
