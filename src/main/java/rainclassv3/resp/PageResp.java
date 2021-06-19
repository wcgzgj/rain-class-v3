package rainclassv3.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName PageResp
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午2:28
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResp<T> {

    private Integer total;
    private Integer pageNum;
    private Integer pageSize;

    private List<T> list;
}
