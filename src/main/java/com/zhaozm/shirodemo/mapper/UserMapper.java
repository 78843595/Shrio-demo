package com.zhaozm.shirodemo.mapper;

import com.zhaozm.shirodemo.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User findByUsername(@Param("username") String username);

}
