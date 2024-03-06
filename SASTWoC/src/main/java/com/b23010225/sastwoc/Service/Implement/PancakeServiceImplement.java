package com.b23010225.sastwoc.Service.Implement;

import com.b23010225.sastwoc.Service.PancakeService;
import com.b23010225.sastwoc.mapper.PancakeMapper;
import com.b23010225.sastwoc.pojo.Pancake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PancakeServiceImplement implements PancakeService {
    @Autowired
    private PancakeMapper pancakeMapper;
    @Override
    public List<Pancake> view() {
        return pancakeMapper.view();
    }

    @Override
    public void bake(Pancake pancake) {
        pancakeMapper.bake(pancake);
    }

    @Override
    public void pan(Integer id) {
        pancakeMapper.pan(id);
    }

    @Override
    public void delete(Integer id) {
        pancakeMapper.delete(id);
    }
}
