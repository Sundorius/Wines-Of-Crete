/* 
 @file:   basket.js
 */


/**
 *
 * @author crow
 */


"use strict";

function addToBasket(itemName, quantity)
{
    console.log("itemName: "+itemName);
    console.log("quantity: "+quantity);

    if (quantity>0)
    {
        var data = "itemName="+itemName+"&quantity="+quantity;
        var ajaxRequest = new XMLHttpRequest();

        ajaxRequest.open('POST', 'AddToBasket', true);
        ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        ajaxRequest.onreadystatechange = function ()
        {

            if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200)
            {
                document.getElementById("basket_mess").innerHTML=ajaxRequest.responseText;
            } 
            else if (ajaxRequest.readyState === 4 && ajaxRequest.status === 400)
            {
                
            }
        };
        ajaxRequest.send(data);
    }
}

function showBasket(){
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open('GET', 'ShowBasket', true);
    ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange = function ()
    {
        if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200)
        {
            document.getElementById("main_div").innerHTML=ajaxRequest.responseText;
        } 
        else if (ajaxRequest.readyState === 4 && ajaxRequest.status === 400)
        {
                
        }
    };
    ajaxRequest.send();
}  
