package rainclassv3.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rainclassv3.req.UserLoginReq;
import rainclassv3.resp.CommonResp;
import rainclassv3.resp.UserLoginResp;
import rainclassv3.service.UserService;
import rainclassv3.util.SnowFlake;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

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
    private SnowFlake snowFlake;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private UserService userService;

    /**
     * 登录接口
     * @param req
     * @return
     */
    @PostMapping("/login")
    public CommonResp login(@RequestBody @Valid UserLoginReq req) {
        LOG.info("传入的密码信息为:{}",req.getPassword());
        UserLoginResp loginUser = userService.login(req);

        /**
         * 为返回值加上 token
         * 将登录信息放入 redis 并设置超时时间为 一天
         */
        if (loginUser!=null) {
            String token = String.valueOf(snowFlake.nextId());
            LOG.info("生成的用户 token 为:{}",token);
            loginUser.setToken(token);
            redisTemplate.opsForValue().set(token, JSON.toJSON(loginUser),3600*24, TimeUnit.SECONDS);
        }

        CommonResp commonResp = new CommonResp();
        commonResp.setContent(loginUser);
        return commonResp;
    }


}
