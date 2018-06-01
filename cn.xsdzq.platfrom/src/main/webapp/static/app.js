'use strict';
var ngApp = angular.module("ngApp", ["ngAnimate", "ui.router"]);

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
	});

	$urlRouterProvider.otherwise("/");
});

ngApp.config(['$httpProvider',function($httpProvider) {
	
	$httpProvider.interceptors.push('pingnInterceptor');
	
}]);


