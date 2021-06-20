package rainclassv3.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName TeacherMyStudentResp
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/20 下午6:08
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherMyStudentResp {
    /**
     * const columns = [
     *         {
     *             title: '姓名',
     *             dataIndex: 'name',
     *             key: 'name',
     *         },
     *         {
     *             title: '邮箱',
     *             dataIndex: 'email',
     *             key: 'email',
     *         },
     *         {
     *             title: '成绩',
     *             dataIndex: 'score',
     *             key: 'score',
     *         },
     *         {
     *             title: '操作',
     *             key: 'action',
     *             slots: {
     *                 customRender: 'action',
     *             },
     *         },
     */
    private Long id;
    private String name;
    private String email;
    private Float score;
}
