package com.pm.reactive.demo.service;

import com.pm.reactive.demo.model.User;
import com.pm.reactive.demo.model.UserDto;
import org.springframework.beans.BeanUtils;

public class Convertor {

    public static User dtoToEntity(UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        return user;
    }
    public static UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }
}
