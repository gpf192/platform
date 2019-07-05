ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' ];
function paramListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils) {

	$scope.formData = {};
	
	$scope.paramList = [];

	$scope.init = function() {
	
		var data = [{
			name : "参数管理",
			goto:"paramlist"
		}, {
			name : "系统参数查询",
			goto:"paramlist"
		}];
		$scope.$emit("changeNavigation", data);

		$scope.getParamList(20000);

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
				$scope.getParamList(newValue);
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
	      for (var i = 0; i < $scope.paramList.length; i++) {
	        var contact = $scope.paramList[i];
	        updateSelected(action, contact.id);
	      }
	    };
	    $scope.isSelected = function (id) {
	      return $scope.selected.indexOf(id) >= 0;
	    };
	    $scope.isSelectedAll = function () {
	      return $scope.selected.length === $scope.paramList.length;
	    };
    
    
    
    // end
	$scope.getParamList = function(pageSize) {
		
		var url = httpUtils.url.paramList;
		var params = {
				code :1,
			pageNumber : 0,
			pageSize : pageSize
		};
		var settings = {
			url : url,
			showPage : 7,
			pageSize : pageSize,
			putDataList : "paramList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};


	//批量编辑
	$scope.batchModifyInfo = function() {
		if($scope.selected.length != 1){
        	layerUtils.iMsg(-1, "请选择单条记录编辑！");
			return;
        }
		for (var h = 0; h < $scope.selected.length; h++) {			
        	var infoId = $scope.selected[h];	
  	      for (var i = 0; i < $scope.paramList.length; i++) {
		        var tempInfo = $scope.paramList[i];
		        if(tempInfo.id == infoId){
		        	var param = tempInfo;
		        }
		      }        	
        } 
		console.log(param);
		layerUtils.iConfirm("是否修该此参数？", function() {
			console.log(param);
			$state.go("modifyParam", {
				param : param
			});
		}, function() {
			console.log("取消");
		})
	}
	//批量删除
	$scope.batchDeleteInfo = function() {
		if($scope.selected.length != 1){
        	layerUtils.iMsg(-1, "请选择单条记录删除！");
			return;
        }
		for (var h = 0; h < $scope.selected.length; h++) {			
        	var infoId = $scope.selected[h];	
  	      for (var i = 0; i < $scope.paramList.length; i++) {
		        var tempInfo = $scope.paramList[i];
		        if(tempInfo.id == infoId){
		        	var param = tempInfo;
		        }
		      }        	
        } 
	
		layerUtils.iConfirm("是否删除该参数？", function() {
			var url = httpUtils.url.deleteInfo;
			$http.post(url, param).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "删除成功");
					$scope.selected = [];//及时删除，否则下次删除仍存在变量中
					$scope.getParamList(20000);
				}
			});
		}, function() {
			console.log("取消");
		});
	}
}