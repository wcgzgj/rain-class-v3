package rainclassv3.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName PicUploadResp
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/20 下午11:32
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PicUploadResp {
    private String path;
    /**
     * 图片展示的页面
     */
    private String showPath;
}
