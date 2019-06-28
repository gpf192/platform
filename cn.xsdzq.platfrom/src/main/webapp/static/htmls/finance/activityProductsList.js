ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' ];
function activityProductsListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils) {
	$scope.activityProductsList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "活动产品管理",
					goto:"activityProductsList"
				},
				"two" : {
					name : "中奖纪录查询",
					goto:"activityProductsList"

				}
			}
		$scope.$emit("changeNavigation", data);
		$scope.getActivityProductsList(10);
		
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
	
	$scope.getActivityProductsList = function(pageSize) {
		
		var url = httpUtils.url.activityProductsList;
		var params = {
			pageNumber : 0,
			pageSize : pageSize
		};
		var settings = {
			url : url,
			showPage : 0,
			pageSize : pageSize,
			putDataList : "activityProductsList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	
	$scope.newBuild = function() {
		$state.go("addProduct");
	}
	
}