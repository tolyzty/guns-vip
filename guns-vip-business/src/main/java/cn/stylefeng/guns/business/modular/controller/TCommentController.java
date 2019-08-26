package cn.stylefeng.guns.business.modular.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.business.modular.entity.TComment;
import cn.stylefeng.guns.business.modular.entity.TNews;
import cn.stylefeng.guns.business.modular.service.TCommentService;
import cn.stylefeng.guns.business.modular.warpper.CommentWarpper;
import cn.stylefeng.guns.business.core.common.constant.state.CommentEnum;
import cn.stylefeng.guns.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Auther: zhangty
 * @Date: 2019/8/19 15:05
 * @Description:留言页面
 * @Version:1.0.0
 */
@Slf4j
@Controller
@RequestMapping("/comment")
public class TCommentController extends BaseController {

    private String PREFIX = "/modular/business/comment/";

    @Autowired
    private TCommentService tCommentService;



    /**
     * 跳转到留言页面
     *
     * @author tolyzty
     * @Date 2019/07/30  17:56
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "list.html";
    }



    /**
     * 查询留言信息
     *
     * @author tolyzty
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String userName,
                       @RequestParam(required = false) String beginTime,
                       @RequestParam(required = false) String endTime) {
        log.info("查询留言列表");
        Page<Map<String, Object>> comment = tCommentService.list(userName,beginTime,endTime);
        Page wrapped = new CommentWarpper(comment).wrap();
        return LayuiPageFactory.createPageInfo(wrapped);
    }


    /**
     * 查询回复详情集合
     * @param userId
     * @param commentType
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/commentInfo")
    public Object listCommentInfo(@RequestParam Long userId,@RequestParam Integer commentType){
        if (ToolUtil.isEmpty(userId) || ToolUtil.isEmpty(commentType)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        TComment comment = new TComment();
        comment.setParentCommentUserId(userId);
        comment.setCommentType(commentType);
        Page<Map<String, Object>> commentList = tCommentService.commentInfo(comment.getParentCommentUserId(),commentType);
        Page wrapped = new CommentWarpper(commentList).wrap();
        return LayuiPageFactory.createPageInfo(wrapped);
    }


    public static void main(String[] arge){
        BigDecimal bigs = new BigDecimal("0.01");
        BigDecimal bigst = new BigDecimal("1.5");
        System.out.println(bigs.multiply(bigst));
    }



    /**
     * 逻辑删除
     * @param  id
     * @return
     */
    @RequestMapping(value = "/upDetelEdit")
    @ResponseBody
    public ResponseData upDetelEdit(@RequestParam Long id){
        log.info("逻辑删除回复信息-->:{}",id);
        TComment tComment = new TComment();
        tComment.setId(id);
        tComment.setCommentStatus(Integer.valueOf(CommentEnum.commentStatus.ALL_01.getCode()));
        this.tCommentService.editTComment(tComment);
        log.info("---逻辑删除成功---");
        return SUCCESS_TIP;
    }




    @RequestMapping(value = "/topUp")
    @ResponseBody
    public ResponseData upDisplay(@RequestParam Long id,@RequestParam String topStatus ){
        log.info("--修改评论是否置顶--> topStatus:{}",topStatus);
        TComment tComment  =new TComment();
        tComment.setTopStatus(Integer.valueOf(topStatus));
        tComment.setId(id);
        this.tCommentService.editTComment(tComment);
        return SUCCESS_TIP;
    }






}
