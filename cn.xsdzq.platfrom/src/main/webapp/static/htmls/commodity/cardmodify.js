ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'  ];
function cardmodifyController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {

	$scope.formData = {};
	$scope.presentList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "卡券管理",
					goto:""

				},
				"two" : {
					name : "修改卡券",
					goto:"cardmodify"

				}
			}
			$scope.$emit("changeNavigation", data);
		
		var param = utils.isEmptyObject($stateParams.param);
		if(param){
			$state.go("cardmanage");
			return;
		}
			
		//分类下拉框复制
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

		$scope.cardStatusList = [{
			name:"上架",
			code:"1"
		},{
			name:"下架",
			code:"0"
		}]
		
		var cardStatus = $stateParams.param.cardStatus;
		if(cardStatus ==1) {
			$scope.cardStatusModel = $scope.cardStatusList[0];
		}else if(cardStatus ==0) {
			$scope.cardStatusModel = $scope.cardStatusList[1];
		}
		angular.copy($stateParams.param,$scope.formData);
	};
	
	
	$scope.newBuild = function() {
		var url = httpUtils.url.addCard;
		var present="";

		var cardId="";
		var password="";
		var cardStatus="1";
		
		
		if(!utils.isEmpty($scope.formData.cardId)) {
			cardId = $scope.formData.cardId;
		}else {
			layerUtils.iMsg(-1, "卡号不能为空");
			return;
		}
		/*if(!utils.isEmpty($scope.cardStatusModel.code)) {
			cardStatus = $scope.cardStatusModel.code;
		}else {
			layerUtils.iMsg(-1, "卡券状态不能为空");
			return;
		}*/
		
		var param = {
				id:$scope.formData.id,
				presentId:$scope.formData.presentNameModel.id,				
				cardId:cardId,
				password:$scope.formData.password,
				cardStatus:cardStatus,
				isNew:1//更新
				
		}		
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iAlert("修改成功",function(){
					$state.go("cardmanage");
				});
			} else {
				layerUtils.iMsg(-1, "修改失败");
			}
		});
	}
	
}