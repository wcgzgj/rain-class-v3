package rainclassv3.resp;

/**
 * @ClassName UserLoginResp
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午4:17
 * @Version 1.0
 **/
public abstract class UserLoginResp {

    protected String role;
    protected String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
