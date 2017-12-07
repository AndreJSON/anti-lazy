var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/antilazy', { useMongoClient: true });
var models = require('./models.js');
var dbh = {};

dbh.test = function () {
	models.user.find({}, function (err, docs) {
		console.log(docs);
	});
}

module.exports = dbh;