package rainclassv3.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import rainclassv3.pojo.Download;
import rainclassv3.pojo.DownloadExample;

public interface DownloadMapper {
    long countByExample(DownloadExample example);

    int deleteByExample(DownloadExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Download record);

    int insertSelective(Download record);

    List<Download> selectByExample(DownloadExample example);

    Download selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Download record, @Param("example") DownloadExample example);

    int updateByExample(@Param("record") Download record, @Param("example") DownloadExample example);

    int updateByPrimaryKeySelective(Download record);

    int updateByPrimaryKey(Download record);
}