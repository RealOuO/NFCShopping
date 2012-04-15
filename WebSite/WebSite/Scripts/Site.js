$(function () {
    $(".children").hide();
   
    $(".head").toggle(function () {
        $(this).parent().children(".children").show();
        $(this).children("span").html("-");
    }, function () {
        $(this).parent().children(".children").hide();
        $(this).children("span").html("+");
       
    });
});
