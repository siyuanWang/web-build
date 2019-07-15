'use strict';
define(['jquery', 'response-common'], function ($, rc) {
    $("#dbListQueryBtn").click(function (e) {
        var $id = $("#dbListQueryIdInput").val();
        if(!$id) {
            alert("请输入主键id")
        }
        var $type = $("#dbListQueryTypeSelect").val();
        queryData($id,$type, function (data) {
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
    function queryData(id,type, callback) {
        $.ajax({
            type: "get",
            url: "/db/query/" + type + "/" + id,
            data: {},
            dataType: "json",
            success: function (data) {
                callback(data);
            }
        })
    }
});