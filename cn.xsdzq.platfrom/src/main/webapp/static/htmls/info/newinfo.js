ngApp.$inject = [ '$scope', '$http', '$state', 'httpUtils', 'layerUtils' ];
function newVersionController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};
	$scope.formData.category = {};
	$scope.categoryList = [];

	$scope.init = function() {
		var data = {
				"one" : {
					name : "信息管理",
					goto:""

				},
				"two" : {
					name : "新建信息",
					goto:"newinfo"

				}
			}
		$scope.$emit("changeNavigation", data);

		$http.get(httpUtils.url.categoryList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categoryList = data.result;
				$scope.formData.category = $scope.categoryList[0];
			}
		});
	};
	var um = UM.getEditor('myEditor');

	$scope.toRelate = function() {
		$state.go("addrelate");
	};

	$scope.addCategory = function() {
		$state.go("newcategory");
	}

	$scope.labelList = [];

	$scope.submit = function() {
		var editorContent = UM.getEditor('myEditor').getContent();
		console.log(editorContent);
		var params = {
			title : $scope.formData.title,
			categoryId : $scope.formData.category.id,
			content : editorContent
		};

		var url = httpUtils.url.addInfo;
		$http.post(url, params).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1, "添加成功");
				$scope.formData = {};
			} else {
				layerUtils.iMsg(-1, "添加失败");
			}
		});
	};
}