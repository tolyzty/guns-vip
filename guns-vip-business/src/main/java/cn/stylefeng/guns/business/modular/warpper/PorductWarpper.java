package cn.stylefeng.guns.business.modular.warpper;

import cn.stylefeng.guns.core.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangty
 * @Date: 2019/3/26 17:58
 * @Description:字典转义转义
 * @Version:1.0.0
 */
public class PorductWarpper extends BaseControllerWrapper {

    public PorductWarpper(Map<String, Object> single) {
        super(single);
    }

    public PorductWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public PorductWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public PorductWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        /**
         * 是否上架
         */
       /* map.put("productIsPublish", ConstantFactory.me().getNewStatus(map.get("productIsPublish")+""));
        map.put("productIsShow", ConstantFactory.me().getNewStatus(map.get("productIsShow")+""));*/
        map.put("productType", ConstantFactory.me().getNewStatus(map.get("productType")+""));
        map.put("productClassify", ConstantFactory.me().getNewStatus(map.get("productClassify")+""));
    }

}
