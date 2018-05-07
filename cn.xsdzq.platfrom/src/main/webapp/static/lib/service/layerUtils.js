ngApp.factory('layerUtils', [ function() {
	function iMsg(num, msg) {
		$.dialog({
			title : "提示信息",
			content : msg,
			lock : true,
			opacity : "0.3",
			time : 2000
		});
	}

	function iAlert(msg, success) {
		$.dialog({
			title : "提示",
			content : msg,
			lock : true,
			opacity : "0.3",
			ok : success
		});
	}

	function iLoading(flag) {
		var $load = $("#xubox_layer6");
		if (flag) {
			$load.show();
		} else {
			$load.hide();
		}
	}

	function iConfirm(msg, callFun, cancelFun) {
		$.dialog({
			title : "确认信息",
			content : msg,
			lock : true,
			opacity : "0.3",
			okValue : "确认",
			style : 'confirm',
			ok : callFun,
			cancelValue : "取消",
			cancel : cancelFun
		});
	}

	var layerUtils = {
		iMsg : iMsg,
		iAlert : iAlert,
		iLoading : iLoading,
		iConfirm : iConfirm
	};
	return layerUtils;
} ]);
