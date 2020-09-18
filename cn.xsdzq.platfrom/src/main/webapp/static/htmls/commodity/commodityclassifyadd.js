ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils','utils' ];
function commodityclassifyaddController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {
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
		$scope.flagList = [{
			name:"否",
			code:"0"
		},{
			name:"是",
			code:"1"
		}]
		$scope.flagModel = $scope.flagList[1];
	};
	
	
	$scope.newBuild = function() {
		var url = httpUtils.url.addCommodityClassify;
		
		var name = "";
		var code = "";
		var flag = "";
		
		
		if(!utils.isEmpty($scope.formData.name)) {
			name = $scope.formData.name;
		}else {
			layerUtils.iMsg(-1, "分类名称不能为空");
			return;
		}
	
		if(!utils.isEmpty($scope.formData.code)) {
			code = $scope.formData.code;
		}else {
			layerUtils.iMsg(-1, "分类代码不能为空");
			return;
		}
		
		if(!utils.isEmpty($scope.flagModel.code)) {
			flag = $scope.flagModel.code;
		}else {
			layerUtils.iMsg(-1, "是否启用不能为空");
			return;
		}

		var param = {
				isNew:0,
				name:name,
				code:code,
				flag:flag,
		}
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"添加成功");
				$state.go("commodityclassify");
			}else if (data.resCode == 1) {
//				layerUtils.iMsg(-1,"  产品代码已存在 ");
				$scope.formData={};
			} else {
				layerUtils.iMsg(-1,"添加失败");
			}
		});
	}
	
}