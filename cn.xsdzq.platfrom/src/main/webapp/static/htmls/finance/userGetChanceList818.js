ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'];
function userGetChanceList818Controller($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {
	$scope.userVoteList= [];
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "818中奖管理",
					goto:""
				},
				"two" : {
					name : "用户获取抽奖机会查询",
					goto:"userGetChance818"

				}
			}
		$scope.$emit("changeNavigation", data);
	
		$scope.voteFromList = [
			{name:"默认",
				code : ""
			},
			{
			name:"购买产品",
			code:13
		},{
			name:"签约投顾",
			code:16
		}]
		$scope.selectedVote = $scope.voteFromList[0];
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
		var url = httpUtils.url.userGetChanceList818;
		var clientName = "";
		var clientId = "";
		var votes_source = "";
		if(!utils.isEmpty($scope.formData.username)) {
			clientName = "%"+$scope.formData.username+"%";
		}
		if(!utils.isEmpty($scope.formData.clientId)) {
			clientId = "%"+$scope.formData.clientId+"%";
		}
		if(!utils.isEmpty($scope.selectedVote.code)) {
			votes_source = $scope.selectedVote.code;
		}
		var params = {
			pageNumber : 0,
			pageSize : pageSize,
			clientName : clientName,
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
		 alasql.promise('SELECT * INTO XLSX("用户获取抽卡机会查询-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
			.then(function (data) {
			  if(data == 1){
				$timeout(function(){
				  console.log('数据导出成功！');
				})
			  }
			});
	};
	function filter818(value) {
		if(value == "12"){
	    	return "分享";
	    }
	    if(value == "13"){
	    	return "购买产品";
	    }
	    if(value == "16"){
	    	return "签约投顾";
	    }else{
	    	return "其他";
	    }
	  
	}
	//组装ecxel数据
	function getExcelData() {
		var arr =[];
		angular.forEach($scope.userVoteList, function(data, index, datas) {
			var newObj = {	
				
			};
			for(k=0;k<$scope.userVoteList.length;k++){	
				newObj["流水号"] = 	data.serialNum;
				newObj["客户姓名"] = 	data.clientName;
				newObj["客户号"] = 	data.clientId;
				newObj["抽卡次数"] = 	data.number;
				//newObj["来源"] =   	(data.reason=="13"?"购买产品":"签约投顾");
				newObj["来源"] =   	filter818(data.reason);
				newObj["跑批时间"] = 	data.recordTime;
			}
			arr.push(newObj);
		});
		return arr;
	}
	
}