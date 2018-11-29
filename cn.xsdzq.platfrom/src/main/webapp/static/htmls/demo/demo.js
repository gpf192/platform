ngApp.$inject = [ '$scope', '$http', '$state', '$stateParams', 'httpUtils', 'layerUtils', 'utils' ];
function demoController($scope, $http, $state, $stateParams, httpUtils, layerUtils, utils) {

	$scope.init = function() {
		
	};
	
	$scope.gridOptions = {
      enableSorting: true,
      //编辑完成后执行事件
      onRegisterApi: function (gridApi) {
          $scope.gridApi = gridApi;
          $scope.gridApi.edit.on.afterCellEdit($scope, function (rowEntity) {
          //编辑离开事件
          $http.post('/manage/api/dictionary/update', {
          "accountName": rowEntity.accountName,
          "userName": rowEntity.userName,
          "unitName": rowEntity.unitName,
           })
              .success(function (data) {
                 if (data.meta.success) {
                     alert('修改成功');
                 } else {
                 alert(data.meta.message);
                 }
           })
               .error(function () {
                 toastr.warning("服务器出错，无法获取数据");
               });
           });
        },
        columnDefs: [
            {field: 'accountName', displayName:'手机号', width: 200},
            {field: 'userName', displayName:'用户名', width: 100},
            {field: 'unitName', displayName:'单位', width: 300},
            {
                field: 'createTime', displayName: '注册时间', width: 200,
                cellTemplate: '<div class="ui-grid-cell-contents"><span ng-bind="grid.appScope.rDateFormat(row.entity.createTime)"></span></div>'
            },
            {
                field: 'roleList', displayName: '类型', width: 200,
                cellTemplate: '<div class="ui-grid-cell-contents"><span ng-repeat="item in row.entity.roleList" style="margin-right:5px;">{{item.roleName}}</span></div>'
            },
            {
                field: 'accountId', displayName: '详细信息', width: 200,
                cellTemplate: '<div class="ui-grid-cell-contents"><button type="button" ng-click="grid.appScope.showAccountDetail(row.entity.roleList, row.entity.accountId)" style="line-height: normal" class="btn-primary btn">查看详情</button></div>'
            }
        ]
    }; 
   $scope.gridOptions.data = [{
         'accountName':'15555555555',
         'userName':'浮生若梦',
         'unitName':'无',
         'createTime':1506661676435,
         'roleList':[{roleName:'前端','roleId':2},{roleName:'后端','roleId':3}],
         'accountId':201
      },
      {
         'accountName':'15555555555',
         'userName':'浮生若梦',
         'unitName':'无',
         'createTime':1506661676435,
         'roleList':[{roleName:'前端','roleId':2},{roleName:'后端','roleId':3}],
         'accountId':201
      }
   ];

	
}