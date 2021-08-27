ngApp.$inject = ['$scope', '$http', '$state', '$stateParams','httpUtils', 'layerUtils', 'utils'];
function modifyRoleController($scope, $http, $state, $stateParams, httpUtils, layerUtils, utils) {
	$scope.permissions=[];
	$scope.init=function(){
		var data = [{
				name : "用户管理",
				goto:""
			}, {
				name : "用户列表管理",
				goto:"userlist"
			}, {
				name : "修改角色",
				goto:"modifyrole"
			}];
		$scope.$emit("changeNavigation", data);
		var flag = utils.isEmptyObject($stateParams.user);
		if(flag){
			$state.go("userlist");
			return;
		}
		$scope.user=$stateParams.user;
		//console.log($scope.user);
		$scope.getPermissions($scope.user);
	};
	$scope.getPermissions=function(){
		var url=httpUtils.url.getPermissionsByUser;
		$http.post(url,$scope.user).success(function(data){
			if (data.resCode == 0) {
				$scope.permissions=data.result;
			}
		});
	}
	

	$scope.submit = function() {
		var params={};
		var rList = [];
		var len = $scope.permissions.length;
		// 增加的权限
		for (var i = 0; i < len; i++) {
			var role = $scope.permissions[i];
			if (role.check) {
				rList.push(role);
			}
		}
		params.user_id = $scope.user.id;
		params.roleDTOs = rList;
		var url = httpUtils.url.userModifyRoles;
		
		$http.post(url, params).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1, "添加成功");
			} else {
				layerUtils.iMsg(-1, "添加失败");
			}
		});
	};
}