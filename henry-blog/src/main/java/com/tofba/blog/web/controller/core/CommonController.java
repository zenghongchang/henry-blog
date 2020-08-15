package com.tofba.blog.web.controller.core;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tofba.blog.model.enums.CommonParamsEnum;

/**
 * <pre>
 * 错误页面控制器
 * </pre>
 *
 * @author : RYAN0UP
 * @date : 2017/12/26
 */
@Controller
public class CommonController implements ErrorController {
    
    private static final String ERROR_PATH = "/error";
    
    /**
     * 渲染404，500
     *
     * @param request request
     * @return String
     */
    @GetMapping(value = ERROR_PATH)
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if (statusCode.equals(CommonParamsEnum.NOT_FOUND.getValue())) {
            return "redirect:/404";
        } else {
            return "redirect:/500";
        }
    }
    
    /**
     * 渲染404页面
     *
     * @param model model
     * @return String
     */
    @GetMapping(value = "/404")
    public String fourZeroFour() {
        return "common/error/404";
    }
    
    /**
     * 渲染500页面
     *
     * @param model model
     * @return String
     */
    @GetMapping(value = "/500")
    public String fiveZeroZero() {
        return "common/error/500";
    }
    
    /**
     * Returns the path of the error page.
     *
     * @return the error path
     */
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
