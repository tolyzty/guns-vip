package cn.stylefeng.guns.web.modular.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: zhangty
 * @Date: 2019/9/3 16:51
 * @Description:
 * @Version:1.0.0
 */

@Controller
@Slf4j
public class IndexController extends BaseController {
         private String PREFIX = "/web/";

         @RequestMapping("/index.html")
        public String index(){
            log.info("访问前台首页:{}");
             return PREFIX+
                     "/index.html";
        }
        @RequestMapping("/work.html")
        public String work(){
             log.info("跳转到work页面");
             return  PREFIX+"/work.html";
        }
}
