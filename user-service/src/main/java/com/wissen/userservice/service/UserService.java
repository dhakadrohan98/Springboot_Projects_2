package com.wissen.userservice.service;

import com.wissen.userservice.dto.ResponseDto;
import com.wissen.userservice.entity.User;

public interface UserService {
    User saveUser(User user);

    ResponseDto getUser(Long userId);
}
