package com.bootcamp.induk.service.interfaces;

import com.bootcamp.induk.domain.UserDto;

import java.util.List;

public interface UserService {

    int createUser(UserDto userDto) throws Exception;
    UserDto readUser(String id) throws Exception;
    UserDto loginUser(String id, String pwd) throws Exception;
    int modifyUserInfo(UserDto userDto) throws Exception;
    int removeUser(String pwd) throws Exception;
}
