/*jshint smarttabs:true, eqeqeq:false, eqnull:true, laxbreak:true */
/**
 * @author zhengjinguang
 */

ngApp.factory("$gridService", ['$compile','commonUtils',function($compile,commonUtils) {
	return {
		// 错误处理，统一的错误处理
		alertFun : function(content) {
			alert(content);
		},
		dataTables : function($scope, element, queryDatas, settings, $http) {
			//console.log(settings);
			var alertFunction = this.alertFun;
			// 把this的函数上下文，复制到事件绑定中。
			var element$ = angular.element(element);
			element$.addClass("ui-grid");
			var totle = parseInt(settings.totleRecord,10);
			var pageSizeInt=parseInt(settings.pageSize,10);
			//var showPages=parseInt(setting.showPage,10);
			// 这有个判断 当分页数小于指定的分页数时不显示 footer  或者根据需求显示其他数据
			if (totle < pageSizeInt) {
				return;
			}
			// pageSize 每页的大小 page_num 总页数
			var page_num = Math.ceil(totle / pageSizeInt);
			var tfooter = ' <tfoot><tr><td class="footTd page_box"><input type="button" value="首页" disabled="true" class="ui-grid-first button page_pre2" /><input class="ui-grid-prev page_pre2 " disabled="true" type="button" value="上一页" /><span class="ellipsis"><b>...</b></span><span class="ui-grid-pageNums"></span><span class="ellipsis"><b>...</b></span><input class="ui-grid-next page_last2" type="button" value="下一页"><input class="ui-grid-last page_last2" type="button" value="尾页" /><span>第<input type="text" name="PageNum"  id="PageNum">页</span><a href=\"javascript:;\" class="PageNumOK">确定</a></div></td></tr></tfoot>';
			var footTxt = "<span class='button'>共<b>" + totle + "</b>条，共<b>" + page_num + "</b>页</span>";
			var isFoot = element$.find("tfoot").size();
			if (isFoot) {
				element$.children("tfoot").remove();
			}
			element$.append(tfooter).find(".footTd").append(footTxt);
			var pagesNavNum = "";
			for (var ipage = 1; ipage <= page_num; ipage++) {
				pagesNavNum += "<a>" + ipage + "</a>";
			}
			// angular.element("table.ui-grid tfoot tr td").attr("colspan",
			// page_num);
			// 防止样式冲突
			var colspanSize = angular.element(element).find("thead > tr >th").size();
			element$.find("tfoot tr td").attr("colspan", colspanSize);
			element$.find('.ui-grid-pageNums').append(pagesNavNum).children("a:first").addClass("active");

			// 导航指向
			var frist$ = element$.find(".ui-grid-first");
			// 首页
			var last$ = element$.find(".ui-grid-last");
			// 尾页
			var prev$ = element$.find(".ui-grid-prev");
			// 上一页
			var next$ = element$.find(".ui-grid-next");
			// 下一页
			// 输入的数字
			var input$ = element$.find(".footTd #PageNum");
			// 确定
			var submit$ = element$.find(".footTd .PageNumOK");

			//这是判断前端和后端分页的，现在保留前端分页。使用后端分页
	
			backGrid($scope, element, queryDatas, $http);

			// 前端分页函数
			function frontGrid(element$) {
				var header, footer, tbody_tr$, totle;
				var pagesNav$;
				var relPage = settings.pageSize - 1;
				tbody_tr$ = element$.children('tbody').children('tr');
				// totle = tbody_tr$.size();
				// 初始化的时候显示规定的条数
				tbody_tr$.filter(":gt(" + relPage + ")").hide();
				// 创建footer
				pagesNav$ = element$.find('.ui-grid-pageNums').children();
				if (settings.showPage < page_num) {
					element$.find("span.ellipsis:last").show();
					var relNavMth = settings.showPage - 1;
					element$.find(".ui-grid-pageNums a").filter(":gt(" + relNavMth + ")").hide();
				} else {
					element$.find("span.ellipsis:last").hide();
				};

				// 跳转页面文本框
				input$.keyup(function() {
					var pattern_d = /^\d+$/;
					// 全数字正则
					if (!pattern_d.exec(input$.val())) {

						alertFunction("友情提示：\n\n请填写正确的数字！");
						input$.focus().val("");
						return false;
					};
				});
				// 跳转页面确定按钮
				submit$.click(function() {
					if (input$.val() == "") {
						alertFunction("友情提示：\n\n请填写您要跳转到第几页！");
						input$.focus().val("");
						return false;
					}
					if (input$.val() > page_num) {

						alertFunction("友情提示：\n\n您跳转的页面不存在！");
						input$.focus().val("");
						return false;
					} else {
						showPages(input$.val());
					};
				});
				// 导航指向
				pagesNav$.click(function() {

					var navTxt = $(this).text();
					showPages(navTxt);
				});
				frist$.bind("click", function() {

					showPages(1);
				});
				last$.bind("click", function() {
					showPages(page_num);
				});
				prev$.bind("click", function() {

					var OldNav = element$.find("span.ui-grid-pageNums a.active");
					// angular.element("table.ui-grid span.ui-grid-pageNums
					// a.active");
					if (OldNav.text() == 1) {
						alertFunction("友情提示：\n\n不要再点啦~已经是首页啦！");
					} else {
						var NavTxt = parseInt(OldNav.text()) - 1;
						showPages(NavTxt);
					};
				});
				next$.bind("click", function() {
					var OldNav = element$.find("span.ui-grid-pageNums a.active");
					// angular.element("table.ui-grid span.ui-grid-pageNums
					// a.active");
					if (OldNav.text() == page_num) {
						alertFunction("友情提示：\n\n不要再点啦~已经是首页啦！");
					} else {
						var NavTxt = parseInt(OldNav.text()) + 1;
						showPages(NavTxt);
					};
				});
				var showPages = function(page) {

					if (page == 1) {
						prev$.prop("disabled", true);
						frist$.prop("disabled", true);
					} else if (page == page_num) {
						next$.prop("disabled", true);
						last$.prop("disabled", true);
					} else {
						prev$.prop("disabled", false);
						frist$.prop("disabled", false);
						next$.prop("disabled", false);
						last$.prop("disabled", false);
					}

					pagesNav$.each(function() {
						var navText = $(this).text();
						if (navText == page) {
							$(this).addClass("active").siblings().removeClass("active");
						};
					});
					var $NavMth = showPages;
					var AllMth = page_num / $NavMth;

					if (page == page_num) {
						element$.find("span.ellipsis:last").hide();
					}
					for (var i = 1; i <= AllMth; i++) {
						var AllMthTemp = "" + AllMth;
						var AllMthInt = parseInt(AllMthTemp, 10);
						if (i == AllMthInt) {
							element$.find("span.ellipsis:last").hide();
						}
						if (page > (i * $NavMth)) {

							pagesNav$.filter(":gt(" + (i * $NavMth - 1) + ")").show();
							pagesNav$.filter(":gt(" + (i * $NavMth - 1 + $NavMth) + ")").hide();
							pagesNav$.filter(":lt(" + (i * $NavMth) + ")").hide();
							element$.find("span.ellipsis:first").show();
						};

						if (page <= $NavMth) {

							pagesNav$.filter(":gt(" + ($NavMth - 1) + ")").hide();
							pagesNav$.filter(":lt(" + $NavMth + ")").show();
							element$.find("span.ellipsis:first").hide();
							element$.find("span.ellipsis:last").show();
						};
					};
					var pageNum = page;
					var LeftPage = settings.pageSize * (pageNum - 1);
					var NowPage = settings.pageSize * pageNum;
					tbody_tr$.hide();
					tbody_tr$.filter(":lt(" + (NowPage) + ")").show();
					tbody_tr$.filter(":lt(" + (LeftPage) + ")").hide();

					/*	pagesNav$.each(function() {
					 var navText = $(this).text();
					 if (navText == pageNum) {
					 $(this).addClass("active").siblings().removeClass("active");
					 };
					 });
					 var $NavMth = settings.showPage;
					 var AllMth = page_num / $NavMth;
					 for (var i = 1; i <= AllMth; i++) {

					 if (pageNum > (i * $NavMth)) {

					 pagesNav$.filter(":gt(" + (i * $NavMth - 1) + ")").show();
					 pagesNav$.filter(":gt(" + (i * $NavMth - 1 + $NavMth) + ")").hide();
					 pagesNav$.filter(":lt(" + (i * $NavMth) + ")").hide();
					 element$.find("span.ellipsis:first").show();
					 };

					 if (pageNum <= $NavMth) {

					 pagesNav$.filter(":gt(" + ($NavMth - 1) + ")").hide();
					 pagesNav$.filter(":lt(" + $NavMth + ")").show();
					 element$.find("span.ellipsis:first").hide();
					 };
					 };
					 var LeftPage = settings.pageSize * (pageNum - 1);
					 var NowPage = settings.pageSize * pageNum;
					 tbody_tr$.hide();
					 tbody_tr$.filter(":lt(" + (NowPage) + ")").show();
					 tbody_tr$.filter(":lt(" + (LeftPage) + ")").hide();*/
				};
			};
			// 后端分页函数
			function backGrid($scope, element, pagableQryData, $http) {
				var pagesNav$ = element$.find('.ui-grid-pageNums').children();
				if (settings.showPage < page_num) {
					element$.find("span.ellipsis:last").show();
					var relNavMth = settings.showPage - 1;
					element$.find(".ui-grid-pageNums a").filter(":gt(" + relNavMth + ")").hide();
				} else {
					element$.find("span.ellipsis:last").hide();
				};

				// 导航指向
				pagesNav$.on("click", function() {
					var navTxt = $(this).text();
					showPages(navTxt);
				});
				frist$.on("click", function() {
					showPages(1);
				});
				last$.on("click", function() {
					showPages(page_num);
				});
				prev$.on("click", function(event) {
					var OldNav = element$.find("span.ui-grid-pageNums a.active");
					if (OldNav.text() == 1) {
						alertFunction("友情提示：\n\n不要再点啦~已经是首页啦！");
					} else {
						var NavTxt = parseInt(OldNav.text(), 10) - 1;
						showPages(NavTxt);
					};
				});
				next$.on("click", function() {
					var OldNav = element$.find("span.ui-grid-pageNums a.active");
					if (OldNav.text() == page_num) {
						alertFunction("友情提示：\n\n不要再点啦~已经是首页啦！");
					} else {
						var NavTxt = parseInt(OldNav.text(), 10) + 1;
						showPages(NavTxt);
					};
				});
				// 跳转页面文本框
				input$.keyup(function() {
					var pattern_d = /^\d+$/;
					// 全数字正则
					if (!pattern_d.exec(input$.val())) {

						alertFunction("友情提示：\n\n请填写正确的数字！");
						input$.focus().val("");
						return false;
					};
				});
				// 跳转页面确定按钮
				submit$.click(function() {
					if (input$.val() == "") {
						alertFunction("友情提示：\n\n请填写您要跳转到第几页！");
						input$.focus().val("");
						return false;
					}
					if (input$.val() > page_num) {

						alertFunction("友情提示：\n\n您跳转的页面不存在！");
						input$.focus().val("");
						return false;
					} else {
						showPages(input$.val());
					};
				});
				var putDataListStr = settings.putDataList;
				var showPages = function(page) {
					//console.log(page);
					//console.log(page_num);

					if (page == 1) {
						prev$.prop("disabled", true);
						frist$.prop("disabled", true);
						next$.prop("disabled", false);
						last$.prop("disabled", false);
					} else if (page == page_num) {
						next$.prop("disabled", true);
						last$.prop("disabled", true);
						prev$.prop("disabled", false);
						frist$.prop("disabled", false);
					} else {
						prev$.prop("disabled", false);
						frist$.prop("disabled", false);
						next$.prop("disabled", false);
						last$.prop("disabled", false);
					}

					pagesNav$.each(function() {
						var navText = $(this).text();
						if (navText == page) {
							$(this).addClass("active").siblings().removeClass("active");
						};
					});
					var $NavMth = parseInt(settings.showPage, 10);
					var AllMth = page_num / $NavMth;

					if (page == page_num) {
						element$.find("span.ellipsis:last").hide();
					}
					for (var i = 1; i <= AllMth; i++) {
						var AllMthTemp = "" + AllMth;
						var AllMthInt = parseInt(AllMthTemp, 10);
						if (i == AllMthInt) {
							element$.find("span.ellipsis:last").hide();
						}
						if (page > (i * $NavMth)) {

							pagesNav$.filter(":gt(" + (i * $NavMth - 1) + ")").show();
							pagesNav$.filter(":gt(" + (i * $NavMth - 1 + $NavMth) + ")").hide();
							pagesNav$.filter(":lt(" + (i * $NavMth) + ")").hide();
							element$.find("span.ellipsis:first").show();
						};

						if (page <= $NavMth) {

							pagesNav$.filter(":gt(" + ($NavMth - 1) + ")").hide();
							pagesNav$.filter(":lt(" + $NavMth + ")").show();
							element$.find("span.ellipsis:first").hide();
							element$.find("span.ellipsis:last").show();
						};
					};
					//var pageNumber= pageSize pagableQryData.limit*(page-1);
					//var pageNumber= settings.pageSize*(page-1);
					var pageNumber= (page-1);
					pagableQryData.pageNumber=pageNumber;

					$scope.currentPage.page = page-1;
					$http({
						url : settings.url,
						method : 'GET',
						params : pagableQryData
					}).success(function(data) {
						$scope[putDataListStr] = data.result;
					});
				};
				$scope.changePageSize = function(pageSize) {
					var sel = $("select[name='vPageSize']")[0];
					if (!pageSize) {
						//$scope.pagableQryData.pageSize = sel.options[sel.selectedIndex].text;
					} else {
						$scope.pagableQryData.pageSize = pageSize;
					}
					$scope.pagableQryData.currentIndex = $scope.currentIndex;
					$scope.pagableQryData.recordNumber = $scope.recordNumber;
					$http.post($scope.pagableQryData.uri, $scope.pagableQryData, function(u) {
						$scope.setPagableData(u);
						$scope.pagableQryCallBack(u);
					});
				};
			};

		},
		queryTableDatas : function($scope, element, queryDatas, queryAttrs, $http) {
			var defaults = {
				"totleRecord" : "",
				"pageSize" : 10,
				"showPage" : 5,
				"url" : "",
				"putDataList" : ""
			};
			var dataTablesQuery = this.dataTables;
			var that = this;
			var settings = angular.extend(defaults, queryAttrs);
			var url = settings.url;
			if (commonUtils.isEmpty(url)) {
				return;
				//需要错误处理
			}
			var putDataListStr = queryAttrs.putDataList;
			$http({
				url : url,
				method : 'GET',
				params : queryDatas
			}).success(function(data) {
				$scope[putDataListStr] = data.result;
				settings.totleRecord = data.pagination.totalItems;
				//判断查询无数据的情况
				if (settings.totleRecord == "0") {
					//console.log(settings.totleRecord);
					element.find('tfoot').remove();
					var content = "<tfoot><tr><td  colspan='30'><div>查询无数据</div></td></tr></tfoot>";
					element.append(content);
				} else {
					element.find('tfoot').remove();
					dataTablesQuery.call(that, $scope, element, queryDatas, settings, $http);
				}
			});
		}
	};

}]);
