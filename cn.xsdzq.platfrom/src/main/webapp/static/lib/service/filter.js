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
	    if(value == "9") {
	    	return "参与评选";
	    }
	}
});

ngApp.filter('kmhSourceFilter', function() { 
	return function (value) {
	    if(value == "13"){
	    	return "购买产品";
	    }
	    if(value == "16"){
	    	return "签约投顾";
	    }
	  
	}
});

ngApp.filter('lcjSourceFilter', function() { 
	return function (value) {
		if(value == "12"){
	    	return "分享";
	    }
	    if(value == "13"){
	    	return "购买产品";
	    }
	    if(value == "16"){
	    	return "签约投顾";
	    }else{
	    	return "其他";
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

ngApp.filter('riskLevelFilter', function() { 
	return function (value) {
	    if(value == "r1"){
	    	return "低风险等级";
	    }
	    if(value == "r2"){
	    	return "中低风险等级";
	    }
	    if(value == "r3"){
	    	return "中风险等级";
	    }
	    if(value == "r4"){
	    	return "中高风险等级";
	    }
	    if(value == "r5"){
	    	return "高风险等级";
	    }
	}
});
ngApp.filter('fundTypeFilter', function() { 
	return function (value) {
	    if(value == 0){
	    	return "场内";
	    }
	    if(value == 1){
	    	return "场外";
	    }
	}
});

//同花顺
ngApp.filter('userRiskFilter', function() { 
	return function (value) {
	    if(value == "1"){
	    	return "保守型";	    	
	    }
	    if(value == "2"){
	    	return "谨慎性";
	    }
	    if(value == "3"){
	    	return "稳健性";
	    }
	    if(value == "4"){
	    	return "积极性";
	    }
	    if(value == "5"){
	    	return "激进型";
	    }
	    if(value == "6"){
	    	return "失信记录";
	    }
	}
});
ngApp.filter('goodsRiskFilter', function() { 
	return function (value) {
	    if(value == "1"){
	    	return "低风险";
	    }
	    if(value == "2"){
	    	return "中低风险";
	    }
	    if(value == "3"){
	    	return "中风险";
	    }
	    if(value == "4"){
	    	return "中高风险";
	    }
	    if(value == "5"){
	    	return "高风险";
	    }
	    if(value == "6"){
	    	return "无";
	    }
	}
});

ngApp.filter('goodsTypeFilter', function() { 
	return function (value) {
	    if(value == "1"){
	    	return "服务包";	 
	    }
	    if(value == "2"){
	    	return "T策略";
	    }
	    if(value == "3"){
	    	return "付费live";
	    }
	    if(value == "4"){
	    	return "付费咨询";
	    }
	    if(value == "5"){
	    	return "付费群";
	    }
	    if(value == "6"){
	    	return "会员卡";
	    }
	}
});

ngApp.filter('matchInstructionFilter', function() { 
	return function (value) {
	    if(value == "1"){
	    	return "匹配";	 
	    }
	    if(value == "0"){
	    	return "不匹配";
	    }
	    if(value == null){
	    	return "无匹配结果";
	    }
	 
	}
});


ngApp.filter('orderStatusFilter', function() { 
	return function (value) {
	    if(value == "1"){
	    	return "支付完成";	 
	    }
	    if(value == "2"){
	    	return "已退款";
	    }
	    
	}
});
ngApp.filter('openFlag', function() { 
	return function (value) {
	    if(value == 0){
	    	return "否";
	    }
	    if(value == 1){
	    	return "是";
	    }
	    
	}
});
ngApp.filter('omitFilter', function() { 
	return function (str) {
		if(str){
            var carContent = '';
            if(str.length >= 5){
                str.length = 5;
                carContent = str.substring(0,5) + '...';
            }
            else {
                carContent = str
            }
            return carContent
        }
	    
	}
});
