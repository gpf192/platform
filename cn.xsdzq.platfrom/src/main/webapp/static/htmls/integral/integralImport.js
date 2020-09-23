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
		
		$scope.getEmpList(50);
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
			showPage : 7,
			pageSize : pageSize,
			putDataList : "userVoteList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};

	//导入
	$scope.importToExcel=function(){
		
		 var form = new FormData();
		var temfile = document.getElementById('input-zh').files[0];
		// var temfile = document.querySelector('input[type=file]').files[0];
		 if(temfile == null){
			 layerUtils.iMsg(-1, "请选择附件");
				return;
		 }
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
	        		layerUtils.iMsg(-1, "导入成功");
	        		//$scope.getEmpList(50);
	        	}else {
					layerUtils.iMsg(-1, "附件上传失败");
					//$scope.getEmpList(50);
				}
	        })
	};
		
		
		//确认提交
	$scope.submit=function(){
		layerUtils.iConfirm("是否提交当前数据？提交后不可删除。", function() {
		//var url = httpUtils.url.submitImportTempToCredit;
			var url = httpUtils.url.submit;

		var param = {
				categoryName:1,
				categoryCode:1,
				integralValue:1,
				frontName:1,
				flag:1
		}
		
		$http.post(url).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"提交成功");
				$scope.getEmpList(50);//此时再次查询 应该是没有数据
			}else {
				layerUtils.iMsg(-1,data.respMsg);
				//$scope.getEmpList(50);
			}
		});
		}, function() {
			console.log("取消");
		});
	}
		//清空当前数据 deletTemp
	$scope.deletTemp=function(){
		layerUtils.iConfirm("是否清空当前临时数据？", function() {
		var url = httpUtils.url.deleteTempData;
		$http.post(url).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"删除成功");
			//	$scope.formData={};
				$scope.getEmpList(50);//此时再次查询 应该是没有数据
			} else {
				layerUtils.iMsg(-1,data.respMsg);
				$scope.getEmpList(50)
			}
		});
		}, function() {
			console.log("取消");
		});
	}
	

	
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
	//ecxel导入模板下载 getExcelData

	$scope.templateDownLoad=function(){ 
		var excelArrs = getExcelData();
		var myDate = new Date().Format("yyyyMMddhhmmss");
		 alasql.promise('SELECT * INTO XLSX("积分明细导入模板-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
			.then(function (data) {
			  if(data == 1){
				$timeout(function(){
				  console.log('数据导出成功！');
				})
			  }
			});
	};
	 
	function getExcelData() {
		var arr =[];
		//angular.forEach($scope.userVoteList, function(data, index, datas) {
			var newObj = {	
				
			};
							
				newObj["clientName"] ="" ;
				newObj["clientId"]  ="";
				newObj["mobile"]  ="" ;
				newObj["departmentDesc"] ="" ;
				newObj["departmentCode"]  ="";
				newObj["itemName"]  ="";
				newObj["itemCode"] ="" ;
				newObj["num"]  =""	;
				newObj["beginDate"]   =""	;
				newObj["endDate"] ="" ;

			
			arr.push(newObj);
		//});
		return arr;
	}
	
	
	
}