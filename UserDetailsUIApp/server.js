/*Variables Declartion*/
var express = require('express');
var app = express();
var http = require('http');
var scope = require('scope');
var request = require('request');

//Controllers and index.html placed under public folder
app.use(express.static(__dirname + "/public"));

//Configure server to run on specific port
app.listen(5000);
console.log("server running on port 5000");

/*Make REST API call and get user information based on username
 *entered by the user.
 */

app.get('/userList/:username', function (req, res) {

	//Pass Request object to promiseTemp method to construct URL.	
	promiseTemp(req).then(function (data) {
		console.log("In Server, data recieved from REST API call" + data);
		//Send response to controller.
		res.send(data);
	});
});

/*Function to make REST API GET call to get user information*/
function promiseTemp(req) {
	var inputedUsername = req.query.username;
	console.log('In Server, username entered by the user :' + inputedUsername);

	var promise = new Promise(function (resolve, reject) {

		//Define Params
		var options = {
			url: 'http://localhost:8085/api/users/' + inputedUsername,
			host: 'localhost',
			port: 8085,
			path: '/api/users/all',
			method: 'GET'
		}

		function callback(error, response, body) {
			resolve(body);
		}

		request(options, callback);
	});

	return promise;
}