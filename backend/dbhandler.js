var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/antilazy', { useMongoClient: true });
var models = require('./models.js');
var dbh = {};

dbh.getUser = function (id, callback) {
	models.user.findById(id, callback);
};

dbh.updateName = function (body, callback) {
	models.user.findByIdAndUpdate(body._id,
		{$set: {name: body.name}},
		{new : true},
		callback
	);
};

dbh.addLocation = function (body, callback) {
	console.log(body._id);
	models.user.findByIdAndUpdate(body._id,
		{$push: {locations: {
			name: body.name,
			description: body.description,
			coords : body.coords,
			category: body.category
		}}},
		{new : true},
		callback
	);
};

dbh.removeLocation = function (body, callback) {
	models.user.update({_id: body.user_id},{$pull: {locations: {_id: body.location_id}}}, callback);
};

dbh.addReward = function (body, callback) {
	models.user.findByIdAndUpdate(body._id,
		{$push: {rewards: {
			points: body.points,
			description: body.description
		}}},
		{new : true},
		callback
	);
};

dbh.removeReward = function (body, callback) {
	models.user.update({_id: body.user_id},{$pull: {rewards: {_id: body.reward_id}}}, callback);
};

dbh.addLogEntry = function (body, callback) {
	models.user.findByIdAndUpdate(body._id,
		{$push: {log: {
			points: body.points,
			date: new Date(),
			description: body.description,
			category: body.category
		}}},
		{new : true},
		callback
	);
};

module.exports = dbh;