<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<link rel="icon" type="image/x-icon" href="images/logo_white.ico" />
<link rel="stylesheet" href="../layui/css/layui.css">
<link rel="stylesheet" href="css/admin-login.css">
<script src="../layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="../js/jquery-1.11.0.min.js"></script>
<title>爱租后台登录</title>
<style type="text/css">
body{
	background-color: #ceb9b9;
}
.layui-user-login{
    margin-top: 10%;

}

</style>
</head>
<body>

	<div class="layui-user-login " id="LAY-user-login">

		<div class="layui-user-login-main" >
			<div class="layui-user-login-header">
				<h1 align="center">
					<img alt="服务器异常" src="images/login.jpg">
				</h1>
			</div>
			<div id="loginStyle">
				<div class="layui-user-login-box layui-form">
					<div class="layui-form-item layui-form-item-input">
						<i class="layui-icon  layui-icon-username layui_user_icon"
							for="LAY-user-login-username"></i> <input type="text"
							name="account" id="LAY-user-login-username" lay-verify="required"
							placeholder="账户" class="layui-input ">
					</div>
					<div class="layui-form-item">
						<i class="layui-icon  layui-icon-password layui_user_icon"
							for="LAY-user-login-password"></i> <input type="password"
							name="pwd" id="LAY-user-login-password" lay-verify="required"
							placeholder="密码" class="layui-input">
					</div>
					<div class="layui-form-item">
						<div class="layui-row">
							<div class="layui-col-xs7">
								<i class="layui-icon  layui-icon-auz layui_user_icon"
									for="LAY-user-login-vercode"></i> <input type="text"
									name="vercode" id="LAY-user-login-vercode" lay-verify="required"
									placeholder="图形验证码" class="layui-input">
							</div>
							<div class="layui-col-xs5">
								<div style="margin-left: 15px;border: 1px solid #f0eeeb;">
									<img src="https://www.oschina.net/action/user/captcha"
										class="layadmin-user-login-codeimg" id="user-get-vercode">
								</div>
							</div>
						</div>
					</div>
					<div class="layui-form-item" style="margin-bottom: 20px;">
						<input type="checkbox" lay-filter="rempwd" name="remember"
							lay-skin="primary" title="记住密码" checked="checked">
						<div class="layui-unselect layui-form-checkbox layui-form-checked"
							lay-skin="primary">
							<span>记住密码</span> <i class="layui-icon layui-icon-ok"></i>
						</div>
					</div>
					<div class="layui-form-item">
						<button class="layui-btn layui-btn-fluid" lay-submit=""
							lay-filter="login-submit">登 入</button>
					</div>
				</div>
			</div>
		</div>

		<div class="layui-trans layadmin-user-login-footer" align="center">

			<p>
				© 2018 <a href="http://www.layui.com/" target="_blank">layui.com</a>
			</p>
			<p>
				<span><a href="http://www.layui.com/admin/#get"
					target="_blank">获取授权</a></span> <span><a
					href="http://www.layui.com/admin/pro/" target="_blank">在线演示</a></span> <span><a
					href="http://www.layui.com/admin/" target="_blank">前往官网</a></span>
			</p>
		</div>


	</div>

	<script type="text/javascript">
		//获取 cookie 
	    $(function(){	    	
	    	//console.log(window.document.cookie);
	    	var account;
	    	var pwd;
	    	if( document.cookie &&  document.cookie!=''){	    		
	    		var cookies= document.cookie.split(",");
				for( var i=0;i<cookies.length;i++ ){
					var cookie=cookies[i].split(";");
					//console.log(cookies[i]);
					account=cookie[0].substr(8);
					pwd=cookie[1].substr(5);
				}
	    	}
			$("#LAY-user-login-username").val(account);
			$("#LAY-user-login-password").val(pwd);
		});
	
	
	
		layui.use([ 'layer', 'form' ],function() {
			var layer = layui.layer;
			var form = layui.form;
			var flag =false;
			//layer.msg('Hello World');
			form.on('submit(login-submit)', function(data) {
				console.log(data);
				//console.log($("#user-get-vercode").val());
				// 获取 账户 密码  验证码 
				var account = data.field.account;
				var pwd = data.field.pwd;
				var vercode = data.field.vercode;
				//是否 记住密码 
				var rempwd=data.field.remember;
				if(rempwd != null && rempwd !=''){
					flag=true;
				}
				
// 				form.on('checkbox(rempwd)', function(remdata) {
// 					//console.log(data.elem); //得到checkbox原始DOM对象
// 					//console.log(remdata.elem.checked); //是否被选中，true或者false
// 					//console.log(data.value); //复选框value值，也可以通过data.elem.value得到
// 					//console.log(data.othis); //得到美化后的DOM对象
// 					flag = remdata.elem.checked;
// 					console.log(flag);
// 				});
				if (account == '' || pwd == '') {
					layer.msg('用户名和密码都不能为空', {icon : 5});
						return false;
				} else {
					$.post('../admin/selectAll', {account : account,pwd : pwd,flag : flag}, function(obj) {
						console.log(obj);
						if (obj == '1') {
							window.location.href = 'index.jsp';
						} else if(obj == '0'){
							layer.msg('抱歉，用户名不存在或密码错误', {icon : 5});
						}
					});
			    }			
			});
			//验证码绑定 点击事件
			layui.$('body').on('click','#user-get-vercode',function(data) {
				console.log(data);
				this.src = "https://www.oschina.net/action/user/captcha?t="+ (new Date).getTime();
			});

	    });
	</script>

</body>
</html>