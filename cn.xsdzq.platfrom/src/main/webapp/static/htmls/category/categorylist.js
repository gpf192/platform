ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', 'httpUtils','layerUtils' ];
function categoryListController($scope, $http, $state, $stateParams, httpUtils,layerUtils) {

	$scope.categorys = [];
	$scope.init = function() {
		var data = [{
					name : "栏目",
					goto:"newcategory"
				},{
					name : "栏目管理",
					goto:"categorylist"
				}]
		$scope.$emit("changeNavigation", data);
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
		console.log($scope.categorys[index]);
		$state.go("modifyCategory", {
			category : $scope.categorys[index]
		});
	};

	$scope.deleteCategory = function(index) {
		layerUtils.iConfirm("是否需要删除栏目？删除栏目同时将会删除栏目下所有文章？", function() {
			var id = $scope.categorys[index].id;
			var url = httpUtils.url.deleteCategory
			var param = {
					id:id
			}
			$http.post(url, param).success(function(data) {
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