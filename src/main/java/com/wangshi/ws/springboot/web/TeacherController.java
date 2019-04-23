package com.wangshi.ws.springboot.web;


import com.wangshi.ws.springboot.model.Teacher;
import com.wangshi.ws.springboot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Icemayor
 * @since 2019-04-07
 */
@Controller
@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @RequestMapping("/getTeacherById")
    public String getTeacherById(Integer id){
        Teacher teacher = teacherService.getTeacherById(id);
        return "real good!";
    }
}

