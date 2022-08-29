ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'];

function ordermanageController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils, utils) {
    $scope.formData = {};
    $scope.orderStatusList = [];
    $scope.rechargeStatusList = [];
    $scope.adjustmentTypeList = [];
    $scope.orderList = [];

    $scope.init = function () {
        const data = {
            "one": {
                name: "订单管理",
                goto: ""
            },
            "two": {
                name: "订单查询",
                goto: "ordermanage"

            }
        };

        $scope.$emit("changeNavigation", data);
        $http.get(httpUtils.url.getOrderStatus, {}).success(function (data) {
            if (data.resCode == 0) {
                $scope.orderStatusList = data.result;
                //设置筛选条件为默认
                for (var k = 0; k < $scope.orderStatusList.length; k++) {
                    if ($scope.orderStatusList[k].desc == "全部") {
                        $scope.formData.orderStatus = $scope.orderStatusList[k].code;
                    }
                }
            }
        });

        $http.get(httpUtils.url.getRechargeStatus, {}).success(function (data) {
            if (data.resCode == 0) {
                $scope.rechargeStatusList = data.result;
                //设置筛选条件为默认
                for (var k = 0; k < $scope.rechargeStatusList.length; k++) {
                    if ($scope.rechargeStatusList[k].desc == "全部") {
                        $scope.formData.rechargeStatus = $scope.rechargeStatusList[k].code;
                    }
                }
            }
        });

        $http.get(httpUtils.url.getAdjustmentType, {}).success(function (data) {
            if (data.resCode == 0) {
                $scope.adjustmentTypeList = data.result;
                //设置筛选条件为默认
                for (var k = 0; k < $scope.adjustmentTypeList.length; k++) {
                    if ($scope.adjustmentTypeList[k].desc == "全部") {
                        $scope.formData.adjustmentType = $scope.adjustmentTypeList[k].code;
                    }
                }
            }
        });

        $scope.getOrderList(50);

        $scope.currentPage = {
            page: 0
        };
        $scope.selectNumList = [{
            num: 50
        }, {
            num: 100
        }, {
            num: 150
        }];
        $scope.selectNum = $scope.selectNumList[0];
        $scope.$watch("selectNum.num", function (newValue, oldValue) {
            if (newValue != oldValue) {
                getOrderList(newValue);
                $scope.currentPage.page = 0;
            }
        }, true);
    };

    $scope.getOrderList = function (pageSize) {
        let orderStatus = null;
        let rechargeStatus = null;
        let adjustmentType = null;

        if ("all" != $scope.formData.orderStatus) {
            orderStatus = $scope.formData.orderStatus;
        }

        if ("all" != $scope.formData.rechargeStatus) {
            rechargeStatus = $scope.formData.rechargeStatus;
        }

        if ("all" != $scope.formData.adjustmentType) {
            adjustmentType = $scope.formData.adjustmentType;
        }

        const params = {
            clientId: $scope.formData.clientId,
            clientName: $scope.formData.clientName,
            orderNo: $scope.formData.orderNo,
            orderStatus: orderStatus,
            rechargeStatus: rechargeStatus,
            adjustmentType: adjustmentType,
            pageNumber: 0,
            pageSize: pageSize
        };
        const settings = {
            url: httpUtils.url.getAllOrder,
            showPage: 7,
            pageSize: pageSize,
            putDataList: "orderList"
        };

        const tableElement = angular.element("#datatable1");
        $gridService.queryTableDatas($scope, tableElement, params, settings, $http);
    };

    //修改积分
    $scope.modify = function (index) {
        var user = $scope.orderList[index];
        console.log(user);
        $state.go("ordermodify", {param: user});
    }
}