package cn.stylefeng.guns.business.modular.mapper;

import cn.stylefeng.guns.business.modular.entity.TProduct;
import cn.stylefeng.guns.business.modular.model.params.TProductParam;
import cn.stylefeng.guns.business.modular.model.result.TProductResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangty
 * @since 2019-08-26
 */
public interface TProductMapper extends BaseMapper<TProduct> {

    /**
     * 获取列表
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    List<TProductResult> customList(@Param("paramCondition") TProductParam paramCondition);

    /**
     * 获取map列表
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") TProductParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    Page<TProductResult> customPageList(@Param("page") Page page, @Param("paramCondition") TProductParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") TProductParam paramCondition);

}
