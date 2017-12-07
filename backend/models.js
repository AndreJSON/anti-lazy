var mongoose = require('mongoose');
var Schema = mongoose.Schema, ObjectId = Schema.ObjectId, models = {};

/*var LogEntrySchema = new Schema({
	
});*/

var UserSchema = new Schema({
	name: {type: String, default: "Anonymous"},
	points: {type: Number, default: 0},
	//locations: [LocationSchema]
	//log: [LogEntrySchema]
});
models.user = mongoose.model('user', UserSchema);

module.exports = models;