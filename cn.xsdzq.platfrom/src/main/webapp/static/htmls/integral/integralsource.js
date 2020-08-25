ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' ];
function integralsourceController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils) {

	$scope.formData = {};
	$scope.formData.category = {};
	$scope.categoryList = [];
	$scope.infoList = [];
    $scope.approveResultOption = {
            全部: "all", 
            待审核: "submit",
            已发布: "approve"
        };
	$scope.init = function() {
		//console.log("开始加载 目前选中数   "+ $scope.selected.length);
		//审核状态 默认为查询 待审核
		$scope.approveResult = $scope.approveResultOption['待审核'];
		var data = [{
			name : "审核管理",
			goto:""
		}, {
			name : "文章审核",
			goto:"checklist"
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
				$scope.getCheckInfosByCategoryId(10);
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
					$scope.getCheckInfosByCategoryId(newValue);//new   method ******
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
    $scope.batchApprove = function(){
        if($scope.selected.length == 0){
        	layerUtils.iMsg(-1, "未选择记录！");
			return;
        }
    
    	var countApprove=0;
    	//bug：全选包括多页的，infolist 只有本页的
        //循环选中的记录 
        for (var i = 0; i < $scope.selected.length; i++) {
            var infoId = $scope.selected[i];//获得  info-id
            //循环当前页的集合，与选中的对比
            for (var k = 0; k < $scope.infoList.length; k++) {
                var infoIdTemp = $scope.infoList[k].id;
                var infoCheckedResultTemp = $scope.infoList[k].checked_result;
                //如果比对成功，判断审核状态
                if(infoId == infoIdTemp){
                	if(infoCheckedResultTemp == 'approve'){
                		countApprove++;
                	}
                }              
              }                     
          }
        //比对完成后，判断选中的记录是不是同时又待审核的和 已发布， 如果有，不可批量操作
        //console.log("11111 "+countApprove);
        if(countApprove >0){
        	console.log("选中数 "+$scope.selected.length);
        	layerUtils.iMsg(-1, "已发布文章无法参与审批！");
			return;
        }
        //数据合法后，开始执行批量审批操作
        layerUtils.iConfirm("您确定要将选中的文章直接展示给客户？", function() {
        for (var h = 0; h < $scope.selected.length; h++) {
			var params = {
					id : $scope.selected[h],
					//true 代表审核通过
					checkFlag : true,
					action : 'approve'
				};
			//选中多页的  ，如果有已审批的 也会被再次审批！1！！ 怎样让选中变量 只保存当前页的数据					
			
				$http.post(httpUtils.url.modifyCheckResult, params).success(function(data) {
					if (data.resCode == 0) {			
						layerUtils.iMsg(-1, "批量审核操作成功");
						
					}
				});
        }
        location.reload(); //审批过后刷新
        }, function() {
			console.log("取消");
		});      
    };
    $scope.batchRevocation = function(){
        if($scope.selected.length == 0){
        	console.log("选中数 "+$scope.selected.length);
        	layerUtils.iMsg(-1, "未选择记录！");
			return;
        }
    	var countSubmit=0;
    	//bug：全选包括多页的，infolist 只有本页的
        //循环选中的记录 
        for (var i = 0; i < $scope.selected.length; i++) {
            var infoId = $scope.selected[i];//获得  info-id
            //循环当前页的集合，与选中的对比
            for (var k = 0; k < $scope.infoList.length; k++) {
                var infoIdTemp = $scope.infoList[k].id;
                var infoCheckedResultTemp = $scope.infoList[k].checked_result;
                //如果比对成功，判断审核状态
                if(infoId == infoIdTemp){
                	if(infoCheckedResultTemp == 'submit'){
                		countSubmit++;
                	}
                }              
              }                     
          }
        //比对完成后，判断选中的记录是不是待审核的， 如果有，不可批量操作
        
        if(countSubmit >0){
        	console.log("选中数 "+$scope.selected.length);
        	layerUtils.iMsg(-1, "待审核文章无法撤回！");
			return;
        }
      //数据合法后，开始执行批量撤回操作
		layerUtils.iConfirm("您确定要将选中的文章从帮助中心撤回？", function() {
        for (var h = 0; h < $scope.selected.length; h++) {
			var params = {
					id : $scope.selected[h],
					//false 代表退回  撤回，最终都是变为待提交状态
					checkFlag : false,
					action : 'callback'
				};	
				$http.post(httpUtils.url.modifyCheckResult, params).success(function(data) {
					if (data.resCode == 0) {			
						layerUtils.iMsg(-1, "批量撤回操作成功");
						
					}
				});
        	}
        	location.reload(); //撤回过后刷新
			}, function() {
				console.log("取消");
			});
			
		}
   
	//p end
	
	   
	$scope.getCheckInfosByCategoryId = function(pageSize) {
		$scope.selected = [];//清空作用域
		var inforTitle1 = $scope.formData.title;	
		if (angular.isEmpty($scope.formData.title) ) {
			//如果用户未输入或者输入为空格 ， 该参数赋值为空
			inforTitle1 = '';		
		}
		var url = httpUtils.url.getCheckInfoList;//new   method ******
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
	
/*	$scope.getCheckList = function(){
		$http.post(httpUtils.url.checkList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.checkList = data.result;
			}
		});
	};*/

	$scope.checkInfo = function(index){
		if ($scope.selected.length>1) {
			layerUtils.iMsg(-1, "请选择单行操作！");
			return;
		}
		var info = $scope.infoList[index];
		console.log(info);
		$state.go("checkdetail", {
			info : info
		});
	}
	$scope.revocation = function(index) {
		if ($scope.selected.length>1) {
			layerUtils.iMsg(-1, "请选择单行操作！");
			return;
		}
		layerUtils.iConfirm("是否要撤回该文章？撤回后，会进入待提交状态。", function() {
			var url = httpUtils.url.revocation;
			var param = $scope.infoList[index];
			$http.post(url, param).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "撤回成功");
					$scope.getCheckInfosByCategoryId(10);
				}
			});
		}, function() {
			console.log("取消");
		});
	}

}