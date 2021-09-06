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
					goto:"commoditymanage"

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
	//var temp = 0;
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
					
				}
			});
			
	       }

			layerUtils.iMsg(-1, "操作完成，若记录含有关联子项，则无法删除");
			$scope.selected = [];
			$scope.getCommodityList(50);
			
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
		var excelArrs = getExcelData();
		var myDate = new Date().Format("yyyyMMddhhmmss");
		 alasql.promise('SELECT * INTO XLSX("商品管理统计表-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
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
		angular.forEach($scope.commodityList, function(data, index, datas) {
			var newObj = {	
				
			};
			for(k=0;k<$scope.commodityList.length;k++){				
				newObj["所属分类"] = 	data.categoryName;
				newObj["商品名称"] = 	data.name;
				newObj["商品代码"] = 	data.code;
				newObj["商品面值（元"] = 	data.faceValue;
				newObj["实际价格（元）"] = 	data.value;
				
				newObj["简介"] = 	(data.tip==null?"":data.tip);
				newObj["产品介绍"] = 	(data.description==null?"":data.description);
				newObj["使用说明"] = 	(data.explain==null?"":data.explain);
				newObj["注意事项"] = 	(data.attention==null?"":data.attention);
				newObj["小图"] = 	(data.image==null?"":data.image);
				
				newObj["大图"] = 	(data.bigImage==null?"":data.bigImage);
				newObj["是否热门"] = 	(data.isHot?"是":"否");
				newObj["总数量"] = 	data.storeNumber;
				newObj["已兑换数量"] = 	data.convertNumber;
				newObj["剩余库存数量"] = 	data.storeUnused;
				newObj["下架数量"] = 	data.illegalNum;
				newObj["状态"] = (data.status==0?"已上架":"已下架");
				newObj["创建时间"] = 	data.createtime;
				
				
			}
			arr.push(newObj);
		});
		return arr;
	}

}