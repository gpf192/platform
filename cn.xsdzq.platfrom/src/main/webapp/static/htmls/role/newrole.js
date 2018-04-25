ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils'];
function newRoleController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};	
	$scope.init=function(){
		
	};


	$scope.submit = function() {
		
		var url = httpUtils.url.addRole;
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