package cn.stylefeng.guns.business.modular.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangty
 * @since 2019-08-26
 */
@Data
public class TProductParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 产品ID
     */
    private Long id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品类型
     */
    private Long productType;

    /**
     * 产品数量
     */
    private Long productNum;

    /**
     * 产品分类
     */
    private String productClassify;

    /**
     * 供货价格
     */
    private BigDecimal productSupPrice;

    /**
     * 市场价格
     */
    private BigDecimal productMarPrice;

    /**
     * 优惠价格
     */
    private BigDecimal productPreferPrice;

    /**
     * 打折价格
     */
    private BigDecimal productDisPrice;

    /**
     * 关键词
     */
    private String keywordKey;

    /**
     * 产品图标
     */
    private String productIcon;

    /**
     * 库存
     */
    private Long productRepertory;

    /**
     * 销量
     */
    private String productSales;

    /**
     * 产品详情
     */
    private String productDetail;

    /**
     * 产品配送类型
     */
    private String productDispatch;

    /**
     * 产品支付方式
     */
    private String productPayMethod;

    /**
     * 产品特征
     */
    private String productFeature;

    /**
     * 产品规格
     */
    private String productStandard;

    /**
     * 是否显示
     */
    private Long productIsShow;

    /**
     * 产品产地
     */
    private String productPlace;

    /**
     * 产品展示类型
     */
    private String productShowType;

    /**
     * 创建时间
     */
    private Date productCreateTime;

    /**
     * 修改时间
     */
    private Date productUpdateTime;

    /**
     * 备注
     */
    private String productRemark;

    /**
     * 操作人
     */
    private String productOperator;

    /**
     * 状态
     */
    private Long productStatus;

    /**
     * 是否上架
     */
    private Long productIsPublish;

    @Override
    public String checkParam() {
        return null;
    }

}
