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
	
	//导出为excel
	$scope.exportToExcel=function(){ 
		var excelArrs = getExcelData();
		var myDate = new Date();
		 alasql.promise('SELECT * INTO XLSX("用户得票数据统计表-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
			.then(function (data) {
			  if(data == 1){
				$timeout(function(){
				  console.log('数据导出成功！');
				})
			  }
			});
	};
	 
	//组装ecxel数据
	function getExcelData() {
		var arr =[];
		angular.forEach($scope.userVoteList, function(data, index, datas) {
			var newObj = {	
				
			};
			for(k=0;k<$scope.userVoteList.length;k++){				
				newObj["用户姓名"] = 	data.username;
				newObj["资金账号"] = 	data.account;
				newObj["所得票数"] = 	data.total_votes;
				newObj["得票来源"] = 	data.votes_source;
				newObj["得票时间"] = 	data.gain_time;
				newObj["使用对象"] = 	data.vote_for;
				newObj["使用票数"] = 	data.vote_for_amount;
				newObj["隶属营业部"] = 	data.sales_department;
				newObj["隶属赛区"] = 	data.division;
			}
			arr.push(newObj);
		});
		return arr;
	}
	
}