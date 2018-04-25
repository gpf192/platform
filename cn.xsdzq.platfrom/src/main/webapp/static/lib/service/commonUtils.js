ngApp.factory("commonUtils", [
function() {

	function isEmpty(value) {

		return isUndefined(value) || value === '' || value === null || value !== value;

	}

	function isUndefined(value) {
		return typeof value === 'undefined';
	}

	function getIndexByChar(charString) {
		var index = 0;
		if (charString == 'A') {
			index = 0;
		} else if (charString == 'B') {
			index = 1;
		} else if (charString == 'C') {
			index = 2;
		} else if (charString == 'D') {
			index = 3;
		} else if (charString == 'E') {
			index = 4;
		} else if (charString == 'F') {
			index = 5;
		} else if (charString == 'G') {
			index = 6;
		} else if (charString == 'H') {
			index = 7;
		} else if (charString == 'I') {
			index = 8;
		} else if (charString == 'J') {
			index = 9;
		} else if (charString == 'K') {
			index = 10;
		} else if (charString == 'L') {
			index = 11;
		} else if (charString == 'M') {
			index = 12;
		} else if (charString == 'N') {
			index = 13;
		} else if (charString == 'O') {
			index = 14;
		} else if (charString == 'P') {
			index = 15;
		} else if (charString == 'Q') {
			index = 16;
		} else if (charString == 'R') {
			index = 17;
		} else if (charString == 'S') {
			index = 18;
		} else if (charString == 'T') {
			index = 19;
		} else if (charString == 'U') {
			index = 20;
		} else if (charString == 'V') {
			index = 21;
		} else if (charString == 'W') {
			index = 22;
		} else if (charString == 'X') {
			index = 23;
		} else if (charString == 'Y') {
			index = 24;
		} else if (charString == 'Z') {
			index = 25;
		}
		return index;
	}

	var utils = {
		isEmpty : isEmpty,
		getIndexByChar : getIndexByChar
	};
	return utils;

}]);
