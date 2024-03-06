package com.b23010225.sastwoc.Service;

import com.b23010225.sastwoc.pojo.Pancake;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PancakeService {
    List<Pancake> view();

    void bake(Pancake pancake);

    void pan(Integer id);

    void delete(Integer id);
}
