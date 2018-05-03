ngApp.filter('substring', function() { //可以注入依赖
    return function(text,start,end) {
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