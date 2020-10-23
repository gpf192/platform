ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'];
function crmCreditApiMsgController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {
	$scope.userVoteList= [];
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "CRM接口查询",
					goto:""
				},
				"two" : {
					name : "CRM接口报错单据查询",
					goto:"userTIcketsList"

				}
			}
		$scope.$emit("changeNavigation", data);
	
		
		$scope.getEmpList(10);
		$scope.currentPage = {
				page : 0
			};
			$scope.selectNumList = [{
				num : 50
			}, {
				num : 100
			}, {
				num : 150
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
		var url = httpUtils.url.crmCreditApiMsg;
		var serialNum = "";
		
		if(!utils.isEmpty($scope.formData.serialNum)) {
			serialNum = "%"+$scope.formData.serialNum+"%";
		}

		var params = {
			pageNumber : 0,
			pageSize : pageSize,
			serialNum : serialNum
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
		 alasql.promise('SELECT * INTO XLSX("CRM积分接口报错记录-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
				newObj["流水号"] = 	data.serialNum;
				newObj["报错"] = 	data.msg;
				newObj["时间"] = 	data.recordTime;
				
				
			}
			arr.push(newObj);
		});
		return arr;
	}
	
	$scope.getDataManual=function(){
		layerUtils.iConfirm("该功能用于重新获取crm接口之前有问题的数据，请确认当前问题数据已修改完毕", function() {
		
			var checkBeforeSubmitUrl = httpUtils.url.getDataManual;
			$http.post(checkBeforeSubmitUrl).success(function(data) {
				if (data.resCode == 1) {
					layerUtils.iMsg(-1,data.respMsg);
					return;
				}
				
			});
		
		}, function() {
			console.log("取消");
		});
	}
	
}