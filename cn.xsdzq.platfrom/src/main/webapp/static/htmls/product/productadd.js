ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'];

function productaddController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils, utils) {
    $scope.formData = {};
    $scope.init = function () {
        const data = {
            "one": {
                name: "商品管理",
                goto: "productmanage"

            },
            "two": {
                name: "新增商品",
                goto: ""
            }
        };
        $scope.$emit("changeNavigation", data);

        $http.get(httpUtils.url.getMallProductSellStatus, {}).success(function (data) {
            if (data.resCode == 0) {
                $scope.sellStatusList = data.result;
                $scope.formData.sellStatus = $scope.sellStatusList[0].code;
            }
        });

        $http.get( httpUtils.url.getAllBrand, {params:{pageNumber:0,pageSize:2147483647}}).success(function (data) {
            if (data.resCode == 0) {
                $scope.brandAllList = data.result;
                $scope.formData.goodsTypeId = $scope.brandAllList[0].goodsTypeId;
            }
        });
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
                layerUtils.iMsg(-1, "添加成功");
                $state.go("productmanage");
            } else {
                layerUtils.iMsg(-1, data.respMsg);
            }
        });
    }

}