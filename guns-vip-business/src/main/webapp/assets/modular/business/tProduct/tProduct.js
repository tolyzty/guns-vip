layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 管理
     */
    var TProduct = {
        tableId: "tProductTable"
    };

    /**
     * 初始化表格的列
     */
    TProduct.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '产品ID'},
            {field: 'productName', sort: true, title: '产品名称'},
            {field: 'productType', sort: true, title: '产品类型'},
            {field: 'productNum', sort: true, title: '产品数量'},
            {field: 'productClassify', sort: true, title: '产品分类'},
            {field: 'productSupPrice', sort: true, title: '供货价格'},
            {field: 'productMarPrice', sort: true, title: '市场价格'},
            {field: 'productPreferPrice', sort: true, title: '优惠价格'},
            {field: 'productDisPrice', sort: true, title: '打折价格'},
            {field: 'keywordKey', sort: true, title: '关键词'},
            {field: 'productIcon', sort: true, title: '产品图标'},
            {field: 'productRepertory', sort: true, title: '库存'},
            {field: 'productSales', sort: true, title: '销量'},
            {field: 'productDetail', sort: true, title: '产品详情'},
            {field: 'productDispatch', sort: true, title: '产品配送类型'},
            {field: 'productPayMethod', sort: true, title: '产品支付方式'},
            {field: 'productFeature', sort: true, title: '产品特征'},
            {field: 'productStandard', sort: true, title: '产品规格'},
            {field: 'productIsShow', sort: true, title: '是否显示'},
            {field: 'productPlace', sort: true, title: '产品产地'},
            {field: 'productShowType', sort: true, title: '产品展示类型'},
            {field: 'productCreateTime', sort: true, title: '创建时间'},
            {field: 'productUpdateTime', sort: true, title: '修改时间'},
            {field: 'productRemark', sort: true, title: '备注'},
            {field: 'productOperator', sort: true, title: '操作人'},
            {field: 'productStatus', sort: true, title: '状态'},
            {field: 'productIsPublish', sort: true, title: '是否上架'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    TProduct.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(TProduct.tableId, {where: queryData});
    };

/*
    /!**
     * 弹出添加对话框
     *!/
    TProduct.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/tProduct/add';
    };
*/


    /**
     * 弹出添加用户对话框
     */
    TProduct.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加商品',
            area: ['55%', '450px'],   //宽高
            content: Feng.ctxPath + '/tProduct/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(TProduct.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    TProduct.exportExcel = function () {
        var checkRows = table.checkStatus(TProduct.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    TProduct.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/tProduct/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    TProduct.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/tProduct/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(TProduct.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + TProduct.tableId,
        url: Feng.ctxPath + '/tProduct/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: TProduct.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        TProduct.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        TProduct.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        TProduct.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + TProduct.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            TProduct.openEditDlg(data);
        } else if (layEvent === 'delete') {
            TProduct.onDeleteItem(data);
        }
    });
});
