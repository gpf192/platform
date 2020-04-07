ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils', '$stateParams', '$gridService','utils'];
function liveLoginRecordController($scope, $http, $state, httpUtils, layerUtils, $stateParams, $gridService,utils) {
	
	$scope.formData = {};
	$scope.prizeList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "直播管理",
					goto:"winPrizeList"
				},
				"two" : {
					name : "用户登录记录",
					goto:"winPrizeList"

				}
			}
		$scope.$emit("changeNavigation", data);
		$scope.formData.beginTime = '';
		$scope.formData.endTime = '';
		$scope.getWinPrizeList(20000);
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
		

		var clientId = "";	
		
		if(!utils.isEmpty($scope.formData.clientId)) {
			clientId = $scope.formData.clientId;
		}		
		var url = httpUtils.url.getLoginRecord;
		var params = {			
			clientId :clientId,
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


