/**
 * 
 */


// 当前操作员 
var account=$(".layui-layout-right span").text();;
$(function(){
	findUnpassedNum();
});

// 查询 待审核的 记录数 
function findUnpassedNum(){
	$.post('../selectUnpassedNum',{pushstatus:'审核中'},function(data){
		if(data != null && data !=''){
			//var num ='<span class="layui-badge">'+data+'</span>';
			$("#badge-dot span").attr("class","layui-badge-dot");
			$("#badge-num span").attr("class","layui-badge");
			$("#badge-num span").html(data);
		}else{
			$("#badge-dot span").attr("class","");
			$("#badge-num span").attr("class","");
		}
	});
}
// 退出系统 
function loginOut(){
	$.get('../admin/loginOut',function(data){
		if(data=='1'){			
			window.location.href="/house/back/login.jsp";
			 setTimeout(function(){
				layui.use(['layer'], function(){				 
					  var layer = layui.layer; // 弹层
					  layer.msg("已成功退出系统");						  
				});			
			  },2000);			
		}		
	});
}

//查看房源信息
function findHouseInfo(){
	var str =' <div class="layui-tab layui-tab-card">'+
		'<ul class="layui-tab-title">'+
		'<li class="layui-this">已通过</li><li>未通过</li></ul>'+
		'<div class="layui-tab-content" style="height:100%;">'+
		'<div class="layui-tab-item layui-show"><table class="layui-hide" id="passed" lay-filter="passed"></table></div>'+
		'<div class="layui-tab-item "><table class="layui-hide" id="unpassed" lay-filter="unpassed"></table></div>'+
		'</div></div>    '; 
	$("#content").html(str);
	
	layui.use(['table','laypage','element','layer'], function(){
		  var table = layui.table; // 表格 
		  var laypage = layui.laypage; //分页
		  var element=layui.element; // 元素操作 
		  var layer = layui.layer; // 弹层 
		  var index = layer.load(0,{time:5000});	//加载层 最多等待5秒 	
		  layer.close(index); // 关闭加载层 
		  // 已通过 
		  table.render({
		    elem: '#passed',
		    url:'../showHouseInfo?pushstatus=passed',
		    toolbar: '#toolbarDemo', // 自定义的表头工具  
		    cellMinWidth:100, //全局定义常规单元格的最小宽度
		    page: true,  // 开启 分页
			jump:true,
		    limit:10,
		    limits:[10,15,20,30],//每页条数的选择项
		    height: 'full-230' , // //高度最大化减去差值
		    skin: 'row', // 列边框风格   line: 行边框风格 
		    even: true,  //开启隔行背景
		    response: {
		        statusName: 'code' //规定数据状态的字段名称，默认：code
		        ,statusCode: 0 //规定成功的状态码，默认：0
		        ,msgName: 'msg' //规定状态信息的字段名称，默认：msg
		        ,countName: 'count' //规定数据总数的字段名称，默认：count
		        ,dataName: 'data' //规定数据列表的字段名称，默认：data
		      },
		    cols: [[
			  {type: 'checkbox', fixed:'left',rowspan: 2}
		      ,{field:'hid', title:'编号', width:80, fixed: 'left', unresize: true, sort: true,rowspan: 2,templet:function(d){
		    	  return d.houseInfoPO.hid;}}
		      // templet: 自定义数据模板  注意：这里一定要被一层 <div></div> 包裹，否则无法读取到模板 
		      ,{field:'uname', title: '所有者',rowspan: 2,templet:'#usernameStyle'} 	      
		      ,{align: 'center', title: '房源地址', colspan: 3} //colspan即横跨的单元格数，这种情况下不用设置field和width
		      ,{field:'title', width:80, title: '标题',rowspan: 2,templet:function(d){
		    	  return d.houseInfoPO.title; }}
		      ,{field:'discrible', title: '房屋描述',rowspan: 2,templet:function(d){
		    	  return d.houseInfoPO.discrible;}}
		      ,{field:'htype', title: '出租类型',rowspan: 2,templet:function(d){
		    	  return d.houseInfoPO.htype;}}
		      ,{field:'hroom', title: '厅室',rowspan: 2,templet:function(d){
		    	  return d.houseInfoPO.hroom;}} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
		      ,{field:'floor', title: '楼层',rowspan: 2,templet:function(d){
		    	  return d.houseInfoPO.floor;}}
		      ,{field:'area', title: '面积',sort: true,rowspan: 2,templet:function(d){
		    	  return d.houseInfoPO.area;}}
		      ,{field:'infrastr', title: '基本设施',rowspan: 2,templet:function(d){
		    	  return d.houseInfoPO.infrastr;}}
		      ,{field:'pushdate', title: '审核时间',sort: true,rowspan: 2,templet:function(d){
		    	  return d.houseInfoPO.pushdate;}}
		      ,{field:'account', title: '审核员',style:'background-color: #009688; color: #fff;',rowspan: 2,templet:function(d){
		    	  return d.houseInfoPO.account;}}
		      ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150,rowspan: 2, align:'center'}
		      ], [
					{field: 'province', title: '省', width:80,templet:function(d){
						  return d.houseInfoPO.province;}}
					,{field: 'city', title: '市', width:80,templet:function(d){
						  return d.houseInfoPO.city;}}
					,{field: 'district', title: '街道', width:80,templet:function(d){
						  return d.houseInfoPO.district;}}
		    ]]
		    ,text: {
		    	    none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
		    	  }
		    ,initSort: {
		    	field: 'hid'//排序字段，对应 cols 设定的各字段名
			    ,type: 'desc'  //排序方式  asc: 升序、desc: 降序、null: 默认排序
				,templet:function(d){
					return d.houseInfoPO.hid;}
		    	} 
		    
		    
		  });
		  // 未通过 
		  table.render({
			    elem: '#unpassed',
			    url:'../showHouseInfo?pushstatus=unpassed',
			    toolbar: '#toolbarDemo',
			    cellMinWidth:100, //全局定义常规单元格的最小宽度
			    page: true,
			    limit:10,
			    limits:[10,15,20,50],//每页条数的选择项
			    height: 'full-230' , // //高度最大化减去差值
			    skin: 'row', // 列边框风格   line: 行边框风格 
			    even: true,  //开启隔行背景
			    response: {
			        statusName: 'code' //规定数据状态的字段名称，默认：code
			        ,statusCode: 0 //规定成功的状态码，默认：0
			        ,msgName: 'msg' //规定状态信息的字段名称，默认：msg
			        ,countName: 'count' //规定数据总数的字段名称，默认：count
			        ,dataName: 'data' //规定数据列表的字段名称，默认：data
			      },			   
			    cols: [[
			      {type: 'checkbox', fixed:'left',rowspan: 2}
			      ,{field:'hid', title: '编号', width:80, fixed:'left',rowspan:2, unresize: true, sort: true,templet:function(d){
			    	  return d.houseInfoPO.hid;}}
			      ,{field:'uname', title: '所有者',rowspan: 2,templet:'#usernameStyle'}
			      ,{align: 'center', width:80, title: '房源位置',colspan: 3}
			      ,{field:'title', width:80, title: '标题',rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.title;}}
			      ,{field:'discrible', title: '房屋描述',rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.discrible;}}
			      ,{field:'htype', title: '出租类型',rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.htype;}}
			      ,{field:'hroom', title: '厅室', width: '20%', minWidth: 60,rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.hroom;}} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
			      ,{field:'floor', title: '楼层',rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.floor;}}
			      ,{field:'area', title: '面积',sort: true,rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.area;}}
			      ,{field:'infrastr', title: '基本设施',rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.infrastr;}}
			      ,{field:'reason', title: '原因描述',rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.reason;}}
			      ,{field:'account', title: '审核员',style:'background-color: #009688; color: #fff;',rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.account;}}
			      ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150,rowspan: 2, align:'center'}
			      ], [
						{field: 'province', title: '省', width:80,templet:function(d){
					    	  return d.houseInfoPO.province;}}
				        ,{field: 'city', title: '市', width:80,templet:function(d){
					    	  return d.houseInfoPO.city;}}
				        ,{field: 'district', title: '街道', width:80,templet:function(d){
					    	  return d.houseInfoPO.district;}}

			    ]]
			      ,text: {
			    	    none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
			    	  }
			    ,initSort: {
			    	field: 'hid'//排序字段，对应 cols 设定的各字段名
			    	,type: 'desc'  //排序方式  asc: 升序、desc: 降序、null: 默认排序
					,templet:function(d){
						return d.houseInfoPO.hid;}		 	 
			    	}
			  });
		  
		//监听数据表格行级工具    已通过的 
		  table.on('tool(passed)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			console.log(obj.data);
			var data = obj.data; //获得当前行数据
		    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		    var tr = obj.tr; //获得当前行 tr 的DOM对象
		   	//console.log(tr);
		    if(layEvent === 'repush'){ //重新审核
		    	layer.msg('此功能尚未开放',{icon :5});
		      //do somehing
		      //console.log(tr);
//               $.post('',{status:'repush',housuInfo:tr},function(data){
//             	  if(data ==true){
// 		        		 layer.msg('操作成功',{icon : 6});
// 		          }else{
// 		        		layer.msg('操作失败',{icon : 5});
// 		          }
// 		      });
		    } else if(layEvent === 'del'){ //通过
		      layer.confirm('确定删除记录麽?', function(index){
		        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
		        layer.close(index);
		        //向服务端发送删除指令
		        $.post('../deleteHouse',{hid:data.houseInfoPO.hid},function(data){
		        	if(data ==true){
		        		 layer.msg('操作成功',{icon : 6});
		        	}else{
		        		layer.msg('操作失败',{icon : 5});
		        	}
		        });
		     
		      });
		    }
		  });
		 //头工具栏事件     已通过的 
			 table.on('toolbar(passed)', function(obj){
			    var checkStatus = table.checkStatus(obj.config.id);
			    switch(obj.event){
			      case 'getCheckData':
			        var data = checkStatus.data;
			        if(data.length==0){
			        	layer.msg('抱歉，未选中任何数据');
			        }else{
			        	//var infoData =JSON.stringify(data)[0];
			        	//layer.msg('用户名'+infoData.uname+'\n 真实姓名：'+infoData.firstname);
			        	layer.alert(JSON.stringify(data));
			        }			        
			      break;
			      case 'getCheckLength':
			        var data = checkStatus.data;
			        layer.msg('选中了：'+ data.length + ' 个');
			      break;
			      case 'isAll':
			        layer.msg(checkStatus.isAll ? '全选': '未全选');
			      break;
			    };
			  });
		
		
		
		//监听  数据表格行级工具  未通过的 
		  table.on('tool(unpassed)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			//console.log(obj.data);
			var data = obj.data; //获得当前行数据
		    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		    var tr = obj.tr; //获得当前行 tr 的DOM对象
		   	//console.log(tr);
		    if(layEvent === 'repush'){ //重新审核 
		      //do somehing
		      //console.log(tr);
		      //layer.msg('还功能暂未开放 ',{icon : 6});
              $.post('',{pushstatus:'',hid:data.houseInfoPO.hid},function(data){
            	  if(data ==true){
		        		 layer.msg('操作成功',{icon : 6});
		          }else{
		        		layer.msg('操作失败',{icon : 5});
		          }
		      });
		    } else if(layEvent === 'del'){ // 删除 
		      layer.confirm('确定删除麽?', function(index){
		    	//layer.msg('还功能暂未开放 ',{icon : 6});  
		        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
		        layer.close(index);		        
		        //向服务端发送删除指令
		        $.post('../deleteHouse',{hid:data.houseInfoPO.hid},function(data){
		        	if(data ==true){
		        		 layer.msg('操作成功',{icon : 6});
		        	}else{
		        		layer.msg('操作失败',{icon : 5});
		        	}
		        });
		 
		      });
		    }
		  }); 
		  //头工具栏事件     未通过的 
			 table.on('toolbar(unpassed)', function(obj){
			    var checkStatus = table.checkStatus(obj.config.id);
			    switch(obj.event){
			      case 'getCheckData':
			        var data = checkStatus.data;
			        if(data.length==0){
			        	layer.msg('抱歉，未选中任何数据');
			        }else{
			        	//var infoData =JSON.stringify(data)[0];
			        	//layer.msg('用户名'+infoData.uname+'\n 真实姓名：'+infoData.firstname);
			        	layer.alert(JSON.stringify(data));
			        }	
			      break;
			      case 'getCheckLength':
			        var data = checkStatus.data;
			        layer.msg('选中了：'+ data.length + ' 个');
			      break;
			      case 'isAll':
			        layer.msg(checkStatus.isAll ? '全选': '未全选');
			      break;
			    };
			  });
		
		
	});	
}


