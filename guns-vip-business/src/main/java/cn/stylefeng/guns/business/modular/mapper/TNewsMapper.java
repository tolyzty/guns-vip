package cn.stylefeng.guns.business.modular.mapper;

import cn.stylefeng.guns.business.modular.entity.TNews;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
public interface TNewsMapper extends BaseMapper<TNews> {

    /**
     * 修改新闻状态
     *//*
    int setStatus(@Param("newId") Long newId, @Param("newStatus") String newStatus);*/

    /**
     * 根据条件查询新闻列表
     */
    Page<Map<String, Object>> selectTnews(@Param("page") Page page,
                                          @Param("newTital") String newTital, @Param("beginTime") String beginTime,
                                          @Param("endTime") String endTime);


    void editNews(TNews news);

}
