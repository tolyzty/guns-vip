package cn.stylefeng.guns.business.modular.controller;

import cn.stylefeng.guns.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.core.properties.GunsProperties;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: zhangty
 * @Date: 2019/8/26 11:49
 * @Description:通用的控制
 * @Version:1.0.0
 */
@Slf4j
@Controller
@RequestMapping("/purpose")
public class PurposeController extends BaseController {

    @Autowired
    private GunsProperties gunsProperties;

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
    public Map<String, Object> uploadImg(@RequestParam(value="file") MultipartFile Imgfile, HttpServletRequest request){
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
}
