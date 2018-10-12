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