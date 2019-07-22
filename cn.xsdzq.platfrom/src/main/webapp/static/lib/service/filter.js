ngApp.filter('substring', function() { //可以注入依赖
    return function(text,start,end) {
    	if(text==null||text==""||text==undefined){
    		return;
    	}
    	var str=text.substring(start,end);
    	if(text.length>23){
    		str+="······";
    	}
    	var dd=str.replace(/<\/?.+?>/g,"");
    	var dds=dd.replace(/ /g,"");//dds为得到后的内容
        return dds;
    }
});

ngApp.filter('trustHtml', function ($sce) {
    return function (content) {
        return $sce.trustAsHtml(content);
    }
});

ngApp.filter('transfera', function() { 
	return function (value) {
	    if(value == 'generate'){
	    	return "待提交";
	    }
	    if(value == 'submit'){
	    	return "待审核";
	    }
	    if(value == 'approve'){
	    	return "已发布";
	    }
	    if(value == 'reject'){
	    	return "审核拒绝";
	    }
	}
});
ngApp.filter('lockTransform', function() { 
	return function (value) {
	    if(value == '5'){
	    	return "已锁定";
	    }
	    if(value != '5'){
	    	return "正常";
	    }
	    
	}
});
ngApp.filter('voteSourceFilter', function() { 
	return function (value) {
	    if(value == "1"){
	    	return "活动登录";
	    }
	    if(value == "2"){
	    	return "活动分享";
	    }
	    if(value == "3"){
	    	return "抽奖";
	    }
	    if(value == "4"){
	    	return "购买理财产品";
	    }
	    if(value == "5"){
	    	return "新开基金账户";
	    }
	    if(value == "6"){
	    	return "签约投顾";
	    }
	}
});
ngApp.filter('divisionFilter', function() { 
	return function (value) {
	    if(value == "0"){
	    	return "新手赛区";
	    }
	    if(value == "1"){
	    	return "王者赛区";
	    }

	}
});