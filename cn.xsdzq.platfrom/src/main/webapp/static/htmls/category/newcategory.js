ngApp.$inject = [ '$scope', '$http', ' $state', 'httpUtils', 'layerUtils' ,'FileUploader'];
function newCategoryController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};

	$scope.init = function() {
		var data = [ {
				name : "分类管理",
				goto:"newcategory"

			}, {
				name : "新建分类",
				goto:"newcategory"

			}];
		$scope.$emit("changeNavigation", data);
	};

	$scope.submit = function() {
		console.log("jinru *** newCategoryController");
		var url = httpUtils.url.addCategory;
		
		if (angular.isEmpty($scope.formData.title)) {
			layerUtils.iMsg(-1, "分类名称不能为空");
			return;
		}
		$http.post(url, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1, "添加成功");
			} else {
				layerUtils.iMsg(-1, data.resMSg);
			}
		});
	};
	
	//图片上传元素添加监听
	var readFront;
	var imageInput = document.getElementById("image");
	var tempImage = document.getElementById("tempImage");
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
					layerUtils.iLoading(false);
					return false;
				}
				readFront = new FileReader();
				readFront.readAsDataURL(imgageFile);
				readFront.onload = function() {
					layerUtils.iLoading(false);
					var contentFront = readFront.result;
					tempImage.src = "";
					tempImage.src = contentFront;
					console.log(tempImage.src);
				}
			}else {
				layerUtils.iAlert("当前浏览器不支持，请切换浏览器，推荐使用chrome浏览器");
				//需要处理不支持的情况
			}
		});
	}
	imageLoad();
	$scope.addCategory=function(){
		var url = httpUtils.url.addCategory;
		var image = tempImage.src;
		if(image.length>0){
			
		}
		$scope.formData.image=image;
		if (angular.isEmpty($scope.formData.title)) {
			layerUtils.iMsg(-1, "分类名称不能为空");
			return;
		}
		$http.post(url, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1, "添加成功");
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
	
      
     
	
    $scope.reader = new FileReader();   // 创建一个FileReader接口
    $scope.form = {     // 用于绑定提交内容，图片或其他数据
        image:{},
    };
    $scope.thumb = {};      // 用于存放图片的base64
    $scope.thumb_default = {    // 用于循环默认的‘加号’添加图片的框
        1111:{}
    };

    $scope.img_upload = function(files) {      // 单次提交图片的函数
    	console.log("img_upload  ******");
    	$scope.guid = (new Date()).valueOf();   // 通过时间戳创建一个随机数，作为键名使用
        $scope.reader.readAsDataURL(files[0]);  // FileReader的方法，把图片转成base64
        $scope.reader.onload = function(ev) {
            $scope.$apply(function(){
                $scope.thumb[$scope.guid] = {
                    imgSrc : ev.target.result,  // 接收base64
                }
            });
        };
        
        var data1 = new FormData();      // 以下为像后台提交图片数据
        data1.append('image', files[0]);
        data1.append('guid',$scope.guid);
		var uploadUrl = httpUtils.url.upload;
		console.log(uploadUrl);
		
		
		
        $http({
            method: 'post',
            url: uploadUrl,
            data:data1,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).success(function(data) {
        	console.log("resCode  ******");
            if (data1.resCode == 0) {
                $scope.form.image[data.guid] = data1.resCode;
                $scope.thumb[data.guid].status = 'SUCCESS';
                console.log($scope.form)
            }
            else{
                console.log(data)
            }
        })
    };

    $scope.img_del = function(key) {    // 删除，删除的时候thumb和form里面的图片数据都要删除，避免提交不必要的
    	console.log("delete ****");
    	var guidArr = [];
        for(var p in $scope.thumb){
            guidArr.push(p);
        }
        delete $scope.thumb[guidArr[key]];
        delete $scope.form.image[guidArr[key]];
    };
    $scope.submit_form = function(){    // 图片选择完毕后的提交，这个提交并没有提交前面的图片数据，只是提交用户操作完毕后，
										// 到底要上传哪些，通过提交键名或者链接，后台来判断最终用户的选择,整个思路也是如此
        console.log("sasdasads");
    	$http({
            method: 'post',
            url: '/comm/test.php',
            data:$scope.form,
        }).success(function(data) {
            console.log(data);   
        })
    };
	
}	
