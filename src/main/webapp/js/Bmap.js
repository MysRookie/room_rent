/**
 * 百度地图加载 
 */

var map;
//var marker;
var point;
var local ;
var index=0;
var myGeo ;
var localmarker;
var pointArray ;

//创建和初始化地图函数：
function initMap(){
  createMap("衡阳");//创建地图
  setMapEvent();//设置地图事件
  addMapControl();//向地图添加控件
  addMapOverlay(); //向地图添加覆盖物
}
function createMap(addr){ 
  map = new BMap.Map("map");   // 初始化地图容器 
  point = new BMap.Point(112.69125,26.888519);  // 获取地图中心点位置   经纬度坐标
  map.centerAndZoom(point,11); //设置  位置  、 地图放大级别  
  map.setCurrentCity("衡阳");   // 设置当前所在城市  
  localmarker = new BMap.Marker(point,{    // 创建当前位置的 标注
	  // 指定Marker的icon属性为Symbol
	  icon: new BMap.Symbol(BMap_Symbol_SHAPE_POINT, {
	    scale: 2,//图标缩放大小
	    fillColor: "orange",//填充颜色
	    fillOpacity: 0.8//填充透明度
	  })
  }); 
  localmarker.addEventListener("click",function(){
	  addClickHandler();
  });
  local = new BMap.LocalSearch("衡阳",   
           { renderOptions:{map: map, autoViewport: true}});      
  searchNearby(addr); // 提供圆形区域检索  在特定结果点范围搜索 / 在某个特定地点附近搜索   
  myGeo = new BMap.Geocoder();
//   var indoorManager = new BMapLib.IndoorManager(map);  // 启用室内图
//   indoorManager.enableIndoor(); 
  bdGEO();  //初始化 map 显示所有 房源位置  
  //让所有点在视野范围内
}

function searchNearby(addr){
	 local.search(addr);
}

//初始化 map 显示所有 房源位置  
function bdGEO(){
	//console.log(addrArry);
	var add = addrArry[index];
	geocodeSearch(add);
	index++;
	//console.log(addrArry+''+addrArry.length);
}
// 地址解析 
function geocodeSearch(add){
	//alert(index + "---" + addrArry.length);
	if(index < addrArry.length){
		setTimeout(window.bdGEO,400);
	}
	//如果房源信息已全部加载 ，结束异步定时器 
	if(index == addrArry.length){
		clearTimeout(clearTimer);
	}
	myGeo.getPoint(add, function(point){
		//alert(add);	
		if (point) {
			//document.getElementById("result").innerHTML +=  index + "、" + add + ":" + point.lng + "," + point.lat + "</br>";
			Paddress = new BMap.Point(point.lng, point.lat);
			var marker = new BMap.Marker(point);
			map.addOverlay(marker);	
		}
	}, "衡阳市");
}	

//设置地图事件 有哪些 
function setMapEvent(){
  map.enableScrollWheelZoom();   //开启鼠标滚轮缩放
  map.enableKeyboard();
  map.enableDragging();
  map.enableDoubleClickZoom();
}
// 
function addClickHandler(target,window){
  target.addEventListener("click",function(){
    target.openInfoWindow(window);  
  });
}
//向地图添加覆盖物
function addMapOverlay(point){	
	map.addOverlay(localmarker);               // 将标注添加到地图中
	localmarker.setAnimation(BMAP_ANIMATION_BOUNCE); // 地标  跳动的动画	
	
		
}
//向地图添加控件
function addMapControl(){
	  var scaleControl = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
	  scaleControl.setUnit(BMAP_UNIT_IMPERIAL);  // 比例尺控件 
	  map.addControl(scaleControl);
	  var navControl = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
	  map.addControl(navControl);   // 平移缩放控件
	  var overviewControl = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:true});
	  map.addControl(overviewControl);   // 缩略图控件 	
	  
	  var stCtrl = new BMap.PanoramaControl(); //构造全景控件
	  stCtrl.setOffset(new BMap.Size(10, 10)); 
	  map.addControl(stCtrl);//添加全景控件
}



//初始化地图 并加载 全局房源信息覆盖物 
var clearTimer =setTimeout(window.initMap,200);