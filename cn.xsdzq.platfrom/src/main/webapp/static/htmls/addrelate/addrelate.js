ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', 'httpUtils'];
function addrRelateController($scope, $http, $state, $stateParams, httpUtils) {

	$scope.formData = {};
	$scope.formData.releases = [];
	$scope.tempReleases = [];
	$scope.init = function() {
		$scope.moduleList = [];
		$scope.releaseList = [];
		$http.get(httpUtils.url.moduleList, {}).success(function(data) {
			$scope.moduleList = data.results;
			$scope.formData.module = $scope.moduleList[0];
		});
		$scope.$watch("formData.module", function(newValue, oldValue) {
			console.log("newValue " + JSON.stringify(newValue));
			console.log("oldValue " + JSON.stringify(oldValue));
			if (!angular.equals(newValue, oldValue)) {
				$scope.getReleasesByModule();
			}
		}, true);
	};

	function getReleaseList() {
		$http.get(httpUtils.url.releaseList, {}).success(function(data) {
			$scope.releaseList = data.results;
		});
	}


	$scope.getReleasesByModule = function() {
		$http.post(httpUtils.url.getReleasesByModule, $scope.formData.module).success(function(data) {
			$scope.releaseList = data.results;
		});

	};
	$scope.submit = function() {
		console.log($scope.formData);
		console.log($scope.tempReleases);
		for (var i = 0; i < $scope.tempReleases.length; i++) {
			console.log($scope.tempReleases);
			if ($scope.tempReleases[i]) {
				$scope.formData.releases.push($scope.tempReleases[i]);
			}
		}
		console.log($scope.formData.releases);
		console.log($scope.formData);

		$http.post(httpUtils.url.addModuleRelease, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				var result = {
					"info" : "关系关联成功"
				};
				$state.go("result", {
					state : result
				});

			}
		});

	};
}