ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils', 'utils'];
function modifyRoleController($scope, $http, $state, $stateParams, httpUtils, layerUtils, utils) {
	$scope.formData = {};	
	$scope.init=function(){
		var data = [{
			name : "角色管理",
			goto:"rolelist"
		}, {
			name : "角色列表管理",
			goto:"rolelist"
		}, {
			name : "修改角色",
			goto:"modifyrole"
		}];
		$scope.$emit("changeNavigation", data);
		var flag = utils.isEmptyObject($stateParams.role);
		if(flag){
			$state.go("rolelist");
			return;
		};
		angular.copy($stateParams.role,$scope.formData);

		
	};


	$scope.submit = function() {
		if(angular.equals($scope.formData,$stateParams.role)){
			layerUtils.iMsg(-1,"请修改后，重新提交");
			return;
		}
		
		var url = httpUtils.url.modifyRole;
		$http.post(url, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iAlert("修改成功",function(){
					$state.go("rolelist");
				});
			} else {
				layerUtils.iMsg(-1,"添加失败");
			}
		});
	};
}