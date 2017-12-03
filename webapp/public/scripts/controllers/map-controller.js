/*global angular*/

angular.module('app').controller('mapController', function ($scope, NgMap) {
	'use strict';

	$scope.data = $scope.$parent.data;

	NgMap.getMap().then(function(map) {
		console.log(map.getCenter().toString());
		console.log('markers', map.markers);
	});
});