ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils' ];
function infoListController($scope, $http, $state, $stateParams, httpUtils, layerUtils) {

	$scope.formData = {};
	$scope.formData.category = {};
	$scope.categoryList = [];
	$scope.infoList = [];
	$scope.init = function() {
		$http.get(httpUtils.url.categoryList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categoryList = data.result;
				$scope.formData.category = $scope.categoryList[0];
			}
		});
	};
	$scope.getInfosByCategoryId = function() {
		var url = httpUtils.url.getInfoList;
		var getUrl = url + "/" + $scope.formData.category.id;
		$http.get(getUrl).success(function(data) {
			if (data.resCode == 0) {
				$scope.infoList = data.result;
			}
		});
	};
	$scope.modifyInfo = function(index) {
		var id = $scope.infoList[index].id;
		$state.go("modifyinfo", {
			id : {id:id}
		});
	}
	$scope.deleteInfo = function(index) {
		layerUtils.iConfirm("是否需要该文章？", function() {
			var url = httpUtils.url.deleteInfo;
			var param = $scope.infoList[index];
			$http.post(url, param).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "删除成功");
					$scope.getInfosByCategoryId();
				}
			});
		}, function() {
			console.log("取消");
		});
		
	}

}