//房源信息审核 
function verifyHouseInfo(){
	//console.log(typeof(account) +'==='+account);
	var str =' <div class="layui-tab layui-tab-card">'+
	'<ul class="layui-tab-title">'+
	'<li class="layui-this">待审核</li></ul>'+
	'<div class="layui-tab-content" style="height:100%;">'+
	'<div class="layui-tab-item layui-show"><table class="layui-hide" id="unVerify" lay-filter="unVerify"></table></div>'+
	'</div></div>    '; 
    $("#content").html(str);

	layui.use(['table','laypage','element','layer'], function(){
		  var table = layui.table; // 表格 
		  var laypage = layui.laypage; //分页
		  var element=layui.element; // 元素操作 
		  var layer = layui.layer; // 弹层
		  var index = layer.load(0,{time:5000});	//加载层 最多等待5秒 	
		  layer.close(index); // 关闭加载层 
		  // 待审核   表格加载  
		  table.render({
			    elem: '#unVerify',
			    url:'../showHouseInfo?pushstatus=passing',
			    toolbar: '#toolbarDemo',
			    cellMinWidth:100, //全局定义常规单元格的最小宽度
			    page: true,
			    limit:10,
				limits:[10,15,20,50],//每页条数的选择项
			    height: 'full-230' , // //高度最大化减去差值
			    skin: 'row', // 列边框风格   line: 行边框风格 
			    even: true,  //开启隔行背景
			    response: {    //  将后台返回的任意数据解析为 table 类型的 
			        statusName: 'code' //规定数据状态的字段名称，默认：code
			        ,statusCode: 0 //规定成功的状态码，默认：0
			        ,msgName: 'msg' //规定状态信息的字段名称，默认：msg
			        ,countName: 'count' //规定数据总数的字段名称，默认：count
			        ,dataName: 'data' //规定数据列表的字段名称，默认：data
			      },
			    cols: [[
			       {type: 'checkbox', fixed: 'left',rowspan: 2}
			      ,{field:'hid', title: '编号', width:80, fixed: 'left', unresize: true, sort: true,rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.hid;}}
			      ,{field:'uname', title: '所有者',rowspan: 2,templet:'#usernameStyle'}
			      ,{align: 'center', width:80, title: '房源位置',colspan: 3}
			      ,{field:'title', width:80, title: '标题',rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.title;}}
			      ,{field:'discrible', title: '房屋描述',rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.discrible;}}
			      ,{field:'htype', title: '出租类型',rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.htype;}}
			      ,{field:'hroom', title: '厅室', width: '20%', minWidth: 60,rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.hroom;}} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
			      ,{field:'floor', title: '楼层',rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.floor;}}
			      ,{field:'area', title: '面积',sort: true,rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.area;}}
			      ,{field:'infrastr', title: '基本设施',rowspan: 2,templet:function(d){
			    	  return d.houseInfoPO.infrastr;}}
			      ,{fixed: 'right', title:'操作', toolbar:'#barUnVerifyHouseInfo', width:150,rowspan: 2, align:'center'}
			      ], [
				        {field: 'province', title: '省', width:80,templet:function(d){
					    	  return d.houseInfoPO.province;}}
				        ,{field: 'city', title: '市', width:80,templet:function(d){
					    	  return d.houseInfoPO.city;}}
				        ,{field: 'district', title: '街道', width:80,templet:function(d){
					    	  return d.houseInfoPO.district;}}
			    ]]
			      ,text:{
			    	    none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
			    	  }
			    ,initSort: {
			    	field: 'hid'//排序字段，对应 cols 设定的各字段名
			    	  ,type: 'desc'  //排序方式  asc: 升序、desc: 降序、null: 默认排序
			    	  ,templet:function(d){
				    	  return d.houseInfoPO.hid;}
			    	}
			  });
		  
		//监听数据表格行级工具   待审核的 
		  table.on('tool(unVerify)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			//console.log(obj.data);
			var data = obj.data; //获得当前行数据
		    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		    var tr = obj.tr; //获得当前行 tr 的DOM对象
		    if(layEvent === 'unpass'){ //不通过		     
		      layer.prompt({title: '请添加原因后确认审核状态', formType: 2}, function(text, index){
		    	    //向服务端发送指令
		    	    $.post('../updateHouseInfo',{pushstatus:'未通过',hid:data.houseInfoPO.hid,reason:text,account:account},function(data){
		    			  // console.log(data);
		            	  if(data==true){
				        		 layer.msg('操作成功',{icon : 6});
				          }else{
				        		layer.msg('操作失败',{icon : 5});
				          }
				      });
		              obj.del();
		              layer.close(index);
		              verifyHouseInfo();
		              findUnpassedNum();     
		      });
		    } else if(layEvent === 'passed'){ //通过
		      layer.confirm('确定通过麽?', function(index){
		    	  //向服务端发送指令
		    	  $.post('../updateHouseInfo',{pushstatus:'已通过',hid:data.houseInfoPO.hid,account:account},function(data){
			         console.log(data);	
		    		 if(data==true){
			        		 layer.msg('操作成功',{icon : 6});
			        	}else{
			        		layer.msg('操作失败',{icon : 5});
			        	}
			      });
			      obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
			      layer.close(index);
			      table.reload('unVerify',{});
			      findUnpassedNum();
		      });
		    }
		  });
		//头工具栏事件     待审核的 
		  table.on('toolbar(unVerify)', function(obj){
			    var checkStatus = table.checkStatus(obj.config.id);
			    var data = checkStatus.data;
			    switch(obj.event){
			      case 'getCheckData':			       
			        if(data.length==0){
			        	layer.msg('抱歉，未选中任何数据');
			        }else{
			        	//var infoData =JSON.stringify(data)[0];
			        	//layer.msg('用户名'+infoData.uname+'\n 真实姓名：'+infoData.firstname);
			        	layer.alert(JSON.stringify(data));
			        }	
			      break;
			      case 'getCheckLength':
			        var data = checkStatus.data;
			        layer.msg('选中了：'+ data.length + ' 个');
			      break;
			      case 'isAll':
			        layer.msg(checkStatus.isAll ? '全选': '未全选');
			      break;
			      case 'passAll':
			    	  //layer.msg("into passAll"+obj.config.id);
			    	  if(data.length==0){
				        	layer.msg('抱歉，未选中任何数据');
				        	break;
				      }
			    	  var hidList=[];
			    	  //循环获取选中的hid 
			    	  data.forEach(function(curr,i){
			    		  //console.log(curr+'\n'+curr.houseInfoPO.hid+'\n'+i);
			    		  hidList.push(curr.houseInfoPO.hid);
			    	  });			    	  
			    	  layer.confirm('确定全部通过麽?', function(index){	
			    		  $.ajax({
			    			  url:'../batchUpdateHouseInfoPushstatus',
						      type: "post",						      
						      data: "hid="+hidList,
						      cache: false,
						      success:function(flag){ 
						    	 if(flag==true){	
					    			    table.reload('unVerify',{});
						        		layer.msg('操作成功',{icon : 6});
						        		findUnpassedNum(); 
						         }else{
						        	    layer.msg('操作失败',{icon : 5});
						         }
						      }
			    		  });
			    		 layer.close(index);			    		  
			    	  });			    	  	    	  
			      break;
			    };
			  }); 	   	 
	});	  
}

