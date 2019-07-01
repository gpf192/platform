ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' ];
function contestantListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils) {
	$scope.empVoteList= [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "活动数据统计",
					goto:""
				},
				"two" : {
					name : "参赛选手得票数据统计",
					goto:"contestantList"

				}
			}
		$scope.$emit("changeNavigation", data);
		$scope.getEmpList(20000);
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
					$scope.getEmpList(newValue);
					$scope.currentPage.page=0;
				}
			}, true);
	};
	
	$scope.getEmpList = function(pageSize) {
		var url = httpUtils.url.contestantList;
		var params = {
			pageNumber : 0,
			pageSize : pageSize
		};
		var settings = {
			url : url,
			showPage : 1,
			pageSize : pageSize,
			putDataList : "empVoteList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	
	
	
	//导出为excel
	$scope.exportToExcel=function(){ 
		var excelArrs = getExcelData();
		var myDate = new Date();
		 alasql.promise('SELECT * INTO XLSX("参赛选手得票数据统计表-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
				newObj["员工姓名"] = 	data.emp_name;
				newObj["员工编号"] = 	data.emp_code;
				newObj["隶属营业部"] = 	data.sales_department;
				newObj["隶属赛区"] = 	data.division;
				newObj["获得票数"] = 	data.get_vote_amount;
				newObj["获得时间"] = 	data.get_vote_time;
				newObj["来源用户"] = 	data.vote_from_user;
			}
			arr.push(newObj);
		});
		return arr;
	}
}