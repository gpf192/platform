ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils'];
function modifyCategoryController($scope, $http, $state, $stateParams, httpUtils, layerUtils) {
	$scope.formData = {};
	$scope.init = function() {
		var data = [{
					name : "分类管理",
					goto:""
				},{
					name : "分类列表管理",
					goto:"categorylist"
				},{
					name : "分类名称修改",
					goto:"modifyCategory"
				}];
		$scope.$emit("changeNavigation", data);
		angular.copy($stateParams.category,$scope.formData);
		if (angular.equals($scope.formData, {})) {
			$state.go("categorylist");
			return;
		}
		document.getElementById("tempImage").src=$scope.formData.image;
	};
	

	$scope.submit = function() {
		if(angular.equals($scope.formData, $stateParams.category)){
			layerUtils.iMsg(-1, "请输入更改标题");
			return;
		}		
		$http.post(httpUtils.url.modifyCategory, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				//window.sessionStorage.setItem("moduleName", $scope.formData.moduleName);
				layerUtils.iMsg(-1, "修改成功");
			} else {
				layerUtils.iMsg(-1, data.resMsg);
			}
		});
	};

	//图片上传元素添加监听
	var readFront;
	var imageInput = document.getElementById("image");
	var tempImage = document.getElementById("tempImage");
	var src1 = tempImage.src;
	function imageLoad(){
		imageInput.addEventListener("change", function() {
			layerUtils.iLoading(true);
			if (FileReader) {
				var imgageFile = imageInput.files[0];
				var fileType = imgageFile.type;
				var immeArray = ['image/jpg', 'image/jpeg', 'image/png'];
				var flag = false;
				for (var i = 0; i < immeArray.length; i++) {
					if (fileType == immeArray[i]) {
						flag = true;
					}
				}
				if (!flag) {
					layerUtils.iMsg(-1, "上传文件的类型不正确，请选择图片上传！");
					return false;
				}
				readFront = new FileReader();
				readFront.readAsDataURL(imgageFile);
				readFront.onload = function() {
					layerUtils.iLoading(false);
					var contentFront = readFront.result;
					tempImage.src = "";
					tempImage.src = contentFront;
					//console.log(tempImage.src);
				}
			}else {
				layerUtils.iAlert("当前浏览器不支持，请切换浏览器，推荐使用chrome浏览器");
				//需要处理不支持的情况
			}
		});
	}
	imageLoad();
	$scope.modifyCategory=function(){
		var url = httpUtils.url.modifyCategory;
		var image = tempImage.src;
		//判断是否上传了图片
		if(src1.length == image.length){
			image = "";
		}
		$scope.formData.image=image;		
		if (angular.isEmpty($scope.formData.title)) {
			layerUtils.iMsg(-1, "分类名称不能为空");
			return;
		}
		$http.post(url, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1, "修改成功");
			} else {
				layerUtils.iMsg(-1, data.resMSg);
			}
		});
		
	}
	
	//可以压缩图片版本
	function imageEventListener() {
		imageInput.addEventListener("change", function() {
			layerUtils.iLoading(true);
			if (FileReader) {
				//console.log("---------------3----------------");
				// 在这里处理上传信息
				var imgageFile = imageInput.files[0];
				var fileSize = imgageFile.size;
				var fileType = imgageFile.type;
				var fileTSize = (Math.round(fileSize * 100 / 1024) / 100);
				var immeArray = ['image/jpg', 'image/jpeg', 'image/png'];
				var flag = false;
				for (var i = 0; i < immeArray.length; i++) {
					if (fileType == immeArray[i]) {
						flag = true;
					}
				}
				if (!flag) {
					layerUtils.iMsg(-1, "上传文件的类型不正确，请选择图片上传！");
					return false;
				}
				var quality;
				var isoRadio;
				var radio = 5;
				if (fileTSize < 200) {
					isoRadio = 1;
					radio = 1;
					quality = 100;

				} else if (fileTSize < 500 && fileTSize >= 200) {
					quality = 90;
					isoRadio = 2;
					radio = 2;
				} else if (fileTSize >= 500 && fileTSize < 1024) {
					quality = 80;
					isoRadio = 6;
					radio = 2;
				} else if (fileTSize >= 1024 && fileTSize < 1500) {
					quality = 70;
					isoRadio = 8;
					radio = 3;
				} else if (fileTSize >= 1500 && fileTSize < 2048) {
					quality = 60;
					isoRadio = 8;
					radio = 4;
				} else if (fileTSize > 2048) {
					quality = 50;
					isoRadio = 10;
					radio = 5;
				} else {
					quality = 60;
				}
				readFront = new FileReader();
				readFront.readAsDataURL(imgageFile);
				readFront.onload = function() {
					layerUtils.iLoading(false);
					var contentFront = readFront.result;
					tempImage.src = "";
					tempImage.src = contentFront;
					if (fileTSize < 500) {
						document.getElementById("image2").src = contentFront;
						$(_pageId).addClass("dispaly_none");
						$(".upload_image").removeClass("dispaly_none");
						$("body").addClass("block");
						return;
					}
					tempImage.onload = function() {
						var width = tempImage.naturalWidth > 0 ? tempImage.naturalWidth : tempImage.width;
						var height = tempImage.naturalHeight > 0 ? tempImage.naturalHeight : tempImage.height;
						var naturalWidth = tempImage.naturalWidth;
						var naturalHeight = tempImage.naturalHeight;
						var reallyHeight;
						var reallyWidth;
						if (ios) {
							reallyHeight = height / isoRadio;
							reallyWidth = width / isoRadio;
							var vertSquashRatio = detectVerticalSquash(tempImage);
							canvas.height = reallyHeight;
							canvas.width = reallyWidth;
							tempImage.width = reallyWidth;
							tempImage.height = reallyHeight;
							ctx.drawImage(tempImage, 0, 0, reallyWidth, (reallyHeight / vertSquashRatio));
							document.getElementById("image2").src = canvas.toDataURL(fileType, quality / 100);
						} else {
							reallyHeight = height / radio;
							reallyWidth = width / radio;
							canvas.height = reallyHeight;
							canvas.width = reallyWidth;
							ctx.drawImage(tempImage, 0, 0, reallyWidth, reallyHeight);
							document.getElementById("image2").src = canvas.toDataURL(fileType, quality / 100);
						}
						$(_pageId).addClass("dispaly_none");
						$(".upload_image").removeClass("dispaly_none");
						$("body").addClass("block");
					};
				};
				readFront.onerror = function() {
					layerUtils.iLoading(false);
					if ($scope.mediaId == 4) {
						layerUtils.iMsg(-1, "身份证正面照上传失败，请重新上传！");
					} else if ($scope.mediaId == 3) {
						layerUtils.iMsg(-1, "身份证反面照上传失败，请重新上传！");
					} else if ($scope.mediaId == 5) {
						layerUtils.iMsg(-1, "头像上传失败，请重新上传！");
					} else {
						layerUtils.iMsg(-1, "照片上传失败，请重新上传！");
					}
				};

			} else {
				layerUtils.iAlert("不支持FileReader对象");
				//需要处理不支持的情况
			}
		}, false);
	}

	//imageEventListener();
}