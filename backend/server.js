'use strict';

var port = 8084;
var express = require('express');
var app = express();
var chalk = require('chalk');

var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/test', { useMongoClient: true });
var Schema = mongoose.Schema;
var ObjectId = Schema.ObjectId;

var ColSchema = new Schema({
	name: String,
	age: Number
});

var colModel = mongoose.model('col', ColSchema);

colModel.find({}, function (err, docs) {
	console.log(docs);
});

/*var LogEntrySchema = new Schema({

});

var UserSchema = new Schema({
	name: {type: String, default: "Anonymous"},
	points: {type: Number, default: 0},
	position: {type: String},
	log: [LogEntrySchema]
});*/

var bodyParser = require('body-parser');
app.use(bodyParser.json());

var router = require('./routes.js');
app.use('', router);

app.listen(port);
console.log(chalk.green("Server is now listening on port: ") + chalk.magenta(port));