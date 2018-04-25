ngApp.directive("uiCheckboxtree", ['$compile', '$timeout',
function($compile, $timeout) {
	return {
		restrict : 'CA',
		scope : true,
		require : '?ngModel',
		link : function(scope, element, attrs, ngModelCtrl) {
			var defaults = {
				/* Callbacks
				 The callbacks should be functions that take one argument. The checkbox tree
				 will return the angular.element wrapped LI element of the item that was checked/expanded.
				 */
				onExpand : null,
				onCollapse : null,
				onCheck : null,
				onUnCheck : null,
				onHalfCheck : null,
				onLabelHoverOver : null,
				onLabelHoverOut : null,
				/* Valid choices: 'expand', 'check' */
				labelAction : "expand",
				// Debug (currently does nothing)
				debug : false
			};

			var settings = angular.extend(defaults, angular.fromJson(attrs.uiCheckboxtree || {}));
			
			//console.log(attrs.ngModel);
			var modelName=attrs.ngModel;
			
			scope.$watch(function() {
				var liSize1 = angular.element(element).find("li").size();
				var liSize2 = angular.element(element).find("li ul li").size();
				var liSize3 = angular.element(element).find("li ul li ul li").size();
				var liSize4 = angular.element(element).find("li ul li ul li ul li").size();
				//console.log("settings.defalutChecked");
				var liSize = liSize1 + liSize2 + liSize3 + liSize4;
				return liSize;
			}, function(newVal, oldVal) {
				//console.log("oldVal: "+oldVal);
				//console.log("newVal: "+newVal);
				if (newVal > 0) {
					$timeout(function() {
						CheckboxTree();
					}, 500);
				}
			});

			scope.checkboxFristTrgger = function() {
				if (scope.collapsed) {
					scope.collapsed = false;
					scope.expand = true;
				} else {
					scope.collapsed = true;
					scope.expand = false;
				}
			};
			scope.checkboxSecoundTrgger = function(index, strId) {
				console.log(index);
				var secoundScope = angular.element("#" + strId + index).scope();
				if (secoundScope.secondCollapsed) {
					secoundScope.secondCollapsed = false;
					secoundScope.secondExpand = true;
				} else {
					secoundScope.secondCollapsed = true;
					secoundScope.secondExpand = false;
				}
			};
			scope.changeDataThirdTrgger = function(rowIndex, index, dataChildren, strId) {
				var thirdScope = angular.element("#" + strId + rowIndex + index).scope();
				if (angular.isEmpty(dataChildren)) {
					thirdScope.myXgThirdClass = '';
				} else {
					thirdScope.dataThirdTrgger = true;
					if (thirdScope.myXgThirdClass == "collapsed" || ng.isEmpty(thirdScope.myXgThirdClass)) {
						thirdScope.myXgThirdClass = 'expanded';
					} else {
						thirdScope.myXgThirdClass = 'collapsed';
					}
				}

			};

			scope.checkBoxLeaf = function(event, index, colIndex,strId) {
				event.stopPropagation();
				var leafScope = angular.element("#" + strId + colIndex + index).scope();
				//获得叶子对象
				var entity=scope[modelName][colIndex].children[index];
				if(leafScope.thirdWradClassFalg){
					leafScope.thirdWradClassFalg=false;
					entity.check=false;
					console.log(entity);
				}else{
					leafScope.thirdWradClassFalg=true;
					entity.check=true;
					console.log(entity);
				}
			};

			scope.myClass = 'collapsed';
			scope.chengeRowDataFristTrgger = function() {
				scope.rowDataFristTrgger = true;
				if (scope.myClass == "collapsed" || ng.isEmpty(scope.myClass)) {
					scope.myClass = 'expanded';
				} else {
					scope.myClass = 'collapsed';
				}
			};
			scope.chengeRowDataSecoundTrgger = function(rootIndex, index, strId) {
				var secoundScope = angular.element("#" + strId + rootIndex + index).scope();
				secoundScope.rowDataSecoundTrgger = true;
				if (secoundScope.mySecoundClass == "collapsed" || ng.isEmpty(secoundScope.mySecoundClass)) {
					secoundScope.mySecoundClass = 'expanded';
				} else {
					secoundScope.mySecoundClass = 'collapsed';
				}
			};
			scope.chengeRowDataThirdTrgger = function(rootIndex, rowIndex, index, strId) {
				var thirdScope = angular.element("#" + strId + rootIndex + rowIndex + index).scope();
				thirdScope.rowDataThirdTrgger = true;
				if (thirdScope.myThirdClass == "collapsed" || ng.isEmpty(thirdScope.myThirdClass)) {
					thirdScope.myThirdClass = 'expanded';
				} else {
					thirdScope.myThirdClass = 'collapsed';
				}
			};

			var CheckboxTree = function() {
				var $tree = element;
				var changeParents = function(eleAttr) {
					var thisElement = eleAttr;
					var $all = thisElement.siblings("ul").find("li > :checkbox");
					//console.log("$all");
					//console.log($all.length);
					var $checked = $all.filter(":checked");
					//console.log("$checked");
					//console.log($checked.length);
					// All children are checked
					if ($all.length == $checked.length) {

						thisElement.prop("checked", true).addClass("checked");
						if (settings.onCheck)
							settings.onCheck(angular.element(this).parent());

					}
					// All children are unchecked
					// else if ($checked.length == 0) {
					//angular.element(this).prop("checked", false);//.siblings(".checkbox").removeClass("checked").removeClass("half_checked");
					// Fire parent's onUnCheck callback
					//	if (settings.onUnCheck)
					//		settings.onUnCheck(angular.element(this).parent());
					//}

					// Some children are checked, makes the parent in a half checked state.
					else {

						thisElement.prop("checked", false).removeClass("checked");
						//.siblings(".checkbox").removeClass("checked").addClass("half_checked");

					}
				};

				$tree.find("li").each(function(index, domEle) {

					// Go through and hide only ul's (subtrees) that do not have a sibling div.expanded:
					// We do this to not collapse *all* the subtrees (if one is open and checkTree is called again)
					angular.element(this).find("ul").each(function() {
						if (!angular.element(this).siblings(".expanded").length)
							angular.element(this).hide();
					});

					/*//angular.element(this).
					 console.log(index);
					 if(index==1){
					 $(domEle).children("span.arrow").click(function(){
					 rowDataFristTrgger=true;
					 });
					 }
					 */

					var $label = angular.element(this).children("label");
					var $checkbox = angular.element(this).children("input:checkbox");
					var $checkboxDiv = angular.element(this).find("div > span > input:checkbox");
					var $arrow = angular.element(this).children("span.arrow");

					// If the li has children:
					if (angular.element(this).is(":has(ul)")) {
						// If the subtree is not visible, make the arrow collapsed. Otherwise expanded.
						if (angular.element(this).children("ul").is(":hidden"))
							$arrow.addClass("collapsed");
						else
							$arrow.addClass("expanded");

						// When you click the image, toggle the child list
						$arrow.unbind("click");
						$arrow.bind("click", function() {

							///$ul.toggle();
							//$ul.show();
							//console.log(angular.element(this).siblings("ul").length);
							//console.log(angular.element(this).children("ul").length);
							/*angular.element(this).siblings("ul").toggle();		*/

							// Swap the classes: expanded <-> collapsed and fire the onExpand/onCollapse events
							if (angular.element(this).hasClass("collapsed")) {
								console.log("expanded");
								angular.element(this).siblings("ul").show();
								angular.element(this).addClass("expanded").removeClass("collapsed");
								if (settings.onExpand)
									settings.onExpand(angular.element(this).parent());
							} else {
								console.log("collapsed");
								angular.element(this).siblings("ul").hide();
								angular.element(this).addClass("collapsed").removeClass("expanded");
								if (settings.onCollapse)
									settings.onCollapse(angular.element(this).parent());
							}

						});
					}

					$checkbox.parents("ul").each(function() {

						var parentsCheckbox = angular.element(this).parent().children(":checkbox");
						changeParents(parentsCheckbox);

					});

					$checkbox.unbind("click.check");
					$checkbox.bind("click.check", function(event) {
						console.log("check is click");

						if ($checkbox.siblings("ul").size() > 0) {

							if ($checkbox.prop("checked")) {
								$checkbox.siblings("ul").find("input:checkbox").prop("checked", true);
							} else {
								$checkbox.siblings("ul").find("input:checkbox").prop("checked", false);

							}

						} else if ($checkbox.siblings("div").size() > 0) {
							if ($checkbox.prop("checked")) {
								$checkbox.siblings("div").find("input:checkbox").prop("checked", true);
							} else {
								$checkbox.siblings("div").find("input:checkbox").prop("checked", false);
							}

						}
						angular.element(this).parents("ul").each(function() {

							var parentsCheckbox = angular.element(this).parent().children(":checkbox");
							changeParents(parentsCheckbox);

						});
					});
					$checkboxDiv.each(function(index, domELement) {
						$(domELement).unbind("click.check");
						$(domELement).bind("click.check", function(event) {
							event.stopPropagation();
							if ($(domELement).parent().parent().is("div")) {
								if ($(domELement).prop("checked")) {
									$(domELement).parent().parent().siblings(":checkbox").prop("checked", true);
									$(domELement).parent().parent().siblings(":checkbox").parents("ul").each(function() {
										var parentsCheckbox = angular.element(this).parent().children(":checkbox");
										changeParents(parentsCheckbox);
									});
								} else {
									if ($(domELement).parent().siblings("span").children(":checkbox").prop("checked")) {

									} else {
										$(domELement).parent().parent().siblings(":checkbox").prop("checked", false);
										$(domELement).parent().parent().siblings(":checkbox").parents("ul").each(function() {
											var parentsCheckbox = angular.element(this).parent().children(":checkbox");
											changeParents(parentsCheckbox);
										});
									}
								}
							}
						});
					});
				}).find("label")
				// Clicking the labels should do the labelAction (either expand or check)
				.click(function() {
					var action = settings.labelAction;
					switch(settings.labelAction) {
						case "expand":
							angular.element(this).siblings(".arrow").click();
							break;
						case "check":
							angular.element(this).siblings(".checkbox").click();
							break;
					}
				})
				// Add a hover class to the labels when hovering
				.hover(function() {
					angular.element(this).addClass("hover");
					if (settings.onLabelHoverOver)
						settings.onLabelHoverOver(angular.element(this).parent());
				}, function() {
					angular.element(this).removeClass("hover");
					if (settings.onLabelHoverOut)
						settings.onLabelHoverOut(angular.element(this).parent());
				}).end();
			};

		}
	};

}]);

