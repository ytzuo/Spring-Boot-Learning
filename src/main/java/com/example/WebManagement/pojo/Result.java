package com.example.WebManagement.pojo;

public class Result {
    private Integer code;
    private String message;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success(Object ob){
        System.out.println();
        System.out.println();
        System.out.println(ob);
        System.out.println();
        System.out.println();
        Result result = new Result();
        result.setCode(1);
        result.setMessage("success");
        result.setData(ob);
        return result;
    }

    public static Result failure(){
        Result result = new Result();
        result.setCode(2);
        result.setMessage("failure");
        result.setData(null);
        return result;
    }

    public static Result error(String str){
        Result result = new Result();
        result.setCode(2);
        result.setMessage(str);
        result.setMessage(null);
        return result;
    }
}
