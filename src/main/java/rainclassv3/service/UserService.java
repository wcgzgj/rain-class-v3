package rainclassv3.service;

import rainclassv3.req.PasswordChangeReq;
import rainclassv3.req.UserLoginReq;
import rainclassv3.req.UserRegisterReq;
import rainclassv3.resp.UserLoginResp;

public interface UserService {


    /**
     * 用户登录
     * @param req
     * @return
     */
    UserLoginResp login(UserLoginReq req);


    /**
     * 更改密码
     */
    void changePassword(PasswordChangeReq req);

    /**
     * 学生注册
     * @param req
     */
    void saveStudent(UserRegisterReq req);

}
