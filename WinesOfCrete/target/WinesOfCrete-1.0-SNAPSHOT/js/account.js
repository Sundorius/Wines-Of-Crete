
"use strict";

function deleteAccount()
{
    var ajaxRequest = new XMLHttpRequest();

      
    
    ajaxRequest.open('GET',"DeleteAccount", true);
    ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    ajaxRequest.onreadystatechange = function ()
    {
        if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200)
        {
            console.log("Successful DeleteAccount() GET request.");
            document.open();
            document.write(ajaxRequest.response);
            document.close();
        } 
        else if (ajaxRequest.status === 400)
        {
            document.getElementById("main_div").innerHTML = ajaxRequest.response;
            console.log("Debt remaining or full basket!.");
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
            console.log("DeleteAccount() POST request for username check on DB failed.");
        }
    };
    ajaxRequest.send();
}