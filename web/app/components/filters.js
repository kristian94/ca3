'use strict';

/* Place your Global Filters in this file */

angular.module('myApp.filters', []).
        filter('checkmark', function () {
            return function (input) {
                return input ? '\u2713' : '\u2718';
            };
        })
        .filter("nullFilter", function () {
            return function (input) {
                if (input === undefined || input === null || input === "") {
                    return "--unknown--";
                }
                return input;
            };
        })
        .filter("searchTypePlaceholder", function () {
            return function (input) {
                if (input === undefined || input === null || input === "") {
                    return "Search Type";
                }
                return input;
            };
        })
        .filter("twoDecimals", function () {
            return function (input) {
                if (input !== undefined) {
                    var index = input.indexOf(".");
                    return input.substring(0,index+3);
                }
                return input;
            };
        });
