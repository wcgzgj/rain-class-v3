package rainclassv3.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName TeacherChangeScoreReq
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/20 下午7:59
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherChangeScoreReq {
    private String studentid;
    private String realname;
    private String score;
    private String classid;
}
