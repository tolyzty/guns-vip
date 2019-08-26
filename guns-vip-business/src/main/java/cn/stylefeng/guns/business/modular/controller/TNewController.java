package cn.stylefeng.guns.business.modular.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.business.modular.entity.TNews;
import cn.stylefeng.guns.business.modular.service.TNewsService;
import cn.stylefeng.guns.business.modular.warpper.NewsWarpper;
import cn.stylefeng.guns.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.properties.GunsProperties;
import cn.stylefeng.guns.business.core.util.TagUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * @Auther: zhangty
 * @Date: 2019/7/30 17:22
 * @Description:
 * @Version:1.0.0
 */

@Controller
@RequestMapping("/news")
@Slf4j
public class TNewController extends BaseController {

    private String PREFIX = "/modular/business/news/";

    @Autowired
    private TNewsService tNewsService;
    @Autowired
    private GunsProperties gunsProperties;

    /**
     * 跳转到部门管理首页
     *
     * @author tolyzty
     * @Date 2019/07/30  17:56
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "news.html";
    }



    /**
     * 查询新闻
     *
     * @author tolyzty
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String newTital,
                       @RequestParam(required = false) String beginTime,
                       @RequestParam(required = false) String endTime) {
            log.info("查询新闻列表");
            Page<Map<String, Object>> news = tNewsService.list(newTital,beginTime,endTime);
            Page wrapped = new NewsWarpper(news).wrap();
            return LayuiPageFactory.createPageInfo(wrapped);
    }

    /**
     * 跳转到添加
     *
     * @author tolyzty
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("/news_add")
    public String newsAdd() {
        return PREFIX + "news_add.html";
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Object newsSave(TNews tNews){
        log.info("获取文章参数:{}",tNews);
        tNews.setNewCrtTime(new Date());
        tNews.setOperator("");
        log.info("封装所有获取文章参数:{}",tNews);
        tNewsService.save(tNews);
        log.info("保存文章成功");
        return SUCCESS_TIP;
    }



    @RequestMapping("/news_edit")
    public String userEdit(@RequestParam Long newId) {
        if (ToolUtil.isEmpty(newId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        TNews news = this.tNewsService.getById(newId);
        log.info("跳转edit页面获取的信息:{}",news);
        LogObjectHolder.me().set(news);
        return PREFIX + "news_edit.html";
    }

    /**
     * 修改文章
     *
     * @author tolyzty
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseData edit(TNews news) {
        log.info("修改文章请求参数:{}",news);
        if (("").equals(news.getNewTop()) || news.getNewTop()==null){
            news.setNewTop("of");
        }
        if (("").equals(news.getNewIsDisplay())|| news.getNewIsDisplay()==null){
            news.setNewIsDisplay("of");
        }
        if (("").equals(news.getNewRecomm()) || news.getNewRecomm()==null){
            news.setNewRecomm("of");
        }
        this.tNewsService.editTNews(news);
        return SUCCESS_TIP;
    }


    /**
     * 修改是否显示 on-显示，of-关闭
     * @param tNews
     * @return
     */
    @RequestMapping(value = "/upDispaly")
    @ResponseBody
    public ResponseData upDisplay(TNews tNews){
        log.info("修改文章是否显示:{}",tNews.getNewIsDisplay());
        this.tNewsService.editTNews(tNews);
        return SUCCESS_TIP;
    }


    /**
     * 修改状态-正常、关闭
     * @param tNews
     * @return
     */
    @RequestMapping(value = "/upStatus")
    @ResponseBody
    public ResponseData upStatus(TNews tNews){
        log.info("修改文章状态:{}",tNews.getNewStatus());
        this.tNewsService.editTNews(tNews);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     * @author tolyzty
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public ResponseData delete(@RequestParam Long newId) {
        log.info("删除文章操作:{}",newId);
        tNewsService.removeById(newId);
        return SUCCESS_TIP;
    }

    /**
     * 获取文章详情
     *
     * @author tolyzty
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/getNewsInfo")
    @ResponseBody
    public Object getUserInfo(@RequestParam Long newId) {
        if (ToolUtil.isEmpty(newId)) {
            throw new RequestEmptyException();
        }

        TNews news = this.tNewsService.getById(newId);
        log.info("查询文章详情:{}",news);
        if(!("").equals(news.getNewContent())){
            news.setNewContent(TagUtil.getTagHtml(news.getNewContent()));
        }
        if (("of").equals(news.getNewTop())){
            news.setNewTop("");
        }
        if (("of").equals(news.getNewRecomm())){
            news.setNewRecomm("");
        }
        if (("of").equals(news.getNewIsDisplay())){
            news.setNewIsDisplay("");
        }
        return ResponseData.success(news);
    }



    /**
     * 上传图片
     *
     * @author tolyzty
     * @Date 2018/12/24 22:44
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public ResponseData upload(@RequestPart("file") MultipartFile picture) {
        HashMap<String, Object> map = new HashMap<>(1);
        try {
            String uploadPath = uploadPath(picture);
            map.put("uploadName", uploadPath);
        }catch (Exception e){
            log.error("上传文件异常");
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return ResponseData.success(200, "上传成功", map);
    }


    @ResponseBody
    @RequestMapping(value="/upload_img",method = RequestMethod.POST)
    public Map<String, Object>  uploadImg(@RequestParam(value="file") MultipartFile Imgfile, HttpServletRequest request){
        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(Imgfile.getOriginalFilename());
        log.info("重名文件名:{}",pictureName);
        Map<String,Object> map2 = new HashMap<>(2);
        try {
            String fileSavePath = gunsProperties.getFileUploadPath();
            log.info("文件地址:{}",fileSavePath);
            Imgfile.transferTo(new File(fileSavePath + pictureName));
            log.info("上传文件成功");
            //图片名称，这个会显示在输入框里
            map2.put("title","");
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }
        Map<String,Object> map = new HashMap<>(4);
        //图片url
        map2.put("src",pictureName);
        //0表示成功，1失败
        map.put("code",0);
        //提示消息
        map.put("msg","上传成功");
        map.put("data",map2);
        map.put("fileUrl",pictureName);
        String  result = new JSONObject(map).toString();
        JSONObject json = JSONObject.parseObject(result);
        log.info("封装上传信息：{}",json);
        Map<String,Object> retMap = new HashMap<>(1);
        String  urls = "../"+json.get("fileUrl")+"";
        log.info("返回editor图片地址:{}",urls);
        retMap.put("data",urls);
        return retMap;
    }



    /**
     * 上传通用方法
     * @return
     */
    private String uploadPath(MultipartFile picture){
        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        log.info("重名文件名:{}",pictureName);
        try {
            String fileSavePath = gunsProperties.getFileUploadPath();
            log.info("文件地址:{}",fileSavePath);
            picture.transferTo(new File(fileSavePath + pictureName));
            log.info("上传文件成功");
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return  pictureName;
    }


    public static void main(String[] arge){
      /*  //Map<String, String> options = EnumUtils.getOptionsMap(NewsEnum.NewsType.class);
        System.out.println("Map的结果是："+options);
        String jsonObject = JSONUtils.toJSONString(options);
        System.out.println("输出的结果是：" + jsonObject);
        System.out.println(options); // label3
        Map<String,Object> reMap  = new HashMap<>(1);
        reMap.put("user","name");
        reMap.put("user1","na`me");
        System.out.println(reMap);*/
    }




}
