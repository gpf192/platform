ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils'];
function userListController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};
	$scope.users=[];
	
	$scope.init=function(){
		var data = {
				"one" : {
					name : "用户管理",
					goto:"userlist"

				},
				"two" : {
					name : "用户列表管理",
					goto:"userlist"

				}
			}
		$scope.$emit("changeNavigation", data);
		$scope.getUserList();
	};
	
	$scope.modifyRole =function(index){
		var user=$scope.users[index];
		console.log(user);
		$state.go("modifyrole",{user:user});
	}
	
	$scope.getUserList = function() {
		$http.get(httpUtils.url.userList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.users = data.result;
			}
		});
	}
	
	
	$scope.modifyUser =function(index){
		var user=$scope.users[index];
		console.log(user);
		$state.go("modifyuser",{user:user});
	}
	
	$scope.deleteUser = function(index) {
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

	$scope.submit = function() {
		
		var url = httpUtils.url.addUser;
		$http.post(url, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"添加成功");
				$scope.formData={};
			} else {
				layerUtils.iMsg(-1,"添加失败");
			}
		});
	};
}