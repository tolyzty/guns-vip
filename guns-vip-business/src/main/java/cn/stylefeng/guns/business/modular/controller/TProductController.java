package cn.stylefeng.guns.business.modular.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.business.core.util.TagUtil;
import cn.stylefeng.guns.business.modular.entity.TProduct;
import cn.stylefeng.guns.business.modular.model.params.TProductParam;
import cn.stylefeng.guns.business.modular.service.TProductService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * 控制器
 *
 * @author zhangty
 * @Date 2019-08-26 09:10:29
 */
@Slf4j
@Controller
@RequestMapping("/tProduct")
public class TProductController extends BaseController {

    private static String PREFIX = "/modular/business/tProduct/";

    @Autowired
    private TProductService tProductService;

    /**
     * 跳转到主页面
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/tProduct.html";
    }

    /**
     * 新增页面
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/tProduct_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/tProduct_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(TProductParam tProductParam) {
        log.info("获取产品信息参数:{}",tProductParam);
        tProductParam.setProductCreateTime(new Date());
        this.tProductService.add(tProductParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(TProductParam tProductParam) {
        tProductParam.setProductUpdateTime(new Date());
        this.tProductService.update(tProductParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(TProductParam tProductParam) {
        this.tProductService.delete(tProductParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(TProductParam tProductParam) {
        log.info("查看详情接口参数:{}",tProductParam);
        TProduct detail = this.tProductService.getById(tProductParam.getId());
        if(!("").equals(detail.getProductDetail())){
            detail.setProductDetail(TagUtil.getTagHtml(detail.getProductDetail()));
        }
        log.info("查看详情结果:{}",detail);
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author zhangty
     * @Date 2019-08-26
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(TProductParam tProductParam) {
        return this.tProductService.findPageBySpec(tProductParam);
    }

}


