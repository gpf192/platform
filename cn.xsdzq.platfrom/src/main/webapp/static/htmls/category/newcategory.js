ngApp.$inject = [ '$scope', '$http', ' $state', 'httpUtils', 'layerUtils' ,'FileUploader'];
function newCategoryController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.formData = {};

	$scope.init = function() {
		var data = {
			"one" : {
				name : "分类管理",
				goto:"newcategory"

			},
			"two" : {
				name : "新建分类",
				goto:"newcategory"

			}
		}
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
	
	
	
	//
	
	

	 var productInfo=[];
    var uploader = $scope.uploader = new FileUploader({
       url: httpUtils.url.add,
       formData:productInfo
   });

     uploader.onSuccessItem = function(fileItem, response, status,  headers) {             
            alert(response);   
    };
      $scope.addim = function() {
    	  console.log("yyyyyyyyy");
       uploader.uploadAll();
       
   };
      
      
      
	//
/*	 $scope.getFile = function () {
	    	console.log("jinru *** Uploader");
	        fileReader.readAsDataUrl($scope.myfile, $scope)
	                      .then(function(result) {
	                          $scope.imageSrc = result;
	                      });
	    };
}*/
	
	   $scope.reader = new FileReader();   //创建一个FileReader接口
	    $scope.form = {     //用于绑定提交内容，图片或其他数据
	        image:{},
	    };
	    $scope.thumb = {};      //用于存放图片的base64
	    $scope.thumb_default = {    //用于循环默认的‘加号’添加图片的框
	        1111:{}
	    };

	    $scope.img_upload = function(files) {      //单次提交图片的函数
	    	console.log("img_upload  ******");
	    	$scope.guid = (new Date()).valueOf();   //通过时间戳创建一个随机数，作为键名使用
	        $scope.reader.readAsDataURL(files[0]);  //FileReader的方法，把图片转成base64
	        $scope.reader.onload = function(ev) {
	            $scope.$apply(function(){
	                $scope.thumb[$scope.guid] = {
	                    imgSrc : ev.target.result,  //接收base64
	                }
	            });
	        };
	        
	        var data1 = new FormData();      //以下为像后台提交图片数据
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

	    $scope.img_del = function(key) {    //删除，删除的时候thumb和form里面的图片数据都要删除，避免提交不必要的
	    	console.log("delete ****");
	    	var guidArr = [];
	        for(var p in $scope.thumb){
	            guidArr.push(p);
	        }
	        delete $scope.thumb[guidArr[key]];
	        delete $scope.form.image[guidArr[key]];
	    };
	    $scope.submit_form = function(){    //图片选择完毕后的提交，这个提交并没有提交前面的图片数据，只是提交用户操作完毕后，　　到底要上传哪些，通过提交键名或者链接，后台来判断最终用户的选择,整个思路也是如此
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
