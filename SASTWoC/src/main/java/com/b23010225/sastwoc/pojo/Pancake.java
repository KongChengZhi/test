package com.b23010225.sastwoc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//饼类
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Pancake {
    private Integer id;
    private String title;
    private Date createTime;
    private Date deadline;
    private Boolean isDone;
}
