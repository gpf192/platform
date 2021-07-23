ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'  ];
function userTotalIntegralModifyController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils,utils) {

		
	$scope.formData = {};
	$scope.formData.category = {};
	$scope.flagList = [];
	$scope.categoryList = [];
	$scope.init=function(){
		
		var data = {
				"one" : {
					name : "积分管理",
					goto:"integralquery"

				},
				"two" : {
					name : "修改积分项目",
					goto:""

				}
			}
			$scope.$emit("changeNavigation", data);
		$http.get(httpUtils.url.getAllItems, {}).success(function(data) {
			if (data.resCode == 0) {
				
				var index = data.result.indexOf("全部");
				data.result.splice(index,1)
				$scope.categoryList = data.result;
			}
		});
		var param = utils.isEmptyObject($stateParams.param);
		if(param){
			$state.go("integralquery");
			return;
		}
					
		$scope.flagList = [{
			name:"增加",
			code:"1"
		},{
			name:"减少",
			code:"0"
		}]
		$scope.flagModel = $scope.flagList[0];
		
		angular.copy($stateParams.param,$scope.formData);
		
	};
	$scope.cancel = function() {
		$state.go("integralquery");
	}
	//检查是否为正整数
/*	$scope.checkNum= function (event,value) {
        var keyCode = event.keyCode;
        if(value<1){
            if(keyCode<49||keyCode>57){
                alert('只能输入正整数');
                event.returnValue = false;
                return false;
            }
        }else{
            if(keyCode<48||keyCode>57) {
                alert('只能输入正整数');
                event.returnValue = false;
                return false;
            }
        }
    };*/
	$scope.newBuild = function() {
		var url = httpUtils.url.modifyUserIntegral;
		var categoryCode="";
		var flag="";//1 -增加  0-减少
		var changeNum;//积分变更值

		console.log($scope.formData.category)
		if(!utils.isEmpty($scope.formData.category)) {
			categoryCode = $scope.formData.category.categoryCode;
		}else {
			layerUtils.iMsg(-1, "项目名称不能为空");
			return;
		}

		
		if($scope.formData.changeNum === '0') {
			layerUtils.iMsg(-1, "值不能为0");
			return;
		}else{
			changeNum = $scope.formData.changeNum;
		}
		
		var param = {
				id:$scope.formData.id,
				categoryCode: categoryCode,
				categoryName: $scope.formData.category.categoryName,
				changeNum: changeNum,
				flag: $scope.flagModel.code,
				clientId: $scope.formData.clientId
				
		}
		console.log(param)
		$http.post(url, param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iAlert("修改成功",function(){
					$state.go("integralquery");
				});
			} else {
				
				layerUtils.iMsg(-1, data.respMsg);
			}
		});
	}

}