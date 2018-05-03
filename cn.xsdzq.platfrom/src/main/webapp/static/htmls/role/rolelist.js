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
	$scope.modifyRoleName =function(index){

		//$state.go("modifyrole",{user:user});
	}
	
	$scope.deleteRole = function(index) {
		layerUtils.iConfirm("是否需要删除用户", function() {
			var url = httpUtils.url.deleteUser
			var user=$scope.users[index];
			$http.post(url, user).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "删除成功");
					$scope.getUserList();
				}
			});
		}, function() {
			console.log("取消");
		});
	}
}