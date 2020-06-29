ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils', '$stateParams', '$gridService','utils'];
function userOrderListController($scope, $http, $state, httpUtils, layerUtils, $stateParams, $gridService,utils) {
	$scope.productsSellList = [];
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "同花顺展业平台管理",
					goto:"userOrderList"
				},
				"two" : {
					name : "用户订单列表", 
					goto:"userOrderList"

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
		console.log('11111111');
		var url = httpUtils.url.getUserOrder;
		var orderId = "";
		var username = "";
		var tgName = "";
		var productName = "";
		
		if(!utils.isEmpty($scope.formData.orderId)) {
			orderId = $scope.formData.orderId;
		}
		if(!utils.isEmpty($scope.formData.username)) {
			username = $scope.formData.username;
		}
		if(!utils.isEmpty($scope.formData.tgName)) {
			tgName = $scope.formData.tgName;
		}
		if(!utils.isEmpty($scope.formData.productName)) {
			productName = $scope.formData.productName;
		}
		var params = {
			pageNumber : 0,
			pageSize : pageSize,
			username : username,
			orderId : orderId,
			tgName : tgName,
			productName: productName
		};
		var settings = {
			url : url,
			showPage : 1,
			pageSize : pageSize,
			putDataList : "productsSellList"
		};
		
		console.log('1112222222');
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	
	

	//导出为excel
	$scope.exportToExcel=function(){ 
		var excelArrs = getExcelData();
		var myDate = new Date();
		 alasql.promise('SELECT * INTO XLSX("同花顺用户订单数据-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
				newObj["订单号"] = 		data.orderId;
				newObj["订单状态"] = 		data.orderStatus;
				newObj["产品类型"] = 		data.goodsType;
				newObj["商品ID"] = 		data.goodsId;
				newObj["用户ID"] = 		data.userId;
				newObj["用户姓名"] = 		data.username;
				newObj["用户身份证号码"] = 	data.certificate;
				newObj["用户身份证有效期"] = 	data.expiredate;
				newObj["投顾产品名称"] = 	data.productName;
				newObj["产品风险等级"] = 	data.goodsRisk;
				newObj["用户风险等级"] = 	data.evaluationResult;
				
				newObj["风险测评答案"] = 	data.evaluationAnswer;
				newObj["所属券商"] = 		data.brokerName;
				newObj["所属营业部名称"] = 	data.salesName;
				newObj["所属投顾姓名"] = 	data.tgName;
				newObj["投顾证书编号"] = 	data.tgCertification;
				newObj["服务周期"] = 		data.serviceCycle;
				newObj["服务开始日期"] = 	data.serviceCycleStart;
				newObj["服务结束日期"] = 	data.serviceCycleEnd;
				newObj["订阅金额"] = 		data.amount;
				
				newObj["订阅时间"] = 		data.bookTime;
				newObj["订阅来源"] = 		data.bookOrigin;
				newObj["签署的所有协议"] = 	data.agreements;
				newObj["风险揭示书"] = 		data.riskRevelation;
				newObj["适当性匹配结果"] = 	data.matchInstruction;
				
				newObj["联系地址"] = 		data.address;
				newObj["职业"] = 		data.occupation;
				newObj["用户手机号"] = 	data.mobile;
				newObj["风测时间"] = 		data.evaluationTime;
				
			}
			arr.push(newObj);
		});
		return arr;
	}
	
}