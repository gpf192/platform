ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils', '$stateParams', '$gridService'];
function productsSellListController($scope, $http, $state, httpUtils, layerUtils, $stateParams, $gridService) {
	$scope.productsSellList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "活动产品管理",
					goto:"productsSellList"
				},
				"two" : {
					name : "活动产品销售数据",
					goto:"productsSellList"

				}
			}
		$scope.$emit("changeNavigation", data);
		$scope.getWinPrizeList(10);
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
	
	
	$scope.getWinPrizeList = function(pageSize) {
		var url = httpUtils.url.productsSellList;
		var params = {
			pageNumber : 0,
			pageSize : pageSize
		};
		var settings = {
			url : url,
			showPage : 1,
			pageSize : pageSize,
			putDataList : "productsSellList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	
	
}