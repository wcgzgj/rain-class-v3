package rainclassv3.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName ClassSaveReq
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午2:34
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClassSaveReq {
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

    private String path;
}
