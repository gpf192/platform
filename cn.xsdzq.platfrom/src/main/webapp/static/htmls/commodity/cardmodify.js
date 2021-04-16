ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'  ];
function cardmodifyController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {

	$scope.formData = {};
	$scope.presentList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "卡券管理",
					goto:"cardmanage"

				},
				"two" : {
					name : "修改卡券",
					goto:""

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
		//对失效日期字段单独处理
		var time = $scope.formData.expiryTime ;//2021-12-01
		if("无"===time){
			$scope.formData.expiryTime = "";
		}else{
			var t = time.substring(0,4)+time.substring(5,7)+time.substring(8,10);
			$scope.formData.expiryTime = t;
		}
		
		
	};
	
	
	$scope.newBuild = function() {
		var url = httpUtils.url.addCard;
		var present="";

		var cardId="";
		var password="";
		var cardStatus="1";
		var expiryTime = "";
		
		if(!utils.isEmpty($scope.formData.cardId)) {
			cardId = $scope.formData.cardId;
		}else {
			layerUtils.iMsg(-1, "卡号不能为空");
			return;
		}
		
		/*if(!utils.isEmpty($scope.formData.password)) {
			if( $scope.formData.password == "无") {
			
				password = password;
			}else{
				
				password = $scope.formData.password;
			}
			
		}*/
		
		
		if(!utils.isEmpty($scope.formData.expiryTime)) {
			//不为空
			if($scope.formData.expiryTime.length != 8){
				//且格式年月日
				 layerUtils.iMsg(-1, "失效日期格式应为年月日，如20200101");
					return;
			 }
			//后端服务器判断 失效日期必须大于当天  			
			expiryTime = $scope.formData.expiryTime;
		}else {
			//为空
			expiryTime = 20991231;
		}
		 
		var param = {
				id:$scope.formData.id,
				presentId:$scope.formData.presentNameModel.id,				
				cardId:cardId,
				password:password,
				cardStatus:cardStatus,
				expiryTime:expiryTime,
				isNew:1//更新
				
		}
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iAlert("修改成功",function(){
					$state.go("cardmanage");
				});
			} else {
				layerUtils.iMsg(-1, data.respMsg);
			}
		});
	}
	
}