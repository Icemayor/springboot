package com.wangshi.ws.springboot.service;

import com.wangshi.ws.springboot.model.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Icemayor
 * @since 2019-04-07
 */
public interface UserService extends IService<User> {
    /**
     * 查找用户名是否存在
     * @param username
     * @return
     */
    Long findUsername(String username);

    /**
     * 根据用户名查找密码
     * @param username
     * @return
     */
    String findPassword(String username);

    /**
     * 根据用户名查找权限字符串
     * @param username
     * @return
     */
    String findPerms(String username);
}
