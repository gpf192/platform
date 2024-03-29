ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'];
function crmProductListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {
	$scope.userVoteList= [];
	$scope.formData = {};
	$scope.categoryList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "CRM接口查询",
					goto:""
				},
				"two" : {
					name : "CRM接口产品列表查询",
					goto:"crmProductList"

				}
			}
		$scope.$emit("changeNavigation", data);
		$http.get(httpUtils.url.getAllItems, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categoryList = data.result;
				//设置筛选条件为默认
				for(var k = 0; k < $scope.categoryList.length; k++){
					if($scope.categoryList[k].categoryName == "全部"){
						$scope.formData.category = $scope.categoryList[k];	
					}
				}
				$scope.getEmpList(10);
			}
		});
		
		
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
		var url = httpUtils.url.crmCreditProduct;

		var params = {
			pageNumber : 0,
			pageSize : pageSize
			
		};
		var settings = {
			url : url,
			showPage : 7,
			pageSize : pageSize,
			putDataList : "userVoteList"
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
		 alasql.promise('SELECT * INTO XLSX("crm接口产品信息列表-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
				newObj["产品代码"] = 	(data.productCode==null?"":data.productCode);
				newObj["产品名称"] = 	(data.productName==null?"":data.productName);
				newObj["产品类型"] = 	(data.productType==null?"":data.productType);
				newObj["风险类型"] = 	(data.riskType==null?"":data.riskType);
				newObj["基金类型"] = 	(data.fundType==null?"":data.fundType);
				newObj["申购费"] = 	(data.purchaseRate==null?"":data.purchaseRate);
				
				
			}
			arr.push(newObj);
		});
		return arr;
	}
	
}