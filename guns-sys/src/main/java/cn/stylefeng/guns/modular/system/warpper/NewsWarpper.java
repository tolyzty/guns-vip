package cn.stylefeng.guns.modular.system.warpper;

import cn.stylefeng.guns.core.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangty
 * @Date: 2019/3/26 17:58
 * @Description:字典转义
 * @Version:1.0.0
 */
public class NewsWarpper extends BaseControllerWrapper {

    public NewsWarpper(Map<String, Object> single) {
        super(single);
    }

    public NewsWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public NewsWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public NewsWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("newStatus", ConstantFactory.me().getNewStatus(map.get("newStatus")+""));
        map.put("newType",ConstantFactory.me().getNewType(map.get("newType")+""));
    }

}
