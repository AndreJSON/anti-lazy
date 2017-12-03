/*global angular*/

angular.module('app').controller('logController', function ($scope, $log) {
	'use strict';

	$scope.data = $scope.$parent.data;
});