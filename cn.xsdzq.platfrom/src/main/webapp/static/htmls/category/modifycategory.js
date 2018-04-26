ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils'];
function modifyCategoryController($scope, $http, $state, $stateParams, httpUtils, layerUtils) {
	$scope.formData = {};
	$scope.init = function() {
		var data = {
				"one" : {
					name : "分类管理",
					goto:""

				},
				"two" : {
					name : "分类名称修改",
					goto:"modifyCategory"

				}
			}
		$scope.$emit("changeNavigation", data);
		angular.copy($stateParams.category,$scope.formData);
		if (angular.equals($scope.formData, {})) {
			$state.go("categorylist");
		}
	};

	$scope.submit = function() {
		if(angular.equals($scope.formData, $stateParams.category)){
			layerUtils.iMsg(-1, "请输入更改标题");
			return;
		}
		$http.post(httpUtils.url.modifyCategory, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				//window.sessionStorage.setItem("moduleName", $scope.formData.moduleName);
				layerUtils.iMsg(-1, "修改成功");
			} else {
				layerUtils.iMsg(-1, data.resMsg);
			}
		});
	};
	
}