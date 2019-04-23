package com.wangshi.ws.springboot.service.impl;

import com.wangshi.ws.springboot.model.Teacher;
import com.wangshi.ws.springboot.mapper.TeacherMapper;
import com.wangshi.ws.springboot.service.TeacherService;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacherById(Integer id) {
        return teacherMapper.selectById(id);
    }
}
