package rainclassv3.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName StudentIsChosenReq
 * @Description 查询学生是否已经选课的 req
 * @Author FARO_Z
 * @Date 2021/6/20 下午2:42
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentIsChosenReq {
    private String studentid;
    private String classid;
}
