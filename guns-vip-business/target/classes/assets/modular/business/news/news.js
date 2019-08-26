layui.use(['layer', 'form', 'table', 'ztree', 'laydate', 'admin', 'ax'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ZTree = layui.ztree;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var admin = layui.admin;

    /**
     * 内容管理--文章管理
     */
    var MgrNews = {
        tableId: "newsTable",    //表格id
        condition: {
            newTital: "",
            beginTime: "",
            endTime: ""
        }
    };

    /**
     * 初始化表格的列
     */
    MgrNews.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'newId', hide: true, sort: true, title: '文章id'},
            {field: 'newImgUrl', sort:true, templet: '#newImgUrl',title: '缩略图', align: 'center'},
            {field: 'newRecomm', sort: true, templet:'#newRecomm',title: '推荐',align: 'center'},
            {field: 'newTop', sort: true, title: '置顶',templet:'#newTop',align: 'center'},
            {field: 'newType', sort: true, title: '文章类型',align: 'center'},
            {field: 'newTital', sort: true, title: '文章标题',align: 'center'},
            {field: 'newAuthor', sort: true, title: '文章作者',align: 'center'},
            {field: 'newBrowse', sort: true, title: '浏览数量',align: 'center'},
            {field: 'newComment', sort: true, title: '评论数量',align: 'center'},
            {field: 'newCollection', sort: true, title: '收藏数量',align: 'center'},
            {field: 'newIsDisplay', sort: true,templet:'#displays', title: '是否显示',align: 'center'},
            {field: 'newStatus', sort: true, templet: '#statusTpl', title: '状态',align: 'center'},
            {field: 'newCrtTime', sort: true, title: '创建时间',align: 'center'},
            {toolbar: '#tableBar', title: '操作', minWidth: 280,align: 'center'}
        ]];
    };


    /**
     * 点击查询按钮
     */
    MgrNews.search = function () {
        var queryData = {};
        queryData['newTital'] = $("#newTital").val();
        queryData['beginTime'] = $("#beginTime").val();
        queryData['endTime'] = $("#endTime").val();
        table.reload(MgrNews.tableId, {where: queryData});
    };

    /**
     * 弹出添加用户对话框
     */
    MgrNews.openAddUser = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加文章',
            area: ['45%', '800px'],   //宽高
            content: Feng.ctxPath + '/news/news_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(MgrNews.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    MgrNews.exportExcel = function () {
        var checkRows = table.checkStatus(MgrNews.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 批量操作按钮
     */
    MgrNews.batchOperation = function(){
        var checkRows = table.checkStatus(MgrNews.tableId);
        console.log(checkRows);
        if (checkRows.data.length === 0){
            Feng.info("请选择要操作的数据");
        }else{
            Feng.info("选择了 "+checkRows.data.length+" 条数据");
        }
    };

    /**
     * 点击编辑用户按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    MgrNews.onEditNews = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑用户',
            area: ['45%', '800px'],   //宽高
            content: Feng.ctxPath + '/news/news_edit?newId=' + data.newId,
            end: function () {
                admin.getTempData('formOk') && table.reload(MgrNews.tableId);
            }
        });
    };

    /**
     * 点击删除用户按钮
     *
     * @param data 点击按钮时候的行数据
     */
    MgrNews.onDeleteNews = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/news/delete?newId="+data.newId, function () {
                table.reload(MgrNews.tableId);
                Feng.success("删除成功!");
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("newId", data.newId);
            ajax.start();
        };
        Feng.confirm("是否删除用户" + data.newId + "?", operation);
    };


    /**
     * 修改是否显示
     *
     * @param newsId 文章id
     * @param checked 是否选中（true,false），选中就是显示，未选中就是不显示
     */
    MgrNews.changeNewsIsDisplay = function (newId, checked) {
        if (checked) {
            var ajax = new $ax(Feng.ctxPath + "/news/upDispaly", function (data) {
                Feng.success("显示中!");
            }, function (data) {
                Feng.error("显示中失败!");
                table.reload(MgrNews.tableId);
            });
            ajax.set("newId", newId);
            ajax.set("newIsDisplay","on");
            ajax.start();
        } else {
            var ajax = new $ax(Feng.ctxPath + "/news/upDispaly", function (data) {
                Feng.success("不显示!");
            }, function (data) {
                Feng.error("显示中失败!");
                table.reload(MgrNews.tableId);
            });
            ajax.set("newId", newId);
            ajax.set("newIsDisplay","of");
            ajax.start();
        }
    };


    /**
     * 修改用户状态
     *
     * @param userId 用户id
     * @param checked 是否选中（true,false），选中就是解锁用户，未选中就是锁定用户
     */
    MgrNews.changeUserStatus = function (newId, checked) {
        if (checked) {
            var ajax = new $ax(Feng.ctxPath + "/news/upStatus", function (data) {
                Feng.success("文章正常!");
            }, function (data) {
                Feng.error("文章正常失败!");
                table.reload(MgrNews.tableId);
            });
            ajax.set("newId", newId);
            ajax.set("newStatus","0");
            ajax.start();
        } else {
            var ajax = new $ax(Feng.ctxPath + "/news/upStatus", function (data) {
                Feng.success("文章关闭!");
            }, function (data) {
                Feng.error("文章删除失败!" + data.responseJSON.message + "!");
                table.reload(MgrNews.tableId);
            });
            ajax.set("newId", newId);
            ajax.set("newStatus","1");
            ajax.start();
        }
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + MgrNews.tableId,
        url: Feng.ctxPath + '/news/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: MgrNews.initColumn()
    });
    //渲染时间选择框
    laydate.render({
        elem: '#beginTime'
    });

    //渲染时间选择框
    laydate.render({
        elem: '#endTime'
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        MgrNews.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        MgrNews.openAddUser();
    });

    // 导出excel
    $('#btnExp').click(function () {
        MgrNews.exportExcel();
    });
    //批量操作
    $('#operatBatch').click(function () {
        MgrNews.batchOperation();
    });

    // 工具条点击事件
    table.on('tool(' + MgrNews.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            MgrNews.onEditNews(data);
        } else if (layEvent === 'delete') {
            MgrNews.onDeleteNews(data);
        }
    });

    // 修改状态
    form.on('switch(newStatus)', function (obj) {
        console.info(obj);
        var newId = obj.elem.value;
        var checked = obj.elem.checked ? true : false;
        MgrNews.changeUserStatus(newId, checked);
    });


    // 修改是否显示
    form.on('switch(newIsDisplay)', function (obj) {
        console.info(obj);
        var newId = obj.elem.value;
        var checked = obj.elem.checked ? true : false;
        MgrNews.changeNewsIsDisplay(newId, checked);
    });

});
