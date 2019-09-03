package cn.stylefeng.guns.business.modular.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: zhangty
 * @Date: 2019/8/19 14:32
 * @Description:
 * @Version:1.0.0
 */

@Data
public class TComment implements Serializable {
    //评论、留言主键
    private Long id;
    //评论、留言人用户ID
    private String userId;
    //评论、留言人用户名称
    private String userName;
    //评论、留言的文章id---newId
    private Long articleId;
    //评论、留言的文章标题---newsTitle
    private String articleTitle;
    //父评论、留言ID
    private Long parentCommentId;
    //父评论、留言用户ID
    private Long parentCommentUserId;
    //被回复的评论ID
    private Long replyCommentId;
    //被恢复的评论用户ID
    private Long replyCommentUserId;
    //评论等级 1：一级评论，2：2及评论
    private Integer commentLevel;
    //评论类型 1：文章评论 ，2：留言
    private Integer commentType;
    //评论内容
    private String commentContent;
    //评论状态 1有效，2逻辑删除
    private Integer commentStatus;
    //点赞数
    private Integer praiseNum;
    //置顶状态 1 置顶 0默认
    private Integer topStatus;
    //创建时间
    private Date createTime;

    //被回复评论的用户名
    private String replyCommentUserName;



    //父级用户名
    private String  parentCommentUserName;


    /**
     * 设置：评论、留言主键
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 获取：评论、留言主键
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：评论、留言人用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * 获取：评论、留言人用户ID
     */
    public String getUserId() {
        return userId;
    }
    /**
     * 设置：评论、留言人用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * 获取：评论、留言人用户名称
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置：评论、留言的文章id---newId
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
    /**
     * 获取：评论、留言的文章id---newId
     */
    public Long getArticleId() {
        return articleId;
    }
    /**
     * 设置：评论、留言的文章标题---newsTitle
     */
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }
    /**
     * 获取：评论、留言的文章标题---newsTitle
     */
    public String getArticleTitle() {
        return articleTitle;
    }
    /**
     * 设置：父评论、留言ID
     */
    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }
    /**
     * 获取：父评论、留言ID
     */
    public Long getParentCommentId() {
        return parentCommentId;
    }
    /**
     * 设置：父评论、留言用户ID
     */
    public void setParentCommentUserId(Long parentCommentUserId) {
        this.parentCommentUserId = parentCommentUserId;
    }
    /**
     * 获取：父评论、留言用户ID
     */
    public Long getParentCommentUserId() {
        return parentCommentUserId;
    }
    /**
     * 设置：被回复的评论ID
     */
    public void setReplyCommentId(Long replyCommentId) {
        this.replyCommentId = replyCommentId;
    }
    /**
     * 获取：被回复的评论ID
     */
    public Long getReplyCommentId() {
        return replyCommentId;
    }
    /**
     * 设置：被恢复的评论用户ID
     */
    public void setReplyCommentUserId(Long replyCommentUserId) {
        this.replyCommentUserId = replyCommentUserId;
    }
    /**
     * 获取：被恢复的评论用户ID
     */
    public Long getReplyCommentUserId() {
        return replyCommentUserId;
    }
    /**
     * 设置：评论等级 1：一级评论，2：2及评论
     */
    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }
    /**
     * 获取：评论等级 1：一级评论，2：2及评论
     */
    public Integer getCommentLevel() {
        return commentLevel;
    }
    /**
     * 设置：评论类型 1：文章评论 ，2：留言
     */
    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }
    /**
     * 获取：评论类型 1：文章评论 ，2：留言
     */
    public Integer getCommentType() {
        return commentType;
    }
    /**
     * 设置：评论内容
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
    /**
     * 获取：评论内容
     */
    public String getCommentContent() {
        return commentContent;
    }
    /**
     * 设置：评论状态 1有效，2逻辑删除
     */
    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }
    /**
     * 获取：评论状态 1有效，2逻辑删除
     */
    public Integer getCommentStatus() {
        return commentStatus;
    }
    /**
     * 设置：点赞数
     */
    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }
    /**
     * 获取：点赞数
     */
    public Integer getPraiseNum() {
        return praiseNum;
    }
    /**
     * 设置：置顶状态 1 置顶 0默认
     */
    public void setTopStatus(Integer topStatus) {
        this.topStatus = topStatus;
    }
    /**
     * 获取：置顶状态 1 置顶 0默认
     */
    public Integer getTopStatus() {
        return topStatus;
    }
    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }


    public String getReplyCommentUserName() {
        return replyCommentUserName;
    }

    public void setReplyCommentUserName(String replyCommentUserName) {
        this.replyCommentUserName = replyCommentUserName;
    }

    public String getParentCommentUserName() {
        return parentCommentUserName;
    }

    public void setParentCommentUserName(String parentCommentUserName) {
        this.parentCommentUserName = parentCommentUserName;
    }

}
