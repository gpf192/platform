ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils','utils' ];
function userBlacklistAddController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {

	$scope.formData = {};
	$scope.presentList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "用户黑名单管理",
					goto:"userBlacklist"

				},
				"two" : {
					name : "新增用户",
					goto:""

				}
			}
			$scope.$emit("changeNavigation", data);

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
		
		if(!utils.isEmpty($scope.formData.clientId)) {
			clientId = $scope.formData.clientId;
		}else {
			layerUtils.iMsg(-1, "客户号不能为空");
			return;
		}
		
		var param = {
				id:$scope.formData.id,
				clientId:$scope.formData.clientId,				
				clientName:clientName,
				departmentDesc:$scope.formData.departmentDesc,
				isNew:0//代表新增
		}
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"添加成功");
				$state.go("userBlacklist");
				
			} else {
				layerUtils.iMsg(-1,"添加失败");
			}
		});
	}
	
}