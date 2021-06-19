package rainclassv3.pojo;

import java.util.Date;

public class Score {
    private Long id;

    private Long studentid;

    private Long classid;

    private Float scorenum;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentid() {
        return studentid;
    }

    public void setStudentid(Long studentid) {
        this.studentid = studentid;
    }

    public Long getClassid() {
        return classid;
    }

    public void setClassid(Long classid) {
        this.classid = classid;
    }

    public Float getScorenum() {
        return scorenum;
    }

    public void setScorenum(Float scorenum) {
        this.scorenum = scorenum;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", studentid=").append(studentid);
        sb.append(", classid=").append(classid);
        sb.append(", scorenum=").append(scorenum);
        sb.append(", createtime=").append(createtime);
        sb.append("]");
        return sb.toString();
    }
}