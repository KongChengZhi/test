package com.b23010225.sastwoc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//成员类
@Data
@NoArgsConstructor
@AllArgsConstructor
//构造方法注解
public class User {
    private Integer id;
    private String username;
    private String password;
    /**
     * role设置：
     *  0为普通成员 权限：1.查询 2.做锅 (更改isDone)
     *  1为管理员 权限：1.2.同上 3.增加饼 4.删除饼
     */
    private Integer role;

}
