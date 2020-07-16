ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils', '$stateParams', '$gridService','utils'];
function userRiskListController($scope, $http, $state, httpUtils, layerUtils, $stateParams, $gridService,utils) {
	$scope.productsSellList = [];
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "同花顺展业平台管理",
					goto:"productsSellList"
				},
				"two" : {
					name : "用户风险评估查询",
					goto:"productsSellList"

				}
			}
		
		$scope.formData.beginTime = '';
		 $scope.formData.endTime = '';
		$scope.$emit("changeNavigation", data);
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
		var url = httpUtils.url.getRiskInfo;
		if( ($scope.formData.beginTime != '') && ($scope.formData.endTime != '')){
			if ($scope.formData.endTime < $scope.formData.beginTime) {
				layerUtils.iMsg(-1, "结束时间不能早于开始时间！");
				return;
			}
		}
		var params = {
			beginTime : $scope.formData.beginTime,
			endTime : $scope.formData.endTime,
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
	
	

	//导出为excel
	$scope.exportToExcel=function(){ 
		var excelArrs = getExcelData();
		var myDate = new Date();
		 alasql.promise('SELECT * INTO XLSX("用户风险测评结果-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
		angular.forEach($scope.productsSellList, function(data, index, datas) {
			var newObj = {	
				
			};
			for(k=0;k<$scope.productsSellList.length;k++){				
				newObj["客户号"] = 	data.clientId;
				newObj["客户姓名"] = 	data.clientName;
				newObj["金融账号"] = 	data.financeAccount;
				newObj["产品代码"] = 	data.productCode;
				newObj["产品名称"] = 	data.productName;
				newObj["是否为场外基金"] = 	data.flag;
				newObj["成交时间"] = 	data.dealTime;
				newObj["成交金额"] = 	data.dealAmount;
				newObj["对应服务员工"] = 	data.empName;
			}
			arr.push(newObj);
		});
		return arr;
	}
	
}