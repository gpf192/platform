ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils' ];
function userBlacklistController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {
	$scope.formData = {};
	$scope.categoryList = [];
	$scope.cardList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "用户黑名单管理",
					goto:""
				},
				"two" : {
					name : "用户黑名单管理",
					goto:""

				}
			}
		$scope.getCardList(50);
		$scope.$emit("changeNavigation", data);
		
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
					getCardList(newValue);
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
	      for (var i = 0; i < $scope.cardList.length; i++) {
	        var contact = $scope.cardList[i];
	        updateSelected(action, contact.id);
	      }
	    };
	    $scope.isSelected = function (id) {
	      return $scope.selected.indexOf(id) >= 0;
	    };
	    $scope.isSelectedAll = function () {
	      return $scope.selected.length === $scope.cardList.length;
	    };
	
	$scope.getCardList = function(pageSize) {
		var clientId = "";
		var clientName = "";
		if(!utils.isEmpty($scope.formData.clientId)) {
			clientId = "%"+$scope.formData.clientId+"%";
		}
		if(!utils.isEmpty($scope.formData.clientName)) {
			clientName = "%"+$scope.formData.clientName+"%";
		}
		var url = httpUtils.url.getAllUserBlacklist;
		var params = {
			clientId:clientId,	
			clientName:clientName,
			pageNumber : 0,
			pageSize : pageSize
		};
		console.log(params);
		var settings = {
			url : url,
			showPage : 7,
			pageSize : pageSize,
			putDataList : "cardList"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	
	$scope.newBuild = function() {
		$state.go("userBlacklistAdd");
	}
	//批量导入
	$scope.cardImport = function() {
		$state.go("userBlacklistImport");
	}
	//编辑
	$scope.batchModifyInfo = function() {
		var param;
		if($scope.selected.length != 1){
        	layerUtils.iMsg(-1, "请选择单条记录编辑！");
			return;
        }
		
		for (var h = 0; h < $scope.selected.length; h++) {			
        	var infoId = $scope.selected[h];
        	
  	      for (var i = 0; i < $scope.cardList.length; i++) {
		        var tempInfo = $scope.cardList[i];
		        if(tempInfo.id == infoId){
		        	 param = tempInfo;
		        	break;
		        }
		      }        	
        } 
		

		layerUtils.iConfirm("是否修该此用户信息？", function() {
			$state.go("userBlacklistModify", {
				param : param
			});
		}, function() {
			param='';
			//console.log("取消");
		})

	}
	//删除
	$scope.batchDeleteInfo = function() {
	
		if($scope.selected.length == 0){
        	layerUtils.iMsg(-1, "请选择要删除的记录！");
			return;
        }
		for (var h = 0; h < $scope.selected.length; h++) {			
        	var infoId = $scope.selected[h];	
  	      for (var i = 0; i < $scope.cardList.length; i++) {
		        var tempInfo = $scope.cardList[i];
		        if(tempInfo.id == infoId){
		        	var param = tempInfo;
		        }
		      }  
  	      }
		layerUtils.iConfirm("是否删除该记录？", function() {
			var list=[];
			
			for (var h = 0; h < $scope.selected.length; h++) {			
	        	var infoId = $scope.selected[h];	
		  	      for (var i = 0; i < $scope.cardList.length; i++) {
				        var tempInfo = $scope.cardList[i];
				        if(tempInfo.id == infoId){
				        	list.push(infoId);
				        }
				      }  	  	      
		  	  
	        }
			 var url = httpUtils.url.deleteUserBlacklist;
				$http.post(url, list).success(function(data) {
					$scope.getCardList(50);
				});
			layerUtils.iMsg(-1, "删除成功");

			$scope.selected = [];
			
		}, function() {
			//console.log("取消");
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
		var excelArrs = getExcelData();
		var myDate = new Date().Format("yyyyMMddhhmmss");
		 alasql.promise('SELECT * INTO XLSX("黑名单导出-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
			.then(function (data) {
			  if(data == 1){
				$timeout(function(){
				  console.log('数据导出成功！');
				})
			  }
			});
	};
	function getExcelData() {
		var arr =[];
		angular.forEach($scope.cardList, function(data, index, datas) {
			var newObj = {	
				
			};
			for(k=0;k<$scope.cardList.length;k++){				
				newObj["客户号"] = 	data.clientId;
				newObj["客户姓名"] = 	data.clientName;
				newObj["营业部"] = 	(data.departmentDesc==null?"":data.departmentDesc);
				newObj["创建时间"] = 	data.createDate;
				
			}
			arr.push(newObj);
		});
		return arr;
	}
}