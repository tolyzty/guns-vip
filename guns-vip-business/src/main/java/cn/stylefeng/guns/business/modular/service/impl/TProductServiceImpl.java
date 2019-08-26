package cn.stylefeng.guns.business.modular.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.business.modular.entity.TProduct;
import cn.stylefeng.guns.business.modular.mapper.TProductMapper;
import cn.stylefeng.guns.business.modular.model.params.TProductParam;
import cn.stylefeng.guns.business.modular.model.result.TProductResult;
import  cn.stylefeng.guns.business.modular.service.TProductService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangty
 * @since 2019-08-26
 */
@Service
public class TProductServiceImpl extends ServiceImpl<TProductMapper, TProduct> implements TProductService {

    @Override
    public void add(TProductParam param){
        TProduct entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(TProductParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(TProductParam param){
        TProduct oldEntity = getOldEntity(param);
        TProduct newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public TProductResult findBySpec(TProductParam param){
        return null;
    }

    @Override
    public List<TProductResult> findListBySpec(TProductParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(TProductParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(TProductParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private TProduct getOldEntity(TProductParam param) {
        return this.getById(getKey(param));
    }

    private TProduct getEntity(TProductParam param) {
        TProduct entity = new TProduct();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
