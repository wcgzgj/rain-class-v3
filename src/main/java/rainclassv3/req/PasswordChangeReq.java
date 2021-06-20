package rainclassv3.req;

/**
 * @ClassName PasswordChangeReq
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午8:54
 * @Version 1.0
 **/
public class PasswordChangeReq {
    /**
     * 用户id
     */
    private String id;
    /**
     * 角色信息
     */
    private String role;
    /**
     * 原密码
     */
    private String origin;
    /**
     * 新密码
     */
    private String newPassword;
}
