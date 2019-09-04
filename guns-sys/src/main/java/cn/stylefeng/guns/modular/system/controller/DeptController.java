/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
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
package cn.stylefeng.guns.modular.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.base.log.BussinessLog;
import cn.stylefeng.guns.core.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.entity.Dept;
import cn.stylefeng.guns.base.shiro.annotion.Permission;
import cn.stylefeng.guns.core.constant.dictmap.DeptDict;
import cn.stylefeng.guns.base.pojo.node.TreeviewNode;
import cn.stylefeng.guns.base.pojo.node.ZTreeNode;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.system.entity.Puch;
import cn.stylefeng.guns.modular.system.entity.PuchName;
import cn.stylefeng.guns.modular.system.entity.PuchNameVO;
import cn.stylefeng.guns.modular.system.model.DeptDto;
import cn.stylefeng.guns.modular.system.service.DeptService;
import cn.stylefeng.guns.modular.system.warpper.DeptTreeWrapper;
import cn.stylefeng.guns.modular.system.warpper.DeptWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.treebuild.DefaultTreeBuildFactory;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 部门控制器
 *
 * @author fengshuonan
 * @Date 2017年2月17日20:27:22
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {

    private String PREFIX = "/modular/system/dept/";

    @Autowired
    private DeptService deptService;

    /**
     * 跳转到部门管理首页
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dept.html";
    }

    /**
     * 跳转到添加部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("/dept_add")
    public String deptAdd() {
        return PREFIX + "dept_add.html";
    }

    /**
     * 跳转到修改部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @Permission
    @RequestMapping("/dept_update")
    public String deptUpdate(@RequestParam("deptId") Long deptId) {

        if (ToolUtil.isEmpty(deptId)) {
            throw new RequestEmptyException();
        }

        //缓存部门修改前详细信息
        Dept dept = deptService.getById(deptId);
        LogObjectHolder.me().set(dept);

        return PREFIX + "dept_edit.html";
    }

    /**
     * 获取部门的tree列表，ztree格式
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.deptService.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 获取部门的tree列表，treeview格式
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/treeview")
    @ResponseBody
    public List<TreeviewNode> treeview() {
        List<TreeviewNode> treeviewNodes = this.deptService.treeviewNodes();

        //构建树
        DefaultTreeBuildFactory<TreeviewNode> factory = new DefaultTreeBuildFactory<>();
        factory.setRootParentId("0");
        List<TreeviewNode> results = factory.doTreeBuild(treeviewNodes);

        //把子节点为空的设为null
        DeptTreeWrapper.clearNull(results);

        return results;
    }

    /**
     * 新增部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @BussinessLog(value = "添加部门", key = "simpleName", dict = DeptDict.class)
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public ResponseData add(Dept dept) {
        this.deptService.addDept(dept);
        return SUCCESS_TIP;
    }

    /**
     * 获取所有部门列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(value = "condition", required = false) String condition,
                       @RequestParam(value = "deptId", required = false) Long deptId) {
        Page<Map<String, Object>> list = this.deptService.list(condition, deptId);
        Page<Map<String, Object>> wrap = new DeptWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 部门详情
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/detail/{deptId}")
    @Permission
    @ResponseBody
    public Object detail(@PathVariable("deptId") Long deptId) {
        Dept dept = deptService.getById(deptId);
        DeptDto deptDto = new DeptDto();
        BeanUtil.copyProperties(dept, deptDto);
        deptDto.setPName(ConstantFactory.me().getDeptName(deptDto.getPid()));
        return deptDto;
    }

    /**
     * 修改部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @BussinessLog(value = "修改部门", key = "simpleName", dict = DeptDict.class)
    @RequestMapping(value = "/update")
    @Permission
    @ResponseBody
    public ResponseData update(Dept dept) {
        deptService.editDept(dept);
        return SUCCESS_TIP;
    }

    /**
     * 删除部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @BussinessLog(value = "删除部门", key = "deptId", dict = DeptDict.class)
    @RequestMapping(value = "/delete")
    @Permission
    @ResponseBody
    public ResponseData delete(@RequestParam Long deptId) {

        //缓存被删除的部门名称
        LogObjectHolder.me().set(ConstantFactory.me().getDeptName(deptId));

        deptService.deleteDept(deptId);

        return SUCCESS_TIP;
    }


    public static void  main(String[] arge){
        String name = "您与tName老师的课程已经成功完成，订单号: oNo，点击可查看订单详情并评论。";
        String jname = "tName先生との授業はもう成功しました。注文番号：oNo、クリックして注文書の詳細を確認し、コメントしてください。";
        String name1="大约还有1小时tName老师就要和你上课啦，请别错过时间，订单号: oNo，点击查看订单详情。";
        String jname1="あと1時間ぐらいtName先生とあなたの授業があります。時間を逃さないでください。注文番号：oNo、クリックして注文書の詳細を調べてください。";
        System.out.println("中文消息---> "+name);
        System.out.println("中文消息1---> "+name1);
        System.out.println("如果中文消息包含---> 老师的课程已经成功完成，订单号");
        System.out.println("如果中文消息包含1---> 老师就要和你上课啦，请别错过时间");
        if(name.contains("老师的课程已经成功完成，订单号")){
            System.out.println("前台展示日文消息--->"+jname);
        }
        if (name1.contains("老师就要和你上课啦，请别错过时间")){
            System.out.println("前台展示日文消息---> "+jname1);
        }

        List<Puch> list = new ArrayList<>();
        Puch puch = new Puch();
        puch.setContains("老师的课程已经成功完成，订单号");
        puch.setName(name);
        puch.setJname(jname);
        list.add(puch);
        Puch puch1 = new Puch();
        puch1.setContains("老师就要和你上课啦，请别错过时间");
        puch1.setName(name1);
        puch1.setJname(jname1);
        list.add(puch1);

        List<PuchName> listName = new ArrayList<>();
        System.out.println("1."+System.currentTimeMillis());
        for(int ii=0;ii<20000;ii++){
            PuchName puchName =new PuchName();
            puchName.setId(String.valueOf(ii));
            puchName.setName(name+ii);
            listName.add(puchName);
        }
        //System.out.println("查询原始数据结果Log:  "+listName.size());
        List<PuchNameVO> listVo = new ArrayList<>();
        int u=0;
        for (PuchName p : listName) {
            u++;
             //System.out.println(""+u+"  . 原始中文数据： "+p.getName());
            for (Puch pu: list) {
                 PuchNameVO vo = new PuchNameVO();
                if (p.getName().contains(pu.getContains())){
                    vo.setName(pu.getJname());
                    listVo.add(vo);
                    //System.out.println(""+u+"  . 翻译日文数据: "+vo.getName());
                }

            }
        }
        System.out.println("2."+System.currentTimeMillis());







    }

}
