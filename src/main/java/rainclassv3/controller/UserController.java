package rainclassv3.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import rainclassv3.req.PasswordChangeReq;
import rainclassv3.req.PasswordCheckReq;
import rainclassv3.req.UserLoginReq;
import rainclassv3.resp.AdminLoginResp;
import rainclassv3.resp.CommonResp;
import rainclassv3.resp.UserLoginResp;
import rainclassv3.service.UserService;
import rainclassv3.util.SnowFlake;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;
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

    /**
     * 登出接口
     * @param token
     * @return
     */
    @RequestMapping("/logout/{token}")
    public CommonResp logout(@PathVariable("token")String token) {
        redisTemplate.delete(token);
        CommonResp commonResp = new CommonResp();
        return commonResp;
    }


    /**
     * 修改密码后端接口
     * @param req
     * @return
     */
    @PostMapping("/changePassword")
    public CommonResp changePassword(@RequestBody @Valid PasswordChangeReq req) {


        CommonResp commonResp = new CommonResp();
        return commonResp;
    }

    /**
     * 检查密码后端接口
     * @param req
     * @return
     */
    @RequestMapping("/checkPassword")
    public CommonResp checkPassword(PasswordCheckReq req) {
        LOG.info("传入的token 信息为:{}",req.getToken());

        Object o = redisTemplate.opsForValue().get(req.getToken());
        LOG.info("获取的缓存信息为:{}",o);
        JSONObject jsonObject = (JSONObject) o;
        String json = jsonObject.toJSONString();
        LOG.info("json数据为:{}",json);
        AdminLoginResp adminLoginResp = JSON.parseObject(json, AdminLoginResp.class);
        String token = adminLoginResp.getToken();
        String password = adminLoginResp.getPassword();

        LOG.info("缓存中的token为:{}",token);

        CommonResp commonResp = new CommonResp();
        if (!Objects.equals(token,req.getToken())) {
            commonResp.setSuccess(false);
            commonResp.setMessage("错误，当前用户未登陆");
            return commonResp;
        }
        if (!Objects.equals(password,req.getOrigin())) {
            commonResp.setSuccess(false);
            commonResp.setMessage("输入密码与原密码不一致");
            return commonResp;
        }

        return commonResp;
    }


}
