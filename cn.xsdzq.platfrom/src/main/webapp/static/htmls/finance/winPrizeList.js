ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils', '$stateParams', '$gridService'];
function winPrizeListController($scope, $http, $state, httpUtils, layerUtils, $stateParams, $gridService) {
	
	$scope.formData = {};
	$scope.prizeList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "转盘中奖管理",
					goto:"winPrizeList"
				},
				"two" : {
					name : "中奖纪录查询",
					goto:"winPrizeList"

				}
			}
		$scope.$emit("changeNavigation", data);
		$scope.formData.beginTime = '';
		$scope.formData.endTime = '';
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
					$scope.getWinPrizeList(newValue);
					$scope.currentPage.page=0;
				}
			}, true);
	};
	
	
	$scope.getWinPrizeList = function(pageSize) {
		if( ($scope.formData.beginTime != '') && ($scope.formData.endTime != '')){
			if ($scope.formData.endTime < $scope.formData.beginTime) {
				layerUtils.iMsg(-1, "结束时间不能早于开始时间！");
				return;
			}
		}
		
		var url = httpUtils.url.winPrizeList;
		var params = {
			beginTime : $scope.formData.beginTime,
			endTime : $scope.formData.endTime,
			pageNumber : 0,
			pageSize : pageSize
		};
		var settings = {
			url : url,
			showPage : 7,
			pageSize : pageSize,
			putDataList : "prizeList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	
}


