ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils', '$stateParams', '$gridService'];
function productsSellListController($scope, $http, $state, httpUtils, layerUtils, $stateParams, $gridService) {
	$scope.productsSellList = [];
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
		var params = {
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
				newObj["用户姓名"] = 	data.username;
				newObj["资金账号"] = 	data.account;
				newObj["委托时间"] = 	data.order_time;
				newObj["成交时间"] = 	data.deal_time;
				newObj["产品代码"] = 	data.product_code;
				newObj["产品名称"] = 	data.product_name;
				newObj["交易份额"] = 	data.deal_share;
				newObj["成交金额"] = 	data.deal_amount;
				newObj["获得票数"] = 	data.votes;
				newObj["对应服务员工"] = 	data.emp_name;
			}
			arr.push(newObj);
		});
		return arr;
	}
	
}