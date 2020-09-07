ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils','utils' ];
function cardaddController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {

	$scope.formData = {};
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
		/*$scope.presentCategoryList = [{
			name:"京东E卡",
			code:"0"
		},{
			name:"爱奇艺月卡",
			code:"1"
		}]
		scope.presentCategoryModel = $scope.presentCategoryList[0];*/
		$scope.presentNameList = [{
			name:"100元京东E卡",
			code:"0"
		},{
			name:"200元京东E卡",
			code:"1"
		}]
		$scope.presentNameModel = $scope.presentNameList[0];
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
		
		var presentCategory="";
		var presentName="";
		var cardId="";
		var password="";
		var cardStatus="";
		
		/*if(!utils.isEmpty($scope.presentCategoryModel.code)) {
			presentCategory = $scope.presentCategoryModel.code;
		}else {
			layerUtils.iMsg(-1, "商品分类不能为空");
			return;
		}*/
		if(!utils.isEmpty($scope.presentNameModel.name)) {
			presentName = $scope.presentNameModel.name;
		}else {
			layerUtils.iMsg(-1, "商品名称不能为空");
			return;
		}
		
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
				presentCategory:presentCategory,
				presentName:presentName,
				cardId:cardId,
				password:password,
				cardStatus:cardStatus,
		}
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