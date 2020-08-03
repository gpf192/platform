ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'];
function contestantListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {
	$scope.empVoteList= [];
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "活动数据统计",
					goto:""
				},
				"two" : {
					name : "参赛选手得票数据统计",
					goto:"contestantList"

				}
			}
		$scope.$emit("changeNavigation", data);
		$scope.divisionFromList = [{
			name:"全部",
			code:""
		},{
			name:"新手赛区",
			code:"0"
		},{
			name:"王者赛区",
			code:"1"
		}]
		
		$scope.selectedDivision = $scope.divisionFromList[0];
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
					$scope.getEmpList(newValue);
					$scope.currentPage.page=0;
				}
			}, true);
	};
	
	//p begin
	//创建变量用来保存选中结果
	    $scope.selected = [];
	    var updateSelected = function (action, id) {
	      if (action == 'add' && $scope.selected.indexOf(id) == -1) $scope.selected.push(id);
	      if (action == 'remove' && $scope.selected.indexOf(id) != -1) $scope.selected.splice($scope.selected.indexOf(id), 1);
	    };
	    //更新某一列数据的选择
	    $scope.updateSelection = function ($event, id) {
	      var checkbox = $event.target;
	      var action = (checkbox.checked ? 'add' : 'remove');
	      updateSelected(action, id);
	    };
	    //全选操作
	    $scope.selectAll = function ($event) {
	      var checkbox = $event.target;
	      var action = (checkbox.checked ? 'add' : 'remove');
	      for (var i = 0; i < $scope.empVoteList.length; i++) {
	        var contact = $scope.empVoteList[i];
	        updateSelected(action, contact.id);
	      }
	    };
	    $scope.isSelected = function (id) {
	      return $scope.selected.indexOf(id) >= 0;
	    };
	    $scope.isSelectedAll = function () {
	      return $scope.selected.length === $scope.empVoteList.length;
	    };
	
	
	$scope.getEmpList = function(pageSize) {
		var url = httpUtils.url.contestantList;
		var empName ="";
		var empCode = "";
		var division = "";
		if(!utils.isEmpty($scope.formData.empName)) {
			empName = $scope.formData.empName;
		}
		if(!utils.isEmpty($scope.formData.empCode)) {
			empCode = $scope.formData.empCode;
		}
		if(!utils.isEmpty($scope.selectedDivision.code)) {
			division =  $scope.selectedDivision.code;
		}
		var params = {
			pageNumber : 0,
			pageSize : pageSize,
			empName : empName,
			empCode : empCode,
			division : division
		};
		var settings = {
			url : url,
			showPage : 1,
			pageSize : pageSize,
			putDataList : "empVoteList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	
	
	$scope.batchAddWeight = function(){
        if($scope.selected.length != 1){
        	layerUtils.iMsg(-1, "请选择单条记录置顶");
			return;
        }
        for (var h = 0; h < $scope.selected.length; h++) {	
        	
        	var infoId = $scope.selected[h];	
  	      for (var i = 0; i < $scope.empVoteList.length; i++) {
		        var tempInfo = $scope.empVoteList[i];
		        if(tempInfo.id == infoId){
		        	var param = tempInfo;
		        	console.log(param);
		        }
		      }        	
        }  
        console.log(param);
		var url = httpUtils.url.addEmpVoteWeight;
		//var param = $scope.infoList[index];
		param.weight=param.weight+1;
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1, "置顶成功");
//				$scope.selected = [];
				$scope.getEmpList(20000);
			}
		});
	}
	
	//编辑
	$scope.batchModifyInfo = function() {
		if($scope.selected.length != 1){
        	layerUtils.iMsg(-1, "请选择单条记录编辑！");
			return;
        }
		
		for (var h = 0; h < $scope.selected.length; h++) {			
        	var infoId = $scope.selected[h];	
  	      for (var i = 0; i < $scope.empVoteList.length; i++) {
		        var tempInfo = $scope.empVoteList[i];
		        if(tempInfo.id == infoId){
		        	var param = tempInfo;
		        }
		      }        	
        } 

		layerUtils.iConfirm("是否修该此产品信息？", function() {
			console.log(param);
			$state.go("modifyContestant", {
				contestant : param
			});
		}, function() {
			console.log("取消");
		})

	}
	//删除
	$scope.batchDeleteInfo = function() {
		if($scope.selected.length != 1){
        	layerUtils.iMsg(-1, "请选择单条记录删除！");
			return;
        }
		
		for (var h = 0; h < $scope.selected.length; h++) {			
        	var infoId = $scope.selected[h];	
  	      for (var i = 0; i < $scope.empVoteList.length; i++) {
		        var tempInfo = $scope.empVoteList[i];
		        if(tempInfo.id == infoId){
		        	var param = tempInfo;
		        }
		      }        	
        } 
		
		layerUtils.iConfirm("是否删除该产品？", function() {
			var url = httpUtils.url.deleteProduct;
			$http.post(url, param).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "删除成功");
					$scope.selected = [];
					$scope.getEmpList(20000);
				}
			});
		}, function() {
			console.log("取消");
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
		 alasql.promise('SELECT * INTO XLSX("参赛选手得票数据统计表-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
				newObj["员工姓名"] = 	data.emp_name;
				newObj["员工编号"] = 	data.emp_code;
				newObj["隶属营业部"] = 	data.sales_department;
				newObj["隶属赛区"] = 	data.division;
				newObj["获得票数"] = 	data.get_vote_amount;
				newObj["权重"] = 	data.weight;
				newObj["获得时间"] = 	data.get_vote_time;
			}
			arr.push(newObj);
		});
		return arr;
	}
}