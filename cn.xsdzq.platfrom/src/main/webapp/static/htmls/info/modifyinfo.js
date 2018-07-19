ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils', 'utils' ];
function modifyInfoController($scope, $http, $state, $stateParams, httpUtils, layerUtils, utils) {
	$scope.formData = {};
	$scope.formData.category = {};
	$scope.formData.info = {};
	$scope.categoryList = [];
	var um = UM.getEditor('myEditor');

	$scope.init = function() {
		var data = [{
						name : "信息管理",
						goto:"infolist"
					},{
						name : "信息列表管理",
						goto:"infolist"	
					},
					{
						name : "修改信息",
						goto:"modifyinfo"
					}];
		$scope.$emit("changeNavigation", data);
		var flag = utils.isEmptyObject($stateParams.info);
		if(flag){
			$state.go("infolist");
			return;
		}
		angular.copy($stateParams.info,$scope.formData);
		$scope.initData();
	};
	$scope.initData=function(){
		UM.getEditor('myEditor').setContent($scope.formData.content);
		$http.get(httpUtils.url.categoryList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categoryList = data.result;
				for(var i=0;i<data.result.length;i++){
					if($scope.formData.categoryId==data.result[i].id){
						$scope.formData.category = $scope.categoryList[i];
					}
				}
			}
		});
	}

	$scope.submit = function(value) {
		var editorContent = UM.getEditor('myEditor').getContent();
		console.log(editorContent);
/*		var params = {
			id :$scope.formData.id,
			title : $scope.formData.title,
			categoryId : $scope.formData.category.id,
			content : editorContent
		};*/
		if(value){
			var params = {
				id :$scope.formData.id,
				title : $scope.formData.title,
				categoryId : $scope.formData.category.id,
				content : editorContent,
				flag : "submit"
				};		
		}else{
			var params = {
					id :$scope.formData.id,
					title : $scope.formData.title,
					categoryId : $scope.formData.category.id,
					content : editorContent,
					flag : "generate"
					};			
		}
		// 允许用户在未修改的情况下，进行提交审核操作
/*		if(($stateParams.info.title==params.title)&&($stateParams.info.categoryId==params.categoryId)&&($stateParams.info.content==params.content)){
			layerUtils.iMsg(-1, "请修改后，在提交");
			return;
		}*/
		var url = httpUtils.url.modifyInfo;
		$http.post(url, params).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iAlert("修改成功",function(){
					$state.go("infolist");
				});
			} else {
				layerUtils.iMsg(-1, data.resMsg);
			}
		});
	};
}