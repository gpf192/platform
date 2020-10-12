ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' ];
function integralallocationController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils) {
	$scope.commodityclassifyList = [];
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "积分管理",
					goto:""
				},
				"two" : {
					name : "项目类别配置 ",
					goto:"commodityclassify"

				}
			}
		$scope.$emit("changeNavigation", data);
		$scope.getCommodityclassifyList(50);
		
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
					getCommodityclassifyList(newValue);
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
	      for (var i = 0; i < $scope.commodityclassifyList.length; i++) {
	        var contact = $scope.commodityclassifyList[i];
	        updateSelected(action, contact.id);
	      }
	    };
	    $scope.isSelected = function (id) {
	      return $scope.selected.indexOf(id) >= 0;
	    };
	    $scope.isSelectedAll = function () {
	      return $scope.selected.length === $scope.commodityclassifyList.length;
	    };
	
	$scope.getCommodityclassifyList = function(pageSize) {
		
		var name = "";
		var code = "";
		if (!angular.isEmpty($scope.formData.name) ) {
			//如果用户未输入或者输入为空格 ， 该参数赋值为空
			name = "%"+$scope.formData.name+"%";		
		}
		if (!angular.isEmpty($scope.formData.code) ) {
			//如果用户未输入或者输入为空格 ， 该参数赋值为空
			code = "%"+$scope.formData.code+"%";		
		}
		var url = httpUtils.url.getCreditCategory;
		var params = {
			name:name,
			code:code,
			pageNumber : 0,
			pageSize : pageSize
		};
		var settings = {
			url : url,
			showPage : 7,
			pageSize : pageSize,
			putDataList : "commodityclassifyList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	
	$scope.newBuild = function() {
		$state.go("integraladd");
	}
	
	//编辑
	$scope.batchModifyInfo = function() {
		if($scope.selected.length != 1){
        	layerUtils.iMsg(-1, "请选择单条记录编辑！");
			return;
        }
		
		for (var h = 0; h < $scope.selected.length; h++) {			
        	var infoId = $scope.selected[h];	
  	      for (var i = 0; i < $scope.commodityclassifyList.length; i++) {
		        var tempInfo = $scope.commodityclassifyList[i];
		        if(tempInfo.id == infoId){
		        	var param = tempInfo;
		        }
		      }        	
        } 

		layerUtils.iConfirm("是否修该此产品信息？", function() {
			console.log(param);
			$state.go("integralModify", {
				param : param
			});
		}, function() {
			console.log("取消");
		})

	}
	
	//批量删除
	$scope.batchDeleteInfo = function() {
		if($scope.selected.length == 0){
        	layerUtils.iMsg(-1, "请选择要删除的记录！");
			return;
        }
		

		layerUtils.iConfirm("是否删除该商品分类？", function() {
			for (var h = 0; h < $scope.selected.length; h++) {			
	        	var infoId = $scope.selected[h];	
	  	      for (var i = 0; i < $scope.commodityclassifyList.length; i++) {
			        var tempInfo = $scope.commodityclassifyList[i];
			        if(tempInfo.id == infoId){
			        	var param = tempInfo;
			        }
			      }  
	  	    var url = httpUtils.url.deleteCreditCategory;
			$http.post(url, param).success(function(data) {			
			});
	        }
			layerUtils.iMsg(-1, "操作完成，若记录含有关联子项，则无法删除");
			$scope.selected = [];
			$scope.getCommodityclassifyList(50);
			
		}, function() {
			console.log("取消");
		});
	}

}