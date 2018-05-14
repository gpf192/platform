ngApp.$inject = [ '$scope', '$http', '$state', 'httpUtils', 'layerUtils' ];
function addRoleController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};
	$scope.formData.user = {};
	$scope.roleList = [];

	$scope.init = function() {
		var data = {
				"one" : {
					name : "授权管理",
					goto:""

				},
				"two" : {
					name : "用户赋权",
					goto:"addrole"

				}
			}
		$scope.$emit("changeNavigation", data);
		
		
		
		$http.get(httpUtils.url.userList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.userList = data.result;
				$scope.formData.user = $scope.userList[0];
				$scope.getExtraRoleListByUser($scope.userList[0]);
			}
		});

	};

	$scope.getExtraRoleListByUser = function(user) {

		$http.post(httpUtils.url.getExtraRole, user).success(function(data) {
			if (data.resCode == 0) {
				$scope.roleList = data.result;
			}
		});
	}

	$scope.toRelate = function() {
		$state.go("addrelate");
	};

	$scope.addCategory = function() {
		$state.go("newcategory");
	}

	$scope.submit = function() {
		var params={};
		var rList = [];
		var len = $scope.roleList.length;
		// 增加的权限
		for (var i = 0; i < len; i++) {
			var role = $scope.roleList[i];
			if (role.check) {
				rList.push(role);
			}
		}
		params.user_id = $scope.formData.user.id;
		params.roleDTOs = rList;
		var url = httpUtils.url.userAddRoles;
		
		$http.post(url, params).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1, "添加成功");
				$scope.formData = {};
			} else {
				layerUtils.iMsg(-1, "添加失败");
			}
		});
	};
}