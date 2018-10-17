ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils', 'utils' ];
function checkDetailController($scope, $http, $state, $stateParams, httpUtils, layerUtils, utils) {

	$scope.init = function() {
		var data = [{
			name : "审核管理",
			goto:""
		}, {
			name : "文章审核",
			goto:"checklist"
		}, {
			name : "审核",
			goto:"checkdetail"
		}];
		$scope.$emit("changeNavigation", data);
		var flag = utils.isEmptyObject($stateParams.info);
		if(flag){
			$state.go("checklist");
			return;
		}
		$scope.info=$stateParams.info;
		console.log($scope.info);
	};
	$scope.getCheckList = function(){
		$http.post(httpUtils.url.checkList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.checkList = data.result;
			}
		});
	};

	$scope.checkInfo = function(index){
		var id = $scope.infoList[index].id;
		$state.go("modifyinfo", {
			id : {id:id}
		});
	}
	
	$scope.check=function(value){
		console.log(value);
		var params = {
				id : $scope.info.id,
				checkFlag : value
			};
		
		$http.post(httpUtils.url.modifyCheckResult, params).success(function(data) {
			if (data.resCode == 0) {
				//window.sessionStorage.setItem("moduleName", $scope.formData.moduleName);
				layerUtils.iMsg(-1, "操作成功");
				$state.go("checklist", {});
			} else {
				layerUtils.iMsg(-1, data.resMsg);
			}
		});
		
	}
	
	$scope.deleteInfo = function(index) {
		layerUtils.iConfirm("是否需要该文章？", function() {
			var url = httpUtils.url.deleteInfo;
			var param = $scope.infoList[index];
			$http.post(url, param).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "删除成功");
					$scope.getInfosByCategoryId();
				}
			});
		}, function() {
			console.log("取消");
		});
		
	}
	$scope.back = function(value) {
		history.go(-1);
	};

}