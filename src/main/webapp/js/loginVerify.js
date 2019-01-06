/**
 * 身份验证 
 */

//判断是否登录    加id
function islogin(){
	$.post("user/yes",function(value){
		if(value!=null && value!=''){
			$("#person").attr("href","my.html");
			$("#puthouse").attr("href","puthouse.html");
			$("#login_name").attr("href","my.html");
			$("#login_name").html(value.uname);
		}else{
			$("#login_name").html("用户登录");
			$("#person").attr("href","");
			$("#login_name").attr("href","login.html");
			$("#puthouse").attr("href","");
			alert_login();
		}
	});
}

function alert_login(){
	layui.use('layer', function(){
		  var layer = layui.layer;
		  layer.msg("您还未登录，请先登录");	  
		  
	});
	
}

function showhousedetail(title){
	  $.post("user/yes",function(value){
			if(value!=null && value!=''){
				window.location.href="detail.html?title="+escape(title);
			}else{
				alert_login();
			}
	  });
}	 
	