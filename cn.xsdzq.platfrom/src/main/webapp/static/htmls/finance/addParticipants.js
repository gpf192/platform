ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils'];
function addParticipantsController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "参赛人员管理",
					goto:""

				},
				"two" : {
					name : "新增员工信息",
					goto:"addParticipants"

				}
			}
			$scope.$emit("changeNavigation", data);
		
		$scope.formData.emp_name="";
		$scope.formData.emp_code="";
		$scope.formData.departmentCode="";
		$scope.formData.sales_department="";
		$scope.formData.division="";
		$scope.formData.emp_category="";
		$scope.formData.contract="";
		$scope.formData.entry_time="";
	};
	
	
	$scope.newBuild = function() {
		var url = httpUtils.url.addEmp;
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