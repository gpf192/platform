ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', 'httpUtils','layerUtils' ];
function categoryListController($scope, $http, $state, $stateParams, httpUtils,layerUtils) {

	$scope.categorys = [];
	$scope.init = function() {
		var data = [{
					name : "栏目",
					goto:"newcategory"
				},{
					name : "栏目管理",
					goto:"categorylist"
				}]
		$scope.$emit("changeNavigation", data);
		$scope.getCategoryList();
	};
	$scope.getCategoryList = function() {
		$http.get(httpUtils.url.categoryListExcept, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categorys = data.result;
				console.log($scope.categorys);
			}
		});
	}

	$scope.modifyCategory = function(index) {
		console.log($scope.categorys[index]);
		$state.go("modifyCategory", {
			category : $scope.categorys[index]
		});
	};
	
	$scope.copyUrl = function(index) {
		console.log($scope.categorys[index]);
		var category=$scope.categorys[index];
		var text=window.location.protocol+"//"+window.location.hostname+":"+window.location.port+"/front/#/list/"+category.id+"/"+category.title;
		if (window.clipboardData) {//如果是IE浏览器
	        window.clipboardData.setData('text', text);
	    } else {//非IE浏览器
    	   var textarea = document.createElement('textarea');
    	   textarea.value = text;
           document.body.appendChild(textarea);
           textarea.select(); // 选择对象
           document.execCommand("Copy"); // 执行浏览器复制命令
    	   textarea.style.display='none';
	    }
		layerUtils.iMsg(-1, "复制成功");
	}

	$scope.deleteCategory = function(index) {
		layerUtils.iConfirm("是否需要删除栏目？删除栏目同时将会删除栏目下所有文章？", function() {
			var id = $scope.categorys[index].id;
			var url = httpUtils.url.deleteCategory
			var param = {
					id:id
			}
			$http.post(url, param).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "删除成功");
					$scope.getCategoryList();
				}
			});
		}, function() {
			console.log("取消");
		});
	}

}