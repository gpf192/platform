ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils'];
function roleListController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};
	$scope.roles=[];
	
	$scope.init=function(){
		var data = [{
					name : "角色管理",
					goto:""
				}, {
					name : "角色列表管理",
					goto:"rolelist"
				}
			];
		$scope.$emit("changeNavigation", data);
		$scope.getRoleList();
	};
	
	$scope.getRoleList = function() {
		$http.get(httpUtils.url.roleList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.roles = data.result;
			}
		});
	}
	$scope.modifyRoleName = function(index){
		var role = $scope.roles[index];
		$state.go("modifyrole2",{role:role});
	}
	$scope.modifyAuthority = function(index){
		var role = $scope.roles[index];
		$state.go("modifyauthority",{role:role});
	}
	
	$scope.deleteRole = function(index) {
		
		//layerUtils.iMsg(-1,"暂未开放删除功能");
		//return;
		layerUtils.iConfirm("用户对应的相应角色权限也会删除，是否需要删除角色？", function() {
			var url = httpUtils.url.deleteRole
			var role=$scope.roles[index];
			$http.post(url, role).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "删除成功");
					$scope.getRoleList();
				}else if(data.resCode == 2){
					layerUtils.iAlert("当前角色有用户正在使用，请删除用户中的角色后，在删除当前的角色！");
				}
			});
		}, function() {
			console.log("取消");
		});
	}
}