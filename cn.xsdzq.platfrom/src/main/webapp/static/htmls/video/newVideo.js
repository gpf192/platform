ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils'];
function newVideoController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};
	$scope.categoryList=[];
	
	$scope.init=function(){
		var data = {
				"one" : {
					name : "基金视频管理",
					goto:""

				},
				"two" : {
					name : "新建基金视频",
					goto:"newVideo"

				}
			}
			$scope.$emit("changeNavigation", data);
		$scope.formData.toogle = false;
		
	};
	$scope.labelList=[];
	var um = UM.getEditor('myEditor');
	
	$scope.submit = function() {
		if (angular.isEmpty($scope.formData.name)) {
			layerUtils.iMsg(-1, "名称不能为空");
			return;
		}
		if (angular.isEmpty($scope.formData.startTime) ) {
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
		
		var url = httpUtils.url.addVideo;
		$scope.formData.content = UM.getEditor('myEditor').getContent();
		$http.post(url, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"添加成功");
				$scope.formData={};
				UM.getEditor('myEditor').setContent('');
			} else {
				layerUtils.iMsg(-1,data.respMsg);
			}
		});
		
	};
}