ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'];

function brandaddController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils, utils) {
    $scope.formData = {};
    $scope.init = function () {
        const data = {
            "one": {
                name: "商品分类管理",
                goto: "brandmanage"

            },
            "two": {
                name: "新增商品分类",
                goto: ""
            }
        };
        $scope.$emit("changeNavigation", data);

        $scope.formData.goodsTypeId = "";
        $scope.formData.goodsTypeName = "";
        $scope.formData.imageUrl = "";
        $scope.formData.sellStatus = "";

        $http.get(httpUtils.url.getBrandSellStatus, {}).success(function (data) {
            if (data.resCode == 0) {
                $scope.sellStatusList = data.result;
                $scope.formData.sellStatus = $scope.sellStatusList[0].code;
            }
        });
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
            goodsTypeId: $scope.formData.goodsTypeId,
            goodsTypeName: $scope.formData.goodsTypeName,
            imageUrl: $scope.formData.imageUrl,
            sellStatus: $scope.formData.sellStatus,
        };

        $http.post(httpUtils.url.addBrand, param).success(function (data) {
            if (data.resCode == 0) {
                layerUtils.iMsg(-1, "添加成功");
                $state.go("brandmanage");
            } else {
                layerUtils.iMsg(-1, data.respMsg);
            }
        });
    }

}