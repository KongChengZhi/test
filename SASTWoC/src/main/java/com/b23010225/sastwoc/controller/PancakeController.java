package com.b23010225.sastwoc.controller;

import com.b23010225.sastwoc.Service.PancakeService;
import com.b23010225.sastwoc.pojo.Pancake;
import com.b23010225.sastwoc.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PancakeController {
    @Autowired
    private PancakeService pancakeService;

    @GetMapping(value = "/pancake")
    public Result view(){
        List<Pancake> pancakeList = pancakeService.view();
        return Result.success(pancakeList);
    }//查看所有饼

    //格式： title,deadline
    @PutMapping(value = "/pancake")
    public Result bake(@RequestBody Pancake pancake){
        pancakeService.bake(pancake);
        return Result.success("你成功画了个饼！");
    } //画饼

    @PostMapping(value = "/pancake/{id}")
    public Result pan(@PathVariable Integer id){
        pancakeService.pan(id);
        return Result.success("你成功做了个锅");
    }//烙饼

    @DeleteMapping(value = "/pancake/{id}")
    public Result delete(@PathVariable Integer id){
        pancakeService.delete(id);
        return Result.success("你成功删除了一个饼");
    }
}
