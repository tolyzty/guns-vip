/**
 * 用户详情对话框
 */
layui.use(['layer', 'form', 'admin', 'laydate', 'ax','tagsInput','upload'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var layer = layui.layer;
    var upload = layui.upload;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();


   //建立编辑器
    var E = window.wangEditor;
    var editor = new E('#newContent');
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
    editor.customConfig.uploadImgServer = '../news/upload_img';
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


    /**
     * 查询下拉列表
     */
    $.ajax({
        url:"/news/findEnumInfo",
        type:"GET",
        dataType:"json",
        success:function(result){
            var list = result;    //返回的数据
            var newType = document.getElementById("newType"); //server为select定义的id
            for(var p in list){
                console.log(p);
                var option = document.createElement("option");  // 创建添加option属性
                option.setAttribute("value",p); // 给option的value添加值
                option.innerText=list[p];     // 打印option对应的纯文本
                newType.appendChild(option);           //给select添加option子标签
                form.render("select");
            } }
    });

    //标签栏
    $("#newTags").tagsInput();
    $("#newKeyword").tagsInput();

    var newImgUrl="";
    //普通图片上传
    upload.render({
        elem: '#picBtn'
        , url: Feng.ctxPath + '/news/upload'
        , before: function (obj) {
            obj.preview(function (index, file, result) {
                $('#img1').attr('src', result);
            });
        }
        , done: function (res) {
            $("#pictureInputHidden").val(res.data.fileId);
            Feng.success(res.message);
            newImgUrl = res.data.uploadName;
            console.info("地址:"+newImgUrl);
        }
        , error: function () {
            Feng.error("上传图片失败！");
        }
    });




    /*var newImgUrl="";
    //普通图片上传
    var uploadInst = upload.render({
        elem: '#test1'
        ,url: '/news/upload'
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#demo1').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            console.info(res);
            //如果上传失败
            if(res.code !== 200){
                return layer.msg('上传失败');
            }else{
                newImgUrl = res.data.uploadName;
                console.info("地址:"+newImgUrl);
                return layer.msg('上传成功');
            }
            //上传成功
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });*/



    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        data.field.newImgUrl = newImgUrl;
        data.field.newContent = editor.txt.html();
        var ajax = new $ax(Feng.ctxPath + "/news/add", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});
