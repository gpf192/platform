ngApp.$inject = ['$scope', '$http', '$state', 'httpUtils', 'layerUtils','utils'];
function addParticipantsController($scope, $http, $state, httpUtils, layerUtils,utils) {
	$scope.formData = {};
	$scope.init=function(){
		var data = {
				"one" : {
					name : "参赛人员管理",
					goto:""

				},
				"two" : {
					name : "新增员工信息",
					goto:"addParticipants"

				}
			}
			$scope.$emit("changeNavigation", data);
		
		$scope.formData.emp_name="";
		$scope.formData.emp_code="";
		$scope.formData.division="";
		$scope.formData.emp_category="";
		$scope.formData.contract="";
		$scope.formData.entry_time="";
		
		$scope.divisionFromList = [{
			name:"新手赛区",
			code:"0"
		},{
			name:"王者赛区",
			code:"1"
		}]
		
		$scope.selectedDivision = $scope.divisionFromList[0];
		$http.get(httpUtils.url.departmentList, {}).success(function(data) {
			if (data.resCode == 0) {
				$scope.departments = data.result;
				$scope.selectedName = $scope.departments[0];
			}
		});
	};
	
	
	$scope.newBuild = function() {
		var url = httpUtils.url.addEmp;
		var emp_name = "";
		var emp_code = "";
		var departmentCode = "";
		var division = "";
		var emp_category = "";
		var contract = "";
		var entry_time = "";
		
		if(!utils.isEmpty($scope.formData.emp_name)) {
			emp_name = $scope.formData.emp_name;
		}
		if(!utils.isEmpty($scope.formData.emp_code)) {
			emp_code = $scope.formData.emp_code;
		}
		if(!utils.isEmpty($scope.selectedName.code)) {
			departmentCode = $scope.selectedName.code;
		}
		if(!utils.isEmpty($scope.selectedDivision.code)) {
			division = $scope.selectedDivision.code;
		}
		if(!utils.isEmpty($scope.formData.emp_category)) {
			emp_category = $scope.formData.emp_category;
		}
		if(!utils.isEmpty($scope.formData.contract)) {
			contract = $scope.formData.contract;
		}
		if(!utils.isEmpty($scope.formData.entry_time)) {
			entry_time = $scope.formData.entry_time;
		}
		var param = {
				emp_name:emp_name,
				emp_code:emp_code,
				departmentCode:departmentCode,
				division:division,
				emp_category:emp_category,
				contract:contract,
				entry_time:entry_time,
		}
		$http.post(url,param).success(function(data) {
			if (data.resCode == 0) {
				layerUtils.iMsg(-1,"添加成功");
				$scope.formData={};
			} else {
				layerUtils.iMsg(-1,"添加失败");
			}
		});
	}
	
//	var excelInput = document.getElementById("sendFile");
//	function newBatchesBuild() {
//		excelInput.addEventListener("change",function() {
//
//		});
//	}
//	
//	newBatchesBuild();
	$scope.newBatchesBuild = function() {
		if(excelInput.files.length<=0) {
		 	layerUtils.iMsg(-1, "请选择文件！");
			return;
		}
		var loadExcel;
		var excelFile = excelInput.files[0];
	    var reader = new FileReader();
	    reader.readAsBinaryString(excelFile);
	    reader.onload = function(e) {
	    	   var data = e.target.result;
	    	   loadExcel = XLSX.read(data, {
                   type: 'binary'
               });
               for(var i=0;i<loadExcel.SheetNames.length;i++){
            	 var data = XLSX.utils.sheet_to_json(loadExcel.Sheets[loadExcel.SheetNames[i]]);
            	 data.forEach(function(item) {
            		 var url = httpUtils.url.addEmp;
            			$http.post(url, item).success(function(data) {
            				if (data.resCode == 0) {
            				
            				} else {
            					
            				}
            			});
            	 })
               }
	    }
	    
	}
	
}