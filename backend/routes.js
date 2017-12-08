'use strict';

var express = require('express');
var router = express.Router();

var dbh = require('./dbhandler.js');

router.get('/', function (req, res) {
	res.json({data: 'The server is up and running!', success: true});
	res.end();
});

router.get('/user', function (req, res) {
	dbh.getUser(req.query._id, function (err, user) {
		if(err == null) {
			res.json({data: user, success: true});
		} else {
			res.json({error: 'Something went wrong.', success: false});
			console.log(err);
		}
		res.end();
	});
});

router.post('/name', function (req, res) {
	dbh.updateName(req.body, function (err, user) {
		if(err == null) {
			res.json({data: user, success: true});
		} else {
			res.json({error: 'Something went wrong.', success: false});
			console.log(err);
		}
		res.end();
	});
});

router.post('/location', function (req, res) {
	dbh.addLocation(req.body, function (err, user) {
		if(err == null) {
			res.json({data: user, success: true});
		} else {
			res.json({error: 'Something went wrong.', success: false});
			console.log(err);
		}
		res.end();
	});
});

router.delete('/location', function (req, res) {
	dbh.removeLocation(req.body, function (err, mongoResponse) {
		if(err == null) {
			res.json({success: true});
		} else {
			res.json({error: 'Something went wrong.', success: false});
			console.log(err);
			console.log(mongoResponse);
		}
		res.end();
	});
});

router.post('/reward', function (req, res) {
	dbh.addReward(req.body, function (err, user) {
		if(err == null) {
			res.json({data: user, success: true});
		} else {
			res.json({error: 'Something went wrong.', success: false});
			console.log(err);
		}
		res.end();
	});
});

router.delete('/reward', function (req, res) {
	dbh.removeReward(req.body, function (err, mongoResponse) {
		if(err == null) {
			res.json({success: true});
		} else {
			res.json({error: 'Something went wrong.', success: false});
			console.log(err);
			console.log(mongoResponse);
		}
		res.end();
	});
});

module.exports = router;