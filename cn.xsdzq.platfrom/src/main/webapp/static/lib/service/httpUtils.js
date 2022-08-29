ngApp.factory("httpUtils", [ function() {

	// var baseUrl = "http://localhost:8080/webapp/";
	var baseUrl = getUrl() + "/platform/";
	var url = {
		menu : baseUrl+"menu",
		addUser : baseUrl + "user/add",
		modifyUser : baseUrl + "user/modify",
		deleteUser : baseUrl + "user/delete",
		userList : baseUrl + "user/getAll",
		userAddRoles: baseUrl + "user/userAddRoles",
		userModifyRoles: baseUrl + "user/userModifyRoles",
		addRole : baseUrl + "role/add",
		modifyRole : baseUrl + "role/modifyRole",
		deleteRole : baseUrl + "role/delete",
		getRoleAuthorityToModify : baseUrl +"role/getPermissionsByUserToModify",
		getExtraRole : baseUrl + "role/getExtraRole",
		addPermissions: baseUrl +"role/addPermissions",
		getPermissionsByUser: baseUrl +"role/getPermissionsByUser",
		modifyRoleAuthority : baseUrl + "role/modifyRoleAuthority",
		roleList : baseUrl + "role/getAll",
		authorityList : baseUrl + "authority/getAll",
		authorityListByRole : baseUrl + "authority/getAllByRole",
		categoryList : baseUrl + "category/getAll",
		categoryListExcept : baseUrl + "category/getAllExcept",
		getDisplayCategories: baseUrl + "category/getDisplayCategories",
		addCategory : baseUrl + "category/addCategory",
		modifyCategory : baseUrl + "category/modifyCategory",
		deleteCategory : baseUrl + "category/deleteCategory",
		sortCategory : baseUrl + "category/sortCategory",
		moduleUpload : baseUrl + "modules/upload",
		getInfoById : baseUrl + "info/getInfoById",
		addInfo : baseUrl + "info/addInfo",
		getInfoList : baseUrl + "info/getInfosByCategoryId",
		getCheckInfoList : baseUrl + "info/getCheckInfosByCategoryId",
		revocation : baseUrl + "info/revocation",
		modifyInfo : baseUrl + "info/modifyInfo",
		addWeight : baseUrl + "info/addWeight",
		deleteInfo : baseUrl + "info/deleteInfo",
		checkList : baseUrl + "info/getUncheckedInfo",
		getCommonInfos : baseUrl + "info/getCommonInfos",
		getInfosByCategoryIdForH5 : baseUrl + "info/getInfosByCategoryIdForH5",
		modifyCheckResult : baseUrl + "info/modifyCheckResult",
		kcList : baseUrl + "kechuang/getKCInfos",
		dzhgg : baseUrl + "dzh/getDzhgg",
		dzhActivityList : baseUrl + "dzh/getDzhActivity",
		turntablePrizeList : baseUrl + "prize/getPrize",
		winPrizeList: baseUrl + "prizeRecord/getPrizeRecord",
		addPrize : baseUrl + "prize/addPrize",
		modifyPrize : baseUrl + "prize/modifyPrize",
		deletePrize : baseUrl + "prize/deletePrize",
		
		userGetChanceList: baseUrl + "prizeRecord/userGetChanceList",
		userTotalChanceList: baseUrl + "prizeRecord/userTotalChanceList",
		
		lcjPrizeList : baseUrl + "prize/getLcjPrize",
		addLcjPrize : baseUrl + "prize/addLcjPrize",
		modifyLcjPrize : baseUrl + "prize/modifyLcjPrize",
		deleteLcjPrize : baseUrl + "prize/deleteLcjPrize",
		winLcjPrizeResultList: baseUrl + "prizeRecord/getLcjPrizeResult",

		
		turntableAwardList : baseUrl + "award/getAward",
		addAward : baseUrl + "award/addAward",
		modifyAward : baseUrl + "award/modifyAward",
		deleteAward : baseUrl + "award/deleteAward",
		
		awardResultList : baseUrl + "award/getAwardResult",
		
		activityProductsList : baseUrl + "product/getProduct",
		addProduct : baseUrl + "product/addProduct",
		modifyProduct: baseUrl + "product/modifyProduct",
		deleteProduct : baseUrl + "product/deleteProduct",
		productsSellList: baseUrl + "product/getProductSell",
		productsSellCwList: baseUrl + "product/getCwProductSell",
		
		participantsList: baseUrl + "emp/getEmp",
		addEmp: baseUrl + "emp/addEmp",
		modifyEmp: baseUrl + "emp/modifyEmp",
		deleteEmp: baseUrl + "emp/deleteEmp",
		
		userTIcketsList: baseUrl + "vote/getUserVote",
		contestantList: baseUrl + "vote/getEmpVote",
		departmentList : baseUrl + "department/getAll",
		paramList: baseUrl + "param/getParam",
		addParam: baseUrl + "param/addParam",
		modifyParam: baseUrl + "param/modifyParam",
		deleteParam: baseUrl + "param/deleteParam",
		addEmpVoteWeight: baseUrl + "vote/addWeight",		
		userVoteForList:baseUrl + "vote/getUserVoteFor",
		
		getRiskInfo: baseUrl + "thx/getRiskInfo",		
		getUserOrder:baseUrl + "thx/getUserOrder",
		
		getLoginRecord:baseUrl + "live/getLoginRecord",
		
		commodityClassify:baseUrl + "mall/categoryy/all",
		getAllPage:baseUrl + "mall/categoryy/getAllPage",
		addCommodityClassify:baseUrl + "mall/categoryy/add",
		deleteCommodityClassify:baseUrl + "mall/categoryy/delete",
		
		commodity:baseUrl + "mall/present/all",
		getAllPresentPage:baseUrl + "mall/present/getAllPresentPage",
		addCommodity:baseUrl + "mall/present/add",
		deleteCommodity:baseUrl + "mall/present/delete",
		card:baseUrl + "mall/card/all",
		addCard:baseUrl + "mall/card/add",
		deleteCard:baseUrl + "mall/card/delete",
		exchangeRecords:baseUrl + "mall/card/exchangeRecords",
		
		uploadCardTemp:baseUrl + "mall/cardImport/uploadCardTemp",
		deleteCardTempData:baseUrl + "mall/cardImport/deleteCardTempData",
		getCardImportTemp:baseUrl + "mall/cardImport/getCardImportTemp",
		submitCardImport:baseUrl + "mall/cardImport/submit",
		
		getCreditCategory:baseUrl + "mall/credit/all",
		getAllItems:baseUrl + "mall/credit/getAllItems",
		addCreditCategory:baseUrl + "mall/credit/add",
		deleteCreditCategory:baseUrl + "mall/credit/delete",
		getUserCreditTotal:baseUrl + "mall/credit/getUserCreditTotal",
		getCreditImportRecord :baseUrl + "mall/credit/getCreditImportRecord",
		modifyUserIntegral :baseUrl + "mall/credit/modifyUserIntegral",
		
		crmCreditRecord:baseUrl + "mall/crm/getCrmCreditRecord",
		crmCreditApiMsg:baseUrl + "mall/crm/getCrmCreditApiMsg",
		crmCreditProduct:baseUrl + "mall/crm/getCrmCreditProduct",
		getDataManual:baseUrl + "mall/crm/getDataManual",
		
		importExcel:baseUrl + "mall/credit/upload",
		getCreditImportTemp :baseUrl + "mall/credit/getCreditImportTemp",
		checkBeforeSubmit:baseUrl + "mall/credit/checkBeforeSubmit",
		submit:baseUrl + "mall/credit/submit",
		deleteTempData:baseUrl + "mall/credit/deleteTempData",
		
		getAllUserBlacklist:baseUrl + "mall/userr/getAll",
		addUserBlacklist:baseUrl + "mall/userr/addUserBlacklist",
		deleteUserBlacklist:baseUrl + "mall/userr/delete",
		
		uploadUserBlacklistImportExcel:baseUrl + "mall/userr/uploadExcel",
		submitUserBlacklistImport:baseUrl + "mall/userr/submit",
		deleteUserBlacklistImport:baseUrl + "mall/userr/deleteUserBlacklistImport",
		getAllUserBlacklistTemp:baseUrl + "mall/userr/getAllUserBlacklistTemp",

		getBrandSellStatus:baseUrl + "mall/brand/sellstatus",
		getAllBrand:baseUrl + "mall/brand/all",
		addBrand:baseUrl + "mall/brand/add",
		deleteBrand:baseUrl + "mall/brand/delete",

		getMallProductSellStatus:baseUrl + "mall/product/sellstatus",
		getMallAllProduct:baseUrl + "mall/product/all",
		addMallProduct:baseUrl + "mall/product/add",
		deleteMallProduct:baseUrl + "mall/product/delete",

		getOrderStatus:baseUrl + "mall/order/orderstatus",
		getRechargeStatus:baseUrl + "mall/order/rechargestatus",
		getAdjustmentType:baseUrl + "mall/order/adjustmenttype",
		getAllOrder:baseUrl + "mall/order/all",
		addOrder:baseUrl + "mall/order/add",
		
		videoList:baseUrl + "video/getVideoList",
		addVideo:baseUrl + "video/addVideo",
		deleteVideo:baseUrl + "video/deleteVideo",
		
		userGetChanceList818:baseUrl + "prize/userGetChanceList818",
		userTotalChanceList818:baseUrl + "prize/userTotalChanceList818"
		
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
