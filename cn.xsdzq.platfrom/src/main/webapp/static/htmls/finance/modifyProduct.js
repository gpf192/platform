ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils', 'utils'];
function modifyProductController($scope, $http, $state, $stateParams, httpUtils, layerUtils, utils) {
	
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "活动产品管理",
					goto:""

				},
				"two" : {
					name : "修改活动产品",
					goto:"modifyProduct"

				}
		}
			
		$scope.$emit("changeNavigation", data);
		var flag = utils.isEmptyObject($stateParams.product);
		if(flag){
			$state.go("activityProductsList");
			return;
		}
		
		$scope.tradePlaceFromList = [{
			name:"场内基金",
			code:"0"
		},{
			name:"场外基金",
			code:"1"
		}]
	var flag =	$stateParams.product.flag;
		if(flag ==0) {
			$scope.selectedTradePlace = $scope.tradePlaceFromList[0];
		}else if(flag ==1) {
			$scope.selectedTradePlace = $scope.tradePlaceFromList[1];
		}
		
		angular.copy($stateParams.product,$scope.formData);
	};
	

	$scope.submit = function() {
		if (angular.isEmpty($scope.formData.code)) {
			layerUtils.iMsg(-1, "产品代码不能为空");
			return;
		}
		if (angular.isEmpty($scope.formData.name)) {
			layerUtils.iMsg(-1, "产品名称不能为空");
			return;
		}
		if (angular.isEmpty($scope.formData.type)) {
			layerUtils.iMsg(-1, "产品类型不能为空");
			return;
		}

		if (angular.isEmpty($scope.formData.begin_date)) {
			layerUtils.iMsg(-1, "产品开放时间不能为空");
			return;
		}
		
		if (angular.isEmpty($scope.formData.coefficient)) {
			layerUtils.iMsg(-1, "产品系数不能为空");
			return;
		}
		if(angular.equals($scope.formData,$stateParams.product)){
			layerUtils.iMsg(-1,"请修改后，重新提交");
			return;
		}
	
		console.log($scope.formData);
		var url = httpUtils.url.modifyProduct;
		
		var newProduct = {
				id:$stateParams.product.id,
				code:$scope.formData.code,
				name:$scope.formData.name,
				type:$scope.formData.type,
				begin_date:$scope.formData.begin_date,
				coefficient:$scope.formData.coefficient	,
				initialAmount:$scope.formData.initialAmount,
				flag: $scope.selectedTradePlace.code,
				endDate:$scope.formData.end_Date
		}
		$http.post(url, newProduct).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iAlert("修改成功",function(){
					$state.go("activityProductsList");
				});
			} else {
				layerUtils.iMsg(-1,"修改失败");
			}
		});
	};
}