ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'];
function userTIcketsListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {
	$scope.userVoteList= [];
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
	
		$scope.voteFromList = [
			{name:"默认",
				code : ""
			},
			{
			name:"活动登录",
			code:1
		},{
			name:"活动分享",
			code:2
		},{
			name:"抽奖",
			code:3
		},{
			name:"购买理财产品",
			code:4
		},{
			name:"新开基金账户",
			code:5
		},{
			name:"签约投顾",
			code:6
		},{
			name:"参与评选",
			code:9
		}]
		$scope.selectedVote = $scope.voteFromList[0];
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
		var url = httpUtils.url.userTIcketsList;
		var username = "";
		var clientId = "";
		var votes_source = "";
		if(!utils.isEmpty($scope.formData.username)) {
			username = $scope.formData.username;
		}
		if(!utils.isEmpty($scope.formData.clientId)) {
			clientId = $scope.formData.clientId;
		}
		if(!utils.isEmpty($scope.selectedVote.code)) {
			votes_source = $scope.selectedVote.code;
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
		 alasql.promise('SELECT * INTO XLSX("用户得票数据统计表-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
				newObj["客户姓名"] = 	data.username;
				newObj["客户号"] = 	data.clientId;
				newObj["所得票数"] = 	data.total_votes;
				newObj["得票来源"] = 	data.votes_source;
				newObj["得票时间"] = 	data.gain_time;
				newObj["隶属营业部"] = 	data.sales_department;
			}
			arr.push(newObj);
		});
		return arr;
	}
	
}