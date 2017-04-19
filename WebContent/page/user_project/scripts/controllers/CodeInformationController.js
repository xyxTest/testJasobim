var index;
function CodeInformationController($scope,CodeInformationService) {
	console.log("载入CodeInformationController");
	var paper="";
	var projectId=79;
	var pageSize=10;
	var pageIndex=1;
	$scope.titlePaper="图纸区";
	$scope.titleVideo="交底区";
	$scope.codePapers="";
	$scope.currentPage="";
	$scope.totalPage=""; 
	$scope.getPapers = function(flag){
		if(flag=="建筑"){
			paper="";
			$scope.titlePaper="建筑";
			paper+= "professionType=" + 4;
			
			CodeInformationService.getCodePapers(projectId,pageSize,pageIndex,paper).then(function (result){
				$scope.codePapers=result.data;
				$(".jianzhuimg").css("display","block");
				$(".filePaper").css("display","none");
				$(".fileVideo").css("display","none");
				$scope.currentPage = result.currentPage;
		        $scope.totalPage = result.totalPage;
		        $scope.paperPage($scope.totalPage,$scope.currentPage);
			});
			
			
		}
		if(flag=="暖通"){
			paper="";
			$scope.titlePaper="暖通";
			paper+= "professionType=" + 1;
			CodeInformationService.getCodePapers(projectId,pageSize,pageIndex,paper).then(function (result){
				$scope.codePapers=result.data;
				$(".jianzhuimg").css("display","block");
				$(".filePaper").css("display","none");
				$(".fileVideo").css("display","none");
				$scope.currentPage = result.currentPage;
		        $scope.totalPage = result.totalPage;
		        $scope.paperPage($scope.totalPage,$scope.currentPage);
			});
			
		}
		if(flag=="给排水"){
			paper="";
			$scope.titlePaper="给排水";
			paper+= "professionType=" + 2;
			CodeInformationService.getCodePapers(projectId,pageSize,pageIndex,paper).then(function (result){
				$scope.codePapers=result.data;
				$(".jianzhuimg").css("display","block");
				$(".filePaper").css("display","none");
				$(".fileVideo").css("display","none");
				$scope.currentPage = result.currentPage;
		        $scope.totalPage = result.totalPage;
		        $scope.paperPage($scope.totalPage,$scope.currentPage);
			});
			
		}
		if(flag=="消防"){
			paper="";
			$scope.titlePaper="消防";
			paper+= "professionType=" + 3;
			CodeInformationService.getCodePapers(projectId,pageSize,pageIndex,paper).then(function (result){
				$scope.codePapers=result.data;
				$(".jianzhuimg").css("display","block");
				$(".filePaper").css("display","none");
				$(".fileVideo").css("display","none");
				$scope.currentPage = result.currentPage;
		        $scope.totalPage = result.totalPage;
		        $scope.paperPage($scope.totalPage,$scope.currentPage);
			});
			
		}
		if(flag=="电气"){
			paper="";
			$scope.titlePaper="电气";
			paper+= "professionType=" + 0;
			CodeInformationService.getCodePapers(projectId,pageSize,pageIndex,paper).then(function (result){
				$scope.codePapers=result.data;
				$(".jianzhuimg").css("display","block");
				$(".filePaper").css("display","none");
				$(".fileVideo").css("display","none");
				$scope.currentPage = result.currentPage;
		        $scope.totalPage = result.totalPage;
		        $scope.paperPage($scope.totalPage,$scope.currentPage);
			});
			
		}
		
		
	}
	////////图纸分页回调函数
	  $scope.paperPage = function(iPageCount,iCurrent) {
		  $("#paperPageCode").remove();
		  $("#table-buton6").append("<div id=\"paperPageCode\"></div>");
		  $("#paperPageCode").createPage({

		      pageCount:iPageCount,

		      current:iCurrent,

		      backFn:function(p){
		    	  $scope.getProjectPaperList($scope.projectid,pageSize,p,paper);
		      }
		  });
	  }
	 //////////图标二维码图片地址获取
	$scope.getCodePapers = function(projectId,pageSize,pageIndex,paper){
		CodeInformationService.getCodePapers(projectId,pageSize,pageIndex,paper).then(function (result){
			$scope.codePapers=result.data;
			$scope.currentPage = result.currentPage;
	        $scope.totalPage = result.totalPage;
	        $scope.paperPage($scope.totalPage,$scope.currentPage);
		});
	}
	
	$scope.getVideos = function(flag){
		if(flag=="安全"){
			$scope.titleVideo="安全交底";
		}
		if(flag=="质量"){
			$scope.titleVideo="质量交底";
		}
		if(flag=="技术"){
			$scope.titleVideo="技术交底";
		}
		$(".filePaper").css("display","none");
		$(".fileVideo").css("display","none");
		$(".videoDetail").css("display","block");
	}
	$scope.returnPaperList= function(){
		$scope.titlePaper="图纸区";
		$scope.titleVideo="交底区";
		$scope.codePapers="";
		$(".paperDetail").css("display","none");
		$(".filePaper").css("display","block");
		$(".fileVideo").css("display","block");
	}
	$scope.returnVideoList= function(){
		$scope.titlePaper="图纸区";
		$scope.titleVideo="交底区";
		$(".videoDetail").css("display","none");
		$(".filePaper").css("display","block");
		$(".fileVideo").css("display","block");
	}
}