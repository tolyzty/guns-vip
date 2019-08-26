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
public class CommentWarpper extends BaseControllerWrapper {

    public CommentWarpper(Map<String, Object> single) {
        super(single);
    }

    public CommentWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public CommentWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public CommentWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("topStatus", ConstantFactory.me().getTopStatus(map.get("topStatus")+""));
        map.put("commentType",ConstantFactory.me().getCommentType(map.get("commentType")+""));
        map.put("commentStatus",ConstantFactory.me().getCommentStatus(map.get("commentStatus")+""));

    }

}
