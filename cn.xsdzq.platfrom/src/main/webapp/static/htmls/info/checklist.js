ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' ];
function checkListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils) {

	$scope.formData = {};
	$scope.formData.category = {};
	$scope.categoryList = [];
	$scope.infoList = [];
    $scope.approveResultOption = {
            全部: "all", 
            待审核: "submit",
            已发布: "approve"
        };
	$scope.init = function() {
		//审核状态 默认为查询 全部
		$scope.approveResult = $scope.approveResultOption['待审核'];
		var data = [{
			name : "审核管理",
			goto:""
		}, {
			name : "文章审核",
			goto:"userlist"
		}];
		$scope.$emit("changeNavigation", data);
		$http.get(httpUtils.url.categoryList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categoryList = data.result;
				$scope.formData.category = $scope.categoryList[0];
				$scope.getCheckInfosByCategoryId(10);
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
					$scope.getCheckInfosByCategoryId(newValue);//new   method ******
					$scope.currentPage.page=0;
				}
			}, true);
		
	};
	
	$scope.getCheckInfosByCategoryId = function(pageSize) {
		var inforTitle1 = $scope.formData.title;	
		if (angular.isEmpty($scope.formData.title) ) {
			//如果用户未输入或者输入为空格 ， 该参数赋值为空
			inforTitle1 = '';		
		}
		var url = httpUtils.url.getCheckInfoList;//new   method ******
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
	
/*	$scope.getCheckList = function(){
		$http.post(httpUtils.url.checkList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.checkList = data.result;
			}
		});
	};*/

	$scope.checkInfo = function(index){
		var info = $scope.infoList[index];
		console.log(info);
		$state.go("checkdetail", {
			info : info
		});
	}
	$scope.revocation = function(index) {
		layerUtils.iConfirm("是否要撤回该文章？撤回后，会进入待提交状态。", function() {
			var url = httpUtils.url.revocation;
			var param = $scope.infoList[index];
			$http.post(url, param).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "撤回成功");
					$scope.getCheckInfosByCategoryId(10);
				}
			});
		}, function() {
			console.log("取消");
		});
	}


}