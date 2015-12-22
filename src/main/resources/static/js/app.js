var application = angular.module('application', []);

application.controller('emailController', function ($scope) {

    $scope.emailList = [
        'evilgeniusses@gmail.com',
        'vicigaming@gmail.com',
        'invictusgaming@gmail.com'
    ];

    $scope.addEmail = function () {
        $scope.emailList.push($scope.email);
    };

    $scope.deleteEmail = function (x) {
        var index = $scope.emailList.indexOf(x);
        if (index > -1) {
            $scope.emailList.splice(index, 1);
        }
    };
});


application.controller('UserController', function ($http, $scope) {
    $scope.userList = {};

    $scope.saveUser = function () {
        $http.post('/api/user', $scope.user).then(success, failed);
        function success(response) {
            $scope.updateUser();
        };

        function failed(response) {
            console.log(response);
            alert('Error : ' + response);
        };

    };


    $scope.deleteUser = function (x) {
        $http.delete('/api/user/' + x.userId).then(success, failed);
        function success(success) {
            $scope.updateUser();
        };

        function failed(response) {
            console.log(response);
            alert('Error : ' + response);
        };
    };

    $scope.updateUser = function () {
        $http.get('/api/user').then(success, failed);

        function success(response) {
            $scope.userList = response.data;
            console.log($scope.userList);
        };

        function failed(response) {
            console.log(response);
            alert('Error : ' + response);
        };
    };
    
    $scope.getUser = function (x) {
        $http.get('/api/user/' + x.userId).then(success, failed);

        function success(response) {
            $scope.user = response.data;
            console.log($scope.user);
        };

        function failed(response) {
            console.log(response);
            alert('Error : ' + response);
        };
    };

    $scope.updateUser();
});

