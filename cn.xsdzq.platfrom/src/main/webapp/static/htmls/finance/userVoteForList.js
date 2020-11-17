ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'];
function userVoteForListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {
	$scope.userVoteForList= [];
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "活动数据统计",
					goto:""
				},
				"two" : {
					name : "用户得票数据统计",
					goto:"userTIcketsList"

				}
			}
		$scope.$emit("changeNavigation", data);
		$scope.getUserVoteForList(100);
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
	
	$scope.getUserVoteForList = function(pageSize) {
		var url = httpUtils.url.userVoteForList;
		var username = "";
		var clientId = "";
		var empName = "";
		var empCode = "";
		if(!utils.isEmpty($scope.formData.username)) {
			username = $scope.formData.username;
		}
		if(!utils.isEmpty($scope.formData.clientId)) {
			clientId = $scope.formData.clientId;
		}
		if(!utils.isEmpty($scope.formData.empName)) {
			empName = $scope.formData.empName;
		}
		if(!utils.isEmpty($scope.formData.empCode)) {
			empCode = $scope.formData.empCode;
		}
		var params = {
			pageNumber : 0,
			pageSize : pageSize,
			username : username,
			clientId : clientId,
			empName : empName,
			empCode : empCode
		};
		var settings = {
			url : url,
			showPage : 1,
			pageSize : pageSize,
			putDataList : "userVoteForList"
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
		 alasql.promise('SELECT * INTO XLSX("用户投票数据统计表-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
			.then(function (data) {
			  if(data == 1){
				$timeout(function(){
				  console.log('数据导出成功！');
				})
			  }
			});
	};
	 
	//组装ecxel数据
	 //赛区  0-新手  1-王者
	 function divisionCheck(value) {
		    if(value == "1"){
		    	return "王者赛区";	 
		    }
		    if(value == "0"){
		    	return "新手赛区";
		    }
		    
		 
		}
	 
	function getExcelData() {
		var arr =[];
		angular.forEach($scope.userVoteForList, function(data, index, datas) {
			var newObj = {	
				
			};
			for(k=0;k<$scope.userVoteForList.length;k++){				
				newObj["客户姓名"] = 	data.username;
				newObj["客户号"] = 	data.clientId;
				newObj["投票时间"] = 	data.voteTime;
				newObj["使用对象"] = 	data.empName;
				newObj["使用对象员工编号"] = 	data.empCode;
				newObj["使用票数"] = 	data.voteNum;
				newObj["隶属营业部"] = 	data.salesDepartment;
				newObj["隶属赛区"] = 	divisionCheck(data.division);
			}
			arr.push(newObj);
		});
		return arr;
	}
	
}