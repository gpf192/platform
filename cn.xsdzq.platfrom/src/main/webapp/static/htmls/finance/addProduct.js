ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils'];
function addProductController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "活动产品管理",
					goto:""

				},
				"two" : {
					name : "添加活动产品",
					goto:"addProduct"

				}
			}
			$scope.$emit("changeNavigation", data);
		
		$scope.formData.name="";
		$scope.formData.type="";
		$scope.formData.begin_date="";
		$scope.formData.coefficient="";
		$scope.formData.code="";
	};
	
	
	$scope.newBuild = function() {
		var url = httpUtils.url.addProduct;
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