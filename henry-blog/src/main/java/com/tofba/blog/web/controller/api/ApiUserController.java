package com.tofba.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tofba.blog.model.domain.User;
import com.tofba.blog.model.dto.JsonResult;
import com.tofba.blog.model.enums.ResponseStatusEnum;
import com.tofba.blog.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/user")
public class ApiUserController {

    @Autowired
    private UserService userService;

    /**
     * 获取博主信息
     *
     * <p>
     *     result json:
     *     <pre>
     * {
     *     "code": 200,
     *     "msg": "OK",
     *     "result": {
     *         "userId": ,
     *         "userDisplayName": "",
     *         "userEmail": "",
     *         "userAvatar": "",
     *         "userDesc": ""
     *     }
     * }
     *     </pre>
     * </p>
     *
     * @return JsonResult
     */
    @GetMapping
    public JsonResult user() {
        User user = userService.findUser();
        return new JsonResult(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMsg(), user);
    }
}
