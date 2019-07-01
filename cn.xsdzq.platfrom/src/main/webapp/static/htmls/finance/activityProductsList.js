ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' ];
function activityProductsListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils) {
	$scope.activityProductsList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "活动产品管理",
					goto:"activityProductsList"
				},
				"two" : {
					name : "中奖纪录查询",
					goto:"activityProductsList"

				}
			}
		$scope.$emit("changeNavigation", data);
		$scope.getActivityProductsList(10);
		
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
					getActivityProductsList(newValue);
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
	      for (var i = 0; i < $scope.activityProductsList.length; i++) {
	        var contact = $scope.activityProductsList[i];
	        updateSelected(action, contact.id);
	      }
	    };
	    $scope.isSelected = function (id) {
	      return $scope.selected.indexOf(id) >= 0;
	    };
	    $scope.isSelectedAll = function () {
	      return $scope.selected.length === $scope.activityProductsList.length;
	    };
	
	$scope.getActivityProductsList = function(pageSize) {
		
		var url = httpUtils.url.activityProductsList;
		var params = {
			pageNumber : 0,
			pageSize : pageSize
		};
		var settings = {
			url : url,
			showPage : 7,
			pageSize : pageSize,
			putDataList : "activityProductsList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	
	$scope.newBuild = function() {
		$state.go("addProduct");
	}
	
	//编辑
	$scope.batchModifyInfo = function() {
		if($scope.selected.length != 1){
        	layerUtils.iMsg(-1, "请选择单条记录编辑！");
			return;
        }
		layerUtils.iConfirm("是否修该此产品信息？", function() {
			var product = $scope.activityProductsList[$scope.selected[0]-1];
			console.log(product);
			$state.go("modifyProduct", {
				product : product
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
		
		layerUtils.iConfirm("是否删除该产品？", function() {
			var url = httpUtils.url.deleteProduct;
			var param = $scope.activityProductsList[$scope.selected[0]-1];
			$http.post(url, param).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "删除成功");
					$scope.getInfosByCategoryId(10);
				}
			});
		}, function() {
			console.log("取消");
		});
	}
}