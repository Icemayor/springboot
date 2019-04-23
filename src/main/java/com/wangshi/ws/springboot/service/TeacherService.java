package com.wangshi.ws.springboot.service;

import com.wangshi.ws.springboot.model.Teacher;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Icemayor
 * @since 2019-04-07
 */
public interface TeacherService extends IService<Teacher> {

    public Teacher getTeacherById(Integer id);
}
