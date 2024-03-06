package com.b23010225.sastwoc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//响应结果
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean success;//响应码，false表明成功，无事发生
    private Integer errCode;
    private Integer errMsg; //两个都是错误提示，null表明无事发生（不要404 Not Found!）,1表示有事发生
    private Object data; //返回的数据

    //增删改 成功响应
    public static Result success(){
        return new Result(false,null,null,null);
    }
    //查询 成功响应
    public static Result success(Object data){
        return new Result(false,null,null,data);
    }
    //失败响应
    public static Result error(){
        return new Result(true,1,1,null);
    }

    public static Result error(Object data){
        return new Result(true,1,1,data);
    }
}
