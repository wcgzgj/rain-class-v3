package rainclassv3.service;

import rainclassv3.req.UserLoginReq;
import rainclassv3.resp.UserLoginResp;

public interface UserService {


    /**
     * 用户登录
     * @param req
     * @return
     */
    UserLoginResp login(UserLoginReq req);

}
