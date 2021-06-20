package rainclassv3.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @ClassName StudentMyScoreReq
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/20 下午8:34
 * @Version 1.0
 **/

public class StudentMyScoreReq extends PageReq{
    private String classname;

    private String type;

    private Integer credit;

    private String studentid;

    private String classid;

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

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    @Override
    public String toString() {
        return "StudentMyScoreReq{" +
                "classname='" + classname + '\'' +
                ", type='" + type + '\'' +
                ", credit=" + credit +
                ", studentid='" + studentid + '\'' +
                ", classid='" + classid + '\'' +
                "} " + super.toString();
    }
}
