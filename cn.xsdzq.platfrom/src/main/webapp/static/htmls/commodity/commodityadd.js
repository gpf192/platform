ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils','utils','FileUploader' ];
function commodityaddController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {

	$scope.formData = {};
	$scope.presentCategoryList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "商品管理",
					goto:""

				},
				"two" : {
					name : "新增商品",
					goto:"commodityadd"

				}
			}
			$scope.$emit("changeNavigation", data);		
		$scope.formData.name="";
		$scope.formData.description="";
		$scope.formData.faceValue="";
		$scope.formData.value="";
		$scope.formData.storeNumber="";

		$http.get(httpUtils.url.commodityClassify, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.presentCategoryList = data.result;
				$scope.formData.presentCategoryModel = $scope.presentCategoryList[0];
			}
		});
	
		$scope.presentStatusList = [{
			name:"上架",
			code:"0"
		},{
			name:"下架",
			code:"1"
		}]
		$scope.presentStatusModel = $scope.presentStatusList[0];
		
		$scope.isHotList = [{
			name:"热门",
			code:true
		},{
			name:"非热门",
			code:false
		}]
		$scope.formData.isHotModel = $scope.isHotList[1];

	};
	
	$scope.newBuild = function() {			
		var url = httpUtils.url.addCommodity;
		var description="";
		var name="";
		var categoryId="";
		var faceValue="";
		var value="";
		var description="";
		var status="";
		var image="";
		var bigImage="";
		var ishot;
		var code="";
		var tip = "";
		var explain = "";
		
		if(!utils.isEmpty($scope.formData.name)) {
			name = $scope.formData.name;
		}else {
			layerUtils.iMsg(-1, "商品名称不能为空");
			return;
		}
		if(!utils.isEmpty($scope.formData.code)) {
			code = $scope.formData.code;
		}else {
			layerUtils.iMsg(-1, "商品代码不能为空");
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

		if(!utils.isEmpty($scope.presentStatusModel.code)) {
			status = $scope.presentStatusModel.code;
		}else {
			layerUtils.iMsg(-1, "商品状态不能为空");
			return;
		}	
		if(!utils.isEmpty($scope.formData.image)) {
			image = $scope.formData.image;
		}
		if(!utils.isEmpty($scope.formData.bigImage)) {
			bigImage = $scope.formData.bigImage;
		}
		
		if(!utils.isEmpty($scope.formData.tip)) {
			tip = $scope.formData.tip;
		}
		if(!utils.isEmpty($scope.formData.explain)) {
			explain = $scope.formData.explain;
		}
		if(!utils.isEmpty($scope.formData.description)) {
			description = $scope.formData.description;
		}
		var param = {
				name:name,
				code:code,
				faceValue:faceValue,
				value:value,
				image:image,
				bigImage:bigImage,
				description:description,
				categoryId:$scope.formData.presentCategoryModel.id,
				isHot:$scope.formData.isHotModel.code,
				status:status,
				tip:tip,
				explain:explain
		}
	
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"添加成功");
				$state.go("commoditymanage");
				
			} else {
				layerUtils.iMsg(-1,"代码已存在");
			}
		});
	}
	
	
}