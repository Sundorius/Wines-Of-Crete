/* 
 @file:   changeUserInformation.js
 */

"use strict";

function changeUserInformation()
{
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open('GET', "ChangeUserInformation", true);
    ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange = function ()
    {
        if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200)
        {
            console.log("Successful changeUserInformation() GET request,.");
            document.getElementById("main_div").innerHTML = (ajaxRequest.response);
        }
        else if(ajaxRequest.status === 401)
        {
            document.open();
            document.write(ajaxRequest.responseText);
            document.close();
        }
        else if (ajaxRequest.status === 500)
        {
            document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                + "<br><br><br><br><center><p> Something went wrong, try again </p></center>\n"
                                + "</div>\n";
            console.log("Something went wrong in the DB.");
        }
        else
        {
            document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                + "<br><br><br><br><center><p> Something went wrong, try again </p></center>\n"
                                + "</div>\n";
            console.log("ChangeUserInformation() GET request failed.");
        }
    };
    ajaxRequest.send();
}