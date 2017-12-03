/*jslint node: true, nomen: true, es5: true*/
'use strict';
var port = 8083;
var express = require('express');
var app = express();

var bodyParser = require('body-parser');
app.use(bodyParser.json());

var router = require('./routes.js');
app.use('/api', router);
require('./paths.js')(app);

app.listen(port);
console.log("Server is now listening on port: " + port);