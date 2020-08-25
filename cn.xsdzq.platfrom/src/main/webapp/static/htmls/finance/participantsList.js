ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils','utils'];
function participantsListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {
	$scope.empList= [];
	$scope.formData = {};
	$scope.departments = [];
	$scope.selectedName = "";
	$scope.init=function(){
		var data = {
				"one" : {
					name : "参赛人员管理",
					goto:""
				},
				"two" : {
					name : "员工信息统计",
					goto:"participantsList"

				}
			}
		$scope.$emit("changeNavigation", data);
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
		$http.get(httpUtils.url.departmentList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.departments = data.result;
				var headerElement = {id: 0, code: "", name: "全部"};
				$scope.departments.unshift(headerElement);
				$scope.selectedName = $scope.departments[0];
				console.log($scope.selectedName.code);
				$scope.getEmpList(50);
			}
		});
		
		
		
		
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
	      for (var i = 0; i < $scope.empList.length; i++) {
	        var contact = $scope.empList[i];
	        updateSelected(action, contact.id);
	      }
	    };
	    $scope.isSelected = function (id) {
	      return $scope.selected.indexOf(id) >= 0;
	    };
	    $scope.isSelectedAll = function () {
	      return $scope.selected.length === $scope.empList.length;
	    };
	
	$scope.addParticipants = function() {
		$state.go("addParticipants");
	}
	$scope.getEmpList = function(pageSize) {
		console.log($scope.selectedName);
		var url = httpUtils.url.participantsList;
		var emp_name = "";
		var emp_code = "";
		var departmentCode = "";
		if(!utils.isEmpty($scope.formData.emp_name)) {
			emp_name = $scope.formData.emp_name;
		}
		if(!utils.isEmpty($scope.formData.emp_code)) {
			emp_code = $scope.formData.emp_code;
		}
		if(!utils.isEmpty($scope.selectedName.code)) {
			departmentCode = $scope.selectedName.code;
		}
		var params = {
			emp_name : emp_name,
			emp_code : emp_code,
			departmentCode : departmentCode,
			pageNumber : 0,
			pageSize : pageSize
		};
		var settings = {
			url : url,
			showPage : 7,
			pageSize : pageSize,
			putDataList : "empList"
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
		 alasql.promise('SELECT * INTO XLSX("参赛人员据统计表-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
		angular.forEach($scope.empList, function(data, index, datas) {
			var newObj = {	
				
			};
			for(k=0;k<$scope.empList.length;k++){				
				newObj["人员姓名"] = 	data.emp_name;
				newObj["人员编号"] = 	data.emp_code;
				newObj["在编性质"] = 	data.emp_type;
				newObj["人员类别"] = 	data.emp_category;
				newObj["签约合同"] = 	data.contract;
				newObj["入职时间"] = 	data.entry_time;
				newObj["隶属营业部"] = 	data.sales_department;
				if(data.division == 0) {
					newObj["隶属赛区"] = "新手赛区";	
				}else {
					newObj["隶属赛区"] = "王者赛区";	
				}
			}
			arr.push(newObj);
		});
		return arr;
	}
	
	//编辑
	$scope.batchModifyInfo = function() {
		if($scope.selected.length != 1){
        	layerUtils.iMsg(-1, "请选择单条记录编辑！");
			return;
        }
		for (var h = 0; h < $scope.selected.length; h++) {			
        	var infoId = $scope.selected[h];	
  	      for (var i = 0; i < $scope.empList.length; i++) {
		        var tempInfo = $scope.empList[i];
		        if(tempInfo.id == infoId){
		        	var param = tempInfo;
		        }
		      }        	
        }
		console.log(param);
		layerUtils.iConfirm("是否修该此参赛人员信息？", function() {
			$state.go("modifyParticipants", {
				participant : param
			});
		}, function() {
			console.log("取消");
		})

	}
	//删除
	/*$scope.batchDeleteInfo = function() {
		if($scope.selected.length != 1){
        	layerUtils.iMsg(-1, "请选择单条记录删除！");
			return;
        }
		for (var h = 0; h < $scope.selected.length; h++) {			
        	var infoId = $scope.selected[h];	
  	      for (var i = 0; i < $scope.empList.length; i++) {
		        var tempInfo = $scope.empList[i];
		        if(tempInfo.id == infoId){
		        	var param = tempInfo;
		        }
		      }        	
        } 
		layerUtils.iConfirm("是否删除该参赛人员？", function() {
			var url = httpUtils.url.deleteEmp;
			$http.post(url, param).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "删除成功");
					$scope.selected = [];
					$scope.getEmpList(50);
				}
			});
		}, function() {
			console.log("取消");
		});
	}*/
	$scope.batchDeleteInfo = function() {
		if($scope.selected.length == 0){
        	layerUtils.iMsg(-1, "请选择要删除的记录！");
			return;
        }
		layerUtils.iConfirm("是否删除该参赛人员？", function() {
			for (var h = 0; h < $scope.selected.length; h++) {			
	        	var infoId = $scope.selected[h];	
	  	      for (var i = 0; i < $scope.empList.length; i++) {
			        var tempInfo = $scope.empList[i];
			        if(tempInfo.id == infoId){
			        	var param = tempInfo;
			        }
			      }  
	  	    var url = httpUtils.url.deleteEmp;
			$http.post(url, param).success(function(data) {
				if (data.resCode != 0) {
					layerUtils.iMsg(-1, "删除失败");	
				}
			});
	        }
			layerUtils.iMsg(-1, "删除成功");
			$scope.selected = [];
			$scope.getEmpList(50);
			
		}, function() {
			console.log("取消");
		});
	}
}