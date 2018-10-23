ngApp.factory('xsdInterceptor', ['$q', '$window','$location', 'layerUtils',
function($q,$window, $location, layerUtils) {
	var interceptor = {
		'request' : function(config) {
			//console.log(config);
			//console.log("request request request");
			var url=config.url;
			if(url.indexOf("login")>-1&&url.indexOf("expire")>-1){
				layerUtils.iConfirm("登录已过期，请重新登录！", function() {
					console.log("登录已过期，请重新登录！");
				});
			}
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
			var data = response.data;
			//console.log($location);
			if((typeof data =='string')){
				if(data.indexOf("!DOCTYPE html")&&data.indexOf("loginin/css/bootstrap.min.css")>-1&&data.indexOf("登录")){
					layerUtils.iConfirm("登录已过期，请重新登录！", function() {
						var loginUrl=$location.protocol()+"://"+$location.host()+":"+$location.port()+"/platform/login";
						//window.location.href=$location.absUrl();
						location.reload();;
					}, function() {
						console.log("取消");
					});
				}
			}
			return response;
		},
		'requestError' : function(responseError) {
			//console.log("requestError  requestError requestError");
			layerUtils.iLoading(false);
			return responseError;
		},
		'responseError' : function(response) {
			//console.log(response);
			//console.log(response.status);
			layerUtils.iLoading(false); 
			// http 的返回状态码
			if(response.status==403){
				layerUtils.iAlert("没有对应的权限，请联系管理员");
			}
			if (response.status === 401) {
//              return responseOrNewPromise
                console.log('401');
                layerUtils.iConfirm("登录已过期，请重新登录！", function() {
					var loginUrl=$location.protocol()+"://"+$location.host()+":"+$location.port()+"/platform/login";
					//window.location.href=$location.absUrl();
					location.reload();;
				}, function() {
					console.log("取消");
				});
            }
			//layerUtils.iMsg(-1, "服务器异常，请稍后再试!");

			return response;
		}
	};
	return interceptor;
}]);
