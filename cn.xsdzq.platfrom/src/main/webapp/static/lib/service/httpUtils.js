ngApp.factory("httpUtils", [
function() {

	//var baseUrl = "http://localhost:8080/webapp/";
	var baseUrl = getUrl() +"/webapp/";
	var url = {
		categoryList : baseUrl + "category/getAll",
		addCategory : baseUrl + "category/addCategory",
		modifyCategory : baseUrl + "category/modifyCategory",
		deleteCategory : baseUrl + "category/deleteCategory",
		moduleUpload : baseUrl + "modules/upload",
		getInfoById : baseUrl + "info/getInfoById",
		addInfo : baseUrl + "info/addInfo",
		getInfoList : baseUrl + "info/getInfosByCategoryId",
		modifyInfo : baseUrl + "info/modifyInfo",
		deleteInfo : baseUrl + "info/deleteInfo"
	};
	
	function getUrl(){
		var url=window.location.protocol+"//"+window.location.host;
		return url;
	}

	var utils = {
		baseUrl : baseUrl,
		url : url
	};
	return utils;

}]);
