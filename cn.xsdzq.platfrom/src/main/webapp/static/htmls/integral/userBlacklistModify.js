ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'  ];
function userBlacklistModifyController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {

	$scope.formData = {};
	$scope.presentList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "用户黑名单管理",
					goto:"userBlacklist"

				},
				"two" : {
					name : "修改用户",
					goto:""

				}
			}
			$scope.$emit("changeNavigation", data);
		
		var param = utils.isEmptyObject($stateParams.param);
			if(param){
				$state.go("userBlacklist");
				return;
			}
		
		// 分类下拉框复制
		$http.get(httpUtils.url.commodity, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.presentList = data.result;
				for(var i=0;i<data.result.length;i++){
					if($scope.formData.presentId==data.result[i].id){
						$scope.formData.presentNameModel = $scope.presentList[i];
					}
				}
			}
		});

		
		var cardStatus = $stateParams.param.cardStatus;
		if(cardStatus ==1) {
			$scope.cardStatusModel = $scope.cardStatusList[0];
		}else if(cardStatus ==0) {
			$scope.cardStatusModel = $scope.cardStatusList[1];
		}
		angular.copy($stateParams.param,$scope.formData);
	};
	
	
	$scope.newBuild = function() {
		var url = httpUtils.url.addUserBlacklist;
		var clientId="";

		var clientName="";
		var departmentDesc="";
	
		if(!utils.isEmpty($scope.formData.clientName)) {
			clientName = $scope.formData.clientName;
		}else {
			layerUtils.iMsg(-1, "客户姓名不能为空");
			return;
		}


		var param = {
				id:$scope.formData.id,
				clientId:$scope.formData.clientId,				
				clientName:clientName,
				departmentDesc:$scope.formData.departmentDesc,
				isNew:1// 更新
				
		}	
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iAlert("修改成功",function(){
					$state.go("userBlacklist");
				});
			} else {
				layerUtils.iMsg(-1, "修改失败");
			}
		});
	}
	
}