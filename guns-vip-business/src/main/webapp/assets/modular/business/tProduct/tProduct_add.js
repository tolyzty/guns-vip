/**
 * 添加或者修改页面
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

    //标签栏
    $("#keywordKey").tagsInput();


    var newImgUrl="";
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
            newImgUrl = res.data.productIcon;
            console.info("地址:"+newImgUrl);
        }
        , error: function () {
            Feng.error("上传图片失败！");
        }
    });

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/tProduct/addItem", function (data) {
            Feng.success("添加成功！");
            window.location.href = Feng.ctxPath + '/tProduct'
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + '/tProduct'
    });

});
