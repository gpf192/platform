ngApp.controller("indexController", function($scope, $http, httpUtils) {

	$scope.init = function() {
		$("#main-nav li > ul > li").each(function(index, domEle) {
			var that = this;
			$(domEle).on("click", function(event) {
				$("#main-nav li > ul > li").removeClass("active");
				$(that).addClass("active");
				event.stopPropagation();
			});
		});
	};

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
