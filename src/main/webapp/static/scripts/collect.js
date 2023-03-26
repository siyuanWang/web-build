'use strict';
define(['jquery', 'response-common'], function ($, rc) {

//    $("#createSku").click(function() {
//
//    })
    $("#skuTable").find(".btn-success").click(function(e) {
        var id = $(e.srcElement).data("id")
        $("#skuUpBtn").attr("data-id", id);
        $("#skuUp").modal('show')
    })

    $("#skuTable").find(".btn-danger").click(function(e) {
        var id = $(e.srcElement).data("id")
        $("#skuDownBtn").attr("data-id", id);
        $("#skuDown").modal('show')
    })

    $("#skuUpBtn").click(function(e) {
        var id = $(e.srcElement).data("id")
        $.ajax({
            type:"post",
            url:"/sku/up/" + id,
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
    $("#skuDownBtn").click(function(e) {
        var id = $(e.srcElement).data("id")
        $.ajax({
            type:"post",
            url:"/sku/down/" + id,
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