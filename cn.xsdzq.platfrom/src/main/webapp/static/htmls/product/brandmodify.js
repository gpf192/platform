ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'];

function brandmodifyController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils, utils) {
    $scope.formData = {};

    $scope.init = function () {
        const data = {
            "one": {
                name: "商品分类管理",
                goto: "brandmanage"

            },
            "two": {
                name: "修改商品分类",
                goto: ""

            }
        };
        $scope.$emit("changeNavigation", data);

        var param = utils.isEmptyObject($stateParams.param);
        if (param) {
            $state.go("brandmanage");
            return;
        }

        $http.get(httpUtils.url.getBrandSellStatus, {}).success(function (data) {
            if (data.resCode == 0) {
                $scope.sellStatusList = data.result;
            }
        });

        angular.copy($stateParams.param, $scope.formData);
        $scope.formData.sellStatus = ""+$stateParams.param.sellStatus;
    };

    $scope.newBuild = function () {
        if (utils.isEmpty($scope.formData.goodsTypeId)) {
            layerUtils.iMsg(-1, "商品类别id不能为空");
            return;
        }

        if (utils.isEmpty($scope.formData.sellStatus)) {
            layerUtils.iMsg(-1, "商品类别状态不能为空");
            return;
        }

        const param = {
            id: $scope.formData.id,
            goodsTypeId: $scope.formData.goodsTypeId,
            goodsTypeName: $scope.formData.goodsTypeName,
            imageUrl: $scope.formData.imageUrl,
            sellStatus: $scope.formData.sellStatus,
        };
        $http.post(httpUtils.url.addBrand, param).success(function (data) {
            if (data.resCode == 0) {
                layerUtils.iAlert("修改成功", function () {
                    $state.go("brandmanage");
                });
            } else {
                layerUtils.iMsg(-1, data.respMsg);
            }
        });
    }

}