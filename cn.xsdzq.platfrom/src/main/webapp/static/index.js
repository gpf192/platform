ngApp.controller("indexController", function($scope, $state, $http, httpUtils) {

	$scope.init = function() {
		var url=httpUtils.url.menu;
		$http.get(url).success(function(data) {
			if (data.resCode == 0) {
				$scope.menu=data.result.menu;
				$scope.user=data.result.user;
			} else {
				layerUtils.iMsg(-1,"菜单获取失败");
			}
		});
	};
	
	$scope.$on("changeNavigation",function(event,data){
		$scope.navigation=data;
	});
	
	$scope.goto = function(state){
		$state.go(state);
	}
	
	

	$scope.getResource = function() {
		var params = {
			"platform" : "android",
			"appId" : "6.1.0",
			"appVersion" : "1",
			"modulesVersions" : "1,1,1,1",
			"modulesName" : "js,css,img,loyaltyProgram"
		};
		$http.post("http://localhost:18080/resource/version/getH5Version", params).success(function(data) {

		});
	};

});
