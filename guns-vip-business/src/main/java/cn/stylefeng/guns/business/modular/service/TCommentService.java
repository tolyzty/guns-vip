package cn.stylefeng.guns.business.modular.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.business.modular.entity.TComment;
import cn.stylefeng.guns.business.modular.mapper.TCommentMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Auther: zhangty
 * @Date: 2019/8/19 14:35
 * @Description:
 * @Version:1.0.0
 */
@Service
@Slf4j
public class TCommentService extends ServiceImpl<TCommentMapper, TComment> {


    @Resource
    private TCommentMapper tCommentMapper;
    /**
     * 获取列表
     *
     * @author tolyzty
     * @Date 2018/12/23 6:05 PM
     */
    public Page<Map<String, Object>> list(String userName, String  beginTime, String  endTime) {
        Page page = LayuiPageFactory.defaultPage();
        return this.tCommentMapper.selectTComment(page,userName,beginTime,endTime);
    }


    /**
     * 查询所有留言下回复信息
     * @param
     * @return
     */
    public Page<Map<String, Object>>  commentInfo(Long parentCommentUserId,Integer commentType){
        log.info("查询留言下所有回复明细请求userId参数:{},commentType请求参数:{}",parentCommentUserId,commentType);
        Page page = LayuiPageFactory.defaultPage();
        return this.tCommentMapper.selectCommentInfo(page,parentCommentUserId,commentType);
    }


    /**
     * 修改留言
     *
     * @author tolyzty
     * @Date 2018/12/24 22:53
     */
    @Transactional(rollbackFor = Exception.class)
    public void editTComment(TComment tComment) {
        log.info("****修改留言Service****:{}",tComment);
        tCommentMapper.editComment(tComment);
    }

}
