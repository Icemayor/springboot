package com.wangshi.ws.springboot.mapper;

import com.wangshi.ws.springboot.model.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Icemayor
 * @since 2019-04-07
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查找是否存在该用户名
     * @param username
     * @return
     */
   public Long findUsername(String username);

    /**
     * 根据用户名查找密码
     * @param username
     * @return
     */
    public String findPassword(String username);

    /**
     * 根据用户名查找权限字符串
     * @param username
     * @return
     */
    String findPerms(String username);
}
