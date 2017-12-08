var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/antilazy', { useMongoClient: true });
var models = require('./models.js');
var dbh = {};

dbh.getUser = function (name, callback) {
	models.user.findOne({"name": name}, callback);
}

module.exports = dbh;