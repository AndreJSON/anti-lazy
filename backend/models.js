var mongoose = require('mongoose');
var Schema = mongoose.Schema, ObjectId = Schema.ObjectId, models = {};

var LocationSchema = new Schema({
	name: String,
	description: String,
	coords: [Number],
	category: String
});

var LogEntrySchema = new Schema({
	points: Number,
	date: Date,
	description: String,
	category: String
});

var RewardSchema = new Schema({
	points: Number,
	description: String
});

var UserSchema = new Schema({
	name: String,
	points: Number,
	locations: [LocationSchema],
	log: [LogEntrySchema],
	rewards: [RewardSchema]
});

models.user = mongoose.model('user', UserSchema);

module.exports = models;