ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' ];
function infoListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils) {

	$scope.formData = {};
	$scope.formData.category = {};
	$scope.categoryList = [];
	$scope.infoList = [];
    $scope.approveResultOption = {
            全部: "all",
            待提交: "generate",
            待审核: "submit",
            已发布: "approve"
        };   
	$scope.init = function() {
		//审核状态 默认为查询 全部
		$scope.approveResult = $scope.approveResultOption['全部'];
		var data = [{
			name : "信息管理",
			goto:"infolist"
		}, {
			name : "内容管理",
			goto:"infolist"
		}];
		$scope.$emit("changeNavigation", data);
		$http.get(httpUtils.url.categoryList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categoryList = data.result;
				//设置筛选条件为默认
				for(var k = 0; k < $scope.categoryList.length; k++){
					if($scope.categoryList[k].title == "全部"){
						$scope.formData.category = $scope.categoryList[k];	
					}
				}
				$scope.getInfosByCategoryId(10);
			}
		});
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
	      for (var i = 0; i < $scope.infoList.length; i++) {
	        var contact = $scope.infoList[i];
	        updateSelected(action, contact.id);
	      }
	    };
	    $scope.isSelected = function (id) {
	      return $scope.selected.indexOf(id) >= 0;
	    };
	    $scope.isSelectedAll = function () {
	      return $scope.selected.length === $scope.infoList.length;
	    };
    
    
    
    // end
	$scope.getInfosByCategoryId = function(pageSize) {
		var inforTitle1 = $scope.formData.title;	
		if (angular.isEmpty($scope.formData.title) ) {
			//如果用户未输入或者输入为空格 ， 该参数赋值为空
			inforTitle1 = '';		
		}
		var url = httpUtils.url.getInfoList;
		var params = {
			id : $scope.formData.category.id,
			infoTitle : inforTitle1,
			approveResult :$scope.approveResult,
			pageNumber : 0,
			pageSize : pageSize
		};
		var settings = {
			url : url,
			showPage : 7,
			pageSize : pageSize,
			putDataList : "infoList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	$scope.modifyInfo = function(index) {
		if($scope.selected.length > 1){
        	layerUtils.iMsg(-1, "请选择单条记录编辑");
			return;
        }
		layerUtils.iConfirm("是否修该此文章？", function() {
			var info = $scope.infoList[index];
			console.log(info);
			$state.go("modifyinfo", {
				info : info
			});
		}, function() {
			console.log("取消");
		})
	}
	$scope.viewInfo = function(index) {
		var info = $scope.infoList[index];
		console.log(info);
		var url = window.location.protocol+"//"+window.location.hostname+":"+window.location.port+"/platform/static/demo.html"+"?id="+info.id;
		window.open(url);
		//$state.go("viewinfo", {
		//	info : info
		//});
	}
	
	$scope.addWeight = function(index){
        if($scope.selected.length > 1){
        	layerUtils.iMsg(-1, "请选择单条记录置顶");
			return;
        }
		var url = httpUtils.url.addWeight;
		var param = $scope.infoList[index];
		 console.log(param);
		param.weight=param.weight+1;
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1, "置顶成功");
			}
		});
	}
	
	$scope.deleteInfo = function(index) {
		if($scope.selected.length > 1){
        	layerUtils.iMsg(-1, "请选择单条记录删除");
			return;
        }
		layerUtils.iConfirm("是否删除该文章？", function() {
			var url = httpUtils.url.deleteInfo;
			var param = $scope.infoList[index];
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
	$scope.copyUrl = function(index) {
		if($scope.selected.length > 1){
        	layerUtils.iMsg(-1, "请选择单条记录复制");
			return;
        }
		console.log($scope.infoList[index]);
		var info=$scope.infoList[index];
		var text=window.location.protocol+"//"+window.location.hostname+":"+window.location.port+"/front/#/detail/"+info.id;
		if (window.clipboardData) {//如果是IE浏览器
	        window.clipboardData.setData('text', text);
	    } else {//非IE浏览器
    	   var textarea = document.createElement('textarea');
    	   textarea.value = text;
           document.body.appendChild(textarea);
           textarea.select(); // 选择对象
           document.execCommand("Copy"); // 执行浏览器复制命令
    	   textarea.style.display='none';
	    }
		layerUtils.iMsg(-1, "复制成功");
	}
	//批量置顶
	$scope.batchAddWeight = function(){
        if($scope.selected.length != 1){
        	layerUtils.iMsg(-1, "请选择单条记录置顶");
			return;
        }
        for (var h = 0; h < $scope.selected.length; h++) {			
        	var infoId = $scope.selected[h];	
  	      for (var i = 0; i < $scope.infoList.length; i++) {
		        var tempInfo = $scope.infoList[i];
		        if(tempInfo.id == infoId){
		        	var param = tempInfo;
		        }
		      }        	
        }  
        console.log(param);
		var url = httpUtils.url.addWeight;
		//var param = $scope.infoList[index];
		param.weight=param.weight+1;
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1, "置顶成功");
			}
		});
	}
	//批量复制
	$scope.batchCopyUrl = function() {
		if($scope.selected.length != 1){
        	layerUtils.iMsg(-1, "请选择单条记录复制！");
			return;
        }
        for (var h = 0; h < $scope.selected.length; h++) {			
        	var infoId = $scope.selected[h];
   	      for (var i = 0; i < $scope.infoList.length; i++) {
		        var tempInfo = $scope.infoList[i];
		        if(tempInfo.id == infoId){
		        	if(tempInfo.checked_result != 'approve'){
		        		layerUtils.iMsg(-1, "未发布状态不可复制！");
		    			return;
		        	}
		        	var param = tempInfo;
		        }
		      } 
        }
        console.log(param);
        
		var text=window.location.protocol+"//"+window.location.hostname+":"+window.location.port+"/front/#/detail/"+infoId;
		if (window.clipboardData) {//如果是IE浏览器
	        window.clipboardData.setData('text', text);
	    } else {//非IE浏览器
    	   var textarea = document.createElement('textarea');
    	   textarea.value = text;
           document.body.appendChild(textarea);
           textarea.select(); // 选择对象
           document.execCommand("Copy"); // 执行浏览器复制命令
    	   textarea.style.display='none';
	    }
		layerUtils.iMsg(-1, "复制成功");
	}
	//批量编辑
	$scope.batchModifyInfo = function() {
		if($scope.selected.length != 1){
        	layerUtils.iMsg(-1, "请选择单条记录编辑！");
			return;
        }
		for (var h = 0; h < $scope.selected.length; h++) {			
        	var infoId = $scope.selected[h];	
  	      for (var i = 0; i < $scope.infoList.length; i++) {
		        var tempInfo = $scope.infoList[i];
		        if(tempInfo.id == infoId){
		        	if(tempInfo.checked_result != 'generate'){
		        		layerUtils.iMsg(-1, "非待提交状态不可编辑！");
		    			return;
		        	}
		        	var param = tempInfo;
		        }
		      }        	
        } 
		console.log(param);
		layerUtils.iConfirm("是否修该此文章？", function() {
			console.log(param);
			$state.go("modifyinfo", {
				info : param
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
  	      for (var i = 0; i < $scope.infoList.length; i++) {
		        var tempInfo = $scope.infoList[i];
		        if(tempInfo.id == infoId){
		        	if(tempInfo.checked_result != 'generate'){
		        		layerUtils.iMsg(-1, "非待提交状态不可删除！");
		    			return;
		        	}
		        	var param = tempInfo;
		        }
		      }        	
        } 
	
		layerUtils.iConfirm("是否删除该文章？", function() {
			var url = httpUtils.url.deleteInfo;
			$http.post(url, param).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "删除成功");
					$scope.selected = [];//及时删除，否则下次删除仍存在变量中
					$scope.getInfosByCategoryId(10);
				}
			});
		}, function() {
			console.log("取消");
		});
	}
}