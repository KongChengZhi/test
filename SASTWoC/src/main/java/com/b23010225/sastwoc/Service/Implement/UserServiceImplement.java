package com.b23010225.sastwoc.Service.Implement;

import com.b23010225.sastwoc.Service.UserService;
import com.b23010225.sastwoc.mapper.UserMapper;
import com.b23010225.sastwoc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user) {
        return userMapper.select(user);
    }
}
