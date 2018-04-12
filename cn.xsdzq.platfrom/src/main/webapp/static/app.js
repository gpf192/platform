'use strict';
var ngApp = angular.module("ngApp", ["ngAnimate", "ui.router"]);

//config
// 配置我们的路由
ngApp.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider.state("index", {
		url : "/",
		templateUrl : "htmls/index/content.html",
		controller : "indexController"
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
	}).state("modifyinfo", {
		url : "/info/modify",
		templateUrl : "htmls/info/modifyinfo.html",
		controller : "modifyInfoController",
		params:{id:{}}
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
	}).state("result", {
		url : "/result",
		templateUrl : "htmls/common/result.html",
		controller : "resultController",
		params:{state:{}}
	});

	$urlRouterProvider.otherwise("/");
});

ngApp.config(['$httpProvider',function($httpProvider) {
	
	$httpProvider.interceptors.push('pingnInterceptor');
	
}]);


