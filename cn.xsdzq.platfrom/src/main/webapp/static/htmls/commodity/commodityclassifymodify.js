ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' , 'utils'];
function commodityclassifymodifyController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "",
					goto:""

				},
				"two" : {
					name : "修改商品分类",
					goto:"commodityclassifyadd"

				}
			}
			$scope.$emit("changeNavigation", data);
		var param = utils.isEmptyObject($stateParams.param);
		if(param){
			$state.go("commodityclassify");
			return;
		}
		
		
		$scope.formData.name="";
		$scope.tradePlaceFromList = [{
			name:"否",
			code:"0"
		},{
			name:"是",
			code:"1"
		}]
		
		var flag =	$stateParams.param.flag;
		if(flag ==0) {
			$scope.selectedTradePlace = $scope.tradePlaceFromList[0];
		}else if(flag ==1) {
			$scope.selectedTradePlace = $scope.tradePlaceFromList[1];
		}
		
		angular.copy($stateParams.param,$scope.formData);
	};
	
	
	$scope.newBuild = function() {
		var url = httpUtils.url.addCommodityClassify;
		
		var name = "";
		var flag = "";
		
		
		if(!utils.isEmpty($scope.formData.name)) {
			name = $scope.formData.name;
		}else {
			layerUtils.iMsg(-1, "分类名称不能为空");
			return;
		}
		if(!utils.isEmpty($scope.formData.flag)) {
			flag = $scope.selectedTradePlace.code;
		}else {
			layerUtils.iMsg(-1, "是否启用不能为空");
			return;
		}

		var param = {
				id:$scope.formData.id,
				name:name,
				flag:flag
		}
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"添加成功");
				//$scope.formData={};
				$state.go("commodityclassify");
				}
			 else {
				layerUtils.iMsg(-1,"添加失败");
			}
		});
	}
	
}