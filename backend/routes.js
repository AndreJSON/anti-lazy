'use strict';

var express = require('express');
var router = express.Router();

var dbh = require('./dbhandler.js');

router.get('/', function (req, res) {
	res.json({data: 'The server is up and running!', success: true});
	res.end();
});

/**
 * Accessed using query string.
 * Example:
 *	?_id=5a2ade958b75881d2c6f6337
 */
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

/**
 * Example JSON:
 * {
 *	"_id": "5a2ade958b75881d2c6f6337",
 *	"name": "John"
 * }
 */
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

/**
 * Example JSON:
 * {
 *	"_id": "5a2ade958b75881d2c6f6337",
 *	"name": "Gym",
 *	"description": "Fitness 24 Kista",
 *	"coords": [59.44, 17.90],
 *	"category": "gym"
 * }
 */
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

/**
 * Example JSON:
 * {
 *	"user_id": "5a2ade958b75881d2c6f6337",
 *	"location_id": "6a2ade958b75881d2c6f6337"
 * }
 */
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

/**
 * Example JSON:
 * {
 *	"_id": "5a2ade958b75881d2c6f6337",
 *	"points": 50,
 *	"description": "Some reward"
 * }
 */
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

/**
 * Example JSON:
 * {
 *	"user_id": "5a2ade958b75881d2c6f6337",
 *	"reward_id": "6a2ade958b75881d2c6f6337"
 * }
 */
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

/**
 * Example JSON:
 * {
 *	"_id": "5a2ade958b75881d2c6f6337",
 *	"points": -50,
 *	"description": "Was at McDonalds.",
 *	"category": "bad"
 * }
 */
router.post('/log-entry', function (req, res) {
	dbh.addLogEntry(req.body, function (err, user) {
		if(err == null) {
			res.json({data: user, success: true});
		} else {
			res.json({error: 'Something went wrong.', success: false});
			console.log(err);
		}
		res.end();
	});
});

module.exports = router;