package cn.stylefeng.guns.business.modular.mapper;

import cn.stylefeng.guns.business.modular.entity.TComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Auther: zhangty
 * @Date: 2019/8/19 14:46
 * @Description:留言、评论功能
 * @Version:1.0.0
 */
public interface TCommentMapper extends BaseMapper<TComment> {


    /**
     * 根据条件查询新闻列表
     */
    Page<Map<String, Object>> selectTComment(@Param("page") Page page,
                                          @Param("userName") String userName, @Param("beginTime") String beginTime,
                                          @Param("endTime") String endTime);

    /**
     * 根据ID查询留言、评论下所有明细信息
     * @param
     * @return
     */
    Page<Map<String, Object>> selectCommentInfo(@Param("page") Page page,@Param("parentCommentUserId") Long parentCommentUserId,
                                                @Param("commentType")Integer commentType);



    void editComment(TComment tComment);

}
