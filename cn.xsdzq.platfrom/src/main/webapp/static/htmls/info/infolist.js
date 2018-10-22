ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' ];
function infoListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils) {

	$scope.formData = {};
	$scope.formData.category = {};
	$scope.categoryList = [];
	$scope.infoList = [];
    $scope.approveResultOption = {
            全部: "all",
            待提交: "generate",
            待审核: "submit",
            已发布: "approve"
        };
    
	$scope.init = function() {
		//审核状态 默认为查询 全部
		$scope.approveResult = $scope.approveResultOption['全部'];
		var data = [{
			name : "信息管理",
			goto:"infolist"
		}, {
			name : "内容管理",
			goto:"infolist"
		}];
		$scope.$emit("changeNavigation", data);
		$http.get(httpUtils.url.categoryList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categoryList = data.result;
				//设置筛选条件为默认
				for(var k = 0; k < $scope.categoryList.length; k++){
					if($scope.categoryList[k].title == "全部"){
						$scope.formData.category = $scope.categoryList[k];	
					}
				}
				$scope.getInfosByCategoryId(10);
			}
		});
		$scope.currentPage = {
			page : 0
		};
		$scope.selectNumList = [{
			num : 10
		}, {
			num : 50
		}, {
			num : 100
		}];
		$scope.selectNum = $scope.selectNumList[0];	
		$scope.$watch("selectNum.num", function(newValue, oldValue) {
			if (newValue != oldValue) {
				$scope.getInfosByCategoryId(newValue);
				$scope.currentPage.page=0;
			}
		}, true);
	};
	$scope.getInfosByCategoryId = function(pageSize) {
		var inforTitle1 = $scope.formData.title;	
		if (angular.isEmpty($scope.formData.title) ) {
			//如果用户未输入或者输入为空格 ， 该参数赋值为空
			inforTitle1 = '';		
		}
		var url = httpUtils.url.getInfoList;
		var params = {
			id : $scope.formData.category.id,
			infoTitle : inforTitle1,
			approveResult :$scope.approveResult,
			pageNumber : 0,
			pageSize : pageSize
		};
		var settings = {
			url : url,
			showPage : 7,
			pageSize : pageSize,
			putDataList : "infoList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	$scope.modifyInfo = function(index) {
		layerUtils.iConfirm("是否修该此文章？", function() {
			var info = $scope.infoList[index];
			console.log(info);
			$state.go("modifyinfo", {
				info : info
			});
		}, function() {
			console.log("取消");
		})
	}
	$scope.viewInfo = function(index) {
		var info = $scope.infoList[index];
		console.log(info);
		$state.go("viewinfo", {
			info : info
		});
	}
	
	$scope.addWeight = function(index){
		var url = httpUtils.url.addWeight;
		var param = $scope.infoList[index];
		param.weight=param.weight+1;
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1, "置顶成功");
			}
		});
	}
	
	$scope.deleteInfo = function(index) {
		layerUtils.iConfirm("是否删除该文章？", function() {
			var url = httpUtils.url.deleteInfo;
			var param = $scope.infoList[index];
			$http.post(url, param).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "删除成功");
					$scope.getInfosByCategoryId(10);
				}
			});
		}, function() {
			console.log("取消");
		});
	}
	$scope.copyUrl = function(index) {
		console.log($scope.infoList[index]);
		var info=$scope.infoList[index];
		var text=window.location.protocol+"//"+window.location.hostname+":"+window.location.port+"/front/#/detail/"+info.id;
		if (window.clipboardData) {//如果是IE浏览器
	        window.clipboardData.setData('text', text);
	    } else {//非IE浏览器
    	   var textarea = document.createElement('textarea');
    	   textarea.value = text;
           document.body.appendChild(textarea);
           textarea.select(); // 选择对象
           document.execCommand("Copy"); // 执行浏览器复制命令
    	   textarea.style.display='none';
	    }
		layerUtils.iMsg(-1, "复制成功");
	}
}