'use strict';
define(['jquery', 'response-common'], function ($, rc) {
    $("#bulkTravelBtn").click(function (e) {
        var $data = {
            "indexName": $("#bulkIndexName").val(),
            "type": $("#bulkTypeName").val(),
            "fromId": parseInt($("#bulkFromId").val()),
            "size": parseInt($("#bulkSizeId").val())
        };
        bulk($data, function (data) {
            var $result = $("#dbResultDiv");
            $result.empty();
            $result.append(data.result)
        })
    });


    /**
     * 删除问卷
     * @param questionObj 对象
     * @param callback 回调
     */
    function bulk(data,callback) {
        $.ajax({
            type: "post",
            url: "/bulk/bulk",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (data) {
                callback(data);
            }
        })
    }
});