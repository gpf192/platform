ngApp.$inject = ['$scope', '$http', '$state','$stateParams', '$gridService', 'httpUtils', 'layerUtils'];
function xfvideoListController($scope, $http, $state,  $stateParams, $gridService, httpUtils, layerUtils) {
	$scope.formData = {};
	$scope.users=[];
	
	$scope.init=function(){
		var data = {
				"one" : {
					name : "基金视频管理",
					goto:""

				},
				"two" : {
					name : "新发基金视频列表",
					goto:"xfvideoList"

				}
			}
		
		$scope.currentPage = {
				page : 0
			};
			$scope.selectNumList = [{
				num : 10
			}, {
				num : 50
			}, {
				num : 100
			}];
			
		$scope.$emit("changeNavigation", data);
		$scope.getUserList(10);
	};
	

	$scope.newVideo = function() {
		$state.go("newVideo");
	}
	$scope.getUserList = function(pageSize) {
		
		var url = httpUtils.url.videoList;
		var params = {
			fundType : 2,
			pageNumber : 0,
			pageSize : pageSize
		};
		var settings = {
			url : url,
			showPage : 7,
			pageSize : pageSize,
			putDataList : "users"
		};
		var tableElement = angular.element("#datatable1");
		$gridService.queryTableDatas($scope, tableElement, params, settings, $http);
	};
	
	$scope.modifyUser =function(index){
		var user=$scope.users[index];
		console.log(user);
		$state.go("modifyVideo",{user:user});
	}
	
	$scope.deleteUser = function(index) {
		layerUtils.iConfirm("是否需要删除", function() {
			var url = httpUtils.url.deleteVideo
			var user=$scope.users[index];
			$http.post(url, user).success(function(data) {
				if (data.resCode == 0) {
					layerUtils.iMsg(-1, "删除成功");
					$scope.getUserList(10);
				}
			});
		}, function() {
			console.log("取消");
		});
	}

	$scope.submit = function() {
		
		var url = httpUtils.url.addUser;
		$http.post(url, $scope.formData).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"添加成功");
				$scope.formData={};
			} else {
				layerUtils.iMsg(-1,"添加失败");
			}
		});
	};
}