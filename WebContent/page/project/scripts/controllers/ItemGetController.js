var index;
function hideUlst(){
	var idom=document.getElementById("name_div");
	if(idom.style.display=="none"){
		document.getElementById("li_show_hide1").style.backgroundImage="url(page/project/images/menuItem2.png)";
		idom.style.display="block";
	}else{
		document.getElementById("li_show_hide1").style.backgroundImage="url(page/project/images/menuItem1.png)";
		idom.style.display = 'none';
		
	}
	
}
function hideUls(){
	var idom=document.getElementById("type_div");
	if(idom.style.display=="none"){
		document.getElementById("li_show_hide2").style.backgroundImage="url(page/project/images/menuItem2.png)";
		idom.style.display="block";
	}else{
		document.getElementById("li_show_hide2").style.backgroundImage="url(page/project/images/menuItem1.png)";
		idom.style.display = 'none';
	}
	
}
function hideUl(){
	var idom=document.getElementById("state_div");
	if(idom.style.display=="none"){
		document.getElementById("li_show_hide3").style.backgroundImage="url(page/project/images/menuItem2.png)";
		idom.style.display="block";
	}else{
		idom.style.display = 'none';
		document.getElementById("li_show_hide3").style.backgroundImage="url(page/project/images/menuItem1.png)";
	}
	
}
function setProject(index){
	index.style.color=="red";
	var test=index.value;
}
//////////////////////////////////////


//////////
function ItemGetController($scope,ItemGetService) {
	console.log("载入ItemGetController");
	var pageSize=10;
	var pageIndex=1;
	var itemGet="";
	var project="";
	$scope.name=null;
	$scope.state=null;
	$scope.projectId=null;
	$scope.itemGetList="";
	var item_temp1="";
	var item_temp2="";
	var item_temp3="";
	$scope.itemNameList=["风管","桥架","弯头"];
	$scope.itemState=["初始化","出库","安装","完成"];
	 ////////////////////////预制化构件列表分页获取
	 $scope.getItemGetList = function(pageSize,pageIndex,itemGet) {
		 var content=document.getElementById("search_input").value;
		 if($scope.projectId!=null && $scope.projectId!=undefined){
			 itemGet+= "projectId=" + $scope.projectId;
		 }
		 if($scope.name!=null && $scope.name!=undefined){
			 itemGet+= "&name=" + $scope.name;
		 }
		 if($scope.state!=null && $scope.state!=undefined){
			 itemGet+= "&state=" + $scope.state;
		 }
		 ItemGetService.getItemGetList(pageSize,pageIndex,itemGet,content).then(function (result){
		  	  $scope.itemGetList = result.data;
		      $scope.currentPage = result.currentPage;
		      $scope.totalPage = result.totalPage;
		      $scope.itemGetPage($scope.totalPage,$scope.currentPage);
		  });
	  };

	
	////////预制化构建分页回调函数
	  $scope.itemGetPage = function(iPageCount,iCurrent) {
		  $("#itemsPageCode").remove();
		  $("#table-buton112").append("<div id=\"itemsPageCode\"></div>");
		  $("#itemsPageCode").createPage({

		      pageCount:iPageCount,

		      current:iCurrent,

		      backFn:function(p){
		    	  $scope.getItemGetList(pageSize,p,itemGet);
		      }
		  });
	  };
	  ///////分页获取项目列表
	$scope.getProjectLists = function(pageSize,pageIndex,project) {
		ItemGetService.getProjectLists(pageSize,pageIndex,project).then(function (result){
		  	  $scope.projectLists = result.data;
		  });
	};
	/////初始化获取问题列表
    $scope.getItemGetList(pageSize,pageIndex,itemGet);
    $scope.getProjectLists(pageSize,pageIndex,project);
    $scope.findItem= function(){
    	  $scope.getItemGetList(pageSize,pageIndex,itemGet);
    };
    $scope.setProjectName = function(temp,index){
    	$scope.projectId=temp;
    	
		if(item_temp1!==index && item_temp1!==""){
			var test=document.getElementById("item"+item_temp1);
        	test.style.color="";
		}else{
			item_temp1=index;
        	var tests=document.getElementById("item"+index);
        	tests.style.color="red";
        	$scope.itemGetList="";
		}
		item_temp1=index;
    	var tests=document.getElementById("item"+index);
    	tests.style.color="red";
    	$scope.itemGetList="";	
    	$scope.getItemGetList(pageSize,pageIndex,itemGet);
    }
    $scope.setType = function(temp,index){
    	$scope.name=temp;
    	if(item_temp2!==index && item_temp2!==""){
			var test=document.getElementById("items"+item_temp2);
        	test.style.color="";
		}else{
			item_temp2=index;
        	var tests=document.getElementById("items"+index);
        	tests.style.color="red";
        	$scope.itemGetList="";
		}
    	item_temp2=index;
    	var test=document.getElementById("items"+index);
    	test.style.color="red";
    	$scope.itemGetList="";
    	$scope.getItemGetList(pageSize,pageIndex,itemGet);
    }
    $scope.setState = function(temp,index){
    	$scope.state=temp;
    	if(item_temp3!==index && item_temp3!==""){
			var test=document.getElementById("itemss"+item_temp3);
        	test.style.color="";
		}else{
			item_temp3=index;
        	var tests=document.getElementById("itemss"+index);
        	tests.style.color="red";
        	$scope.itemGetList="";
		}
    	item_temp3=index;
    	var test=document.getElementById("itemss"+index);
    	test.style.color="red";
    	$scope.itemGetList="";
    	$scope.getItemGetList(pageSize,pageIndex,itemGet);
    }
    $scope.resetOne= function(){
    	$scope.projectId=null;
    	var test=document.getElementById("item"+item_temp1);
    	test.style.color="";
    	$scope.getItemGetList(pageSize,pageIndex,itemGet);
    }
    $scope.resetTwo= function(){
    	$scope.name=null;
    	var test=document.getElementById("items"+item_temp2);
    	test.style.color="";
    	$scope.getItemGetList(pageSize,pageIndex,itemGet);
    }
    $scope.resetThree= function(){
    	$scope.state=null;
    	var test=document.getElementById("itemss"+item_temp3);
    	test.style.color="";
    	$scope.getItemGetList(pageSize,pageIndex,itemGet);
    }
    $scope.showList = function(){
    	var teststs1=document.getElementById("data_get");
    	teststs1.style.border="none";
    	var teststs=document.getElementById("item_list");
    	teststs.style.borderBottom="1px solid #2d64b3";
    	
    	var idomst=document.getElementById("table-buton112");
    	idomst.style.display="block";
    	

    	var idoms1=document.getElementById("content_ones");
    	idoms1.style.display="block";
    	var idoms2=document.getElementById("content_twos");
    	idoms2.style.display="none";
    }
    $scope.showData = function(){
    	var testst=document.getElementById("item_list");
    	testst.style.border="none";
    	var testst1=document.getElementById("data_get");
    	testst1.style.borderBottom="1px solid #2d64b3";
    	
    	var idomst=document.getElementById("table-buton112");
    	idomst.style.display="none";
    	
    	var idom1=document.getElementById("content_ones");
    	idom1.style.display="none";
    	var idom2=document.getElementById("content_twos");
    	idom2.style.display="block";

    }
    
}