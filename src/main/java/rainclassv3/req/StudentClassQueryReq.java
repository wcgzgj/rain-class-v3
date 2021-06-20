package rainclassv3.req;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName StudentClassQueryReq
 * @Description 学成课程列表查询
 * @Author FARO_Z
 * @Date 2021/6/20 下午2:01
 * @Version 1.0
 **/
public class StudentClassQueryReq extends PageReq {

    private String classname;

    private String type;

    private Integer credit;

    /**
     * 学生 id
     */
    @NotEmpty(message = "错误，学生id不能为空")
    private String studentid;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    @Override
    public String toString() {
        return "StudentClassQueryReq{" +
                "classname='" + classname + '\'' +
                ", type='" + type + '\'' +
                ", credit=" + credit +
                ", studentid=" + studentid +
                "} " + super.toString();
    }
}
