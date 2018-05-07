ngApp.factory("httpUtils", [ function() {

	// var baseUrl = "http://localhost:8080/webapp/";
	var baseUrl = getUrl() + "/platform/";
	var url = {
		menu : baseUrl+"menu",
		addUser : baseUrl + "user/add",
		deleteUser : baseUrl + "user/delete",
		userList : baseUrl + "user/getAll",
		userAddRoles: baseUrl + "user/userAddRoles",
		userModifyRoles: baseUrl + "user/userModifyRoles",
		addRole : baseUrl + "role/add",
		getExtraRole : baseUrl + "role/getExtraRole",
		addPermissions: baseUrl +"role/addPermissions",
		getPermissionsByUser: baseUrl +"role/getPermissionsByUser",
		roleList : baseUrl + "role/getAll",
		authorityList : baseUrl + "authority/getAll",
		authorityListByRole : baseUrl + "authority/getAllByRole",
		categoryList : baseUrl + "category/getAll",
		addCategory : baseUrl + "category/addCategory",
		modifyCategory : baseUrl + "category/modifyCategory",
		deleteCategory : baseUrl + "category/deleteCategory",
		moduleUpload : baseUrl + "modules/upload",
		getInfoById : baseUrl + "info/getInfoById",
		addInfo : baseUrl + "info/addInfo",
		getInfoList : baseUrl + "info/getInfosByCategoryId",
		modifyInfo : baseUrl + "info/modifyInfo",
		deleteInfo : baseUrl + "info/deleteInfo",
		checkList : baseUrl + "info/getUncheckedInfo",
		modifyCheckResult : baseUrl + "info/modifyCheckResult"
	};

	function getUrl() {
		var url = window.location.protocol + "//" + window.location.host;
		return url;
	}

	var utils = {
		baseUrl : baseUrl,
		url : url
	};
	return utils;

} ]);
