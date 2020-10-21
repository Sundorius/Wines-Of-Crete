/* 
 @file:   orders.js
 */


/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
"use strict";


//This function will get all the user's orders from the database
function getUserOrders()
{
    var ajaxRequest = new XMLHttpRequest();

       // TODO ADD SERVLET NAME!
    
    ajaxRequest.open('GET', "GetUserOrders", true);
    ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    ajaxRequest.onreadystatechange = function ()
    {
        if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200)
        {
            document.getElementById("main_div").innerHTML = (ajaxRequest.response);
        } 
        else if (ajaxRequest.status === 400)
        {
            document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                + "<br><br><br><br><center><p> Something went wrong with the request, send an email requesting to solve the error 400 problem\n\
                                   on Get User Orders</p></center>\n"
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
            console.log("GetUserOrders() POST request for username check on DB failed.");
        }
    };
    ajaxRequest.send();
} 


//This function is called when the user wants to make an order and redirects him to the proper page.
function makeOrder()
{
    var ajaxRequest = new XMLHttpRequest();
    
    // TODO ADD PARAMETERS TO DATA, AND SEND IT TO SERVLET!
    // SOMETHING LIKE A LOOP TO FIND ALL THE ITEMS FROM THE BASKET!
    // IT IS EASY AS BASKET IS SAVED IN THE DATABASE!
    var data;  
    ajaxRequest.open('POST', "GetUserOrders", true);
    ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    ajaxRequest.onreadystatechange = function ()
    {
        if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200)
        {
            document.getElementById("main_div").innerHTML = (ajaxRequest.response);
        } 
        else if (ajaxRequest.status === 400)
        {
            var span = document.getElementById("error_span").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                + "<br><br><br><br><center><p> Something went wrong with the request, send an email requesting to solve the error 400 problem\n\
                                   on Make Order</p></center>\n"
                                + "<center><a id=\"emailRef\" href = \"mailto:csd3195@csd.uoc.gr\">Send Email to Manos</a></center>\n"
                                + "<center><a id=\"emailRef\" href = \"mailto:csd3208@csd.uoc.gr\">Send Email to John</a></center>\n"
                                + "</div>\n";
            console.log("Something wrong with the request parameters.");
        }
        else
        {
            console.log("POST request for user's new order failed.");
        }
    };
    ajaxRequest.send(data);  
}


//This function will get all of the orders in the database
function getOrders()
{
        // TODO, DELETE IT AS I THINK IS NOT NEEDED FOR US, EXCEPT FOR THE TASK WITH THE TOP PRODUCTS,
        // KEEP IT AS IS UNTIL WE AGREE.
}

