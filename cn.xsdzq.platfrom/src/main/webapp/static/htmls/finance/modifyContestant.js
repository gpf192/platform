ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils', 'utils'];
function modifyProductController($scope, $http, $state, $stateParams, httpUtils, layerUtils, utils) {
	
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "活动数据统计",
					goto:""

				},
				"two" : {
					name : "修改参赛选手得票数据",
					goto:"modifyContestant"

				}
		}
			
		$scope.$emit("changeNavigation", data);
		var flag = utils.isEmptyObject($stateParams.contestant);
		if(flag){
			$state.go("contestantList");
			return;
		}
		angular.copy($stateParams.contestant,$scope.formData);
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
				coefficient:$scope.formData.coefficient	
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