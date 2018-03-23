ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils'];
function moduleListController($scope, $http, $state, $stateParams, httpUtils, layerUtils) {

	$scope.formData = {};
	$scope.releaseList = [];
	$scope.modules = [];
	$scope.init = function() {
		$http.get(httpUtils.url.releaseList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.releaseList = data.results;
				if (data.results.length > 0) {
					console.log("aaaaaaaaaaa");
					$scope.formData.release = $scope.releaseList[0];
				}
			}
		});
	};

	$scope.queryModules = function() {
		if (angular.isEmpty($scope.formData.release)) {
			layerUtils.iMsg("-1", "当前无列表信息，请您先新建版本");
			return;
		}
		$http.post(httpUtils.url.queryModules, $scope.formData.release).success(function(data) {
			if (data.resCode == 0) {
				$scope.modules = data.results;
				var modulePlatform = [];
				if ($scope.formData.release.android == "true") {
					modulePlatform.push("android");
				}
				if ($scope.formData.release.ios == "true") {
					modulePlatform.push("ios");
				}
				if (modulePlatform.length > 1) {
					$scope.modulePlatformString = modulePlatform.join(" | ");
				} else {
					$scope.modulePlatformString = modulePlatform.join("");
				}
			}
		});

	};

	$scope.deleteRelate = function(index) {
		layerUtils.iConfirm("是否需要解除模块关联?", function() {
			var url = httpUtils.url.deleteRelate;
			var params = {
				release : $scope.formData.release,
				module : $scope.modules[index]
			};
			$http.post(url, params).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "解绑成功");
					$scope.queryModules();
				}

			});

		}, function() {
			console.log("取消");
		});

	};

	$scope.processForm = function() {
		alert("awesome");
	};
}