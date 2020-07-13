ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils', '$stateParams', '$gridService','utils'];
function userOrderListController($scope, $http, $state, httpUtils, layerUtils, $stateParams, $gridService,utils) {
	$scope.productsSellList = [];
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "同花顺展业平台管理",
					goto:"userOrderList"
				},
				"two" : {
					name : "用户订单列表", 
					goto:"userOrderList"

				}
			}
		$scope.$emit("changeNavigation", data);
		$scope.getWinPrizeList(20000);
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
					$scope.getWinPrizeList(newValue);
					$scope.currentPage.page=0;
				}
			}, true);
	};
	
	
	$scope.getWinPrizeList = function(pageSize) {
		
		var url = httpUtils.url.getUserOrder;
		var orderId = "";
		var username = "";
		var tgName = "";
		var productName = "";
		
		if(!utils.isEmpty($scope.formData.orderId)) {
			orderId = $scope.formData.orderId;
		}
		if(!utils.isEmpty($scope.formData.username)) {
			username = $scope.formData.username;
		}
		if(!utils.isEmpty($scope.formData.tgName)) {
			tgName = $scope.formData.tgName;
		}
		if(!utils.isEmpty($scope.formData.productName)) {
			productName = $scope.formData.productName;
		}
		var params = {
			pageNumber : 0,
			pageSize : pageSize,
			username : username,
			orderId : orderId,
			tgName : tgName,
			productName: productName
		};
		var settings = {
			url : url,
			showPage : 1,
			pageSize : pageSize,
			putDataList : "productsSellList"
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
		
		 alasql.promise('SELECT * INTO XLSX("同花顺用户订单数据-' + myDate+ '.xlsx",{headers:true}) FROM ?',[excelArrs])
			.then(function (data) {
			  if(data == 1){
				$timeout(function(){
				  console.log('数据导出成功！');
				})
			  }
			});
	};
	 
	//组装ecxel数据
	//订单状态
	function orderStatus(value) {
	    if(value == "1"){
	    	return "支付完成";	
	    }
	    if(value == "2"){
	    	return "已退款";
	    }
	    
	}
	//产品类型
	 function goodsType(value) {
		    if(value == "1"){
		    	return "服务包";	 
		    }
		    if(value == "2"){
		    	return "T策略";
		    }
		    if(value == "3"){
		    	return "付费live";
		    }
		    if(value == "4"){
		    	return "付费咨询";
		    }
		    if(value == "5"){
		    	return "付费群";
		    }
		    if(value == "6"){
		    	return "会员卡";
		    }
		}
	 //产品风险等级
	 function goodsRisk(value) {
		    if(value == "1"){
		    	return "低风险";
		    }
		    if(value == "2"){
		    	return "中低风险";
		    }
		    if(value == "3"){
		    	return "中风险";
		    }
		    if(value == "4"){
		    	return "中高风险";
		    }
		    if(value == "5"){
		    	return "高风险";
		    }
		    if(value == "6"){
		    	return "无";
		    }
		}
	 //用户风险等级
	 function userRisk(value) {
		    if(value == "1"){
		    	return "保守型";	    	
		    }
		    if(value == "2"){
		    	return "谨慎性";
		    }
		    if(value == "3"){
		    	return "稳健性";
		    }
		    if(value == "4"){
		    	return "积极性";
		    }
		    if(value == "5"){
		    	return "激进型";
		    }
		    if(value == "6"){
		    	return "失信记录";
		    }
		}
	 //适当性匹配结果
	 function matchInstruction(value) {
		    if(value == "1"){
		    	return "匹配";	 
		    }
		    if(value == "0"){
		    	return "不匹配";
		    }
		    if(value == null){
		    	return "无匹配结果";
		    }
		 
		}
	 
	function getExcelData() {
		var arr =[];
		angular.forEach($scope.productsSellList, function(data, index, datas) {
			var newObj = {	
				
			};
			for(k=0;k<$scope.productsSellList.length;k++){				
				newObj["订单号"] = 		data.orderId;
				
				newObj["订单状态"] =  orderStatus(data.orderStatus);
				newObj["产品类型"] = 	goodsType(data.goodsType);
				newObj["商品ID"] = 		data.goodsId;
				newObj["用户ID"] = 		data.userId;
				newObj["用户姓名"] = 		data.username;
				newObj["用户身份证号码"] = 	data.certificate;
				newObj["用户身份证有效期"] = 	data.expiredate;
				newObj["投顾产品名称"] = 	data.productName;
				
				newObj["产品风险等级"] = 	goodsRisk(data.goodsRisk);
				
				newObj["用户风险等级"] = 	userRisk(data.evaluationResult);
				
				newObj["风险测评答案"] = 	data.evaluationAnswer;
				newObj["所属券商"] = 		data.brokerName;
				newObj["所属营业部名称"] = 	data.salesName;
				newObj["所属投顾姓名"] = 	data.tgName;
				newObj["投顾证书编号"] = 	data.tgCertification;
				newObj["服务周期"] = 		data.serviceCycle;
				newObj["服务开始日期"] = 	data.serviceCycleStart;
				newObj["服务结束日期"] = 	data.serviceCycleEnd;
				newObj["订阅金额"] = 		data.amount;
				
				newObj["订阅时间"] = 		data.bookTime;
				
				newObj["订阅来源"] = 		data.bookOrigin==2?"同花顺":data.bookOrigin;
				
				newObj["签署的所有协议"] = 	data.agreements;
				newObj["风险揭示书"] = 		data.riskRevelation;
				
				newObj["适当性匹配结果"] = 	matchInstruction(data.matchInstruction);
				
				newObj["联系地址"] = 		data.address;
				newObj["职业"] = 		data.occupation;
				newObj["用户手机号"] = 	data.mobile;
				newObj["风测时间"] = 		data.evaluationTime;
				
			}
			arr.push(newObj);
		});
		return arr;
	}
	
}