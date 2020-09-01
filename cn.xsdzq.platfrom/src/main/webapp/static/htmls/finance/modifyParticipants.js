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
		var url = httpUtils.url.modifyEmp;
		var emp_name = "";
		var emp_code = "";
		var departmentCode = "";
		var division = "";
		var emp_type = "";
		var emp_category = "";
		var contract = "";
		var entry_time = "";
		if(!utils.isEmpty($scope.formData.emp_name)) {
			emp_name = $scope.formData.emp_name;
		}else {
			layerUtils.iMsg(-1,"人员姓名不能为空");
			return;
		}
		if(!utils.isEmpty($scope.formData.emp_code)) {
			emp_code = $scope.formData.emp_code;
		}else {
			layerUtils.iMsg(-1,"人员编号不能为空");
			return;
		}
		if(!utils.isEmpty($scope.selectedName.code)) {
			departmentCode = $scope.selectedName.code;
		}else {
			layerUtils.iMsg(-1,"隶属营业部不能为空");
			return;
		}
		if(!utils.isEmpty($scope.selectedDivision.code)) {
			division = $scope.selectedDivision.code;
		}else {
			layerUtils.iMsg(-1,"隶属赛区不能为空");
			return;
		}
		if(!utils.isEmpty($scope.formData.emp_type)) {
			emp_type = $scope.formData.emp_type;
		}else {
			layerUtils.iMsg(-1,"在编性质不能为空");
			return;
		}
		if(!utils.isEmpty($scope.formData.emp_category)) {
			emp_category = $scope.formData.emp_category;
		}else {
			layerUtils.iMsg(-1,"人员类别不能为空");
			return;
		}
		if(!utils.isEmpty($scope.formData.contract)) {
			contract = $scope.formData.contract;
		}else {
			layerUtils.iMsg(-1,"签约合同不能为空");
			return;
		}
		if(!utils.isEmpty($scope.formData.entry_time)) {
			entry_time = $scope.formData.entry_time;
		}else {
			layerUtils.iMsg(-1,"入职时间不能为空");
			return;
		}
		
		var param = {
				id:$scope.formData.id,
				emp_name:emp_name,
				emp_code:emp_code,
				departmentCode:departmentCode,
				division:division,
				emp_type:emp_type,
				emp_category:emp_category,
				contract:contract,
				entry_time:entry_time,
		}
		console.log(param);
		$http.post(url, param).success(function(data) {
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