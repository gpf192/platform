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
						name : "查看信息",
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
	$scope.back = function(value) {
		history.go(-1);
	};
}