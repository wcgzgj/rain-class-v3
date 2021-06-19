package rainclassv3.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName UserLoginReq
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午4:22
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginReq {
    @NotEmpty(message = "登录名不能为空")
    private String loginname;

    @NotEmpty(message = "密码不能为空")
    private String password;

    @NotEmpty(message = "角色信息不能为空")
    private String role;
}
