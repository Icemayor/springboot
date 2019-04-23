package com.wangshi.ws.springboot.web;

import com.wangshi.ws.springboot.common.config.FebsProperties;
import com.wangshi.ws.springboot.common.controller.BaseController;
import com.wangshi.ws.springboot.common.domain.ResponseBo;
import com.wangshi.ws.springboot.common.util.MD5Utils;
import com.wangshi.ws.springboot.common.util.vcode.Captcha;
import com.wangshi.ws.springboot.common.util.vcode.GifCaptcha;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Icemayor
 * @since 2019-04-07
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private FebsProperties febsProperties;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String CODE_KEY = "_code";

    @RequestMapping("/backPage")
    public String backPage(){
        return "测试一下！";
    }

    @RequestMapping("test")
    public String user(Model model){
        //添加数据
        model.addAttribute("name", "网事科技！");
        //返回html页面
        return "test";
    }

    /**
     * 修改用户名
     * @return
     */
    @RequestMapping("alterUsername")
    public String alterUsername(){
        return "user/alterUsername";
    }

    /**
     * 修改密码
     * @return
     */
    @RequestMapping("alterPassword")
    public String alterPassword(){
        return "user/alterPassword";
    }

    /**
     * 默认跳转的登录页面
     * @return
     */
    @RequestMapping("toLogin")
    public String toDefaultPage(){
        return "login";
    }

    /**
     * 默认跳转的未授权页面
     * @return
     */
    @RequestMapping("noAuth")
    public String noAuth(){
        return "noAuth";
    }

    /**
     * 实现登录操作
     * @return
     */
    @RequestMapping("login")
    public ResponseBo login(String username, String password, Model model, String code, Boolean rememberMe){

        if (!StringUtils.isNotBlank(code)) {
            //直接类调用方法
            return ResponseBo.warn("验证码不能为空！");
        }
        Session session = super.getSession();
        String sessionCode = (String) session.getAttribute(CODE_KEY);
        if (!code.equalsIgnoreCase(sessionCode)) {
            return ResponseBo.warn("验证码错误！");
        }

        //shiro编写认证操作
        //获取subject
//        Subject subject = SecurityUtils.getSubject();
//        //封装数据
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行登录方法(只要login的方法没有任何异常，就说明登录成功）
        // 密码 MD5 加密

//        password = MD5Utils.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject != null)
                subject.logout();
            super.login(token);
//            this.userService.updateLoginTime(username);
            return ResponseBo.ok();
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseBo.error("认证失败！");
        }
    }

    @RequestMapping("gifCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");

            Captcha captcha = new GifCaptcha(
                    febsProperties.getValidateCode().getWidth(),
                    febsProperties.getValidateCode().getHeight(),
                    febsProperties.getValidateCode().getLength());
            HttpSession session = request.getSession(true);
            captcha.out(response.getOutputStream());
            session.removeAttribute(CODE_KEY);
            session.setAttribute(CODE_KEY, captcha.text().toLowerCase());
        } catch (Exception e) {
            log.error("图形验证码生成失败", e);
        }
    }
}

