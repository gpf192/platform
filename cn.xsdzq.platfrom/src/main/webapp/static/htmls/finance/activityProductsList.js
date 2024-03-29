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
					name : "参与活动产品信息 ",
					goto:"activityProductsList"

				}
			}
		$scope.$emit("changeNavigation", data);
		$scope.getActivityProductsList(20000);
		
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
		
		for (var h = 0; h < $scope.selected.length; h++) {			
        	var infoId = $scope.selected[h];	
  	      for (var i = 0; i < $scope.activityProductsList.length; i++) {
		        var tempInfo = $scope.activityProductsList[i];
		        if(tempInfo.id == infoId){
		        	var param = tempInfo;
		        }
		      }        	
        } 

		layerUtils.iConfirm("是否修该此产品信息？", function() {
			console.log(param);
			$state.go("modifyProduct", {
				product : param
			});
		}, function() {
			console.log("取消");
		})

	}
	//单条删除
	/*$scope.batchDeleteInfo = function() {
		if($scope.selected.length != 1){
        	layerUtils.iMsg(-1, "请选择单条记录删除！");
			return;
        }
		
		for (var h = 0; h < $scope.selected.length; h++) {			
        	var infoId = $scope.selected[h];	
  	      for (var i = 0; i < $scope.activityProductsList.length; i++) {
		        var tempInfo = $scope.activityProductsList[i];
		        if(tempInfo.id == infoId){
		        	var param = tempInfo;
		        }
		      }        	
        } 
		
		layerUtils.iConfirm("是否删除该产品？", function() {
			var url = httpUtils.url.deleteProduct;
			$http.post(url, param).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "删除成功");
					$scope.selected = [];
					$scope.getActivityProductsList(20000);
				}else {
					layerUtils.iMsg(-1, "删除失败");
				}
			});
		}, function() {
			console.log("取消");
		});
	}*/
	//批量删除
	$scope.batchDeleteInfo = function() {
		if($scope.selected.length == 0){
        	layerUtils.iMsg(-1, "请选择要删除的记录！");
			return;
        }
		
		
		
		layerUtils.iConfirm("是否删除该产品？", function() {
			for (var h = 0; h < $scope.selected.length; h++) {			
	        	var infoId = $scope.selected[h];	
	  	      for (var i = 0; i < $scope.activityProductsList.length; i++) {
			        var tempInfo = $scope.activityProductsList[i];
			        if(tempInfo.id == infoId){
			        	var param = tempInfo;
			        }
			      } 
	  	    var url = httpUtils.url.deleteProduct;
			$http.post(url, param).success(function(data) {
				if (data.resCode != 0) {
					layerUtils.iMsg(-1, "删除失败");
					
				}
			});
	        } 
			layerUtils.iMsg(-1, "删除成功");
			$scope.selected = [];
			$scope.getActivityProductsList(20000);
			
		}, function() {
			console.log("取消");
		});
	}
	

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
		console.log("lalal")
		var excelArrs = getExcelData();
		var myDate = new Date().Format("yyyyMMddhhmmss");
		 alasql.promise('SELECT * INTO XLSX("参与活动产品表-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
		angular.forEach($scope.activityProductsList, function(data, index, datas) {
			var newObj = {	
				
			};
			for(k=0;k<$scope.activityProductsList.length;k++){				
				newObj["产品代码"] = 	data.code;
				newObj["产品名称"] = 	data.name;
				newObj["产品风险等级"] = 	data.riskLevel;
				newObj["产品类型"] = 	data.type;
				newObj["起购金额"] = 	data.initial_amount;
				newObj["优惠信息"] = 	data.preferentialInfo;
				newObj["转化系数"] = 	data.coefficient;
				newObj["是否为场外基金"] = 	data.flag;
				newObj["是否扫描场内交易"] = 	data.scanFlag;
				newObj["开放时间"] = 	data.begin_date;
				newObj["截止时间"] = 	data.end_date;
			}
			arr.push(newObj);
		});
		return arr;
	}
//	var excelInput = document.getElementById("sendFile");
//	function newBatchesBuild() {
//		excelInput.addEventListener("change",function() {
//
//		});
//	}
//	
//	newBatchesBuild();
	$scope.newBatchesBuild = function() {
		if(excelInput.files.length<=0) {
		 	layerUtils.iMsg(-1, "请选择文件！");
			return;
		}
		var loadExcel;
		var excelFile = excelInput.files[0];
	    var reader = new FileReader();
	    reader.readAsBinaryString(excelFile);
	    reader.onload = function(e) {
	    	   var data = e.target.result;
	    	   loadExcel = XLSX.read(data, {
                   type: 'binary'
               });
               for(var i=0;i<loadExcel.SheetNames.length;i++){
            	 var data = XLSX.utils.sheet_to_json(loadExcel.Sheets[loadExcel.SheetNames[i]]);
            	 data.forEach(function(item) {
            		 var url = httpUtils.url.addProduct;
            			$http.post(url, item).success(function(data) {
            				if (data.resCode == 0) {
            				
            				} else {
            					
            				}
            			});
            	 })
               }
	    }
	    
	}
}