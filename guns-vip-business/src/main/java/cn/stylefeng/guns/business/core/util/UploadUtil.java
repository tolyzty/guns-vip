/**
 * Copyright 2018-2020 stylefeng & tolyzty (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.business.core.util;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**

 * @author tolyzty
 * @Date 2017/8/25 10:59
 */
@Slf4j
public class UploadUtil {


        public Map<String,Object> uploadImg(String rootPath, HttpServletRequest request, MultipartFile Imgfile){
            //本地使用
          //  String rootPath="C:/User/zhangty/uploads/";
            //服务器上传
            String webapp="";
            try{
                String pathRoot =  ResourceUtils.getURL("classpath:").getPath()+"static/imgs/";
                String pathRoots = request.getRealPath("")+"imgs\\";
                String url = request.getSession().getServletContext().getRealPath("");
                log.info("上传文件目录:{}",url);
                int ch = url.lastIndexOf("\\");
                webapp = url.substring(0, ch-4)+"\\imgs\\";
                //webapp="C:\\Users\\zhangty\\Desktop\\star-master\\target\\classes\\static\\upload\\";
                log.info("上传文件地址:{}",webapp);
            }catch (Exception e){
                log.error("上传图片异常：{}",e);
            }

            String  result  = uploadFile(webapp,Imgfile);
            JSONObject json = JSONObject.parseObject(result);
            log.info("上传结果:{}",json);
            Map<String,Object> retMap = new HashMap<>();
            retMap.put("data",json.get("fileUrl")+"");
            return retMap;
        }


    /**
     * 上传工具类
     * @param rootPath 保存地址
     * @param Imgfile file文件信息
     * @return
     */
    public static String uploadFile(String rootPath, MultipartFile Imgfile){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String res = sdf.format(new Date());
        log.info("当前时间:{}",res);
        //服务器上使用
        // String rootPath =request.getServletContext().getRealPath("/resource/uploads/");//target的目录
        //本地使用
        rootPath="C:/User/zhangty/uploads/";
        //原始名称
        String originalName = Imgfile.getOriginalFilename();
        //新的文件名称
        String newFileName = res+originalName.substring(originalName.lastIndexOf("."));
        File newFile =new File(rootPath+newFileName);
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> map2 = new HashMap<String,Object>();
        String fileUrl="";
        String result="";
        //将内存中的数据写入磁盘
        try {
            Imgfile.transferTo(newFile);
            //完整的url
            fileUrl ="/imgs/"+newFileName;
        } catch (IOException e) {
            map.put("code",1);//0表示成功，1失败
            map.put("msg","上传异常");//提示消息
            map.put("data",map2);
            map2.put("src",fileUrl);//图片url
            map2.put("title",newFileName);//图片名称，这个会显示在输入框里
            result = new JSONObject(map).toString();
            return result;
        }
        map2.put("src",fileUrl);//图片url
        map2.put("title",newFileName);//图片名称，这个会显示在输入框里

        map.put("code",0);//0表示成功，1失败
        map.put("msg","上传成功");//提示消息
        map.put("data",map2);
        map.put("fileUrl",fileUrl);
        log.info("返回结果:{}",map);
        result = new JSONObject(map).toString();

        return result;
    }

    /**
     * 获取上传文件的路径通用方法
     * @param request
     * @param type
     * @return
     */
    public static String  pathRoot(HttpServletRequest request,String type){
        String url = request.getSession().getServletContext().getRealPath("");
        log.info("上传文件目录:{}",url);
        String  webapp = "";
        int ch = url.lastIndexOf("\\");
        if (("imgs").equals(type)){
            webapp = url.substring(0, ch-4)+"\\imgs\\";
            log.info("获取图片路径:{}",webapp);
        }else if(("pdf").equals(type)){
            webapp = url.substring(0, ch-4)+"\\pdf\\";
            log.info("获取PDF路径:{}",webapp);
        }else if(("temple").equals(type)){
            webapp = url.substring(0, ch-4)+"\\temple\\";
            log.info("获取模板位置:{}",webapp);
        }
        return webapp;
    }

    /**
     * 获取zip文件的目录
     * @param request
     * @return
     */
    public static String  zipUploadPath(HttpServletRequest request){
        String  fiName = System.currentTimeMillis()+"";
        log.info("获取zip压缩包时间:{}",fiName);
        String zipUrl = request.getSession().getServletContext().getRealPath("");
        log.info("zip文件目录:{}",zipUrl);
        int ch = zipUrl.lastIndexOf("\\");
        String zipFilename = zipUrl.substring(0, ch-4)+"\\zip\\"+""+fiName+".zip";
        log.info("ZIP文件地址:{}",zipFilename);
        return zipFilename;
    }

}
