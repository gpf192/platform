ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' ];
function userTIcketsListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils) {
	$scope.userVoteList= [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "活动数据统计",
					goto:""
				},
				"two" : {
					name : "用户得票数据统计",
					goto:"userTIcketsList"

				}
			}
		$scope.$emit("changeNavigation", data);
		$scope.getEmpList(10);
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
	
	$scope.getEmpList = function(pageSize) {
		var url = httpUtils.url.userTIcketsList;
		var params = {
			pageNumber : 0,
			pageSize : pageSize
		};
		var settings = {
			url : url,
			showPage : 1,
			pageSize : pageSize,
			putDataList : "userVoteList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
}