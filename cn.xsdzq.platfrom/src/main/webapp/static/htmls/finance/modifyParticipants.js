ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils', 'utils'];
function modifyParticipantsController($scope, $http, $state, $stateParams, httpUtils, layerUtils, utils) {
	
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "参赛人员管理",
					goto:""

				},
				"two" : {
					name : "修改员工信息",
					goto:"modifyContestant"

				}
		}
			
		$scope.$emit("changeNavigation", data);
		var flag = utils.isEmptyObject($stateParams.participant);
		if(flag){
			$state.go("participantsList");
			return;
		}
		
		$scope.divisionFromList = [{
			name:"新手赛区",
			code:"0"
		},{
			name:"王者赛区",
			code:"1"
		}]
		var departmentCode = $stateParams.participant.departmentCode;
		var division = $stateParams.participant.division;
		if(division == 0){
			$scope.selectedDivision = $scope.divisionFromList[0];
		}else if (division == 1) {
			$scope.selectedDivision = $scope.divisionFromList[1];
		}
		angular.copy($stateParams.participant,$scope.formData);
		$http.get(httpUtils.url.departmentList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.departments = data.result;
				$scope.departments.forEach(function(item,index) {
					if(item.code == departmentCode) {
						$scope.selectedName = $scope.departments[index];
						return;
					}
				})
			}
		});
		
		
	};
	

	$scope.submit = function() {
//		if (angular.isEmpty($scope.formData.code)) {
//			layerUtils.iMsg(-1, "产品代码不能为空");
//			return;
//		}
//		if (angular.isEmpty($scope.formData.name)) {
//			layerUtils.iMsg(-1, "产品名称不能为空");
//			return;
//		}
//		if (angular.isEmpty($scope.formData.type)) {
//			layerUtils.iMsg(-1, "产品类型不能为空");
//			return;
//		}
//		if (angular.isEmpty($scope.formData.name)) {
//			layerUtils.iMsg(-1, "产品名称不能为空");
//			return;
//		}
//		if (angular.isEmpty($scope.formData.begin_date)) {
//			layerUtils.iMsg(-1, "产品开放时间不能为空");
//			return;
//		}
//		
//		if (angular.isEmpty($scope.formData.begin_date)) {
//			layerUtils.iMsg(-1, "产品开放时间不能为空");
//			return;
//		}
//		if (angular.isEmpty($scope.formData.coefficient)) {
//			layerUtils.iMsg(-1, "产品系数不能为空");
//			return;
//		}
//		if(angular.equals($scope.formData,$stateParams.product)){
//			layerUtils.iMsg(-1,"请修改后，重新提交");
//			return;
//		}
	
		console.log($scope.formData);
		var url = httpUtils.url.modifyEmp;
		$http.post(url, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iAlert("修改成功",function(){
					$state.go("participantsList");
				});
			} else {
				layerUtils.iMsg(-1,"修改失败");
			}
		});
	};
}