ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils'];
function newUserController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};
	$scope.categoryList=[];
	
	$scope.init=function(){
		
	};

	$scope.toRelate = function() {
		$state.go("addrelate");
	};
	
	$scope.addCategory = function() {
		$state.go("newcategory");
	}
	
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