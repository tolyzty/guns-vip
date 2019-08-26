package cn.stylefeng.guns.business.modular.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.business.modular.entity.TProduct;
import cn.stylefeng.guns.business.modular.model.params.TProductParam;
import cn.stylefeng.guns.business.modular.model.result.TProductResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangty
 * @since 2019-08-26
 */
public interface TProductService extends IService<TProduct> {

    /**
     * 新增
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    void add(TProductParam param);

    /**
     * 删除
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    void delete(TProductParam param);

    /**
     * 更新
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    void update(TProductParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    TProductResult findBySpec(TProductParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    List<TProductResult> findListBySpec(TProductParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author zhangty
     * @Date 2019-08-26
     */
     LayuiPageInfo findPageBySpec(TProductParam param);

}
