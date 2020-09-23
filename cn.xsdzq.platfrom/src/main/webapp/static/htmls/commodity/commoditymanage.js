ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' , 'utils'];
function commoditymanageController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {
	$scope.formData = {};
	$scope.commodityList = [];
	$scope.categoryList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "商品管理",
					goto:""
				},
				"two" : {
					name : "商品管理",
					goto:"commodityclassify"

				}
			}
		$scope.$emit("changeNavigation", data);
		$http.get(httpUtils.url.commodityClassify, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categoryList = data.result;
				//设置筛选条件为默认
				for(var k = 0; k < $scope.categoryList.length; k++){
					if($scope.categoryList[k].name == "全部"){
						$scope.formData.category = $scope.categoryList[k];	
					}
				}
				$scope.getCommodityList(50);
			}
		});
		
		
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
					getCommodityList(newValue);
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
	      for (var i = 0; i < $scope.commodityList.length; i++) {
	        var contact = $scope.commodityList[i];
	        updateSelected(action, contact.id);
	      }
	    };
	    $scope.isSelected = function (id) {
	      return $scope.selected.indexOf(id) >= 0;
	    };
	    $scope.isSelectedAll = function () {
	      return $scope.selected.length === $scope.commodityList.length;
	    };
	
	$scope.getCommodityList = function(pageSize) {
		var name = "";
		var categoryCode = "";
		if(!utils.isEmpty($scope.formData.name)) {
			name = "%"+$scope.formData.name+"%";
		}
		if("全部" != $scope.formData.category.name){
			categoryCode = "%"+$scope.formData.category.code+"%";
		}
		console.log(categoryCode+"-"+name);
		var url = httpUtils.url.getAllPresentPage;
		var params = {
				name:name,
				categoryCode:categoryCode,		
			pageNumber : 0,
			pageSize : pageSize
		};
		var settings = {
			url : url,
			showPage : 7,
			pageSize : pageSize,
			putDataList : "commodityList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	
	$scope.newBuild = function() {
		$state.go("commodityadd");
	}
	
	//编辑
	$scope.batchModifyInfo = function() {
		if($scope.selected.length != 1){
        	layerUtils.iMsg(-1, "请选择单条记录编辑！");
			return;
        }
		
		for (var h = 0; h < $scope.selected.length; h++) {			
        	var infoId = $scope.selected[h];	
  	      for (var i = 0; i < $scope.commodityList.length; i++) {
		        var tempInfo = $scope.commodityList[i];
		        if(tempInfo.id == infoId){
		        	var param = tempInfo;
		        }
		      }        	
        } 

		layerUtils.iConfirm("是否修该此产品信息？", function() {
			console.log(param);
			$state.go("commoditymodify", {
				param : param
			});
		}, function() {
			console.log("取消");
		})

	}
	//删除
	$scope.batchDeleteInfo = function() {
		
		if($scope.selected.length == 0){
        	layerUtils.iMsg(-1, "请选择要删除的记录！");
			return;
        }

		layerUtils.iConfirm("是否删除该商品？", function() {
			for (var h = 0; h < $scope.selected.length; h++) {			
	        	var infoId = $scope.selected[h];	
	  	      for (var i = 0; i < $scope.commodityList.length; i++) {
			        var tempInfo = $scope.commodityList[i];
			        if(tempInfo.id == infoId){
			        	var param = tempInfo;
			        }
			      }  
	  	    var url = httpUtils.url.deleteCommodity;
	  	    console.log(param.id);
			$http.post(url, param).success(function(data) {
				if (data.resCode != 0) {
					layerUtils.iMsg(-1, "删除失败,含有关联子项");	
				}
			});
	        }
			$scope.getCommodityList(50);
			
		}, function() {
			console.log("取消");
		});
	}
	

}