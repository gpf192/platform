ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils', 'utils'];
function turntablePrizeListController($scope, $http, $state, httpUtils, layerUtils, utils) {
	var customaryPrizeList = [];
	$scope.newPrizeList = [];
	$scope.formData = {};
	var modifyIndex = 0;
	$scope.isModify = false;
	
	$scope.init=function(){
		var data = {
				"one" : {
					name : "转盘中奖管理",
					goto:"turntablePrizeList"
				},
				"two" : {
					name : "转盘奖品查询",
					goto:"turntablePrizeList"

				}
			}
		$scope.$emit("changeNavigation", data);
		$scope.isModify = false;
		angular.element('#newBuild').text('确认新增');
		$scope.getPrizeList();
	};
	
	
	$scope.getPrizeList = function() {
		//1.请求存在的奖品列表
		var url = httpUtils.url.turntablePrizeList;
		$http.get(url, {}).success(function(data) {
			if (data.resCode == 0) {
					if(!utils.isEmpty(data.result)) {
						var finalData = data.result;
						customaryPrizeList =[].concat(finalData); ;
						$scope.newPrizeList = [].concat(finalData);	
					}
			}
		});

		
	}
	

	//添加和修改逻辑
	$scope.newBuild = function() {
		if(utils.isEmpty($scope.formData.name)||utils.isEmpty($scope.formData.amount)) {
			layerUtils.iMsg(-1,"参数未填写!");
			return;
		}
		if($scope.isModify) {
			var childPrize = $scope.newPrizeList[modifyIndex];
			var url = httpUtils.url.modifyPrize;
			var newProduct = {id:childPrize.id,name:$scope.formData.name,amount:$scope.formData.amount,price:$scope.formData.price,rate:$scope.formData.rate};
			$http.post(url,newProduct).success(function(data) {
				if (data.resCode == 0) {
					$scope.isModify = false;
					angular.element('#newBuild').text('确认新增');
					childPrize.name = $scope.formData.name;
					childPrize.price = $scope.formData.price;
					childPrize.amount = $scope.formData.amount;
					childPrize.rate = $scope.formData.rate;
					
					layerUtils.iMsg(-1,"修改成功");
					$scope.formData.name="";
					$scope.formData.price="";
					$scope.formData.amount="";
					$scope.formData.rate="";
					
				}
			});
		}else {
			//2.新建奖品 查看目前是否已经满足了八个产品
			if($scope.newPrizeList.length == 12) {
				//如果满足的话就不让添加
				layerUtils.iMsg(-1,"奖品已满足12个");
			}else {
				//如果不满足的话就让添加并且更新列表 
				var newProduct = {name:$scope.formData.name,amount:$scope.formData.amount,price:$scope.formData.price,rate:$scope.formData.rate};
				$scope.newPrizeList.push(newProduct);
				console.log("newPrizeList="+angular.toJson($scope.newPrizeList));
				console.log("customaryPrizeList="+angular.toJson(customaryPrizeList));
				$scope.formData.name="";
				$scope.formData.price="";
				$scope.formData.amount="";
				$scope.formData.rate="";
				
			}	
		}
		
	}
	
	
	//点击发布
	$scope.release = function() {
		if($scope.isModify) {
			layerUtils.iMsg(-1,"目前在修改状态！");
			return;
		}
		//判断是否满足8个产品
		if($scope.newPrizeList.length >= 12) {
			//如果满足8个产品
			//比较现在修改过的对象和原来的对象是否一致
			if(angular.toJson(customaryPrizeList) == angular.toJson($scope.newPrizeList)) {
				console.log("customaryPrizeList="+angular.toJson(customaryPrizeList));
				console.log("newPrizeList="+angular.toJson($scope.newPrizeList));
				//如果一致提示没有奖品更新
				layerUtils.iMsg(-1,"奖品没有更新");
			}else {
				//如果不一致提交更新
				var url = httpUtils.url.addPrize;
				$http.post(url,{batchPrizeJson:angular.toJson($scope.newPrizeList)}).success(function(data) {
					if (data.resCode == 0) {
						layerUtils.iMsg(-1,"奖品添加成功");
						customaryPrizeList =[].concat($scope.newPrizeList); ;
					}
				});

			}
		}else {
			//如果不满足8个产品
			//提示奖品必须有8个
			layerUtils.iMsg(-1,"奖品不满足12个");
		}
	}
	

	
	//修改功能 弹出框重新录入 更新列表
	
	$scope.modifyPrize = function(index) {
		if($scope.isModify) {
			layerUtils.iMsg(-1,"目前在修改状态！");
			return;
		}
		modifyIndex = index;
		$scope.isModify = true;
		angular.element('#newBuild').text('确认修改');
		angular.element('#name').focus();
		angular.copy($scope.newPrizeList[index],$scope.formData);
	}
	
	
	$scope.deletePrize = function(index) {
		if($scope.isModify) {
			layerUtils.iMsg(-1,"目前在修改状态！");
			return;
		}
		//删除功能 新的奖品列表对象 删除纪录
		//删除一个元素
		layerUtils.iConfirm("是否需要删除奖品", function() {
			var url = httpUtils.url.deletePrize;
			var prize=$scope.newPrizeList[index];
			$http.post(url, prize).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "删除成功");
					$scope.getPrizeList();
				}else if (data.resCode = 1) {
					 layerUtils.iMsg(-1, "已有中奖纪录 ，无法删除 ");
				}
			});
		}, function() {
			console.log("取消");
		});
	}
	
	$scope.cancleModify = function() {
		if($scope.isModify) {
			$scope.isModify = false;
			angular.element('#newBuild').text('确认新增');
			$scope.formData.name="";
			$scope.formData.price="";
			$scope.formData.amount="";
			$scope.formData.rate="";
			
		}
	}
	
}