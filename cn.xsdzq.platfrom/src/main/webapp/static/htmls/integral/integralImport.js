ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'];
function integralImportController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {
	$scope.userVoteList= [];
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "积分管理",
					goto:""
				},
				"two" : {
					name : "积分来源导入",
					goto:"userTIcketsList"

				}
			}
		$scope.$emit("changeNavigation", data);
	
		
		$scope.getEmpList(100);
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
		var url = httpUtils.url.getCreditImportTemp;
		var username = "";
		var clientId = "";
		var votes_source = "";
		if(!utils.isEmpty($scope.formData.username)) {
			username = $scope.formData.username;
		}
		if(!utils.isEmpty($scope.formData.clientId)) {
			clientId = $scope.formData.clientId;
		}
		
		var params = {
			pageNumber : 0,
			pageSize : pageSize,
			username : username,
			clientId : clientId,
			sourceId : votes_source
		};
		var settings = {
			url : url,
			showPage : 1,
			pageSize : pageSize,
			putDataList : "userVoteList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};

	//导入
	$scope.importToExcel=function(){
		console.log("daoru");
		//var url = httpUtils.url.importExcel;
		
		 var form = new FormData();
		 var temfile = document.querySelector('input[type=file]').files[0];
    	//var temfile = document.getElementById('input-zh').files[0];
		 form.append('upfile', temfile);
      
		 $http({
	            method: 'POST',
	            url: httpUtils.url.importExcel,
	            data: form,
	            headers: {'Content-Type': undefined},
	            transformRequest: angular.identity
	        }).success(function (data) {
	        	if (data.resCode == 0) {
	        		//同步上传，上传附件后，插入表
	        		//$scope.addImage();
	        	}else {
					layerUtils.iMsg(-1, "附件上传失败");
				}
	        })
	};
		
		
		//确认提交
	$scope.submit=function(){
		var url = httpUtils.url.submitImportTempToCredit;
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"提交成功");
			//	$scope.formData={};
				$scope.getEmpList(100);//此时再次查询 应该是没有数据
			}else {
				layerUtils.iMsg(-1,"添加失败");
				$scope.getEmpList(100);
			}
		});
	}
		//清空当前数据 deletTemp
	$scope.deletTemp=function(){
		var url = httpUtils.url.deleteTempData;
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"删除成功");
			//	$scope.formData={};
				$scope.getEmpList(100);//此时再次查询 应该是没有数据
			} else {
				layerUtils.iMsg(-1,"删除失败");
				$scope.getEmpList(100)
			}
		});
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
				newObj["客户姓名"] = 	data.userName;
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