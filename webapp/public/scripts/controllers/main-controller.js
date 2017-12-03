/*global angular*/

angular.module('app').controller('mainController', function ($scope, $log, $mdDialog) {
	'use strict';

	$scope.changeTab = function (tab) {
		$scope.data.selectedTab = tab;
	};

	$scope.showSettings = function(ev) {
		$mdDialog.show({
			controller: 'settingsController',
			templateUrl: 'partials/settings.html',
			targetEvent: ev,
			clickOutsideToClose: false
		});
	};

	$scope.data = {
		selectedTab: "Today",
		points: 77,
		activities: [
			{
				points: -50,
				time: "Today 20:57",
				what: "Was at McDonalds!",
				icon: "restaurant"
			},
			{
				points: 57,
				time: "Today 19:32-20:29",
				what: "Went to the gym for 57 minutes!",
				icon: "fitness_center"
			},
			{
				points: 25,
				time: "Today 7:54",
				what: "Woke up before 8!",
				icon: "hotel"
			},
			{
				points: -25,
				time: "Today 02:21",
				what: "Went to bed after 12!",
				icon: "hotel"
			},
			{
				points: 49,
				time: "Yesterday 19:21-20:09",
				what: "Went to the gym for 49 minutes!",
				icon: "fitness_center"
			},
			{
				points: 25,
				time: "Yesterday 7:44",
				what: "Woke up before 8!",
				icon: "hotel"
			},
			{
				points: 25,
				time: "Sunday 22:58",
				what: "Went to bed before 12!",
				icon: "hotel"
			},
			{
				points: -50,
				time: "Sunday 03:57",
				what: "Was at McDonalds!",
				icon: "restaurant"
			}
		],
		locations: [
			{
				name: "Gym",
				address: "Storgatan 17",
				coords: [59.4066837, 17.84642509999993],
				icon: "fitness_center",
				color: "#44CC44"
			},
			{
				name: "McDonalds Barkarby",
				address: "Lillgatan 2",
				coords: [59.3930837, 17.87342509999993],
				icon: "restaurant",
				color: "#CC4444"
			}
		],
		rewards: [
			{
				points: 50,
				what: "Some reward"
			},
			{
				points: 75,
				what: "Some other reward"
			},
			{
				points: 100,
				what: "Some third reward"
			},
			{
				points: 200,
				what: "Some fourth reward"
			},
			{
				points: 500,
				what: "Some fifth reward"
			}
		]
	};
});