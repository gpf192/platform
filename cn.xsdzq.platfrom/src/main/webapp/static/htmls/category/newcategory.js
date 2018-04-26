ngApp.$inject = [ '$scope', '$http', ' $state', 'httpUtils', 'layerUtils' ];
function newCategoryController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};

	$scope.init = function() {
		var data = {
			"one" : {
				name : "分类管理",
				goto:"newcategory"

			},
			"two" : {
				name : "新建分类",
				goto:"newcategory"

			}
		}
		$scope.$emit("changeNavigation", data);
	};

	$scope.submit = function() {
		var url = httpUtils.url.addCategory;
		if (angular.isEmpty($scope.formData.title)) {
			layerUtils.iMsg(-1, "分类名称不能为空");
			return;
		}
		$http.post(url, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1, "添加成功");
			} else {
				layerUtils.iMsg(-1, data.resMSg);
			}
		});
	};
}