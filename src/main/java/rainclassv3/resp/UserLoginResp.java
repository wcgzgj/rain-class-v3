package rainclassv3.resp;

/**
 * @ClassName UserLoginResp
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午4:17
 * @Version 1.0
 **/
public abstract class UserLoginResp {

    protected String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
