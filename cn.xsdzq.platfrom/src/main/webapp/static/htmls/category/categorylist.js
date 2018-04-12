ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', 'httpUtils','layerUtils' ];
function categoryListController($scope, $http, $state, $stateParams, httpUtils,layerUtils) {

	$scope.categorys = [];
	$scope.init = function() {
		$scope.getCategoryList();
	};
	$scope.getCategoryList = function() {
		$http.get(httpUtils.url.categoryList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categorys = data.result;
				console.log($scope.categorys);
			}
		});
	}

	$scope.modifyCategory = function(index) {
		$state.go("modifyCategory", {
			category : $scope.categorys[index]
		});
	};

	$scope.deleteCategory = function(index) {
		layerUtils.iConfirm("是否需要解除分类，将会删除分类下所有信息文章？", function() {
			var id = $scope.categorys[index].id;
			var url = httpUtils.url.deleteCategory + "/" + id;
			$http.post(url, {}).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "删除成功");
					$scope.getCategoryList();
				}
			});
		}, function() {
			console.log("取消");
		});
	}

}