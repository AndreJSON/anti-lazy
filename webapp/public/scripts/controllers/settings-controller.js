/*global angular*/

angular.module('app').controller('settingsController', function ($scope, $mdDialog) {
	'use strict';

	$scope.close = function () {
		$mdDialog.hide();
	};
});