<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<link rel="icon" type="image/x-icon" href="images/logo_white.ico" />
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<script src="../layui/layui.js" charset="utf-8"></script>
<script src="../js/jquery-1.11.0.min.js"></script>

<style type="text/css">
.layui-layout-admin .layui-side {
	width: 150px;
}
.layui-side-scroll {
	width: 150px;
}
.layui-nav-tree {
	width: 150px;
}
.layui-body {
	left: 150px;
}
</style>
<title>爱租后台管理</title>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">

				<a href="index.jsp" style="color: #0dbf8c;"><img alt="logo异常"
					src="images/logo.ico">爱租后台管理</a>
			</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="javascript:addAdmin()">添加账户</a></li>
				<li class="layui-nav-item"><a href="javascript:safeManage()">安全设置</a></li>
				<li class="layui-nav-item"><a href="javascript:userFeedback()">用户反馈<span class="layui-badge">2</span></a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它设置</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:infoManage()">消息管理</a>
						</dd>
						<dd>
							<a href="javascript:limitControl()">授权管理</a>
						</dd>
					</dl></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="./images/headphoto.jpg" class="layui-nav-img"> <span
						id="account">${sessionScope.adminPO.account}</span>
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:addAdmin()">添加账户</a>
						</dd>
						<dd>
							<a href="javascript:safeManage()">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="javascript:loginOut();">退出</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item">
						<!--  layui-nav-itemed  设置菜单项展开 --> <a class=""
						href="javascript:;"  id="badge-dot">房源信息管理<span  class=""></span></a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:findHouseInfo()">查看房源信息</a>
							</dd>
							<dd>
								<a href="javascript:verifyHouseInfo();" id="badge-num">房源信息审核<span class=""></span></a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item"><a href="javascript:;">用户信息管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:findUserInfo()">查看用户信息</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">通知信息管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:releaseComInfo()">发布通知信息</a>
							</dd>
							<dd>
								<a href="javascript:findComInfo()">查看通知信息</a>
							</dd>
						</dl></li>

					<li class="layui-nav-item"><a href="javascript:limitControl()">权限管理</a></li>
					<li class="layui-nav-item"><a href="javascript:sysManager()">系统维护</a></li>
				</ul>
			</div>
		</div>

		<div class="layui-body" style="background-color: #fcf4f0;">
			<!-- 分割线  -->
			<hr class="layui-bg-orange" noshade="noshade">
			<hr class="layui-bg-orange" noshade="noshade">
			<!-- 内容主体区域 -->
			<div id="content"style="padding: 8px; width: 74%; height: 100%; float: left;">

				<!--  <iframe frameborder="0" scrolling="no" src="AddCommInfo.html" width="90%" height="90%" style="margin-left: 10%;margin-top: 8%;"></iframe>	
	   -->
				<!--  动态包含 <jsp:include page="AddCommInfo.jsp"></jsp:include>  -->
				<!-- 轮播图  -->
				<div style="width:100%;height:95%;background-color: white;" align="center">
					<div>
						<img alt="君上，本地资源不存在" src="images/welcome.PNG">
					</div>
					<div class="layui-carousel" id="carouselphoto">
					  <div carousel-item>
					    <div><img alt="君上，服务器资源不见了" src="images/company1.jpg"></div>
					    <div><img alt="君上，服务器资源不见了" src="images/company2.jpg"></div>
					    <div><img alt="君上，服务器资源不见了" src="images/company3.jpg"></div>
					  </div>
				    </div>
				</div>				
			</div>

			<div id="commons"style="padding: 2px; width: 20%; float: right; margin-top: 2%; margin-right: 2%;">
				
				<!-- 公告时间 -->
				<div class="layui-inline" id="show_date" style="padding-top: 5px;"></div>
			</div>
		</div>

		<div class="layui-footer" align="center" style="left:100px;">
			<!-- 底部固定区域 -->
			? aizu.com - 版权所有 : yc_house_backManager<br />
		</div>
	</div>
	<!-- 添加管理员  -->
	<div style="display: none; margin: 20px;" id="addAdmin">
		<form class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">账户名：</label>
				<div class="layui-input-block">
					<input type="text" name="account" required lay-verify="required"
						placeholder="请输入账户名" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码：</label>
				<div class="layui-input-inline">
					<input type="password" name="pwd" required lay-verify="required"
						placeholder="请输入密码" autocomplete="off" class="layui-input">
				</div>
			</div>


		</form>
	</div>
	<!-- 添加通知信息  -->
    <div class="layui-card" id="addcomm" >
		<div class="layui-card-header" align="center">
			<b style="font-size: 20px; font-style: italic; color: #e61804;">添加通知信息</b>
		</div>
		<div class="layui-card-body" style="height: 70%">
		<form>
				<div class="layui-form layui-form-item">
					<label class="layui-form layui-form-label">信息类型：</label>
					<div class="layui-form layui-input-block">
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
					    <button class="layui-btn" id="submitCommInfo"  style="margin-left:0%" >确认发布通知</button>
					    <button type="reset" class="layui-btn layui-btn-primary"  style="margin-left:70%;">重置</button>
											
					</div>
				</div>
			</form>
		</div>
	</div>
	
<script type="text/html" id="toolbarDemo">
	  <div class="layui-btn-container">
	    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
	    <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
	    <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
        <button class="layui-btn layui-btn-sm" lay-event="passAll">批量通过</button>
	  </div>
</script>

	<script type="text/html" id="barDemo">
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
      <a class="layui-btn layui-btn-xs" lay-event="repush">重新审核</a>
</script>

<script type="text/html" id="barUnVerifyHouseInfo">
<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="unpass">不通过</a>	
<a class="layui-btn layui-btn-xs" lay-event="passed">通过</a>	   
</script>


	<script type="text/html" id="barUserInfoEdit">
	  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<!-- 用户性别 样式  -->
<script type="text/html" id="sexStyle">
  {{#  if(d.sex === '女'){ }}
    <span style="color: #F581B1;">{{ d.sex }}</span>
  {{#  } else { }}
    {{ d.sex }}
  {{#  } }}
</script>
<!-- 房源所有者样式 -->
<script type="text/html" id="usernameStyle">
  <span class="layui-table-link" target="_blank">{{ d.uname }}</span>
</script>

<script type="text/javascript" src="js/backIndex.js">

</script>
</body>
</html>