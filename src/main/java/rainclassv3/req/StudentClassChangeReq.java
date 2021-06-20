package rainclassv3.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName StudentClassChangeReq
 * @Description 学生选课/退选 请求包
 * @Author FARO_Z
 * @Date 2021/6/20 下午3:54
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentClassChangeReq {
    private String studentid;
    private String classid;
}
