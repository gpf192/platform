ngApp.factory('pingnInterceptor', ['$q', '$location', 'layerUtils',
function($q, $location, layerUtils) {
	var interceptor = {
		'request' : function(config) {
			//console.log(config);
			//console.log("request request request");
			var url=config.url;
			var position=url.indexOf(".");
			if(position<0){
				layerUtils.iLoading(true);
			}
			//var deferred=$q.defer();
			//$q.when(config);
			//if(config.url.indexOf("Function700001")>0){
			//	$location.path("welcome");
			//	return ;
			//}
			//return deferred.when(config);
			return config;
		},
		'response' : function(response) {
			//console.log("response  response response");
			layerUtils.iLoading(false);
			return response;
		},
		'requestError' : function(responseError) {
			//console.log("requestError  requestError requestError");
			layerUtils.iLoading(false);
			return responseError;
		},
		'responseError' : function(rejection) {
			//console.log("responseError  responseError responseError");
			layerUtils.iLoading(false);
			//layerUtils.iMsg(-1, "服务器异常，请稍后再试!");

			return rejection;
		}
	};
	return interceptor;
}]);
