package rainclassv3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rainclassv3.req.UserLoginReq;
import rainclassv3.resp.CommonResp;
import rainclassv3.resp.UserLoginResp;
import rainclassv3.service.UserService;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午4:15
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG= LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public CommonResp login(@RequestBody @Valid UserLoginReq req) {
        LOG.info("传入的密码信息为:{}",req.getPassword());
        UserLoginResp loginUser = userService.login(req);

        CommonResp commonResp = new CommonResp();
        commonResp.setContent(loginUser);
        return commonResp;
    }

}
