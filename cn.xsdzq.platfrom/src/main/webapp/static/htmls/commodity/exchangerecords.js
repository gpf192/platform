ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' ];
function exchangerecordsController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils) {

	$scope.exchangeRecordsList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "商品兑换记录查询",
					goto:""
				},
				"two" : {
					name : "兑换记录 ",
					goto:"exchangerecords"

				}
			}
		$scope.$emit("changeNavigation", data);
		$scope.getExchangeRecordsList(20000);
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
					$scope.getExchangeRecordsList(newValue);
					$scope.currentPage.page=0;
				}
			}, true);
	};
	
	
	$scope.getExchangeRecordsList = function(pageSize) {
		var url = httpUtils.url.exchangeRecords;
//		var clientId = "";
//		var financeAccount = "";
//		var productCode = "";
//		if(!utils.isEmpty($scope.formData.clientId)) {
//			clientId = $scope.formData.clientId;
//		}
//		if(!utils.isEmpty($scope.formData.financeAccount)) {
//			financeAccount = $scope.formData.financeAccount;
//		}
//		if(!utils.isEmpty($scope.formData.productCode)) {
//			productCode = $scope.formData.productCode;
//		}
		var params = {
			pageNumber : 0,
			pageSize : pageSize,
//			clientId : clientId,
//			financeAccount : financeAccount,
//			productCode : productCode
		};
		var settings = {
			url : url,
			showPage : 1,
			pageSize : pageSize,
			putDataList : "exchangeRecordsList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	
	

	//导出为excel
	$scope.exportToExcel=function(){ 
		var excelArrs = getExcelData();
		var myDate = new Date();
		 alasql.promise('SELECT * INTO XLSX("商品兑换记录数据表-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
			for(k=0;k<$scope.exchangeRecordsList.length;k++){				
				newObj["客户姓名"] = 	data.clientName;
				newObj["客户号"] = 	data.clientId;
				newObj["营业部"] = 	data.department;
				newObj["手机号"] = 	data.mobile;
				newObj["商品名称"] = 	data.presentName;
				newObj["卡号"] = 	data.cardId;
				newObj["密码"] = 	data.password;
				newObj["实际价格（元）"] = 	data.price;
				newObj["消费积分"] = 	data.integral;
				newObj["兑换时间"] = 	data.recordTime;
			}
			arr.push(newObj);
		});
		return arr;
	}
	
}