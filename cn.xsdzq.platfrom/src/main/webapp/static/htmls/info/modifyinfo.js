ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils', 'utils' ];
function modifyInfoController($scope, $http, $state, $stateParams, httpUtils, layerUtils, utils) {
	$scope.formData = {};
	$scope.formData.category = {};
	$scope.formData.info = {};
	$scope.categoryList = [];
	var um = UM.getEditor('myEditor');

	$scope.init = function() {
		console.log($stateParams);
		var flag = utils.isEmptyObject($stateParams.id);
		if(flag){
			$state.go("infolist");
			return;
		}
		var id = $stateParams.id;
		var url = httpUtils.url.getInfoById + "/" + id.id;		
		$http.get(httpUtils.url.categoryList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categoryList = data.result;
				$http.get(url, {}).success(function(innerData) {
					if (innerData.resCode == 0) {
						$scope.formData.info = innerData.result;
						var categoryId = innerData.result.categoryId;
						console.log(categoryId);
						um.setContent(innerData.result.content);
						for(var i in $scope.categoryList){
							console.log($scope.categoryList[i]);
							if($scope.categoryList[i].id==categoryId){
								$scope.formData.category = $scope.categoryList[i];
							}
						}	
					}
				});
			}
		});
	};

	$scope.submit = function() {
		var editorContent = UM.getEditor('myEditor').getContent();
		console.log(editorContent);
		var params = {
			id :$scope.formData.info.id,
			title : $scope.formData.info.title,
			categoryId : $scope.formData.category.id,
			content : editorContent
		};
		$scope.formData.info.categoryId = $scope.formData.category.id;
		$scope.formData.info.content = editorContent;
		var url = httpUtils.url.modifyInfo;
		$http.post(url, $scope.formData.info).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1, "修改成功");
				$scope.formData = {};
			} else {
				layerUtils.iMsg(-1, data.resMsg);
			}
		});
	};
}