ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils', 'utils'];
function modifyVideoController($scope, $http, $state, $stateParams, httpUtils, layerUtils, utils) {
	
	$scope.formData = {};
	$scope.temp = {};
	$scope.init=function(){
		var data = [{
					name : "基金视频管理",
					goto:""
				},
				
				{
					name : "编辑视频",
					goto:"modifyVideo"
				}];
		$scope.$emit("changeNavigation", data);
		
		var flag = utils.isEmptyObject($stateParams.user);
		if(flag){
			$state.go("videoList");
			return;
		}
		angular.copy($stateParams.user,$scope.formData);
		UM.getEditor('myEditor').setContent($scope.formData.content);
		if($scope.formData.fundType === 1){
			$scope.temp.ft = "持营类";
		}else if($scope.formData.fundType === 2){
			$scope.temp.ft = "新发类";
		}
	};
	
	var um = UM.getEditor('myEditor');
	$scope.submit = function() {
		if (angular.isEmpty($scope.formData.name)) {
			layerUtils.iMsg(-1, "用名称不能为空");
			return;
		}
		if (angular.isEmpty($scope.formData.startTime)) {
			layerUtils.iMsg(-1, "时间不能为空");
			return;
		}
		if($scope.formData.startTime.length != 8){
			layerUtils.iMsg(-1, "时间格式不正确");
			return;
		}
		if (angular.isEmpty($scope.formData.fundCode)) {
			layerUtils.iMsg(-1, "基金代码不能为空");
			return;
		}
		if (angular.isEmpty($scope.formData.videoUrl)) {
			layerUtils.iMsg(-1, "视频链接不能为空");
			return;
		}
	
		$scope.formData.content = UM.getEditor('myEditor').getContent();
		if(angular.equals($scope.formData,$stateParams.user)){
			layerUtils.iMsg(-1,"当前无更新");
			return;
		}
	
		//console.log($scope.formData);
		var url = httpUtils.url.addVideo;
		$http.post(url, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iAlert("修改成功",function(){
					if($scope.formData.fundType === 1){
						$state.go("videoList");
					}else{
						$state.go("xfvideoList");
					}
					//$state.go("videoList");
				});
			} else {
				layerUtils.iMsg(-1,data.respMsg);
			}
		});
	};
}