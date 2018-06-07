ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' ];
function infoListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils) {

	$scope.formData = {};
	$scope.formData.category = {};
	$scope.categoryList = [];
	$scope.infoList = [];
	$scope.init = function() {
		var data = [{
			name : "信息管理",
			goto:""
		}, {
			name : "信息列表管理",
			goto:"infolist"
		}];
		$scope.$emit("changeNavigation", data);
		$http.get(httpUtils.url.categoryList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categoryList = data.result;
				$scope.formData.category = $scope.categoryList[0];
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
		var url = httpUtils.url.getInfoList;
		var params = {
			id : $scope.formData.category.id,
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
		var info = $scope.infoList[index];
		console.log(info);
		$state.go("modifyinfo", {
			info : info
		});
	}
	$scope.viewInfo = function(index) {
		var info = $scope.infoList[index];
		console.log(info);
		$state.go("viewinfo", {
			info : info
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
}