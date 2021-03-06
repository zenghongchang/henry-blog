package com.tofba.blog.web.controller.admin;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tofba.blog.model.domain.User;
import com.tofba.blog.model.dto.BlogConstant;
import com.tofba.blog.model.dto.JsonResult;
import com.tofba.blog.model.enums.ResultCodeEnum;
import com.tofba.blog.service.UserService;
import com.tofba.blog.utils.LocaleMessageUtil;

import cn.hutool.crypto.SecureUtil;
import freemarker.template.Configuration;

@Slf4j
@Controller
@RequestMapping(value = "/admin/profile")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private Configuration configuration;
    
    @Autowired
    private LocaleMessageUtil localeMessageUtil;
    
    /**
     * 获取用户信息并跳转
     *
     * @return 模板路径admin/admin_profile
     */
    @GetMapping
    public String profile() {
        return "admin/admin_profile";
    }
    
    /**
     * 处理修改用户资料的请求
     *
     * @param user user
     * @param session session
     * @return JsonResult
     */
    @PostMapping(value = "save")
    @ResponseBody
    public JsonResult saveProfile(@Valid @ModelAttribute User user, BindingResult result, HttpSession session) {
        try {
            if (result.hasErrors()) {
                for (ObjectError error : result.getAllErrors()) {
                    return new JsonResult(ResultCodeEnum.FAIL.getCode(), error.getDefaultMessage());
                }
            }
            userService.save(user);
            configuration.setSharedVariable("user", userService.findUser());
            session.removeAttribute(BlogConstant.USER_SESSION_KEY);
        } catch (Exception e) {
            log.error("Failed to modify user profile: {}", e.getMessage());
            return new JsonResult(ResultCodeEnum.FAIL.getCode(), localeMessageUtil.getMessage("code.admin.common.edit-failed"));
        }
        return new JsonResult(ResultCodeEnum.SUCCESS.getCode(), localeMessageUtil.getMessage("code.admin.common.edit-success"));
    }
    
    /**
     * 处理修改密码的请求
     *
     * @param beforePass 旧密码
     * @param newPass 新密码
     * @param userId 用户编号
     * @param session session
     * @return JsonResult
     */
    @PostMapping(value = "changePass")
    @ResponseBody
    public JsonResult changePass(@ModelAttribute("beforePass") String beforePass, @ModelAttribute("newPass") String newPass, @ModelAttribute("userId") Long userId, HttpSession session) {
        try {
            User user = userService.findByUserIdAndUserPass(userId, SecureUtil.md5(beforePass));
            if (null != user) {
                user.setUserPass(SecureUtil.md5(newPass));
                userService.save(user);
                session.removeAttribute(BlogConstant.USER_SESSION_KEY);
            } else {
                return new JsonResult(ResultCodeEnum.FAIL.getCode(), localeMessageUtil.getMessage("code.admin.user.old-password-error"));
            }
        } catch (Exception e) {
            log.error("Password change failed: {}", e.getMessage());
            return new JsonResult(ResultCodeEnum.FAIL.getCode(), localeMessageUtil.getMessage("code.admin.user.update-password-failed"));
        }
        return new JsonResult(ResultCodeEnum.SUCCESS.getCode(), localeMessageUtil.getMessage("code.admin.user.update-password-success"));
    }
}
