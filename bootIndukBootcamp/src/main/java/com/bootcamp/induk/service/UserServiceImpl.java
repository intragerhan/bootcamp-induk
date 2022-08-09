package com.bootcamp.induk.service;

import com.bootcamp.induk.domain.UserDto;
import com.bootcamp.induk.mapper.UserMapper;
import com.bootcamp.induk.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public int createUser(UserDto userDto) throws Exception {
        return userMapper.insertUser(userDto);
    }

    @Override
    public UserDto readUser(String id) throws Exception {
        return userMapper.selectUser(id);
    }

    @Override
    public UserDto loginUser(String id, String pwd) throws Exception {
        return userMapper.loginUser(id, pwd);
    }

    @Override
    public int modifyUserInfo(UserDto userDto) throws Exception {
        return userMapper.updateUser(userDto);
    }

    @Override
    public int removeUser(String pwd) throws Exception {
        return userMapper.deleteUser(pwd);
    }
}
