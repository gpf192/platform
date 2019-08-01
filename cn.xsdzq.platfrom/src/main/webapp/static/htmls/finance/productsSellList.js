ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils', '$stateParams', '$gridService','utils'];
function productsSellListController($scope, $http, $state, httpUtils, layerUtils, $stateParams, $gridService,utils) {
	$scope.productsSellList = [];
	$scope.formData = {};
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
		var url = httpUtils.url.productsSellList;
		var clientId = "";
		var financeAccount = "";
		var code = "";
		if(!utils.isEmpty($scope.formData.clientId)) {
			clientId = $scope.formData.clientId;
		}
		if(!utils.isEmpty($scope.formData.financeAccount)) {
			financeAccount = $scope.formData.financeAccount;
		}
		if(!utils.isEmpty($scope.formData.code)) {
			code = $scope.formData.code;
		}
		var params = {
			pageNumber : 0,
			pageSize : pageSize,
			clientId : clientId,
			financeAccount : financeAccount,
			code : code
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
		 alasql.promise('SELECT * INTO XLSX("活动产品销售数据表-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
				newObj["客户姓名"] = 	data.username;
				newObj["金融账号"] = 	data.financeAccount;
				newObj["产品代码"] = 	data.code;
				newObj["产品名称"] = 	data.product_name;
				newObj["是否为场外基金"] = 	data.flag;
				newObj["成交时间"] = 	data.deal_time;
				newObj["成交金额"] = 	data.deal_share;
				newObj["对应服务员工"] = 	data.emp_name;
			}
			arr.push(newObj);
		});
		return arr;
	}
	
}