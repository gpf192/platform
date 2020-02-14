ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' ];
function kcListController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils) {

	$scope.formData = {};
	$scope.formData.category = {};
	$scope.categoryList = [];
	$scope.infoList = [];
    $scope.approveResultOption = {
    		 全部: "all",
             公司: "company",
             经纪: "broker"
        };   
	$scope.init = function() {
		//审核状态 默认为查询 全部
		$scope.approveResult = $scope.approveResultOption['全部'];
		$scope.formData.beginTime = '';
		 $scope.formData.endTime = '';
		
		
		var data = [{
			name : "两融及期权预约",
			goto:"infolist"
		}, {
			name : "两融及期权预约",
			goto:"infolist"
		}];
		$scope.$emit("changeNavigation", data);
		$scope.getOpinionBy(2000);
		
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
				$scope.getOpinionBy(newValue);
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
    
		$scope.addCategory = function() {
			$state.go("newinfo");
		}
    
    // end
	$scope.getOpinionBy = function(pageSize) {
		if( ($scope.formData.beginTime != '') && ($scope.formData.endTime != '')){
			if ($scope.formData.endTime < $scope.formData.beginTime) {
				layerUtils.iMsg(-1, "结束时间不能早于开始时间！");
				return;
			}
		}
		
		var url = httpUtils.url.kcList;
		var params = {
			beginTime : $scope.formData.beginTime,
			endTime : $scope.formData.endTime,
			pageNumber : 0,
			pageSize : pageSize
		};
		console.log(params);
		var settings = {
			url : url,
			showPage : 7,
			pageSize : pageSize,
			putDataList : "infoList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	
	
	

 
	//导出为excel
	$scope.exportToExcel=function(){ 
		var excelArrs = getExcelData();
		var myDate = new Date();
		 alasql.promise('SELECT * INTO XLSX("两融及期权预约开户记录表-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
		var columns = 8;
		angular.forEach($scope.infoList, function(data, index, datas) {
			var newObj = {	
				
			};
			var column = 0;
			if(data.hasOwnProperty('minList')) {
				var minList = data.minList; 
				if(minList != null && minList.length > 0){
					angular.forEach(minList, function(dataM, indexM, datasM) {
						if(dataM){
							if(dataM.value){
								column++;
								newObj["value"+indexM] = dataM.value;
								if(dataM.predict)
									newObj["date"+indexM] = dataM.date;
							}
						}
					});
				}
			}
			for(;column < columns ; column++){
				
			}
			for(k=0;k<$scope.infoList.length;k++){				
				newObj["姓名"] = 	data.name;
				newObj["客户号"] = 	data.clientId;
				newObj["手机号码"] = 	data.phone;
				newObj["预约时间"] = 	data.createtime;
				
				
			}
			
			
			arr.push(newObj);
		});
		return arr;
	}
	 
 
	$scope.reset = function() {
		 $scope.formData.beginTime = '';
		 $scope.formData.endTime = '';
		
	}
	
	
}