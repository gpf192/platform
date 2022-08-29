ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'];

function ordermodifyController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils, utils) {
    $scope.formData = {};

    $scope.init = function () {
        const data = {
            "one": {
                name: "订单管理",
                goto: "ordermanage"

            },
            "two": {
                name: "修改订单",
                goto: ""

            }
        };
        $scope.$emit("changeNavigation", data);

        var param = utils.isEmptyObject($stateParams.param);
        if (param) {
            $state.go("ordermanage");
            return;
        }

        $http.get(httpUtils.url.getAdjustmentType, {}).success(function (data) {
            if (data.resCode == 0) {
                $scope.adjustmentTypeList = data.result;
            }
        });

        angular.copy($stateParams.param, $scope.formData);
        $scope.formData.adjustmentType = "" + $stateParams.param.adjustmentType;
    };

    $scope.newBuild = function () {
        if (utils.isEmpty($scope.formData.adjustmentType)) {
            layerUtils.iMsg(-1, "调账类型不能为空");
            return;
        }

        if (utils.isEmpty($scope.formData.adjustmentReason)) {
            layerUtils.iMsg(-1, "调账原因不能为空");
            return;
        }

        const param = {
            id: $scope.formData.id,
            adjustmentType: $scope.formData.adjustmentType,
            adjustmentReason: $scope.formData.adjustmentReason,
        };
        $http.post(httpUtils.url.addOrder, param).success(function (data) {
            if (data.resCode == 0) {
                layerUtils.iAlert("修改成功", function () {
                    $state.go("ordermanage");
                });
            } else {
                layerUtils.iMsg(-1, data.respMsg);
            }
        });
    }

    $scope.cancel = function() {
        $state.go("ordermanage");
    }

}