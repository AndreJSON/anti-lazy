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

module.exports = dbh;