ngApp.$inject = [ '$scope', '$http', '$state', 'httpUtils', 'layerUtils' ];
function sortCategoryController($scope, $http, $state, httpUtils, layerUtils) {

	$scope.categorys = [];

	$scope.init = function() {
		var data = [{
			name : "栏目",
			goto:""

		},{
			name : "栏目排序",
			goto:"sortcategory"

		}];
		$scope.$emit("changeNavigation", data);
		$scope.getCategoryList();
	};
	
	$scope.getCategoryList = function() {
		$http.get(httpUtils.url.getDisplayCategories, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.categorys = data.result;
			}
		});
	}
	
	$scope.dragElement=null;
	$scope.initEvent=function(index){
		setTimeout(function(){
			var dragElement = document.getElementById("drag"+(index));
			var imgIcon = document.getElementById("drag-img-"+(index));
			dragElement.addEventListener("dragstart", function(event) {
				// console.log("dragstart");
				$scope.dragElement = this;
				// event.dataTransfer.setDragImage(imgIcon,45,45);
			}, false);
			
			dragElement.addEventListener("dragenter", function(event) {
				if ($scope.dragElement != this) {
					// console.log(this.parentNode);
					this.parentNode.insertBefore($scope.dragElement, this);
				}
			}, false);
			dragElement.addEventListener('dragleave', function(event) {
				if ($scope.dragElement != this) {
					if (this == this.parentNode.lastElementChild
							|| this == this.parentNode.lastChild) {
						this.parentNode.appendChild($scope.dragElement);
					}
				}
			}, false)
		},100);
	}
	$scope.sort = function(){
		var array=[];
		var wraper=$("#dragWrapper").children();
		wraper.each(function(index, domEle){
			var id=$(this).attr("id");
			array.push(id.substring(4));
		});
		if(array.join("")=="012345"){
			layerUtils.iMsg(-1, "请排序后，在提交");
		}
		for(var i=0;i<array.length;i++){
			var index=array[i];
			$scope.categorys[index].categorysort=i+1;
		}
		var params={
			categoryDTOs:$scope.categorys
		}
		var url = httpUtils.url.sortCategory
		$http.post(url, params).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1, "排序成功");
				$scope.formData = {};
			} else {
				layerUtils.iMsg(-1, "排序失败");
			}
		});

	}

	$scope.submit = function() {
		var params={};
		var rList = [];
		var len = $scope.roleList.length;
		// 增加的权限
		for (var i = 0; i < len; i++) {
			var role = $scope.roleList[i];
			if (role.check) {
				rList.push(role);
			}
		}
		params.user_id = $scope.formData.user.id;
		params.roleDTOs = rList;
		var url = httpUtils.url.userAddRoles;
		
		$http.post(url, params).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1, "添加成功");
				$scope.formData = {};
			} else {
				layerUtils.iMsg(-1, "添加失败");
			}
		});
	};
}