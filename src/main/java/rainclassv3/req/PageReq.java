package rainclassv3.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

/**
 * @ClassName PageReq
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午1:44
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageReq {

    private int pageNum=1;

    /**
     * 之所以要对每页条数做限制
     * 是防止其他人直接使用脚本查询后端数据
     * 比如说后端有100w条数据
     * 如果不做校验，让别人用脚本直接一下子查出来
     * 那服务器就挂掉了
     */
    @Max(value = 1000,message = "【每页条数】不能超过1000")
    private int pageSize=3;
}
