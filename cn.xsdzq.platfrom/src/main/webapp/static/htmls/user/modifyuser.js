ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils', 'utils'];
function modifyUserController($scope, $http, $state, $stateParams, httpUtils, layerUtils, utils) {
	
	$scope.formData = {};
	$scope.init=function(){
		var data = [{
					name : "用户管理",
					goto:""
				},
				{
					name : "用户列表管理",
					goto:"userlist"
				},
				{
					name : "修改用户",
					goto:"modifyuser"
				}];
		$scope.$emit("changeNavigation", data);
		var flag = utils.isEmptyObject($stateParams.user);
		if(flag){
			$state.go("userlist");
			return;
		}
		angular.copy($stateParams.user,$scope.formData);
	};
	

	$scope.submit = function() {
		if (angular.isEmpty($scope.formData.username)) {
			layerUtils.iMsg(-1, "用户名不能为空");
			return;
		}
		if (angular.isEmpty($scope.formData.password)) {
			layerUtils.iMsg(-1, "密码不能为空");
			return;
		}
		if (($scope.formData.lockFlag < 0 || $scope.formData.lockFlag > 5)) {
			layerUtils.iMsg(-1, "请输入5以内数字");
			return;
		}
		if(angular.equals($scope.formData,$stateParams.user)){
			layerUtils.iMsg(-1,"请修改后，重新提交");
			return;
		}
	
		console.log($scope.formData);
		var url = httpUtils.url.modifyUser;
		$http.post(url, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iAlert("修改成功",function(){
					$state.go("userlist");
				});
			} else {
				layerUtils.iMsg(-1,"修改失败");
			}
		});
	};
}