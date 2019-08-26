package cn.stylefeng.guns.business.modular.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangty
 * @since 2019-08-26
 */
@TableName("T_PRODUCT")
public class TProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 产品名称
     */
    @TableField("PRODUCT_NAME")
    private String productName;

    /**
     * 产品类型
     */
    @TableField("PRODUCT_TYPE")
    private Long productType;

    /**
     * 产品数量
     */
    @TableField("PRODUCT_NUM")
    private Long productNum;

    /**
     * 产品分类
     */
    @TableField("PRODUCT_CLASSIFY")
    private String productClassify;

    /**
     * 供货价格
     */
    @TableField("PRODUCT_SUP_PRICE")
    private BigDecimal productSupPrice;

    /**
     * 市场价格
     */
    @TableField("PRODUCT_MAR_PRICE")
    private BigDecimal productMarPrice;

    /**
     * 优惠价格
     */
    @TableField("PRODUCT_PREFER_PRICE")
    private BigDecimal productPreferPrice;

    /**
     * 打折价格
     */
    @TableField("PRODUCT_DIS_PRICE")
    private BigDecimal productDisPrice;

    /**
     * 关键词
     */
    @TableField("KEYWORD_KEY")
    private String keywordKey;

    /**
     * 产品图标
     */
    @TableField("PRODUCT_ICON")
    private String productIcon;

    /**
     * 库存
     */
    @TableField("PRODUCT_REPERTORY")
    private Long productRepertory;

    /**
     * 销量
     */
    @TableField("PRODUCT_SALES")
    private String productSales;

    /**
     * 产品详情
     */
    @TableField("PRODUCT_DETAIL")
    private String productDetail;

    /**
     * 产品配送类型
     */
    @TableField("PRODUCT_DISPATCH")
    private String productDispatch;

    /**
     * 产品支付方式
     */
    @TableField("PRODUCT_PAY_METHOD")
    private String productPayMethod;

    /**
     * 产品特征
     */
    @TableField("PRODUCT_FEATURE")
    private String productFeature;

    /**
     * 产品规格
     */
    @TableField("PRODUCT_STANDARD")
    private String productStandard;

    /**
     * 是否显示
     */
    @TableField("PRODUCT_IS_SHOW")
    private Long productIsShow;

    /**
     * 产品产地
     */
    @TableField("PRODUCT_PLACE")
    private String productPlace;

    /**
     * 产品展示类型
     */
    @TableField("PRODUCT_SHOW_TYPE")
    private String productShowType;

    /**
     * 创建时间
     */
    @TableField("PRODUCT_CREATE_TIME")
    private Date productCreateTime;

    /**
     * 修改时间
     */
    @TableField("PRODUCT_UPDATE_TIME")
    private Date productUpdateTime;

    /**
     * 备注
     */
    @TableField("PRODUCT_REMARK")
    private String productRemark;

    /**
     * 操作人
     */
    @TableField("PRODUCT_OPERATOR")
    private String productOperator;

    /**
     * 状态
     */
    @TableField("PRODUCT_STATUS")
    private Long productStatus;

    /**
     * 是否上架
     */
    @TableField("PRODUCT_IS_PUBLISH")
    private Long productIsPublish;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductType() {
        return productType;
    }

    public void setProductType(Long productType) {
        this.productType = productType;
    }

    public Long getProductNum() {
        return productNum;
    }

    public void setProductNum(Long productNum) {
        this.productNum = productNum;
    }

    public String getProductClassify() {
        return productClassify;
    }

    public void setProductClassify(String productClassify) {
        this.productClassify = productClassify;
    }

    public BigDecimal getProductSupPrice() {
        return productSupPrice;
    }

    public void setProductSupPrice(BigDecimal productSupPrice) {
        this.productSupPrice = productSupPrice;
    }

    public BigDecimal getProductMarPrice() {
        return productMarPrice;
    }

    public void setProductMarPrice(BigDecimal productMarPrice) {
        this.productMarPrice = productMarPrice;
    }

    public BigDecimal getProductPreferPrice() {
        return productPreferPrice;
    }

    public void setProductPreferPrice(BigDecimal productPreferPrice) {
        this.productPreferPrice = productPreferPrice;
    }

    public BigDecimal getProductDisPrice() {
        return productDisPrice;
    }

    public void setProductDisPrice(BigDecimal productDisPrice) {
        this.productDisPrice = productDisPrice;
    }

    public String getKeywordKey() {
        return keywordKey;
    }

    public void setKeywordKey(String keywordKey) {
        this.keywordKey = keywordKey;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public Long getProductRepertory() {
        return productRepertory;
    }

    public void setProductRepertory(Long productRepertory) {
        this.productRepertory = productRepertory;
    }

    public String getProductSales() {
        return productSales;
    }

    public void setProductSales(String productSales) {
        this.productSales = productSales;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public String getProductDispatch() {
        return productDispatch;
    }

    public void setProductDispatch(String productDispatch) {
        this.productDispatch = productDispatch;
    }

    public String getProductPayMethod() {
        return productPayMethod;
    }

    public void setProductPayMethod(String productPayMethod) {
        this.productPayMethod = productPayMethod;
    }

    public String getProductFeature() {
        return productFeature;
    }

    public void setProductFeature(String productFeature) {
        this.productFeature = productFeature;
    }

    public String getProductStandard() {
        return productStandard;
    }

    public void setProductStandard(String productStandard) {
        this.productStandard = productStandard;
    }

    public Long getProductIsShow() {
        return productIsShow;
    }

    public void setProductIsShow(Long productIsShow) {
        this.productIsShow = productIsShow;
    }

    public String getProductPlace() {
        return productPlace;
    }

    public void setProductPlace(String productPlace) {
        this.productPlace = productPlace;
    }

    public String getProductShowType() {
        return productShowType;
    }

    public void setProductShowType(String productShowType) {
        this.productShowType = productShowType;
    }

    public Date getProductCreateTime() {
        return productCreateTime;
    }

    public void setProductCreateTime(Date productCreateTime) {
        this.productCreateTime = productCreateTime;
    }

    public Date getProductUpdateTime() {
        return productUpdateTime;
    }

    public void setProductUpdateTime(Date productUpdateTime) {
        this.productUpdateTime = productUpdateTime;
    }

    public String getProductRemark() {
        return productRemark;
    }

    public void setProductRemark(String productRemark) {
        this.productRemark = productRemark;
    }

    public String getProductOperator() {
        return productOperator;
    }

    public void setProductOperator(String productOperator) {
        this.productOperator = productOperator;
    }

    public Long getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Long productStatus) {
        this.productStatus = productStatus;
    }

    public Long getProductIsPublish() {
        return productIsPublish;
    }

    public void setProductIsPublish(Long productIsPublish) {
        this.productIsPublish = productIsPublish;
    }

    @Override
    public String toString() {
        return "TProduct{" +
        "id=" + id +
        ", productName=" + productName +
        ", productType=" + productType +
        ", productNum=" + productNum +
        ", productClassify=" + productClassify +
        ", productSupPrice=" + productSupPrice +
        ", productMarPrice=" + productMarPrice +
        ", productPreferPrice=" + productPreferPrice +
        ", productDisPrice=" + productDisPrice +
        ", keywordKey=" + keywordKey +
        ", productIcon=" + productIcon +
        ", productRepertory=" + productRepertory +
        ", productSales=" + productSales +
        ", productDetail=" + productDetail +
        ", productDispatch=" + productDispatch +
        ", productPayMethod=" + productPayMethod +
        ", productFeature=" + productFeature +
        ", productStandard=" + productStandard +
        ", productIsShow=" + productIsShow +
        ", productPlace=" + productPlace +
        ", productShowType=" + productShowType +
        ", productCreateTime=" + productCreateTime +
        ", productUpdateTime=" + productUpdateTime +
        ", productRemark=" + productRemark +
        ", productOperator=" + productOperator +
        ", productStatus=" + productStatus +
        ", productIsPublish=" + productIsPublish +
        "}";
    }
}
