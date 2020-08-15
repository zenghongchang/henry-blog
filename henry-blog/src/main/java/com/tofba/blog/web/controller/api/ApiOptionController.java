package com.tofba.blog.web.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tofba.blog.model.dto.JsonResult;
import com.tofba.blog.model.enums.BlogPropertiesEnum;
import com.tofba.blog.model.enums.ResponseStatusEnum;
import com.tofba.blog.service.OptionsService;

/**
 * <pre>
 *     系统设置API
 * </pre>
 *
 * @author : RYAN0UP
 * @date : 2018/7/19
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/options")
public class ApiOptionController {

    @Autowired
    private OptionsService optionsService;

    /**
     * 获取所有设置选项
     *
     * <p>
     *     result json:
     *     <pre>
     * {
     *     "code": 200,
     *     "msg": "OK",
     *     "result": {
     *         "blog_start": "",
     *         "blog_locale": "",
     *         "blog_url": "",
     *         "api_token": "",
     *         "api_status": "",
     *         "blog_title": "",
     *         "new_comment_notice": "",
     *         "smtp_email_enable": "",
     *         "attach_loc": "",
     *         "theme": "",
     *         "comment_reply_notice": "",
     *         "is_install": "",
     *         "comment_pass_notice": ""
     *     }
     * }
     *     </pre>
     * </p>
     *
     * @return JsonResult
     */
    @GetMapping
    public JsonResult options() {
        Map<String, String> options = optionsService.findAllOptions();
        //去掉隐私元素
        options.remove(BlogPropertiesEnum.MAIL_SMTP_HOST.getProp());
        options.remove(BlogPropertiesEnum.MAIL_FROM_NAME.getProp());
        options.remove(BlogPropertiesEnum.MAIL_SMTP_PASSWORD.getProp());
        options.remove(BlogPropertiesEnum.MAIL_SMTP_USERNAME.getProp());
        options.remove(BlogPropertiesEnum.MAIL_SMTP_USERNAME.getProp());
        return new JsonResult(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMsg(), options);
    }

    /**
     * 获取单个设置项
     *
     * <p>
     *     result json:
     *     <pre>
     * {
     *     "code": 200,
     *     "msg": "OK",
     *     "result": ""
     * }
     *     </pre>
     * </p>
     *
     * @param optionName 设置选项名称
     * @return JsonResult
     */
    @GetMapping(value = "/{optionName}")
    public JsonResult option(@PathVariable(value = "optionName") String optionName) {
        String optionValue = optionsService.findOneOption(optionName);
        return new JsonResult(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMsg(), optionValue);
    }
}
