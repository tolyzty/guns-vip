/**
 * 详情对话框
 */
var TProductInfoDlg = {
    data: {
        productName: "",
        productType: "",
        productNum: "",
        productClassify: "",
        productSupPrice: "",
        productMarPrice: "",
        productPreferPrice: "",
        productDisPrice: "",
        keywordKey: "",
        productIcon: "",
        productRepertory: "",
        productSales: "",
        productDetail: "",
        productDispatch: "",
        productPayMethod: "",
        productFeature: "",
        productStandard: "",
        productIsShow: "",
        productPlace: "",
        productShowType: "",
        productCreateTime: "",
        productUpdateTime: "",
        productRemark: "",
        productOperator: "",
        productStatus: "",
        productIsPublish: ""
    }
};

layui.use(['form', 'admin', 'ax','upload'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var upload = layui.upload;
    //让当前iframe弹层高度适应
    admin.iframeAuto();


    //建立编辑器
    var E = window.wangEditor;
    var editor = new E('#productDetail');
    // 或者 var editor = new E( document.getElementById('editor') )
    editor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'fontSize',  // 字号
        // 'fontName',  // 字体
        // 'italic',  // 斜体
        // 'underline',  // 下划线
        // 'strikeThrough',  // 删除线
        // 'foreColor',  // 文字颜色
        // 'backColor',  // 背景颜色
        'list',  // 列表
        'justify',  // 对齐方式
        // 'quote',  // 引用
        'emoticon',  // 表情
        'image',  // 插入图片
        // 'table',  // 表格
        'undo',  // 撤销
        'redo'  // 重复
    ]
    editor.customConfig.uploadImgShowBase64 = true;
    /!* 处理上传图片的controller路径 *!/
    editor.customConfig.uploadImgServer = '../purpose/upload_img';
    /!* 定义上传图片的默认名字 *!/
    editor.customConfig.uploadFileName = 'file';
    editor.customConfig.uploadImgHooks = {
        customInsert: function (insertImg, result, editor) {
            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
            // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
            var url =result.data;
            insertImg(url);
            // result 必须是一个 JSON 格式字符串！！！否则报错
        }
    };
    editor.create();
    var productIconUrl="";
    //普通图片上传
    upload.render({
        elem: '#picBtn'
        , url: Feng.ctxPath + '/purpose/upload'
        , before: function (obj) {
            obj.preview(function (index, file, result) {
                $('#img1').attr('src', result);
            });
        }
        , done: function (res) {
            $("#pictureInputHidden").val(res.data.fileId);
            Feng.success(res.message);
            productIconUrl = res.data.uploadName;
            console.info("地址:"+newImgUrl);
        }
        , error: function () {
            Feng.error("上传图片失败！");
        }
    });

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/tProduct/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    editor.txt.html(result.data.productDetail);
    $("#keywordKey").tagsInput().importTags(result.data.keywordKey);
    $('#img1').attr('src', '../'+result.data.productIcon);
    productIconUrl = result.data.productIcon;
    form.val('tProductForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        data.field.productDetail = editor.txt.html();
        data.field.productIcon = productIconUrl;
        var ajax = new $ax(Feng.ctxPath + "/tProduct/editItem", function (data) {
            Feng.success("更新成功！");
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + '/tProduct'
    });

});
