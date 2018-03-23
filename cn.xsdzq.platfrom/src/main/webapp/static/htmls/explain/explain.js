ngApp.$inject = ['$scope', '$http'];
function explainController($scope, $http) {
	$scope.formData = {};
	$scope.processForm = function() {
		alert("awesome");
	};
}