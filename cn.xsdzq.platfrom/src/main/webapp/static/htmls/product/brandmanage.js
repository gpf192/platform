ngApp.$inject = ['$scope', '$http', '$state', '$stateParams', '$gridService', 'httpUtils', 'layerUtils', 'utils'];

function brandmanageController($scope, $http, $state, $stateParams, $gridService, httpUtils, layerUtils, utils) {
    $scope.formData = {};
    $scope.sellStatusList = [];
    $scope.brandList = [];

    $scope.init = function () {
        const data = {
            "one": {
                name: "商品分类管理",
                goto: ""
            },
            "two": {
                name: "商品分类查询",
                goto: "brandmanage"

            }
        };

        $scope.$emit("changeNavigation", data);
        $http.get( httpUtils.url.getBrandSellStatus, {}).success(function (data) {
            if (data.resCode == 0) {
                $scope.sellStatusList = data.result;
                //设置筛选条件为默认
                for (var k = 0; k < $scope.sellStatusList.length; k++) {
                    if ($scope.sellStatusList[k].desc == "全部") {
                        $scope.formData.sellStatus = $scope.sellStatusList[k].code;
                    }
                }

                $scope.getBrandList(50);
            }
        });

        $scope.currentPage = {
            page: 0
        };
        $scope.selectNumList = [{
            num: 50
        }];
        $scope.selectNum = $scope.selectNumList[0];
    };

    //p begin
    //创建变量用来保存选中结果
    $scope.selected = [];
    const updateSelected = function (action, id) {
        if (action == 'add' && $scope.selected.indexOf(id) == -1) $scope.selected.push(id);
        if (action == 'remove' && $scope.selected.indexOf(id) != -1) $scope.selected.splice($scope.selected.indexOf(id), 1);
    };
    //更新某一列数据的选择
    $scope.updateSelection = function ($event, id) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');
        updateSelected(action, id);
    };
    //全选操作
    $scope.selectAll = function ($event) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');
        for (var i = 0; i < $scope.brandList.length; i++) {
            const contact = $scope.brandList[i];
            updateSelected(action, contact.id);
        }
    };
    $scope.isSelected = function (id) {
        return $scope.selected.indexOf(id) >= 0;
    };
    $scope.isSelectedAll = function () {
        return $scope.selected.length === $scope.brandList.length;
    };

    $scope.getBrandList = function (pageSize) {
        let sellStatus = null;

        if ("all" != $scope.formData.sellStatus) {
            sellStatus = $scope.formData.sellStatus;
        }

        const params = {
            goodsTypeId: $scope.formData.goodsTypeId,
            sellStatus: sellStatus,
            pageNumber: 0,
            pageSize: pageSize
        };
        const settings = {
            url: httpUtils.url.getAllBrand,
            showPage: 7,
            pageSize: pageSize,
            putDataList: "brandList"
        };
        const tableElement = angular.element("#datatable1");
        $gridService.queryTableDatas($scope, tableElement, params, settings, $http);
    };

    $scope.newBuild = function () {
        $state.go("brandadd");
    }

    //编辑
    $scope.batchModifyInfo = function () {
        if ($scope.selected.length != 1) {
            layerUtils.iMsg(-1, "请选择单条记录编辑！");
            return;
        }

        for (var h = 0; h < $scope.selected.length; h++) {
            var infoId = $scope.selected[h];
            for (var i = 0; i < $scope.brandList.length; i++) {
                var tempInfo = $scope.brandList[i];
                if (tempInfo.id == infoId) {
                    var param = tempInfo;
                }
            }
        }

        layerUtils.iConfirm("是否修该此商品分类信息？", function () {
            $state.go("brandmodify", {
                param: param
            });
        }, function () {
            //console.log("取消");
        })

    }

    //删除
    $scope.batchDeleteInfo = function () {
        if ($scope.selected.length == 0) {
            layerUtils.iMsg(-1, "请选择要删除的记录！");
            return;
        }

        layerUtils.iConfirm("是否删除该商品分类？", function () {
            const idList = [];

            for (var h = 0; h < $scope.selected.length; h++) {
                const infoId = $scope.selected[h];
                for (var i = 0; i < $scope.brandList.length; i++) {
                    var tempInfo = $scope.brandList[i];
                    if (tempInfo.id == infoId) {
                        idList.push(infoId);
                    }
                }
            }

            $http.post(httpUtils.url.deleteBrand, idList).success(function (data) {
                $scope.getBrandList(50);
            });
            layerUtils.iMsg(-1, "删除成功");

            $scope.selected = [];
        }, function () {
            //console.log("取消");
        });
    }
}