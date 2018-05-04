ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils' ];
function checkListController($scope, $http, $state, $stateParams, httpUtils, layerUtils) {

	$scope.checkList = [];
	$scope.init = function() {
		var data = [{
			name : "审核管理",
			goto:""
		}, {
			name : "文章审核",
			goto:"userlist"
		}];
		$scope.$emit("changeNavigation", data);
		$scope.getCheckList();
	};
	$scope.getCheckList = function(){
		$http.post(httpUtils.url.checkList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.checkList = data.result;
			}
		});
	};
	$scope.getInfosByCategoryId = function() {
		var url = httpUtils.url.getInfoList;
		var getUrl = url + "/" + $scope.formData.category.id;
		$http.get(getUrl).success(function(data) {
			if (data.resCode == 0) {
				$scope.infoList = data.result;
			}
		});
	};
	$scope.checkInfo = function(index){
		var info = $scope.checkList[index];
		console.log(info);
		$state.go("checkdetail", {
			info : info
		});
	}
	$scope.modifyInfo = function(index) {
		var id = $scope.infoList[index].id;
		$state.go("modifyinfo", {
			id : {id:id}
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

}