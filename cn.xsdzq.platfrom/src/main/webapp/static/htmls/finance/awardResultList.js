ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils', '$stateParams', '$gridService','utils'];
function awardResultListController($scope, $http, $state, httpUtils, layerUtils, $stateParams, $gridService,utils) {
	
	$scope.formData = {};
	$scope.prizeList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "转盘中奖管理",
					goto:"winPrizeList"
				},
				"two" : {
					name : "兑奖纪录查询",
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
		if( ($scope.formData.beginTime != '') && ($scope.formData.endTime != '')){
			if ($scope.formData.endTime < $scope.formData.beginTime) {
				layerUtils.iMsg(-1, "结束时间不能早于开始时间！");
				return;
			}
		}
		var beginTime = "";
		var endTime = "";
		var clientId = "";
		var prizeName = "";
		if(!utils.isEmpty($scope.formData.beginTime)) {
			beginTime = $scope.formData.beginTime;
		}
		if(!utils.isEmpty($scope.formData.endTime)) {
			endTime = $scope.formData.endTime;
		}
		if(!utils.isEmpty($scope.formData.clientId)) {
			clientId = $scope.formData.clientId;
		}
		if(!utils.isEmpty($scope.formData.prizeName)) {
			prizeName = $scope.formData.prizeName;
		}
		
		var url = httpUtils.url.awardResultList;
		var params = {
			beginTime : beginTime,
			endTime : endTime,
			clientId :clientId,
			prizeName : prizeName,
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
	
	

	//导出为excel
	$scope.exportToExcel=function(){ 
		var excelArrs = getExcelData();
		var myDate = new Date();
		 alasql.promise('SELECT * INTO XLSX("组合奖兑奖记录表-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
		angular.forEach($scope.prizeList, function(data, index, datas) {
			var newObj = {	
				
			};
			for(k=0;k<$scope.prizeList.length;k++){				
				newObj["用户姓名"] = 	data.username;
				newObj["用户客户号"] = 	data.clientId;
				newObj["组合奖名称"] = 	data.prizeName;
				newObj["数量"] = 	data.awardNum;
				newObj["兑奖时间"] = 	data.createtime;
				newObj["营业部"] = 	data.departName;
				
			}
			arr.push(newObj);
		});
		return arr;
	}
	 
	
}


