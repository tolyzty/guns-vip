package cn.stylefeng.guns.web;

import cn.stylefeng.guns.modular.system.entity.TmenuIndex;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: zhangty
 * @Date: 2019/9/5 13:42
 * @Description:
 * @Version:1.0.0
 */

@Component
@Slf4j
public class ApplicationRunners implements ApplicationRunner {

    @Autowired
    private cn.stylefeng.guns.modular.system.service.TmenuIndexService tMenuIndexService;

    /**
     * 初始化查询菜单信息
     */
    public static List<TmenuIndex> LISTMENU;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("=====启动初始化程序开始====");
        List<TmenuIndex> list = tMenuIndexService.tmenuIndexList();
        setTmenuIndex(list);
        log.info("=====启动初始化程序完成====");
    }

    public void setTmenuIndex(List<TmenuIndex> lists){
        LISTMENU = lists;
    }
}
