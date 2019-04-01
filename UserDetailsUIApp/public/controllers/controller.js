var app = angular.module("myApp", []);

app.controller("AppCtrl", function ($scope, $http) {
    console.log("Inside controller");
    $scope.getUserInfo = function () {
        $http.get('/userList/:username', {
            params: {
                username: $scope.userNameInput
            }
        }).then(function (response) {

            if (response.data) {
                $scope.user = response.data;
                console.log('Response is received from server');
            } else {
                console.log('No Response is received from server');
                $scope.user = {
                    "id": -1
                };

            }
        }, function (error) {
            console.log(error, 'Can not get response ');
        });
    };
});
