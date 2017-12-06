'use strict';

var express = require('express');
var router = express.Router();

router.get('/', function (req, res) {
	res.json({data: 'Hello World!', success: true});
	res.end();
});

router.get('/greeting', function (req, res) {
	var qs = req.query;
	res.json({greeting: 'Hello ' + qs.name + '!', success: true});
	res.end();
});

module.exports = router;