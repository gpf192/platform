ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'  ];
function integralModifyController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {

		
	$scope.formData = {};
	$scope.formData.category = {};
	$scope.flagList = [];

	$scope.init=function(){
		var data = {
				"one" : {
					name : "积分项目管理",
					goto:""

				},
				"two" : {
					name : "修改积分项目",
					goto:"integraldetail"

				}
			}
			$scope.$emit("changeNavigation", data);
		var param = utils.isEmptyObject($stateParams.param);
		if(param){
			$state.go("integralallocation");
			return;
		}
					
		$scope.flagList = [{
			name:"是",
			code:"1"
		},{
			name:"否",
			code:"0"
		}]
		
		var flag = $stateParams.param.flag;
		if(flag ==1) {
			$scope.flagModel = $scope.flagList[0];
		}else if(flag ==0) {
			$scope.flagModel = $scope.flagList[1];
		}
		angular.copy($stateParams.param,$scope.formData);
	};
	
	
	$scope.newBuild = function() {
		var url = httpUtils.url.addCreditCategory;
		var categoryName="";
		var categoryCode="";
		var frontName="";
		var integralValue="";
		var flag="";
				
		
		if(!utils.isEmpty($scope.formData.categoryName)) {
			categoryName = $scope.formData.categoryName;
		}else {
			layerUtils.iMsg(-1, "项目名称不能为空");
			return;
		}

		if(!utils.isEmpty($scope.formData.categoryCode)) {
			categoryCode = $scope.formData.categoryCode;
		}else {
			layerUtils.iMsg(-1, "项目编码不能为空");
			return;
		}
		if(!utils.isEmpty($scope.formData.integralValue)) {
			integralValue = $scope.formData.integralValue;
		}else {
			layerUtils.iMsg(-1, "面值不能为空");
			return;
		}
		if(!utils.isEmpty($scope.formData.frontName)) {
			frontName = $scope.formData.frontName;
		}else {
			layerUtils.iMsg(-1, "前端显示名称不能为空");
			return;
		}
	
		
		var param = {
				id:$scope.formData.id,
				categoryName:categoryName,
				categoryCode:categoryCode,
				integralValue:integralValue,
				frontName:frontName,
				flag:$scope.flagModel.code
		}
		
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iAlert("修改成功",function(){
					$state.go("integralallocation");
				});
			} else {
				layerUtils.iMsg(-1, data.resMsg);
			}
		});
	}

}