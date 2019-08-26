package cn.stylefeng.guns.business.modular.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

    /**
     * 实体
     * 表名 T_NEWS
     *
     * @author tolyzty
     * @date 2019-07-31 09:06:38
     */
    @Data
    @TableName("T_NEWS")
    public class TNews implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * 新闻ID
         */
        @TableId(value = "NEW_ID", type = IdType.ID_WORKER)
        private Long newId;
        /**
         * 标题
         */
        private String newTital;
        /**
         * 内容
         */
        private String newContent;
        /**
         * 摘要
         */
        private String newAbstracts;
        /**
         * 类型
         */
        private String newType;
        /**
         * 收藏
         */
        private String newCollection;
        /**
         * 作者
         */
        private String newAuthor;
        /**
         * 浏览
         */
        private String newBrowse;
        /**
         * 评论
         */
        private String newComment;
        /**
         * 图片地址
         */
        private String newImgUrl;
        /**
         * 是否显示
         */
        private String newIsDisplay;
        /**
         * 新闻状态
         */
        private String newStatus;
        /**
         * 标签
         */
        private String newTags;
        /**
         * 关键字
         */
        private String newKeyword;
        /**
         * 推荐
         */
        private String newRecomm;
        /**
         * 置顶
         */
        private String newTop;
        /**
         * 创建时间
         */
        private Date newCrtTime;
        /**
         * 修改时间
         */
        private Date newUptTime;
        /**
         * 操作人
         */
        private String operator;


}
