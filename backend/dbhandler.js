var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/antilazy', { useMongoClient: true });
var models = require('./models.js');
var dbh = {};

dbh.printUser = function (name) {
	models.user.findOne({"name": name}, function (err, docs) {
		console.log(docs);
	});
}

module.exports = dbh;