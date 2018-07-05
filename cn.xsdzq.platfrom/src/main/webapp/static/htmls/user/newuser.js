ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils'];
function newUserController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};
	$scope.categoryList=[];
	
	$scope.init=function(){
		var data = {
				"one" : {
					name : "用户管理",
					goto:""

				},
				"two" : {
					name : "新建用户",
					goto:"newuser"

				}
			}
			$scope.$emit("changeNavigation", data);
		$scope.formData.username="";
		$scope.formData.password="";
	};
	
	$scope.labelList=[];

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