package cn.stylefeng.guns.business.modular.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.business.modular.entity.TNews;
import cn.stylefeng.guns.business.modular.mapper.TNewsMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;


/**
 *
 * @author tolyzty
 * @date 2019-07-31 09:06:38
 */
@Service
@Slf4j
public class TNewsService extends ServiceImpl<TNewsMapper, TNews> {

    @Resource
    private TNewsMapper tNewsMapper;


    /**
     * 获取列表
     *
     * @author tolyzty
     * @Date 2018/12/23 6:05 PM
     */
    public Page<Map<String, Object>> list(String newTital,String  beginTime,String  endTime) {
        Page page = LayuiPageFactory.defaultPage();
        return this.tNewsMapper.selectTnews(page,newTital,beginTime,endTime);
    }


    /**
     * 修改文章
     *
     * @author tolyzty
     * @Date 2018/12/24 22:53
     */
    @Transactional(rollbackFor = Exception.class)
    public void editTNews(TNews news) {
        log.info("****修改文章Service****:{}",news);
        tNewsMapper.editNews(news);
    }

}
