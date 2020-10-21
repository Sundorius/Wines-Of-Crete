/* 
 @file:   logout.js
 */

/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
'use strict';



function logOut()
{
    var ajaxRequest = new XMLHttpRequest();
	
    ajaxRequest.open('GET', "LogOut", true);
    ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange = function ()
    {
        if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200)
        {
            console.log("Successful logOut() GET request.");
            document.open();
            document.write(ajaxRequest.response);
            document.close();
        }
        else
        {
            document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                + "<br><br><br><br><center><p> Something went wrong, try again </p></center>\n"
                                + "</div>\n";
            document.getElementById("right_div").innerHTML = "";
            console.log("logOut() GET request failed.");
        }
    };
    ajaxRequest.send();
}


