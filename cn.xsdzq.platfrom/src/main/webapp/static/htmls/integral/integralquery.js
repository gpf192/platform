ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'];
function integralqueryController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {
	$scope.userVoteList= [];
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "积分管理",
					goto:""
				},
				"two" : {
					name : "客户总积分查询",
					goto:"integralquery"

				}
			}
		$scope.$emit("changeNavigation", data);
	
		
		$scope.getEmpList(100);
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
					$scope.getInfosByCategoryId(newValue);
					$scope.currentPage.page=0;
				}
			}, true);
	};
	
	$scope.getEmpList = function(pageSize) {
		var url = httpUtils.url.getUserCreditTotal;
		var username = "";
		var clientId = "";
		var mobile = "";
		if(!utils.isEmpty($scope.formData.clientName)) {
			username = "%"+$scope.formData.clientName+"%";
		}
		if(!utils.isEmpty($scope.formData.clientId)) {
			clientId = "%"+$scope.formData.clientId+"%";
		}
		if(!utils.isEmpty($scope.formData.mobile)) {
			mobile = "%"+$scope.formData.mobile+"%";
		}
		console.log(username+"-ppp");
		var params = {
			pageNumber : 0,
			pageSize : pageSize,
			username : username,
			clientId : clientId,
			mobile : mobile
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
	//修改积分 
	$scope.modify =function(index){
		var user=$scope.userVoteList[index];
		console.log(user);
		$state.go("userTotalIntegralModify",{param :user});
	}
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
		 alasql.promise('SELECT * INTO XLSX("客户积分查询-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
				newObj["客户姓名"] = 	data.clientName;
				newObj["客户号"] = 	data.clientId;
				newObj["手机号"] = 	data.mobile;
				newObj["营业部"] = 	data.departmentDesc;
				newObj["总积分"] = 	data.total;
				
			}
			arr.push(newObj);
		});
		return arr;
	}
	
}