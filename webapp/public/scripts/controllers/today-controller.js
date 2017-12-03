/*global angular*/

angular.module('app').controller('todayController', function ($scope, $mdDialog) {
	'use strict';

	$scope.data = $scope.$parent.data;
});