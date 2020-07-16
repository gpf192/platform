ngApp.$inject = ['$scope', '$http', '$state',  '$stateParams','httpUtils', 'layerUtils'];
function addParamController($scope, $http, $state, $stateParams, httpUtils, layerUtils) {
	$scope.formData = {};

	$scope.init=function(){
		
		var data = {
				"one" : {
					name : "系统参数查询",
					goto:""

				},
				"two" : {
					name : "新建参数",
					goto:"addParam"

				}
			}
			$scope.$emit("changeNavigation", data);
		$scope.formData.title="";
		$scope.formData.code="";
		$scope.formData.value="";
	};
	
	
	

	//添加
	$scope.newBuild = function() {
		var url = httpUtils.url.addParam;
		$http.post(url, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"添加成功");
				$scope.formData={};
			} else {
				layerUtils.iMsg(-1,"添加失败");
			}
		});

	}
	
}