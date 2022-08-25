ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'];

function productmodifyController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils, utils) {
    $scope.formData = {};

    $scope.init = function () {
        const data = {
            "one": {
                name: "商品管理",
                goto: "productmanage"

            },
            "two": {
                name: "修改商品",
                goto: ""

            }
        };
        $scope.$emit("changeNavigation", data);

        var param = utils.isEmptyObject($stateParams.param);
        if (param) {
            $state.go("productmanage");
            return;
        }

        $http.get(httpUtils.url.getMallProductSellStatus, {}).success(function (data) {
            if (data.resCode == 0) {
                $scope.sellStatusList = data.result;
            }
        });

        angular.copy($stateParams.param, $scope.formData);
        $scope.formData.sellStatus = ""+$stateParams.param.sellStatus;
    };

    $scope.newBuild = function () {
        if (utils.isEmpty($scope.formData.goodsNo)) {
            layerUtils.iMsg(-1, "商品编码不能为空");
            return;
        }

        if (utils.isEmpty($scope.formData.officialPrice)) {
            layerUtils.iMsg(-1, "商品面额不能为空");
            return;
        }

        if (utils.isEmpty($scope.formData.price)) {
            layerUtils.iMsg(-1, "实际价格不能为空");
            return;
        }

        if (utils.isEmpty($scope.formData.sellStatus)) {
            layerUtils.iMsg(-1, "商品状态不能为空");
            return;
        }

        if (utils.isEmpty($scope.formData.goodsTypeId)) {
            layerUtils.iMsg(-1, "商品类别id不能为空");
            return;
        }

        const param = {
            id: $scope.formData.id,
            goodsName: $scope.formData.goodsName,
            goodsNo: $scope.formData.goodsNo,
            officialPrice: $scope.formData.officialPrice,
            price: $scope.formData.price,
            sellStatus: $scope.formData.sellStatus,
            bigImage: $scope.formData.bigImage,
            smallImage: $scope.formData.smallImage,
            goodsTypeId: $scope.formData.goodsTypeId,
        };
        $http.post(httpUtils.url.addMallProduct, param).success(function (data) {
            if (data.resCode == 0) {
                layerUtils.iAlert("修改成功", function () {
                    $state.go("productmanage");
                });
            } else {
                layerUtils.iMsg(-1, data.respMsg);
            }
        });
    }

}