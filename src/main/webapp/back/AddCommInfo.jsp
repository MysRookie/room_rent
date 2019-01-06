<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta  charset="utf-8">
<link rel="icon" type="image/x-icon" href="images/logo_white.ico" />
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<script src="../layui/layui.js"  charset="utf-8"></script>
<script type="text/javascript" src="../js/jquery-1.11.0.min.js"></script>
<title>添加通知信息</title>
</head>
<body>

	<div class="layui-card" >
		<div class="layui-card-header" align="center">
			<b style="font-size: 20px; font-style: italic; color: #e61804;">添加通知信息</b>
		</div>
		<div class="layui-card-body" style="height: 70%">
		<form class="layui-form">
				<div class="layui-form-item">
					<label class="layui-form-label">信息类型：</label>
					<div class="layui-input-block">
						<select name="comType" lay-verify="">
							<option value="公告"  selected>公告</option>
							<option value="新闻">新闻</option>
						</select>
						
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">内容：</label>
					<div class="layui-input-block">
						<textarea name="desc" placeholder="请输入内容" class="layui-textarea"style="height:180px;"></textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
					    <button type="reset" class="layui-btn layui-btn-primary"  style="margin-left:60%;">重置</button>
						  <button class="layui-btn" lay-submit lay-filter="submitCommInfo" 
						 style="margin-left:8%">确认发布通知</button>
						
					</div>
				</div>
			</form>
		</div>
	</div>
	
<script type="text/javascript">
	layui.use(['form','element','layer'], function(){
		  //var table = layui.table; // 表格 
		  var form = layui.form; //表单 
		  var element=layui.element; // 元素操作 
		  var layer = layui.layer; // 弹层 
		  //添加通知信息 form提交
		  form.on('submit(submitCommInfo)', function(data) {
				console.log(data);
				var comType=$("select[ name='comType' ]").val();
				var desc=$("textarea[ name='desc']").val();
				console.log('comType:'+comType+'\n desc:'+desc);
				//layer.msg('恭喜，发布成功！', {icon : 6});
				if(desc==''){
					layer.msg('请先添加要发布的内容...', {
						icon : 5,
						time: 3000, //2s后自动关闭
			  	        btn: ['OK']});
				}else{
					$.post('../insertInfo',{info_type:comType,content:desc},function(data){
						if(data>0){
							layer.msg('恭喜，发布成功！', {
								icon : 6,
								time: 2000, //2s后自动关闭
					  	        btn: ['OK']});
						}else{
							layer.msg('抱歉，发布失败！请检查信息是否齐全', {
					  			icon : 5, //图标 
					  			time: 3000, //2s后自动关闭
					  	        btn: ['知道了', '哦噢']});
						}				
					});  					
				}				
		  });		    
	});	
	
</script>
</body>
</html>