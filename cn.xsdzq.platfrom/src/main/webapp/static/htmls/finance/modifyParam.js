ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils'];
function addPrizeController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};
	
	$scope.init=function(){
		
		var data = {
				"one" : {
					name : "系统参数查询",
					goto:""

				},
				"two" : {
					name : "修改参数",
					goto:"addParam"

				}
			}
			$scope.$emit("changeNavigation", data);
		var flag = utils.isEmptyObject($stateParams.param);
		if(flag){
			$state.go("paramlist");
			return;
		}
		angular.copy($stateParams.param,$scope.formData);
	};
	
	
	

	//添加
	$scope.newBuild = function() {
		var url = httpUtils.url.modifyParam;
		$http.post(url, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"修改成功");
				$scope.formData={};
			} else {
				layerUtils.iMsg(-1,"修改失败");
			}
		});

	}
	
}