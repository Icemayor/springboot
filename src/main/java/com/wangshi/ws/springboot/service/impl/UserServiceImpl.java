package com.wangshi.ws.springboot.service.impl;

import com.wangshi.ws.springboot.model.User;
import com.wangshi.ws.springboot.mapper.UserMapper;
import com.wangshi.ws.springboot.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Icemayor
 * @since 2019-04-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查找用户名是否存在
     *
     * @param username
     * @return
     */
    @Override
    public Long findUsername(String username) {
        return userMapper.findUsername(username);
    }

    /**
     * 根据用户名查找密码
     *
     * @param username
     * @return
     */
    @Override
    public String findPassword(String username) {
        return userMapper.findPassword(username);
    }

    /**
     * 根据用户名查找权限字符串
     *
     * @param username
     * @return
     */
    @Override
    public String findPerms(String username) {
        return userMapper.findPerms(username);
    }
}
