ngApp.$inject = [ '$scope', '$http', '$state', 'httpUtils', 'layerUtils' ];
function newVersionController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};
	$scope.formData.category = {};
	$scope.categoryList = [];
	
	$scope.init = function() {
		var data = [
				 {
					name : "信息管理",
					goto:"infolist"

				}, {
					name : "新建文章",
					goto:"newinfo"
				}];
		$scope.$emit("changeNavigation", data);

		$http.get(httpUtils.url.categoryList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categoryList = data.result;
				$scope.formData.category = $scope.categoryList[1];
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
	$scope.submit = function(value) {
		var editorContent = UM.getEditor('myEditor').getContent();
		if (angular.isEmpty($scope.formData.title)) {
			layerUtils.iMsg(-1, "标题不能为空");
			return;
		}
		if (angular.isEmpty($scope.formData.category) ) {
			layerUtils.iMsg(-1, "分类不能为空");
			return;
		}
		if (angular.isEmpty(editorContent)) {
			layerUtils.iMsg(-1, "信息内容不能为空");
			return;
		}
		//判断勾选框
		if($scope.commonFlagCheck){
			var commonFlag = 'Y';
		}else{
			var commonFlag = 'N';
		}
		console.log(10000000);
		console.log(commonFlag);
		if(value){
			var params = {
				title : $scope.formData.title,
				categoryId : $scope.formData.category.id,
				content : editorContent,
				flag : "submit"
				};		
		}else{
			var params = {
				title : $scope.formData.title,
				categoryId : $scope.formData.category.id,
				content : editorContent,
				flag : "generate"
				};			
		}
		var url = httpUtils.url.addInfo;
		$http.post(url, params).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1, "添加成功");
				$scope.formData.title = "";
				UM.getEditor('myEditor').setContent("");
			} else {
				layerUtils.iMsg(-1, "添加失败");
			}
		});
	};
}