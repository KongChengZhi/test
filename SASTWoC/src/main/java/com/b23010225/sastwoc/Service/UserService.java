package com.b23010225.sastwoc.Service;

import com.b23010225.sastwoc.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User login(User user);
}
