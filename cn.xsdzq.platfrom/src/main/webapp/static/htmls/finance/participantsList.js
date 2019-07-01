ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils','utils'];
function participantsListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {
	$scope.empList= [];
	$scope.formData = {};
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
		$scope.getEmpList(20000);
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
		var url = httpUtils.url.participantsList;
		var emp_name = "";
		var emp_code = "";
		var sales_department = "";
		if(!utils.isEmpty($scope.formData.emp_name)) {
			emp_name = $scope.formData.emp_name;
		}
		if(!utils.isEmpty($scope.formData.emp_code)) {
			emp_code = $scope.formData.emp_code;
		}
		if(!utils.isEmpty($scope.formData.sales_department)) {
			sales_department = $scope.formData.sales_department;
		}
		var params = {
			emp_name : emp_name,
			emp_code : emp_code,
			sales_department : sales_department,
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
		layerUtils.iConfirm("是否修该此产品信息？", function() {
			$state.go("modifyParticipants", {
				participant : param
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
  	      for (var i = 0; i < $scope.empList.length; i++) {
		        var tempInfo = $scope.empList[i];
		        if(tempInfo.id == infoId){
		        	var param = tempInfo;
		        }
		      }        	
        } 
		layerUtils.iConfirm("是否删除该产品？", function() {
			var url = httpUtils.url.deleteEmp;
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
}