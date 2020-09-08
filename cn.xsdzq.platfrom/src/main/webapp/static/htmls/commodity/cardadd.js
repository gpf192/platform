ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils','utils' ];
function cardaddController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {

	$scope.formData = {};
	$scope.presentList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "",
					goto:""

				},
				"two" : {
					name : "新增卡券",
					goto:"cardadd"

				}
			}
			$scope.$emit("changeNavigation", data);
		$scope.formData.cardId="";
		$scope.formData.password="";	
		
		
		$http.get(httpUtils.url.commodity, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.presentList = data.result;
				$scope.formData.presentNameModel = $scope.presentList[0];
			}
		});
				
		
		$scope.cardStatusList = [{
			name:"上架",
			code:"1"
		},{
			name:"下架",
			code:"0"
		}]
		$scope.cardStatusModel = $scope.cardStatusList[0];
	};
	
	
	$scope.newBuild = function() {
		var url = httpUtils.url.addCard;		
		var presentId="";
		var cardId="";
		var password="";
		var cardStatus="";
				
		
		if(!utils.isEmpty($scope.formData.cardId)) {
			cardId = $scope.formData.cardId;
		}else {
			layerUtils.iMsg(-1, "卡号不能为空");
			return;
		}
		if(!utils.isEmpty($scope.cardStatusModel.code)) {
			cardStatus = $scope.cardStatusModel.code;
		}else {
			layerUtils.iMsg(-1, "卡券状态不能为空");
			return;
		}
		
		var param = {
				presentId:$scope.formData.presentNameModel.id,
				cardId:cardId,
				password:$scope.formData.password,
				cardStatus:cardStatus,
		}
		console.log($scope.formData.presentNameModel.id+"--00");
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"添加成功");
				$scope.formData={};
			}else if (data.resCode == 1) {
				$scope.formData={};
			} else {
				layerUtils.iMsg(-1,"添加失败");
			}
		});
	}
	
}