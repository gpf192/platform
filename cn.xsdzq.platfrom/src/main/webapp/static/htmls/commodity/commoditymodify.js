ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils' ];
function commoditymodifyController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils) {

	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "",
					goto:""

				},
				"two" : {
					name : "修改商品",
					goto:"commoditymodify"

				}
			}
			$scope.$emit("changeNavigation", data);
		var param = utils.isEmptyObject($stateParams.param);
		if(param){
			$state.go("commoditymanage");
			return;
		}
		
		$scope.formData.name="";
		$scope.formData.presentCategory="";
		$scope.formData.faceValue="";
		$scope.formData.value="";
		$scope.formData.storeNumber="";
		
		$scope.presentCategoryList = [{
			name:"京东E卡",
			code:"0"
		},{
			name:"爱奇艺月卡",
			code:"1"
		}]
	
		$scope.presentStatusList = [{
			name:"上架",
			code:"0"
		},{
			name:"下架",
			code:"1"
		}]		
		var presentCategory =	$stateParams.param.presentCategory;
		if(presentCategory ==0) {
			$scope.presentCategoryModel = $scope.presentCategoryList[0];
		}else if(flag ==1) {
			$scope.presentCategoryModel = $scope.presentCategoryList[1];
		}
		
		var status = $stateParams.param.status;
		if(status ==0) {
			$scope.presentStatusModel = $scope.presentStatusList[0];
		}else if(status ==1) {
			$scope.presentStatusModel = $scope.presentStatusList[1];
		}
		angular.copy($stateParams.param,$scope.formData);
	};
	
	
	$scope.newBuild = function() {
		var url = httpUtils.url.addCommodity;

		var name="";
		var presentCategory="";
		var faceValue="";
		var value="";
		var storeNumber="";
		var status="";

		if(!utils.isEmpty($scope.formData.name)) {
			name = $scope.formData.name;
		}else {
			layerUtils.iMsg(-1, "商品名称不能为空");
			return;
		}
		
		if(!utils.isEmpty($scope.formData.faceValue)) {
			faceValue = $scope.formData.faceValue;
		}else {
			layerUtils.iMsg(-1, "商品面值不能为空");
			return;
		}
		
		if(!utils.isEmpty($scope.formData.value)) {
			value = $scope.formData.value;
		}else {
			layerUtils.iMsg(-1, "实际价格不能为空");
			return;
		}
		
		if(!utils.isEmpty($scope.formData.storeNumber)) {
			storeNumber = $scope.formData.storeNumber;
		}else {
			layerUtils.iMsg(-1, "商品库存不能为空");
			return;
		}
		
		if(!utils.isEmpty($scope.presentCategoryModel.code)) {
			presentCategory = $scope.presentCategoryModel.code;
		}else {
			layerUtils.iMsg(-1, "商品分类不能为空");
			return;
		}

		if(!utils.isEmpty($scope.presentStatusModel.code)) {
			status = $scope.presentStatusModel.code;
		}else {
			layerUtils.iMsg(-1, "商品状态不能为空");
			return;
		}
		
		var param = {
				name:name,
				faceValue:faceValue,
				value:value,
				storeNumber:storeNumber,
				presentCategory:presentCategory,
				status:status
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