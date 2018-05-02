ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils'];
function addAuthorityController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};
	$scope.formData.role={};
	$scope.roleList=[];
	$scope.authorityList=[];
	
	$scope.init=function(){
		var data = {
				"one" : {
					name : "授权管理",
					goto:""

				},
				"two" : {
					name : "角色赋权",
					goto:"addauthority"

				}
			}
		$scope.$emit("changeNavigation", data);
		$http.get(httpUtils.url.roleList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.roleList = data.result;
				$scope.formData.role=$scope.roleList[0];
				$scope.getAuthorityListByRole($scope.roleList[0]);
			}
		});
	
	};

	$scope.toRelate = function() {
		$state.go("addrelate");
	};
	
	$scope.addCategory = function() {
		$state.go("newcategory");
	}
	
	$scope.labelList=[];
	
	$scope.getAuthorityListByRole = function(role){
		$http.post(httpUtils.url.authorityListByRole, role).success(function(data) {
			if (data.resCode == 0) {
				$scope.authorityList = data.result;
			}
		});
	}

	$scope.submit = function() {
		var params={};
		var authorities=[];
		var len=$scope.authorityList.length;
		// 增加的权限
		for(var i=0;i<len;i++){
			var len2=$scope.authorityList[i].child.length;
			for(var j=0;j<len2;j++){
				var authority=$scope.authorityList[i].child[j];
				if(authority.check){
					authorities.push(authority);
				}
			}
		}
		params.role_id=$scope.formData.role.id;
		params.authorityEntities=authorities;
		var url = httpUtils.url.addPermissions;
		$http.post(url, params).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"添加成功");
				$scope.formData={};
			} else {
				layerUtils.iMsg(-1,"添加失败");
			}
		});
	};
}