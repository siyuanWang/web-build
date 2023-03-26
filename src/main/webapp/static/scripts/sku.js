'use strict';
define(['jquery', 'response-common'], function ($, rc) {

    //收藏
    $("#skuTable").find(".btn-success").click(function(e) {
        var id = $(e.srcElement).data("id")
        $("#collectBtn").attr("data-id", id);
        $("#skuCollect").modal('show')
    })

    $("#collectBtn").click(function(e) {
        var id = $(e.srcElement).data("id")
        $.ajax({
            type:"post",
            url:"/collect/save/" + id,
            data:{},
            dataType: "json",
            success:function (data) {
                if (data.code == 200) {
                    location.reload();
                } else {
                    console.log(data);
                }
            }
        });
    })

});