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

});
