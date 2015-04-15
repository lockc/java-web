'use strict';

angular.module('hippoApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


