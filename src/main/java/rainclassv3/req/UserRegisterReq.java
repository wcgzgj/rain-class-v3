package rainclassv3.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserRegisterReq
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/21 上午11:57
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterReq {
    private String loginname;
    private String realname;
    private String password;
}
