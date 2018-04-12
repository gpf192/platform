ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', 'httpUtils'];
function appConfigController($scope, $http, $state, $stateParams, httpUtils) {

	$scope.formData = {};
	$scope.formData.release = {};
	$scope.releaseList = [];
	$scope.platforms = [{
		name : "android",
		vaule : true
	}, {
		name : "ios",
		vaule : true
	}];
	$scope.os = {};
	$scope.init = function() {
		$scope.os = $scope.platforms[0];
		$http.get(httpUtils.url.releaseList, {}).success(function(data) {
			//对象 对象
			if (data.resCode == 0) {
				$scope.releaseList = data.results;
				$scope.formData.release = data.results[0];
			}
		});
	};
	$scope.getAppConfig = function() {

		$http.post(httpUtils.url.queryAppConfig, $scope.formData.release).success(function(data) {
			if (data.resCode == 0) {
				//$scope.modules = data.results;
				$scope.appConfig=data.results;
				console.log($scope.modules);
			}
		});

	};

}