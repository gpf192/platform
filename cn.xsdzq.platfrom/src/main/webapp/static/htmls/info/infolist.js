ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils' ];
function infoListController($scope, $http, $state, $stateParams, httpUtils, layerUtils) {

	$scope.formData = {};
	$scope.formData.category = {};
	$scope.categoryList = [];
	$scope.infoList = [];
	$scope.init = function() {
		var data = [{
			name : "信息管理",
			goto:""
		}, {
			name : "信息列表管理",
			goto:"infolist"
		}];
		$scope.$emit("changeNavigation", data);
		$http.get(httpUtils.url.categoryList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categoryList = data.result;
				$scope.formData.category = $scope.categoryList[0];
			}
		});
	};
	$scope.getInfosByCategoryId = function() {
		var url = httpUtils.url.getInfoList;
		var params = {
			id : $scope.formData.category.id
		}
		$http({
			url : url,
			method : 'GET',
			params : params
		}).success(function(data) {
			if (data.resCode == 0) {
				$scope.infoList = data.result;
			}
		});
	};
	$scope.modifyInfo = function(index) {
		//var id = $scope.infoList[index].id;
		var info = $scope.infoList[index];
		console.log(info);
		$state.go("modifyinfo", {
			info : info
		});
	}
	$scope.deleteInfo = function(index) {
		layerUtils.iConfirm("是否删除该文章？", function() {
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