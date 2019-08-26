layui.use(['layer', 'form', 'table', 'laydate', 'admin', 'ax'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var admin = layui.admin;

    /**
     * 内容管理--留言管理
     */
    var MgrLists = {
        tableId: "commentTable",    //表格id
        condition: {
            userName: "",
            beginTime: "",
            endTime: ""
        }
    };

    /**
     * 初始化表格的列
     */
    MgrLists.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', sort: true, title: '留言ID'},
            {field: 'userId',hide:true, sort:true, title:'留言用户ID'},
            {field: 'userName', sort:true, title:'留言用户名',align: 'center'},
            {field: 'articleId',hide:true, sort:true, title:'文章ID'},
            {field: 'articleTitle', sort:true, title:'文章标题',align: 'center'},
            {field: 'commentLevel', sort: true, title: '评论级别',align: 'center'},
            {field: 'commentType', sort: true, title: '评论类型',align: 'center'},
            {field: 'topStatus', sort: true, title: '置顶状态',templet:'#topStatus',align: 'center'},
            {field: 'commentStatus', sort: true, title: '评论状态',align: 'center'},
            {field: 'praiseNum', sort: true, title: '点赞数量',align: 'center'},
            {field: 'createTime', sort: true, title: '创建时间',align: 'center'},
            {toolbar: '#tableBar', title: '操作', minWidth: 280,align: 'center'}
        ]];
    };


    /**
     * 点击查询按钮
     */
    MgrLists.search = function () {
        var queryData = {};
        queryData['userName'] = $("#userName").val();
        queryData['beginTime'] = $("#beginTime").val();
        queryData['endTime'] = $("#endTime").val();
        console.log(MgrLists.tableId);
        table.reload(MgrLists.tableId, {where: queryData});
    };

 /*   /!**
     * 弹出添加用户对话框
     *!/
    MgrLists.openAddUser = function () {
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
    };*/

    /**
     * 导出excel按钮
     */
    MgrLists.exportExcel = function () {
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
    MgrLists.batchOperation = function(){
        var checkRows = table.checkStatus(MgrLists.tableId);
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
    MgrLists.onEditNews = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑留言',
            area: ['45%', '800px'],   //宽高
            content: Feng.ctxPath + '/comment/comment_edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(MgrLists.tableId);
            }
        });
    };

    /**
     * 点击删除用户按钮
     *
     * @param data 点击按钮时候的行数据
     */
    MgrLists.onDeleteNews = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/comment/delete?id="+data.id, function () {
                table.reload(MgrLists.tableId);
                Feng.success("删除成功!");
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除用户" + data.id + "?", operation);
    };


    /**
     * 修改留言是否正定
     *
     * @param id 留言id
     * @param level  =1
     * @param checked 是否选中（true,false），选中就是显示，未选中就是不显示
     */
    MgrLists.changeCommentIsTop = function (id, checked) {
        if (checked) {
            var ajax = new $ax(Feng.ctxPath + "/comment/topUp", function (data) {
                Feng.success("置顶中!");
            }, function (data) {
                Feng.error("置顶中失败!");
                table.reload(MgrLists.tableId);
            });
            ajax.set("id", id);
            ajax.set("topStatus","1");
            ajax.start();
        } else {
            var ajax = new $ax(Feng.ctxPath + "/comment/topUp", function (data) {
                Feng.success("未置顶!");
            }, function (data) {
                Feng.error("未置顶失败!");
                table.reload(MgrLists.tableId);
            });
            ajax.set("id", id);
            ajax.set("topStatus","0");
            ajax.start();
        }
    };


   /* /!**
     * 修改用户状态
     *
     * @param userId 用户id
     * @param checked 是否选中（true,false），选中就是解锁用户，未选中就是锁定用户
     *!/
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
    };*/

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + MgrLists.tableId,
        url: Feng.ctxPath + '/comment/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: MgrLists.initColumn()
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
        MgrLists.search();
    });

  /*  // 添加按钮点击事件
    $('#btnAdd').click(function () {
        MgrNews.openAddUser();
    });*/

    // 导出excel
    $('#btnExp').click(function () {
        MgrLists.exportExcel();
    });
    //批量操作
    $('#operatBatch').click(function () {
        MgrLists.batchOperation();
    });


    // 查看评论
    function showComments(id,commentType) {
        if(commentType==='留言'){
            commentType ='4';
        }else{
            commentType = '3';
        }
        admin.open({
            type: 1,
            area: '750px',
            offset: '65px',
            title: '查看评论',
            content: $('#commentsModel').html(),
            success: function () {
                // 渲染表格
                var insTbCom = table.render({
                    elem: '#commentInfo',
                    url: Feng.ctxPath + '/comment/commentInfo?userId=' + id+'&commentType='+commentType,
                    page: true,
                    height: 400,
                    cellMinWidth: 100,
                    cols: [[
                        {type: 'checkbox'},
                        {field: 'id', sort: true,hide:true, title: '留言ID'},
                        {field: 'userId',hide:true, sort:true, title:'楼主用户ID'},
                        {field: 'parentCommentUserName', sort: true, title: '楼主'},
                        {field: 'userName', sort: true, title: '评论人'},
                        {field: 'replyCommentUserName', sort: true, title: '被评论人'},
                        {field: 'commentType', sort: true, title: '评论类型'},
                        {field: 'commentContent', sort: true, title: '评论内容'},
                        {field:'createTime',title: '评论时间', sort: true},
                        {align: 'center', toolbar: '#tableEDCommentsBar', title: '操作', minWidth: 80, width: 85}
                    ]]

                });

                // 查看评论工具条点击事件
                table.on('tool(commentInfo)', function (obj) {
                    var data = obj.data;
                    var layEvent = obj.event;
                    if (layEvent === 'del') { // 删除
                        console.log(data.id);
                        var operation = function () {
                            var ajax = new $ax(Feng.ctxPath + "/comment/upDetelEdit?id="+data.id, function () {
                                table.reload("commentInfo");
                                Feng.success("删除成功!");
                            }, function (data) {
                                Feng.error("删除失败!" + data.responseJSON.message + "!");
                            });
                            ajax.set("id", data.id);
                            ajax.start();
                        };
                        Feng.confirm("是否删除留言" + data.id + "?", operation);
                        /*layer.msg('删除成功', {icon: 1});*/
                    }
                });

                // 发表评论
                $('#BtnAddComment').click(function () {
                    layer.prompt({
                        title: '发表评论',
                        shade: .1,
                        offset: '165px',
                        skin: 'layui-layer-admin layui-layer-prompt',
                        formType: 2
                    }, function (value, index, elem) {
                        layer.close(index);
                        layer.msg('评论成功', {icon: 1});
                    });
                });

            }
        });
    }

    // 工具条点击事件
    table.on('tool(' + MgrLists.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            MgrLists.onEditNews(data);
        } else if (layEvent === 'delete') {
            MgrLists.onDeleteNews(data);
        } else  if (layEvent === 'comments') {  // 查看评论
            showComments(data.userId,data.commentType);
        }
    });

 /*   // 修改状态
    form.on('switch(newStatus)', function (obj) {
        console.info(obj);
        var id = obj.elem.value;
        var checked = obj.elem.checked ? true : false;
        MgrNews.changeUserStatus(id, checked);
    });
*/

    // 修改是否显示
    form.on('switch(topStatus)', function (obj) {
        console.info(obj);
        var id = obj.elem.value;
        var checked = obj.elem.checked ? true : false;
        MgrLists.changeCommentIsTop(id, checked);
    });






});
