package com.bootcamp.induk.mapper;

import com.bootcamp.induk.domain.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    int insertUser(UserDto userDto) throws Exception;
    UserDto selectUser(String id) throws Exception;
    UserDto loginUser(String id, String pwd) throws Exception;
    int updateUser(UserDto user) throws Exception;
    int deleteUser(String id) throws Exception;
}
