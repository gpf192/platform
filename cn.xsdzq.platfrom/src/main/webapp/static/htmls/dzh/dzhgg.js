ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils','utils' ];
function dzhggController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {

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
		
		$scope.approveResult = $scope.approveResultOption['全部'];
		$scope.formData.beginTime = '';
		 $scope.formData.endTime = '';
		
		
		var data = [{
			name : "用户信息",
			goto:"infolist"
		}, {
			name : "用户信息",
			goto:"infolist"
		}];
		$scope.$emit("changeNavigation", data);
		$scope.getOpinionBy(2000);
		$http.get(httpUtils.url.dzhActivityList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categoryList = data.result;
				//设置筛选条件为默认
				for(var k = 0; k < $scope.categoryList.length; k++){
					if($scope.categoryList[k].name == "全部"){
						$scope.formData.category = $scope.categoryList[k];	
					}
				}
				
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

		var url = httpUtils.url.dzhgg;
		
		var activity = "";
		var name = "";
		var phone = "";
		//var activity = $scope.formData.category.name;
		/*if(!utils.isEmpty($scope.formData.category.name) || !$scope.formData.category.name == "全部" ) {
			activity = $scope.formData.category.name;
		}*/
		/*if($scope.formData.category.name == "全部"){
			
			activity = "";
		}else{
			activity = $scope.formData.category.name;
		}*/
		if(!utils.isEmpty($scope.formData.category.name)) {
			activity = $scope.formData.category.name;
		}
		if(!utils.isEmpty($scope.formData.name)) {
			name = $scope.formData.name;
		}
		if(!utils.isEmpty($scope.formData.phone)) {
			phone = $scope.formData.phone;
		}
		
		var params = {
				
			activity : activity,
			name : name,
			phone: phone,
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
		 alasql.promise('SELECT * INTO XLSX("客户信息记录表-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
				newObj["活动名称"] = 	data.activity;
				newObj["客户姓名"] = 	data.name;
				newObj["手机号"] = 	data.phone;
				newObj["时间"] = 	data.recordtime;
				
				
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