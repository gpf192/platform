ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' ];
function commodityclassifyaddController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils) {
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "",
					goto:""

				},
				"two" : {
					name : "添加活动产品",
					goto:"commodityclassifyadd"

				}
			}
			$scope.$emit("changeNavigation", data);
		
		$scope.formData.name="";
		$scope.formData.flag= "";
		$scope.tradePlaceFromList = [{
			name:"否",
			code:"0"
		},{
			name:"是",
			code:"1"
		}]
		scope.selectedTradePlace = $scope.tradePlaceFromList[0];
		
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
				name:name,
				flag:flag,
		}
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"添加成功");
				$scope.formData={};
			}else if (data.resCode == 1) {
//				layerUtils.iMsg(-1,"  产品代码已存在 ");
				$scope.formData={};
			} else {
				layerUtils.iMsg(-1,"添加失败");
			}
		});
	}
	
}