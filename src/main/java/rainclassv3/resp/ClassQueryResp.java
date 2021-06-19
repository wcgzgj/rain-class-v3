package rainclassv3.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rainclassv3.pojo.Teacher;

import java.util.Date;

/**
 * @ClassName ClassQueryResp
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午2:22
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClassQueryResp {
    private Long id;

    private String classname;

    private Long teacherid;

    private String starttime;

    private String code;

    private String place;

    private String notice;

    private String type;

    private Integer credit;

    private String desc;


    /**
     * 教授课程的教师信息
     */
    private Teacher teacher;
}
