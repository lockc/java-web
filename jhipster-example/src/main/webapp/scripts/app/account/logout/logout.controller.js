'use strict';

angular.module('hippoApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