//查看用户信息 
function findUserInfo(){
	var str = '<table class="layui-hide" id="userInfo" lay-filter="userInfo"></table>';	
	$("#content").html(str);
	layui.use(['table','laypage','element','layer'], function(){
		  var table = layui.table; // 表格 
		  var laypage = layui.laypage; //分页
		  var element=layui.element; // 元素操作 
		  var layer = layui.layer; // 弹层 
		  var index = layer.load(0,{time:5000});	//加载层 最多等待5秒 	
		  layer.close(index); // 关闭加载层 
		  table.render({
		    elem: '#userInfo',    // 操作的表源 
		    url:'../user/selectUser',  //数据接口 
		    toolbar: 'default', //  使用 表头工具  可自定义 
		    cellMinWidth:100, //全局定义常规单元格的最小宽度		   
		    page: true ,   // 开启分页  
		    limit:10,
		    limits:[10,15,20,50],//每页条数的选择项
		    height: 'full-170' , // //高度最大化减去差值
		    skin: 'row', // 列边框风格   line: 行边框风格 
		    even: true,  //开启隔行背景
		    response: {
		        statusName: 'code' //规定数据状态的字段名称，默认：code
		        ,statusCode: 0 //规定成功的状态码，默认：0
		        ,msgName: 'msg' //规定状态信息的字段名称，默认：msg
		        ,countName: 'count' //规定数据总数的字段名称，默认：count
		        ,dataName: 'data' //规定数据列表的字段名称，默认：data
		      },
		    cols: [[
		      {type: 'checkbox', fixed: 'left'}
		      ,{field:'uid', width:40, title: 'ID',fixed: 'left', unresize: true, sort: true}
		      ,{field:'uname', title: '用户名'}
		      ,{field:'sex',title: '性别',templet:'#sexStyle'}
		      ,{field:'idcard', width:120, title: '身份证号码'}
		      ,{field:'tel', title: '联系电话'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
		      ,{field:'email', title: '邮箱'}
		      ,{field:'utype', title: '用户类型'}
		      ,{field:'ugrade', title: '用户等级', sort: true}
		      ,{fixed: 'right', title:'操作', toolbar: '#barUserInfoEdit', width:130, align:'center'}
		    ]]
		  	
		  ,text: {
	    	    none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
	    	  }
	      ,initSort: {
	    	  field: 'uid' //排序字段，对应 cols 设定的各字段名
	    	  ,type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
	    	}
		  });
		  
		//监听行工具事件       用户管理 
		  table.on('tool(userInfo)', function(obj){
		    var data = obj.data;
		    //console.log(obj)
		    if(obj.event === 'del'){   // 用户信息删除根据id  
		      layer.confirm('主人，确定删除行么?', function(index){  
		        obj.del();  // 删除表格数据 
		        layer.close(index);
		        //alert(index);
		        //向服务器发送请求
		        // 将数据 解析为 json格式的字符串  
		        $.ajax({
			    	 url:'../user/delectUserInfo',
			    	 type: "post",
			    	 data:{"uid":data.uid},
			    	 cache: false,
			    	 success:function(flag){
					     if(flag == true){
					        	layer.msg("主人，删除成功哦.",{
					        	icon : 6, //图标 
								time: 2000, //2s后自动关闭				
					            });
					        	table.reload('userInfo',{});
					     }else{
					        layer.msg("对不起，删除失败，网络异常请检查.",{
					        	icon : 5, //图标 
								time: 2000, //2s后自动关闭				
					        });
					     }
			    	 } 
			     });
		      });
		    } else if(obj.event === 'edit'){  // 用户信息修改 
		    	layer.msg("不可随意修改用户数据，请三思...");
// 		      layer.prompt({
// 		        formType: 2
// 		        ,value: data.content
// 		      }, function(value, index){
// 		        obj.update({
// 		        	content: value
// 		        });
// 		        $.post('../user/updateUserInfo',{},function(flag){
// 		        	if(flag == true){
// 		        		 layer.msg("修改成功.",{
// 		        			 icon : 6, //图标 
// 					  		 time: 2000, //2s后自动关闭				
// 		        		 });
// 		        	}else{
// 		        		 layer.msg("修改失败，网络异常请检查.",{
// 		        			 icon : 5, //图标 
// 					  		 time: 2000, //2s后自动关闭				
// 		        		 });
// 		        	}  
// 		        });
// 		        layer.close(index);
// 		      });
		    }
		  });
		  
		//监听头工具栏事件  用户信息 
		  table.on('toolbar(userInfo)', function(obj){
		    var checkStatus = table.checkStatus(obj.config.id)
		    ,data = checkStatus.data; //获取选中的数据
		    switch(obj.event){
		      case 'add':
		    	  layer.msg("主人的操作我无法理解哦.");
		      break;
		      case 'update':
		        if(data.length === 0){
		          layer.msg('请选择一行');
		        } else if(data.length > 1){
		          layer.msg('只能同时编辑一个');
		        } else {
		          layer.msg("主人您要编辑的数据："+checkStatus.data[0]);
		          //layer.alert('编辑 [id]：'+ checkStatus.data[0].id);		          
// 		        	$.post('../user/updateUserInfo',{},function(flag){
// 			        	if(flag == true){
// 			        		 layer.msg("主人，修改成功.",{
// 			        			 icon : 6, //图标 
// 						  		 time: 2000, //2s后自动关闭				
// 			        		 });
// 			        	}else{
// 			        		 layer.msg("对不起，修改失败，网络异常请检查.",{
// 			        			 icon : 5, //图标 
// 						  		 time: 2000, //2s后自动关闭				
// 			        		 });
// 			        	}  
// 			        });	
		        }
		      break;
		      case 'delete':
		        if(data.length === 0){
		          layer.msg('请至少选择一行');
		        } else {		    
				     //layer.msg(data);
				     // 将数据 解析为 json格式的字符串  
				     var jsondata=JSON.stringify(data);  
				     $.ajax({
				    	 url:'../user/batchDeleteUserInfo',
				    	 type: "post",
				    	 dataType:"json",
				    	 contentType:"application/json; charset=utf-8",
				    	 data:jsondata,
				    	 cache: false,
				    	 success:function(flag){
						     if(flag == true){
						        	layer.msg("主人，删除成功哦.",{
						        	icon : 6, //图标 
									time: 2000, //2s后自动关闭				
						            });
						        	table.reload('userInfo',{});
						     }else{
						        layer.msg("对不起，删除失败，网络异常请检查.",{
						        	icon : 5, //图标 
									time: 2000, //2s后自动关闭				
						        });
						     }
				    	 } 
				     });				        
		        }
		      break;
		    };
		  });
		  
	});	
	
}

//发布通知信息  
function releaseComInfo(){	
	//window.location.href='index.jsp';
	//$("#content").html(str);
	layui.use(['element','layer','form'], function(){
		var form = layui.form; //表单 
		var element=layui.element; // 元素操作 
	    var layer = layui.layer; // 弹层 
		var index = layer.load(0,{time:5000});	//加载层 最多等待3秒 	
		layer.close(index); // 关闭加载层 
		layer.open({
			  type: 1, 
			  content: $('#addcomm'),//这里content是一个div，'no':不想让iframe出现滚动条
			  area: ['60%', '70%'],
			  skin:'layui-layer-molv',
			  anim: 2,
			  //btn: ['确认发布', '取消发布'],			 
			  cancel: function(index, layero){ 
				  if(confirm('未发布的信息不会进行保存，确定要关闭么？')){ //只有当点击confirm框的确定时，该层才会关闭
				    layer.close(index)
				  }
				  return false; 
			  },
			  success:function(layero, index){
				  //添加通知信息 form提交
				  $("#submitCommInfo").on("click",function(){
					  console.log(layero+'==='+index);
					  var comType=$("select[ name='comType'] option:selected ").val();
					  var desc=$(".layui-textarea").val();
					  if(desc==''){
							layer.msg('请先添加要发布的内容...');							
						}else{
							$.post('../insertInfo',{info_type:comType,content:desc},function(data){
								if(data>0){
									layer.msg('恭喜，发布成功！', {
										icon : 6,
										time: 2000 //2s后自动关闭
									});
									layer.close(index);
								}else{
									layer.msg('抱歉，发布失败！请检查信息是否齐全', {
							  			icon : 5, //图标 
							  			time: 2000 //2s后自动关闭
							  	    });
								}				
							});  					
						}	
				  });

			  },
			  yes: function(index, layero){
				    layer.close(index); //如果设定了yes回调，需进行手工关闭
				    releaseComInfo();
			  }
			  
		});			
		
	});
	
}
//查看 通知信息 
function findComInfo(){
var str = '<table class="layui-hide" id="commInfo" lay-filter="commInfo"></table>';	
	$("#content").html(str);
	layui.use(['table','laypage','element','layer'], function(){
		  var table = layui.table; // 表格 
		  var laypage = layui.laypage; //分页
		  var element=layui.element; // 元素操作 
		  var layer = layui.layer; // 弹层 	
		  var index = layer.load(0,{time:5000});	//加载层 最多等待5秒 	
		  layer.close(index); // 关闭加载层 
		  table.render({
		    elem: '#commInfo',
		    url:'../selectInformation',
		    toolbar: 'default',
		    cellMinWidth:100, //全局定义常规单元格的最小宽度
		    page:true,
		    limit:10,
		    limits:[5,10,15,20],//每页条数的选择项
		    height: 'full-170' , // //高度最大化减去差值
		    skin: 'row', // 列边框风格   line: 行边框风格 
		    even: true,  //开启隔行背景
		    response: {
		        statusName: 'code' //规定数据状态的字段名称，默认：code
		        ,statusCode: 0 //规定成功的状态码，默认：0
		        ,msgName: 'msg' //规定状态信息的字段名称，默认：msg
		        ,countName: 'count' //规定数据总数的字段名称，默认：count
		        ,dataName: 'data' //规定数据列表的字段名称，默认：data
		      },
		    cols: [[
		      {type: 'checkbox', fixed: 'left'}
		      ,{field:'fid', width:40, title: 'ID',fixed: 'left', unresize: true, sort: true}
		      ,{field:'content', title: '通知内容'}
		      ,{field:'info_date',title: '发布时间'}
		      ,{field:'info_type',title: '通知类型'}
		      ,{fixed: 'right', title:'操作', toolbar: '#barUserInfoEdit', width:130, align:'center'}
		    ]]
		  ,text: {
	    	    none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
	    	  }
	      ,initSort: {
	    	  field: 'fid' //排序字段，对应 cols 设定的各字段名
	    	  ,type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
	    	}
		  });
		  
		  //监听行工具事件
		  table.on('tool(commInfo)', function(obj){
		    var data = obj.data;
		    //console.log(obj)
		    if(obj.event === 'del'){   // 通知内容删除 
		      layer.confirm('真的删除行么', function(index){  
		        obj.del();
		        layer.close(index);
		        //alert(index);
		        $.post('../deleteInfo',{fid:data.fid}, function(flag){
		        	if(flag == true){
		        		 layer.msg("删除成功.",{
		        			 icon : 6, //图标 
					  		 time: 2000, //2s后自动关闭				
		        		 });		 
		        	}else{
		        		 layer.msg("删除失败，网络异常请检查.",{
		        			 icon : 5, //图标 
					  		 time: 2000, //2s后自动关闭				
		        		 });
		        	}
		        });
		        
		      });
		    } else if(obj.event === 'edit'){  // 通知内容修改 
		      layer.prompt({
		        formType: 2
		        ,value: data.content
		      }, function(value, index){
		        obj.update({
		        	content: value
		        });
		        $.post('../updateInfo',{content:value,fid:data.fid},function(flag){
		        	if(flag == true){
		        		 layer.msg("修改成功.",{
		        			 icon : 6, //图标 
					  		 time: 2000, //2s后自动关闭				
		        		 });
		        	}else{
		        		 layer.msg("修改失败，网络异常请检查.",{
		        			 icon : 5, //图标 
					  		 time: 2000, //2s后自动关闭				
		        		 });
		        	}  
		        });
		        layer.close(index);
		      });
		    }
		  });
		  
		//监听头工具栏事件  通知信息 
		  table.on('toolbar(commInfo)', function(obj){
		    var checkStatus = table.checkStatus(obj.config.id)
		    ,data = checkStatus.data; //获取选中的数据
		    switch(obj.event){
		      case 'add':
		    	  releaseComInfo();
		      break;
		      case 'update':
		        if(data.length === 0){
		          layer.msg('请选择一行');
		        } else if(data.length > 1){
		          layer.msg('只能同时编辑一个');
		        } else {
		          //layer.alert('编辑 [id]：'+ checkStatus.data[0].id);		          
		        	$.post('../updateInfo',{content:data[0].content,fid:data[0].fid},function(flag){
			        	if(flag == true){
			        		 layer.msg("修改成功.",{
			        			 icon : 6, //图标 
						  		 time: 2000, //2s后自动关闭				
			        		 });
			        	}else{
			        		 layer.msg("修改失败，网络异常请检查.",{
			        			 icon : 5, //图标 
						  		 time: 2000, //2s后自动关闭				
			        		 });
			        	}  
			        });	
		        }
		      break;
		      case 'delete':
		        if(data.length === 0){
		          layer.msg('请至少选择一行');
		        } else {		    
				     //layer.msg(data);
				     // 将数据 解析为 json格式的字符串  
				     var jsondata=JSON.stringify(data);  
				     $.ajax({
				    	 url:'../batchDeleteInfo',
				    	 type: "post",
				    	 dataType:"json",
				    	 contentType:"application/json; charset=utf-8",
				    	 data:jsondata,
				    	 cache: false,
				    	 success:function(flag){
						     if(flag == true){
						        	layer.msg("批量删除成功.",{
						        	icon : 6, //图标 
									time: 2000, //2s后自动关闭				
						            });
						        	findComInfo();
						     }else{
						        layer.msg("批量删除失败，网络异常请检查.",{
						        	icon : 5, //图标 
									time: 2000, //2s后自动关闭				
						        });
						     }
				    	 } 
				     });				        
		        }
		      break;
		    };
		  });
		  
		  //分页
		  laypage.render({
		    elem: '#commInfo' //分页容器的id
		    ,skin: '#FF5722' //自定义选中色值
		    ,skip: true //开启跳页
		    ,layout: ['limit', 'prev', 'page', 'next']
		    ,prev: '<em> << </em>'
		    ,next: '<em> >> </em>'
		    ,jump: function(obj, first){
		      if(!first){
		        layer.msg('第'+ obj.curr +'页', {offset: 'b'});
		      }
		    }
		  });
		  
		  
		  

	});
	
	 
}

//添加管理员  
function addAdmin(){
	alert("抱歉，此功能暂未开放，敬请期待...");
// 	layui.use(['laypage','element','layer'], function(){
// 		var form = layui.form; //表单 
// 		var element=layui.element; // 元素操作 
// 	    var layer = layui.layer; // 弹层 
// 		var index = layer.load(0,{time:3000});
// 		layer.close(index); // 关闭加载层 
// 		layer.open({
// 			  type: 1,
// 			  content: $('#addAdmin'),//这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
// 			  area: ['40%', '50%'],
// 			  skin:'layui-layer-molv',
// 			  anim: 5,
// 			  btn: ['确认添加', '取消'],	
// 			  yes:function( index, layero){
// 				 insertAdmin(index,layero);
// 			  },
// 			  shadeClose:true,
// 			  btn2:function( index, layero){
// 				  if(confirm("信息还未添加，确认关闭？")){
// 					  layer.close(index);
// 					  layer.closeAll();
					  
// 				  }
// 				return false;
// 			  },
// 			  cancel:function( index, layero){
// 				  if(confirm("信息还未添加，确认关闭？")){
// 					  layer.close(index);
					  
// 				  }
// 				return false;
// 			  }
// 			});
// 	});
// 	layer.open({
// 		  type: 1,
// 		  content: $('#id') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
// 		});	
}
// insertAdmin();
function insertAdmin(index,layero){
	 
}

//安全设置 
function safeManage(){
	alert("抱歉，此功能暂未开放，敬请期待...");
}

// 用户反馈 
function userFeedback(){
	alert("抱歉，此功能暂未开放，敬请期待...");
}

//消息 管理 
function infoManage(){
	alert("抱歉，此功能暂未开放，敬请期待...");
}

//权限控制 
function limitControl(){
	alert("抱歉，此功能暂未开放，敬请期待...");
}
// 系统维护 
function sysManager(){
	alert("抱歉，此功能暂未开放，敬请期待...");
}


//JavaScript代码区域  插件架加载 
layui.use(['element','form','layer','laydate','carousel'], function(){
  var laydate = layui.laydate; // 日历 
  var form = layui.form; //表单 
  var element=layui.element; // 元素操作 
  var layer = layui.layer; // 弹层 
  var carousel = layui.carousel; // 轮播实例 
  //console.log(laydate);
  laydate.render({
	    elem:'#show_date',
	    position: 'static',
	    calendar: true //农历 
	});
  //建造实例
  carousel.render({
    elem: '#carouselphoto'
    ,width: '80%' //设置容器宽度
    ,height:'70%' // 高度 
    ,arrow: 'always' //始终显示箭头
    //,anim: 'updown' //切换动画方式
    
  });
  

	 
  
});


