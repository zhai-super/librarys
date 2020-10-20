package com.zyh.utils;

public class Send<T> {
    private Integer status;
    private String message;
    private T data;

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Send<T> sendJsonSucess( T data) {
        Send<T> send = new Send<>();
        send.setStatus(200);
        send.setMessage("成功");
        send.setData(data);
        return send;
    }
    public static <T> Send<T> sendJsonError( T data) {
        Send<T> send = new Send<>();
        send.setStatus(404);
        send.setMessage("失败");
        return send;
    }

    @Override
    public String toString() {
        return "Send{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
