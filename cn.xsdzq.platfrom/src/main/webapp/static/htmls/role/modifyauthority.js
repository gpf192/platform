ngApp.$inject = ['$scope', '$http', '$state', '$stateParams','httpUtils', 'layerUtils', 'utils'];
function modifyAuthorityController($scope, $http, $state, $stateParams, httpUtils, layerUtils, utils) {
	$scope.permissions=[];
	$scope.init=function(){
		var data = [{
				name : "角色管理",
				goto:""
			}, {
				name : "角色列表管理",
				goto:"rolelist"
			}, {
				name : "修改权限",
				goto:"modifyauthority"
			}];
		$scope.$emit("changeNavigation", data);
		var flag = utils.isEmptyObject($stateParams.role);
		if(flag){
			$state.go("rolelist");
			return;
		}
		$scope.role=$stateParams.role;
		$scope.getPermissions();
	};
	$scope.getPermissions=function(){
		var url=httpUtils.url.getRoleAuthorityToModify;
		$http.post(url,$scope.role).success(function(data){
			if (data.resCode == 0) {
				$scope.permissions=data.result;
			}
		});
	}
	

	$scope.submit = function() {
		var params={};
		var pList = [];
		var len = $scope.permissions.length;
		// 增加的权限
		for (var i = 0; i < len; i++) {
			for(var j=0; j<$scope.permissions[i].child.length;j++ ){
				var permission=$scope.permissions[i].child[j];
					pList.push(permission);
			}
		}
		params.authorityEntities = pList;
		params.role_id = $scope.role.id;
		var url = httpUtils.url.modifyRoleAuthority;
		$http.post(url, params).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iAlert("修改成功",function(){
					$state.go("rolelist");
				});
			} else {
				layerUtils.iMsg(-1, "修改失败");
			}
		});
	};
}