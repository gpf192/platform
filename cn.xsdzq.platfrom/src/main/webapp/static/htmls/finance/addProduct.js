ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils','utils'];
function addProductController($scope, $http, $state, httpUtils, layerUtils,utils) {
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "活动产品管理",
					goto:""

				},
				"two" : {
					name : "添加活动产品",
					goto:"addProduct"

				}
			}
			$scope.$emit("changeNavigation", data);
		
		$scope.formData.name="";
		$scope.formData.type="";
		$scope.formData.begin_date="";
		$scope.formData.coefficient="";
		$scope.formData.code="";
		
		$scope.tradePlaceFromList = [{
			name:"场内基金",
			code:"0"
		},{
			name:"场外基金",
			code:"1"
		}]
		$scope.selectedTradePlace = $scope.tradePlaceFromList[0];
	};
	
	
	$scope.newBuild = function() {
		var url = httpUtils.url.addProduct;
		var code = "";
		var name = "";
		var type = "";
		var coefficient = "";
		var initialAmount = "";
		var flag = "";
		var beginDate = "";
		var endDate = "";
		if(!utils.isEmpty($scope.formData.code)) {
			code = $scope.formData.code;
		}else {
			layerUtils.iMsg(-1, "产品代码不能为空");
			return;
		}
		if(!utils.isEmpty($scope.formData.name)) {
			name = $scope.formData.name;
		}else {
			layerUtils.iMsg(-1, "产品名称不能为空");
			return;
		}
		if(!utils.isEmpty($scope.formData.type)) {
			type = $scope.formData.type;
		}else {
			layerUtils.iMsg(-1, "产品类型不能为空");
			return;
		}
		if(!utils.isEmpty($scope.formData.coefficient)) {
			coefficient = $scope.formData.coefficient;
		}else {
			layerUtils.iMsg(-1, "票数系数不能为空");
			return;
		}
		if(!utils.isEmpty($scope.formData.initialAmount)) {
			initialAmount = $scope.formData.initialAmount;
		}else {
			layerUtils.iMsg(-1, "起始金额不能为空");
			return;
		}
		if(!utils.isEmpty( $scope.selectedTradePlace.code)) {
			flag = $scope.selectedTradePlace.code;
		}else {
			layerUtils.iMsg(-1, "是否为场外基金不能为空");
			return;
		}
		if(!utils.isEmpty($scope.formData.begin_date)) {
			beginDate = $scope.formData.begin_date;
		}else {
			layerUtils.iMsg(-1, "开放时间不能为空");
			return;
		}
		if(!utils.isEmpty($scope.formData.end_Date)) {
			endDate = $scope.formData.end_Date;
		}else {
			layerUtils.iMsg(-1, "截止时间不能为空");
			return;
		}
			if (endDate < beginDate) {
				layerUtils.iMsg(-1, "结束时间不能早于开始时间！");
				return;
			}
		var param = {
				code:code,
				name:name,
				type:type,
				coefficient:coefficient,
				initialAmount:initialAmount,
				flag:flag,
				beginDate:beginDate,
				endDate:endDate
		}
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"添加成功");
				$scope.formData={};
			}else if (data.resCode == 1) {
				layerUtils.iMsg(-1,"  产品代码已存在 ");
				$scope.formData={};
			} else {
				layerUtils.iMsg(-1,"添加失败");
			}
		});
	}
	
}