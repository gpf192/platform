ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils' ];
function commoditymodifyController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {

	$scope.formData = {};
	$scope.presentCategoryList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "商品管理",
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
		
		//分类下拉框复制
		$http.get(httpUtils.url.commodityClassify, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.presentCategoryList = data.result;
				for(var i=0;i<data.result.length;i++){
					if($scope.formData.categoryId==data.result[i].id){
						$scope.formData.presentCategoryModel = $scope.presentCategoryList[i];
					}
				}
			}
		});
		angular.copy($stateParams.param,$scope.formData);
		$scope.presentStatusList = [{
			name:"上架",
			code:"0"
		},{
			name:"下架",
			code:"1"
		}]		

		var status = $stateParams.param.status;
		if(status ==0) {
			$scope.presentStatusModel = $scope.presentStatusList[0];
		}else if(status ==1) {
			$scope.presentStatusModel = $scope.presentStatusList[1];
		}
		
		var  tempHot =  $stateParams.param.isHot;	
		$scope.isHotList = [{
			name:"是",
			code:true
		},{
			name:"否",
			code:false
		}]
		if(tempHot) {
			$scope.isHotModel = $scope.isHotList[0];
		}else{
			$scope.isHotModel = $scope.isHotList[1];
		}		
		
	};	

	$scope.newBuild = function() {
		var url = httpUtils.url.addCommodity;

		var name="";
		var presentCategory="";
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
		var attention = "";
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
		if(!utils.isEmpty($scope.formData.description)) {
			description = $scope.formData.description;
		}
		if(!utils.isEmpty($scope.formData.tip)) {
			tip = $scope.formData.tip;
		}
		if(!utils.isEmpty($scope.formData.explain)) {
			explain = $scope.formData.explain;
		}
		if(!utils.isEmpty($scope.formData.attention)) {
			attention = $scope.formData.attention;
		}		
		var param = {
				id:$scope.formData.id,
				categoryId:$scope.formData.presentCategoryModel.id,
				name:name,
				code:code,
				faceValue:faceValue,
				value:value,
				image:image,
				bigImage:bigImage,
				presentCategory:presentCategory,
				status:status,
				isHot:$scope.isHotModel.code,
				description:description,
				tip:tip,
				explain:explain,
				createtime:$scope.formData.createtime,
				attention:attention,
				newFlag:0
		}
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iAlert("修改成功",function(){
				$state.go("commoditymanage");
			});
			
			} else {
				layerUtils.iMsg(-1, "代码已存在");
			}
		});
	}
	
}