ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils'];
function addPrizeController($scope, $http, $state, httpUtils, layerUtils) {
	$scope.customaryPrizeList = [];
	$scope.newPrizeList = [];
	$scope.formData = {};
	var modifyIndex = 0;
	var isModify = false;
	
	$scope.init=function(){
		isModify = false;
		angular.element('#newBuild').text('确认新增');
		//1.请求存在的奖品列表
		$scope.customaryPrizeList = [{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10},
			{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10},
			{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10},
			{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10},
			{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10},
			{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10},
			{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10},
			{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10}
			];
		$scope.newPrizeList =  [{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10},
			{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10},
			{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10},
			{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10},
			{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10},
			{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10},
			{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10},
			{prizeName:"苹果",prizeNum:20,prizeValue:3000,probability:"20%",winnersNum:10,remainPrizes:10}
			];
	};
	
	
	

	//添加和修改逻辑
	$scope.newBuild = function() {
		if(isModify) {
			var newProduct = {prizeName:$scope.formData.prizename,prizeNum:20,prizeValue:$scope.formData.prizeprice,probability:$scope.formData.probability,winnersNum:10,remainPrizes:10};
			console.log("newProduct="+newProduct.prizeName);
			$scope.newPrizeList.splice(modifyIndex,1,newProduct);
			console.log("newPrizeList="+$scope.newPrizeList);
			angular.element('#newBuild').text('确认新增');
		}else {
			//2.新建奖品 查看目前是否已经满足了八个产品
			if($scope.newPrizeList.length == 8) {
				//如果满足的话就不让添加
			}else {
				//如果不满足的话就让添加并且更新列表 
				var newProduct = {prizeName:$scope.formData.prizename,prizeNum:20,prizeValue:$scope.formData.prizeprice,probability:$scope.formData.probability,winnersNum:10,remainPrizes:10};
				$scope.newPrizeList.push(newProduct);
			}	
		}
		
		$scope.formData.prizename="";
		$scope.formData.prizeprice="";
		$scope.formData.prizenum="";
		$scope.formData.probability="";
	}
	
	
	//点击发布
	$scope.release = function() {
		//判断是否满足8个产品
		if($scope.newPrizeList.length == 8) {
			//如果满足8个产品
			//比较现在修改过的对象和原来的对象是否一致
			if(JSON.stringify(customaryPrizeList) == JSON.stringify(newPrizeList)) {
				//如果一致提示没有奖品更新
			}else {
				//如果不一致提交更新
			}
		}else {
			//如果不满足8个产品
			//提示奖品必须有8个	
		}
	}
	

	
	//修改功能 弹出框重新录入 更新列表
	
	$scope.modifyPrize = function(index) {
		modifyIndex = index;
		isModify = true;
		console.log("isModify="+isModify+"----modifyIndex="+modifyIndex);
		angular.element('#newBuild').text('确认修改');
//		$scope.newBuild.text("确认修改");
		angular.element('#prizename').focus();
//		$scope.prizeprice
		
	}
	

	$scope.submit = function() {
		console.log($scope.formData);
		$scope.formData = {};
		
		//每次添加查询奖品接口看有多少个
		//如果不足8个就可以添加 
		
	}
	
	$scope.deletePrize = function(index) {
		//删除功能 新的奖品列表对象 删除纪录
		//删除一个元素
		$scope.newPrizeList.splice(index,1);
	}
	
}