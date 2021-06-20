package rainclassv3.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rainclassv3.pojo.Class;

/**
 * @ClassName StudentMyScoreResp
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/20 下午8:35
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentMyScoreResp {
    private Class classInfo;
    private Float score;
}
