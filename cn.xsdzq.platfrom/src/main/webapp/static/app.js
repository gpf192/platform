'use strict';
var ngApp = angular.module("ngApp", ["ngAnimate", "ui.router", "ui.grid", "ui.grid.resizeColumns", "ui.grid.moveColumns",]);

//config
// 配置我们的路由
ngApp.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider.state("index", {//
		url : "/",
		templateUrl : "htmls/index/content.html",
		controller : "indexController"
	}).state("newuser", { 
		url : "/user/new",
		templateUrl : "htmls/user/newuser.html",
		controller : "newUserController"
	}).state("modifyuser", { 
		url : "/user/modify",
		templateUrl : "htmls/user/modifyuser.html",
		controller : "modifyUserController",
		params:{user:{}}
	}).state("userlist", { 
		url : "/user/list",
		templateUrl : "htmls/user/userlist.html",
		controller : "userListController"
	}).state("modifyrole", { 
		url : "/user/role/modify",
		templateUrl : "htmls/user/modifyrole.html",
		controller : "modifyRoleController",
		params:{user:{}}
	}).state("newrole", {
		url : "/role/new",
		templateUrl : "htmls/role/newrole.html",
		controller : "newRoleController"
	}).state("modifyrole2", {
		url : "/role/modify",
		templateUrl : "htmls/role/modifyrole.html",
		controller : "modifyRoleController",
		params:{role:{}}
	}).state("modifyauthority", {
		url : "/role/modify/authority",
		templateUrl : "htmls/role/modifyauthority.html",
		controller : "modifyAuthorityController",
		params:{role:{}}
	}).state("rolelist", {
		url : "/role/list",
		templateUrl : "htmls/role/rolelist.html",
		controller : "roleListController"
	}).state("addrole", {
		url : "/role/add",
		templateUrl : "htmls/admin/addrole.html",
		controller : "addRoleController"
	}).state("addauthority", {
		url : "/admin/addauthority",
		templateUrl : "htmls/admin/addauthority.html",
		controller : "addAuthorityController"
	}).state("categorylist", {
		url : "/category/list",
		templateUrl : "htmls/category/categorylist.html",
		controller : "categoryListController"
	}).state("newcategory", {
		url : "/category/new",
		templateUrl : "htmls/category/newcategory.html",
		controller : "newCategoryController"
	}).state("newmoduleupload", {
		url : "/module/upload",
		templateUrl : "htmls/module/newmoduleupload.html",
		controller : "newModuleUploadController",
		params:{moduleId:null}
	}).state("modifyCategory",{
		url:"/category/modify",
		templateUrl : "htmls/category/modifycategory.html",
		controller : "modifyCategoryController",
		params:{category:{}}
	}).state("modulelistmanage",{
		url:"/module/list/manage",
		templateUrl : "htmls/module/modulelist.html",
		controller : "moduleListManageController",
		params:{module:{}}
	}).state("newinfo", { 
		url : "/info/new",
		templateUrl : "htmls/info/newinfo.html",
		controller : "newVersionController"
	}).state("infolist", {
		url : "/info/list",
		templateUrl : "htmls/info/infolist.html",
		controller : "infoListController"
	}).state("checkdetail", {
		url : "/info/check/datail",
		templateUrl : "htmls/info/checkdetail.html",
		controller : "checkDetailController",
		params:{info:{}}
	}).state("modifyinfo", {
		url : "/info/modify",
		templateUrl : "htmls/info/modifyinfo.html",
		controller : "modifyInfoController",
		params:{info:{}}
	}).state("viewinfo", {
		url : "/info/view",
		templateUrl : "htmls/info/viewinfo.html",
		controller : "modifyInfoController",
		params:{info:{}}
	}).state("addrelate", {
		url : "/relate/add",
		templateUrl : "htmls/addrelate/addrelate.html",
		controller : "addrRelateController"
	}).state("explain", {
		url : "/explain",
		templateUrl : "htmls/explain/explain.html",
		controller : "explainController"
	}).state("getappconfig", {
		url : "/config/app",
		templateUrl : "htmls/config/appconfig.html",
		controller : "appConfigController",
		params:{state:{}}
	}).state("checklist", {
		url : "/info/checklist",
		templateUrl : "htmls/info/checklist.html",
		controller : "checkListController",
		params:{state:{}}
	}).state("result", {
		url : "/result",
		templateUrl : "htmls/common/result.html",
		controller : "resultController",
		params:{state:{}}
	}).state("sortcategory", {
		url : "/category/sort",
		templateUrl : "htmls/admin/sortcategory.html",
		controller : "sortCategoryController"
	}).state("kcList", {
		url : "/kc/kcList",
		templateUrl : "htmls/kc/kcList.html",
		controller : "kcListController"
	}).state("dzhgg", {
		url : "/dzh/dzhgg",
		templateUrl : "htmls/dzh/dzhgg.html",
		controller : "dzhggController"
	}).state("fundList", {
		url : "/finance/fundList",
		templateUrl : "htmls/finance/fundList.html",
		controller : "fundListController"
	}).state("divisionList", {
		url : "/finance/divisionList",
		templateUrl : "htmls/finance/divisionList.html",
		controller : "divisionListController"
	}).state("oddsList", {
		url : "/finance/oddsList",
		templateUrl : "htmls/finance/oddsList.html",
		controller : "oddsListController"
	}).state("rankList", {
		url : "/finance/rankList",
		templateUrl : "htmls/finance/rankList.html",
		controller : "rankListController"
	}).state("shareList", {
		url : "/finance/shareList",
		templateUrl : "htmls/finance/shareList.html",
		controller : "shareListController"
	}).state("turntablePrizeList", {
		url : "/turntablePrizeList",
		templateUrl : "htmls/finance/turntablePrizeList.html",
		controller : "turntablePrizeListController"
	}).state("lcjPrizeList", {
		url : "/lcjPrizeList",
		templateUrl : "htmls/finance/lcjPrizeList.html",
		controller : "lcjPrizeListController"
	}).state("lcjPrizeResultList", {
		url : "/lcjPrizeResultList",
		templateUrl : "htmls/finance/lcjPrizeResultList.html",
		controller : "lcjPrizeResultListController"
	}).state("addPrize", {
		url : "/addPrize",
		templateUrl : "htmls/finance/addPrize.html",
		controller : "addPrizeController"
	}).state("winPrizeList", {
		url : "/winPrizeList",
		templateUrl : "htmls/finance/winPrizeList.html",
		controller : "winPrizeListController"
	}).state("LcjPrizeResultList", {
		url : "/winLcjPrizeResultList",
		templateUrl : "htmls/finance/lcjPrizeResultList.html",
		controller : "lcjPrizeResultListController"
	}).state("activityProductsList", {
		url : "/activityProductsList",
		templateUrl : "htmls/finance/activityProductsList.html",
		controller : "activityProductsListController"
	}).state("newProduct", {
		url : "/newProduct",
		templateUrl : "htmls/finance/newProduct.html",
		controller : "newProductListController"
	}).state("userTIcketsList", {
		url : "/userTIcketsList",
		templateUrl : "htmls/finance/userTIcketsList.html",
		controller : "userTIcketsListController"
	}).state("userGetChanceList", {
		url : "/userGetChanceList",
		templateUrl : "htmls/finance/userGetChanceList.html",
		controller : "userGetChanceListController"
	}).state("userTotalChanceList", {
		url : "/userTotalChanceList",
		templateUrl : "htmls/finance/userTotalChanceList.html",
		controller : "userTotalChanceListController"
	}).state("contestantList", {
		url : "/contestantList",
		templateUrl : "htmls/finance/contestantList.html",
		controller : "contestantListController"
	}).state("productsSellList", {
		url : "/productsSellList",
		templateUrl : "htmls/finance/productsSellList.html",
		controller : "productsSellListController"
	}).state("productsSellCwList", {
		url : "/productsSellCwList",
		templateUrl : "htmls/finance/productsSellCwList.html",
		controller : "productsSellCwListController"
	}).state("participantsList", {
		url : "/participantsList",
		templateUrl : "htmls/finance/participantsList.html",
		controller : "participantsListController"
	}).state("addEmp", {
		url : "/addParticipants",
		templateUrl : "htmls/finance/addParticipants.html",
		controller : "addParticipantsController"
	}).state("modifyParticipants", {
		url : "/modifyParticipants",
		templateUrl : "htmls/finance/modifyParticipants.html",
		controller : "modifyParticipantsController",
		params:{participant:{}}
	}).state("addProduct", {
		url : "/addProduct",
		templateUrl : "htmls/finance/addProduct.html",
		controller : "addProductController"
	}).state("modifyProduct", {
		url : "/finance/modifyProduct",
		templateUrl : "htmls/finance/modifyProduct.html",
		controller : "modifyProductController",
		params:{product:{}}
	}).state("paramList", {
		url : "/finance/paramList",
		templateUrl : "htmls/finance/paramList.html",
		controller : "paramListController",
	}).state("modifyParam", {
		url : "/finance/modifyParam",
		templateUrl : "htmls/finance/modifyParam.html",
		controller : "modifyParamController",
		params:{param:{}}
	}).state("addParam", {
		url : "/finance/addParam",
		templateUrl : "htmls/finance/addParam.html",
		controller : "addParamController",
	}).state("userVoteForList", {
		url : "/userVoteForList",
		templateUrl : "htmls/finance/userVoteForList.html",
		controller : "userVoteForListController"
	}).state("turntableAwardList", {
		url : "/finance/awardList",
		templateUrl : "htmls/finance/awardList.html",
		controller : "awardListController"
	}).state("awardResultList", {
		url : "/finance/awardResultList",
		templateUrl : "htmls/finance/awardResultList.html",
		controller : "awardResultListController"
	}).state("liveLoginRecord", {
		url : "/finance/liveLoginRecord",
		templateUrl : "htmls/finance/liveLoginRecord.html",
		controller : "liveLoginRecordController"
	}).state("userRiskList", {
		url : "/thx/userRiskList",
		templateUrl : "htmls/thx/userRiskList.html",
		controller : "userRiskListController"
			
	}).state("userOrderList", {
		url : "/thx/userOrderList",
		templateUrl : "htmls/thx/userOrderList.html",
		controller : "userOrderListController"
			
	}).state("modifyContestant", {
		url : "/modifyContestant",
		templateUrl : "htmls/finance/modifyContestant.html",
		controller : "modifyContestantController",
		params:{contestant:{}}
	}).state("demo", {
		url : "/demo",
		templateUrl : "htmls/demo/demo.html",
		controller : "demoController"
	}).state("integralallocation", {
		url : "/integral/integralallocation",
		templateUrl : "htmls/integral/integralallocation.html",
		controller : "integralallocationController"
	}).state("integraladd", {
		url : "/integral/integraladd",
		templateUrl : "htmls/integral/integraladd.html",
		controller : "integraladdController"
	}).state("integralquery", {
		url : "/integral/integralquery",
		templateUrl : "htmls/integral/integralquery.html",
		controller : "integralqueryController"
	}).state("integralModify", {
		url : "/integral/integralModify",
		templateUrl : "htmls/integral/integralModify.html",
		controller : "integralModifyController",
		params:{param:{}}
	}).state("userTotalIntegralModify", {
		url : "/integral/userTotalIntegralModify",
		templateUrl : "htmls/integral/userTotalIntegralModify.html",
		controller : "userTotalIntegralModifyController",
		params:{param:{}}
	}).state("integralUserDetail", {
		url : "/integral/integralUserDetail",
		templateUrl : "htmls/integral/integralUserDetail.html",
		controller : "integralUserDetailController"
	}).state("integralImport", {
		url : "/integral/integralImport",
		templateUrl : "htmls/integral/integralImport.html",
		controller : "integralImportController"
	}).state("crmCreditRecord", {
		url : "/integral/crmCreditRecord",
		templateUrl : "htmls/integral/crmCreditRecord.html",
		controller : "crmCreditRecordController"
	}).state("userBlacklist", {
		url : "/integral/userBlacklist",
		templateUrl : "htmls/integral/userBlacklist.html",
		controller : "userBlacklistController"
	}).state("userBlacklistAdd", {
		url : "/integral/userBlacklistAdd",
		templateUrl : "htmls/integral/userBlacklistAdd.html",
		controller : "userBlacklistAddController"
	}).state("userBlacklistModify", {
		url : "/integral/userBlacklistModify",
		templateUrl : "htmls/integral/userBlacklistModify.html",
		controller : "userBlacklistModifyController",
		params:{param:{}}
	}).state("userBlacklistImport", {
		url : "/integral/userBlacklistImport",
		templateUrl : "htmls/integral/userBlacklistImport.html",
		controller : "userBlacklistImportController"
	}).state("crmProductList", {
		url : "/integral/crmProductList",
		templateUrl : "htmls/integral/crmProductList.html",
		controller : "crmProductListController"
	}).state("crmCreditApiMsg", {
		url : "/integral/crmCreditApiMsg",
		templateUrl : "htmls/integral/crmCreditApiMsg.html",
		controller : "crmCreditApiMsgController"
	}).state("commodityclassify", {
		url : "/commodity/commodityclassify",
		templateUrl : "htmls/commodity/commodityclassify.html",
		controller : "commodityclassifyController"
	}).state("commodityclassifyadd", {
		url : "/commodity/commodityclassifyadd",
		templateUrl : "htmls/commodity/commodityclassifyadd.html",
		controller : "commodityclassifyaddController"
	}).state("commodityclassifymodify", {
		url : "/commodity/commodityclassifymodify",
		templateUrl : "htmls/commodity/commodityclassifymodify.html",
		controller : "commodityclassifymodifyController",
		params:{param:{}}
	}).state("commodityadd", {
		url : "/commodity/commodityadd",
		templateUrl : "htmls/commodity/commodityadd.html",
		controller : "commodityaddController"
	}).state("commoditymodify", {
		url : "/commodity/commoditymodify",
		templateUrl : "htmls/commodity/commoditymodify.html",
		controller : "commoditymodifyController",
		params:{param:{}}
	}).state("cardadd", {
		url : "/commodity/cardadd",
		templateUrl : "htmls/commodity/cardadd.html",
		controller : "cardaddController"
	}).state("cardmodify", {
		url : "/commodity/cardmodify",
		templateUrl : "htmls/commodity/cardmodify.html",
		controller : "cardmodifyController",
		params:{param:{}}
	}).state("cardmanage", {
		url : "/commodity/cardmanage",
		templateUrl : "htmls/commodity/cardmanage.html",
		controller : "cardmanageController"
	}).state("cardImport", {
		url : "/commodity/cardImport",
		templateUrl : "htmls/commodity/cardImport.html",
		controller : "cardImportController"
	}).state("exchangerecords", {
		url : "/commodity/exchangerecords", 
		templateUrl : "htmls/commodity/exchangerecords.html",
		controller : "exchangerecordsController"
	}).state("commoditymanage", {
		url : "/commodity/commoditymanage",
		templateUrl : "htmls/commodity/commoditymanage.html",
		controller : "commoditymanageController"
	});
	

	$urlRouterProvider.otherwise("/");
});

ngApp.config(['$httpProvider',function($httpProvider) {
	
	$httpProvider.interceptors.push('xsdInterceptor');
	
}]);


