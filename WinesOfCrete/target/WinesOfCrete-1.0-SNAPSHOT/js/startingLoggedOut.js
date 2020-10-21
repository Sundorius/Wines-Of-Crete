/* 
 @file:   startingLoggedOut.js
 */


/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */


"use strict";

function changePage(selection)
{
    var data = "option=" + selection;
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open('POST', "SignInRegister", true);
    ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange = function ()
    {
        if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200)
        {
            console.log("Successful changePage() POST request, " + selection + " page returned.");
            document.open();
            document.write(ajaxRequest.response);
            document.close();
        }
        else if (ajaxRequest.status === 400)
        {
            document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                + "<br><br><br><br><center><p> Something went wrong with the request, send an email requesting to solve the error 400 problem\n\
                                   on Starting/Logged Out</p></center>\n"
                                + "<center><a id=\"emailRef\" href = \"mailto:csd3195@csd.uoc.gr\">Send Email to Manos</a></center>\n"
                                + "<center><a id=\"emailRef\" href = \"mailto:csd3208@csd.uoc.gr\">Send Email to John</a></center>\n"
                                + "</div>\n";
            console.log("Something wrong with the request parameters.");
        }
        else
        {
            document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                + "<br><br><br><br><center><p> Something went wrong, try again </p></center>\n"
                                + "</div>\n";
            console.log("changePage() POST request failed.");
        }
    };
    ajaxRequest.send(data);
}