package rainclassv3.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName PasswordCheckReq
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午9:12
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordCheckReq {
    private String origin;
    private String token;
}
