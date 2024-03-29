ngApp.controller("indexController", function($scope, $state, $http, httpUtils) {

	$scope.init = function() {
		$scope.isOpen=false;
		$scope.isActive=false;
		var url=httpUtils.url.menu;
		$http.get(url).success(function(data) {
			if (data.resCode == 0) {
				$scope.menu=data.result.menu;
				$scope.user=data.result.user;
				handlerMenu();
			} else {
				layerUtils.iMsg(-1,"菜单获取失败");
			}
		});
	};
	
	var handlerMenu=function(){
		for(var i=0;i<$scope.menu.length;i++){
			$scope.menu[i].isOpen=false;
			$scope.menu[i].isActive=false;
			if($scope.menu[i].child.length>0){
				$scope.menu[i].arrow=true;
			}
			for(var j=0;j<$scope.menu[i].child.length;j++){
				$scope.menu[i].child[j].isCurrent=false;
			}
		}
	}
	
	$scope.$on("changeNavigation",function(event,data){
		$scope.navigation=data;
	});
	
	$scope.goto=function(state){
		$state.go(state);
	}
	$scope.gotoMain=function(){
		$state.go("index");
		$scope.navigation=[];
	}
	$scope.gotoNav=function(state){
		console.log($scope.navigation);
		console.log(state);
		for(var i =0;i<$scope.navigation.length;i++){
			if(i=1){
				return;
			}
			if($scope.navigation[i]['goto']==state&&(i<$scope.navigation.length-1)){
				//$scope.navigation=$scope.navigation.splice(i-1);
				$scope.navigation.splice(i-1);
				console.log($scope.navigation);
			}
		}
		$state.go(state);
	}
	
	$scope.gotoMenu = function(first,second,state,$event){
		$event.stopPropagation();
		$state.go(state);
//		if(first==1&&second==0) {
//			$state.go("exchangerecords")
//		}else {
//			$state.go(state);
//		}
		
		//console.log("1"+window.location.url()); 
		console.log("2 "+window.location.hash); 
		//var hashPath = window.location.hash;
		// if((state == "infolist") || (state == "checklist")){
		//if((hashPath != "#/") && (state == "infolist" || state == "checklist")){
			// location.reload();
	//	}
		console.log(first+" "+second);
		for(var i=0;i<$scope.menu.length;i++){
			if(i==first){
				$scope.menu[i].isActive=true;
			}else{
				$scope.menu[i].isActive=false;
			}
			for(var j=0;j<$scope.menu[i].child.length;j++){
				if(first==i&&second==j){
					$scope.menu[first].child[second].isCurrent=true;
				}else{
					$scope.menu[i].child[j].isCurrent=false;
				}
			}
		}
	}
	
	$scope.isOpen=true;
	$scope.expanded = function(index){
		for(var i=0;i<$scope.menu.length;i++){
			if(index==i){
				$scope.menu[i].isOpen=true;
			}else{
				$scope.menu[i].isOpen=false;
			}
		}
	}

});
