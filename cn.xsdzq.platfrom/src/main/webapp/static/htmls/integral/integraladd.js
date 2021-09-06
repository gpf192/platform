ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils','utils' ];
function integraladdController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {

	$scope.formData = {};
	$scope.presentList = [];
	$scope.init=function(){
		var data = {
				"one" : {
					name : "积分类别配置",
					goto:""

				},
				"two" : {
					name : "新增积分项目",
					goto:"cardadd"

				}
			}
			$scope.$emit("changeNavigation", data);
		$scope.formData.cardId="";
		$scope.formData.password="";	
		
	
				
		
		$scope.flagList = [{
			name:"是",
			code:"1"
		},{
			name:"否",
			code:"0"
		}]
		$scope.flagModel = $scope.flagList[0];
	};
	
	function checksum(chars){
		var sum = 0; 
		for (var i=0; i<chars.length; i++)
		{ 
		    var c = chars.charCodeAt(i); 
		    if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f))
	
		{ 
		    sum++; 
		    }
		    else 
		    {     
		    sum+=2; 
	
		    } 
		}
		return sum;
	}
	
	$scope.newBuild = function() {
		var url = httpUtils.url.addCreditCategory;	
		
		var categoryName="";
		var categoryCode="";
		var frontName="";
		var integralValue="";
		var flag="";
				
		
		if(!utils.isEmpty($scope.formData.categoryName)) {
			if("全部"==$scope.formData.categoryName){
				layerUtils.iMsg(-1, "项目名称不合法");
				return;
			}
			categoryName = $scope.formData.categoryName;
		}else {
			layerUtils.iMsg(-1, "项目名称不能为空");
			return;
		}

		if(!utils.isEmpty($scope.formData.categoryCode)) {
			categoryCode = $scope.formData.categoryCode;
		}else {
			layerUtils.iMsg(-1, "项目编码不能为空");
			return;
		}
		
		if(!utils.isEmpty($scope.formData.frontName)) {
			frontName = $scope.formData.frontName;
		}else {
			layerUtils.iMsg(-1, "前端显示名称不能为空");
			return;
		}
		
		var s1 = checksum($scope.formData.categoryName);
		var s2 = checksum($scope.formData.frontName);
		if(s1>40 || s2>40){
			layerUtils.iMsg(-1, "项目名称或前端显示名称字数超过限制");
			return;
		}
		
		
		var param = {
				categoryName:categoryName,
				categoryCode:categoryCode,
				integralValue:integralValue,
				frontName:frontName,
				//flag:$scope.flagModel.code
				flag:'1'
		}
	//	console.log($scope.formData.presentNameModel.id+"--00");
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"添加成功");
			//	$scope.formData={};
				$state.go("integralallocation");
			}else if (data.resCode == 1) {
				$scope.formData={};
			} else {
				layerUtils.iMsg(-1,"添加失败");
			}
		});
	}

}