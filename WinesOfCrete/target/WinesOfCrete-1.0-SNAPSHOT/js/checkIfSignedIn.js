/* 
 @file:   checkIfSignedIn.js
 */


/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
"use strict";

function checkIfLoggedIn()
{
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open('GET', "GetSession", true);
    ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange = function ()
    {
        if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200)
        {
            console.log("Successful checkIfLoggedIn() POST request.");
            var response = JSON.parse(ajaxRequest.response);

            if (response["empty"])
            {
                signInPage();
            }
            else
            {
                var ajaxReq = new XMLHttpRequest();
                ajaxReq.open('GET', "Home", true);
                ajaxReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                ajaxReq.onreadystatechange = function ()
                {
                    if (ajaxReq.readyState === 4 && ajaxReq.status === 200)
                    {
                        console.log("Successfully auto logged in, based on non-expired session.");
                        document.open();
                        document.write(ajaxReq.responseText);
                        document.close();
                    } 
                    else
                    {
                        console.log("Unable to log in automatically.");
                    }
                };
                ajaxReq.send();
            }
        }
    };
    ajaxRequest.send();
}
    
function signInPage()
{
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open('GET', "Starting", true);
    ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange = function ()
    {
        if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200)
        {
            console.log("Successful signInPage() GET request.");
            document.open();
            document.write(ajaxRequest.responseText);
            document.close();
        }
        else
        {
            document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                + "<br><br><br><br><center><p> Something went wrong, try again </p></center>\n"
                                + "</div>\n";
            document.getElementById("right_div").innerHTML = "";
            console.log("signInPage() GET request failed.");
        }
    };
    ajaxRequest.send();
}