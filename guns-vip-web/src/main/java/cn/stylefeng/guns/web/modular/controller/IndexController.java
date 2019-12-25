package cn.stylefeng.guns.web.modular.controller;

import cn.stylefeng.guns.modular.system.entity.TmenuIndex;
import cn.stylefeng.guns.web.ApplicationRunners;
import cn.stylefeng.roses.core.base.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Auther: zhangty
 * @Date: 2019/9/3 16:51
 * @Description:
 * @Version:1.0.0
 */

@Controller
@Slf4j
public class IndexController extends BaseController {

    private static String PREFIX = "/web/";



    @RequestMapping("/index.html")
    public String index(Model model){
        log.info("访问前台首页");
        List<TmenuIndex> list = ApplicationRunners.LISTMENU;
        log.info("获取所有菜单:{}",list.size());
        model.addAttribute("data",list);
        model.addAttribute("flag","index.html");
        return PREFIX+ "index.html";
    }

    @RequestMapping("/work.html")
    public String work(Model model){
        log.info("跳转到work页面");
        List<TmenuIndex> list = ApplicationRunners.LISTMENU;
        log.info("获取所有菜单:{}",list.size());
        model.addAttribute("data",list);
        model.addAttribute("flag","work.html");
        return  PREFIX+"work.html";
    }

    @RequestMapping("/about.html")
    public String about(Model model){
        log.info("跳转到about页面");
        List<TmenuIndex> list = ApplicationRunners.LISTMENU;
        model.addAttribute("data",list);
        model.addAttribute("flag","about.html");
        return PREFIX+"about.html";
    }

    @RequestMapping("/contact.html")
    public String contact(Model model){
        log.info("-------contact------");
        List<TmenuIndex> list = ApplicationRunners.LISTMENU;
        model.addAttribute("data",list);
        model.addAttribute("flag","contact.html");
        return PREFIX+"contact.html";
    }

    @RequestMapping("/single.html")
    public String single(Model model){
        log.info("-------single------");
        List<TmenuIndex> list = ApplicationRunners.LISTMENU;
        model.addAttribute("data",list);
        model.addAttribute("flag","single.html");
        return PREFIX+"single.html";
    }


    public static void main(String[] arge){
        System.out.println("测试一个main方法");
    }


}
