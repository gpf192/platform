ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils' ];
function exchangerecordsController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {
	$scope.formData = {};
	$scope.categoryList = [];
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
		$http.get(httpUtils.url.commodity, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categoryList = data.result;
				//设置筛选条件为默认
				for(var k = 0; k < $scope.categoryList.length; k++){
					if($scope.categoryList[k].name == "全部"){
						$scope.formData.category = $scope.categoryList[k];	
					}
				}
				$scope.getExchangeRecordsList(100);
			}
		});
		
		$scope.currentPage = {
				page : 0
			};
			$scope.selectNumList = [{
				num : 100
			}, {
				num : 150
			}, {
				num : 200
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
		var presentId = "";
		var clientId = "";
		var mobile = "";
		if("全部" != $scope.formData.category.name){
			presentId = $scope.formData.category.id;
		}
		if(!utils.isEmpty($scope.formData.clientId)) {
			clientId = "%"+$scope.formData.clientId+"%";
		}
		
		if(!utils.isEmpty($scope.formData.mobile)) {
			mobile = "%"+$scope.formData.mobile+"%";
		}
		var params = {
			presentId:presentId,
			clientId:clientId,
			mobile:mobile,
			pageNumber : 0,
			pageSize : pageSize,

		};
		var settings = {
			url : url,
			showPage : 7,
			pageSize : pageSize,
			putDataList : "exchangeRecordsList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	
	

	//导出为excel
	Date.prototype.Format = function (fmt) {  
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
	$scope.exportToExcel=function(){ 
		var excelArrs = getExcelData();
		var myDate = new Date().Format("yyyyMMddhhmmss");
		 alasql.promise('SELECT * INTO XLSX("兑换记录统计表-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
		angular.forEach($scope.exchangeRecordsList, function(data, index, datas) {
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
				newObj["消费积分"] = 	data.integralNum;
				newObj["兑换时间"] = 	data.recordTime;
			}
			arr.push(newObj);
		});
		return arr;
	}
	
}