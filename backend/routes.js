'use strict';

var express = require('express');
var router = express.Router();

var dbh = require('./dbhandler.js');

router.get('/', function (req, res) {
	res.json({data: 'The server is up and running!', success: true});
	res.end();
});

router.get('/user', function (req, res) {
	var qs = req.query;
	dbh.getUser(qs.name, function (err, user) {
		if(err == null) {
			res.json({data: user, success: true});
		} else {
			res.json({error: 'Something went wrong.', success: false});
			console.log(err);
		}
		res.end();
	});
})

module.exports = router;