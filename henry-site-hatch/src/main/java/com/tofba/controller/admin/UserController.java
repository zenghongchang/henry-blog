package com.tofba.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tofba.common.system.ServiceResponse;
import com.tofba.common.system.ServiceResponseCode;
import com.tofba.common.system.ServiceResponseDescription;
import com.tofba.common.system.UserSingleton;
import com.tofba.common.util.IpUtil;
import com.tofba.common.util.MD5Util;
import com.tofba.model.UserDomain;
import com.tofba.service.user.UserService;

import io.swagger.annotations.Api;

@Api("用户管理类")
@Controller
@RequestMapping(value = "/admin")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);    
    @Autowired
    private UserSingleton userSingleton;    
    @Autowired
    private UserService userService;
    
    /**
     * 根据ID获取用户信息
     * 
     * @param id
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2018年5月17日]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/getUserInfoById/{id}", method = RequestMethod.GET)
    public UserDomain getUserInfoById(@PathVariable Integer id) {
        UserDomain userDomain = userService.getUserInfoById(id);
        if (null != userDomain) {
            logger.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^:{}", userDomain);
        }
        return userDomain;
    }
    
    /**
     * 登陆界面
     * 
     * @param modelAndView
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2018年5月18日]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/loginForm")
    public String index(Model model) {
        return "/admin/login";
    }
    
    /**
     * 用户登陆
     * 
     * @param model
     * @param username
     * @param password
     * @param request
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2018年3月27日]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, @RequestParam(value = "username", required = true) String username, @RequestParam(value = "password", required = true) String password, HttpServletRequest request) {
        if (StringUtils.isBlank(username)) {
            model.addAttribute("error", "请输入用户名");
            return "/admin/login";
        }
        if (StringUtils.isBlank(password)) {
            model.addAttribute("error", "请输入密码");
            return "/admin/login";
        }
        System.out.println(password);
        System.out.println(new MD5Util(password).toMD5());
        UserDomain userDomain = userService.validationUser(username, password);
        if (null == userDomain) {
            model.addAttribute("error", "用户名或者密码错误");
            return "/admin/login";
        }
        userDomain.setRemoteAddr(IpUtil.getIpAddress(request));
        userSingleton.setUser(userDomain);
        return "redirect:/admin/adminIndex";
    }
    
    /**
     * 管理员后台首页
     * 
     * @param model
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2018年5月18日]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "adminIndex")
    public String adminIndex(Model model) {
        model.addAttribute("userDomain", userSingleton.getUser());
        return "/admin/index";
    }
    
    /**
     * 修改显示名
     * 
     * @param screenName
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2018年5月17日]
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/modifyScreenName")
    @ResponseBody
    public ServiceResponse modifyScreenName(@RequestParam(value = "screenName", required = true) String screenName) {
        UserDomain userDomain = userSingleton.getUser();
        ServiceResponse response = new ServiceResponse();
        if (StringUtils.isBlank(screenName)) {
            response.setCode(ServiceResponseCode.ERROR);
            response.setDescription("请确保信息输入完整");
            return response;
        }
        if (screenName.length() > 20) {
            response.setCode(ServiceResponseCode.ERROR);
            response.setDescription("显示名长度超过20个字符,请重新输入");
            return response;
        }
        UserDomain update_user_domain = new UserDomain();
        update_user_domain.setScreenName(screenName);
        update_user_domain.setUid(userDomain.getUid());
        try {
            int result = userService.updateUserInfo(update_user_domain);
            if (result <= 0) {
                response.setCode(ServiceResponseCode.ERROR);
                response.setDescription("修改用户密码失败");
                return response;
            } else {
                // TODO 记录修改日志
                // 更新session中的数据
                userDomain.setPassword(update_user_domain.getPassword());
                userSingleton.setUser(userDomain);
            }
            response.setCode(ServiceResponseCode.SUCCESS);
            response.setDescription(ServiceResponseDescription.SUCCESS);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(ServiceResponseCode.SERVER_ERROR);
            response.setDescription("系统内部发生异常!");
            return response;
        }
    }
    
    /**
     * 修改用户密码
     * 
     * @param oldPassword 旧密码
     * @param password 新密码
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2018年5月17日]
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/modifyPassword")
    @ResponseBody
    public ServiceResponse modifyPassword(@RequestParam(value = "oldPassword", required = true) String oldPassword, @RequestParam(value = "password", required = true) String password) {
        UserDomain userDomain = userSingleton.getUser();
        ServiceResponse response = new ServiceResponse();
        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(password)) {
            response.setCode(ServiceResponseCode.ERROR);
            response.setDescription("请确保信息输入完整");
            return response;
        }
        if (!userDomain.getPassword().equals(new MD5Util(oldPassword).toMD5())) {
            response.setCode(ServiceResponseCode.ERROR);
            response.setDescription("旧密码错误");
            return response;
        }
        if (password.length() < 6 || password.length() > 14) {
            response.setCode(ServiceResponseCode.ERROR);
            response.setDescription("请输入6-14位密码");
            return response;
        }
        try {
            UserDomain update_user_domain = new UserDomain();
            update_user_domain.setUid(userDomain.getUid());
            update_user_domain.setPassword(new MD5Util(password).toMD5());
            int result = userService.updateUserInfo(update_user_domain);
            if (result <= 0) {
                response.setCode(ServiceResponseCode.ERROR);
                response.setDescription("修改用户密码失败");
                return response;
            } else {
                // TODO 记录修改日志
                // 更新session中的数据
                userDomain.setPassword(update_user_domain.getPassword());
                userSingleton.setUser(userDomain);
            }
            response.setCode(ServiceResponseCode.SUCCESS);
            response.setDescription(ServiceResponseDescription.SUCCESS);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(ServiceResponseCode.SERVER_ERROR);
            response.setDescription("系统内部发生异常!");
            return response;
        }
    }
    
    /**
     * 请求超时
     * 
     * @param model
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2018年5月17日]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/timeout")
    public String timeout(Model model) {
        userSingleton.unsetUser();
        model.addAttribute("message", "会话超时, <a style=\"text-decoration: underline;\" href=\"/\">请重新登录</a>");
        return "page/error";
    }
    
    /**
     * 登出
     * 
     * @param model
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2018年5月17日]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/logout")
    public String logout(Model model) {
        userSingleton.unsetUser();
        return "redirect:/admin/login";
    }
}