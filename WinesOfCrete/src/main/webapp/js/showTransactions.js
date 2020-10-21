/* 
 @file:   showTransactions.js
 */


/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */


"use strict";

function showTransactions()
{
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open('GET', "ShowTransactions",true);
    ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange = function()
    {
        if(ajaxRequest.readyState === 4 && (ajaxRequest.status === 200))
        {
            console.log("Successful showTransactions() GET request on DB.");
            document.getElementById("main_div").innerHTML = ajaxRequest.response;
        }
        else if(ajaxRequest.status === 400)
        {
            document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                + "<br><br><br><br><center><p> Something went wrong with the request, send an email requesting to solve the error 400 problem\n\
                                   on Show Transactions</p></center>\n"
                                + "<center><a id=\"emailRef\" href = \"mailto:csd3195@csd.uoc.gr\">Send Email to Manos</a></center>\n"
                                + "<center><a id=\"emailRef\" href = \"mailto:csd3208@csd.uoc.gr\">Send Email to John</a></center>\n"
                                + "<center><a id=\"emailRef\" href = \"mailto:csd3280@csd.uoc.gr\">Send Email to Laertis</a></center>\n"
                                + "</div>\n";
            console.log("Something wrong with the request parameters.");
        }
        else if(ajaxRequest.status === 401)
        {
            document.open();
            document.write(ajaxRequest.responseText);
            document.close();
        }
        else
        {
            document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                            + "<br><br><br><br><center><p> Something went wrong, try again </p></center>\n"
                            + "</div>\n";
            console.log("showTransactions() POST request for username check on DB failed.");
        }
    };
    ajaxRequest.send();
}