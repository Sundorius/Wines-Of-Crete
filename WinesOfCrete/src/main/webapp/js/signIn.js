/* 
 @file:   signIn.js
 */


/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */


"use strict";

function SubmitSignInInputsToDB()
{
    var username = document.getElementById("username");
    var password = document.getElementById("password");
    var usernameRes;
    var passwordRes;

    if (username.validity.valid && password.validity.valid)
    {
        var data = "option=signIn&username=" + username.value + "&password=" + password.value;
        var ajaxRequest = new XMLHttpRequest();

        ajaxRequest.open('POST', "ValidateData", true);
        ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        ajaxRequest.onreadystatechange = function ()
        {

            if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200)
            {
                document.open();
                document.write(ajaxRequest.responseText);
                document.close();
            } 
            else if (ajaxRequest.readyState === 4 && ajaxRequest.status === 404) 
            {
                console.log("Successful POST request for username and password check on DB.");
                var response = JSON.parse(ajaxRequest.response);

                var span = document.getElementById("error_span");
                usernameRes = response["username"];
                passwordRes = response["password"];
                if (usernameRes === "exist")
                {
                    // Checks password only if username is correct.
                    if (passwordRes !== "Cpattern_Cpassword")
                    {
                        span.innerHTML = "Wrong password!".bold();
                        span.style.visibility = "visible";
                    }
                } 
                else
                {
                    span.innerHTML = "Username does not exist!".bold();
                    span.style.visibility = "visible";
                }
            }
            else if (ajaxRequest.status === 400)
            {
                var span = document.getElementById("error_span").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                    + "<br><br><br><br><center><p> Something went wrong with the request, send an email requesting to solve the error 400 problem\n\
                                       on Sign In</p></center>\n"
                                    + "<center><a id=\"emailRef\" href = \"mailto:csd3195@csd.uoc.gr\">Send Email to Manos</a></center>\n"
                                    + "<center><a id=\"emailRef\" href = \"mailto:csd3208@csd.uoc.gr\">Send Email to John</a></center>\n"
                                    + "</div>\n";
                console.log("Something wrong with the request parameters.");
            }
            else
            {
                console.log("POST request for username and password check on DB failed.");
            }
        };
        ajaxRequest.send(data);
    } 
    else
    {
        // Write message in span!
        var span = document.getElementById("error_span");
        span.innerHTML = "Wrong username or password".bold();
        span.style.visibility = "visible";
    }
}

function CreateRegisterPage()
{
    var data = "option=register";
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open('POST', "SignInRegister", true);
    ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange = function ()
    {
        if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200)
        {
            console.log("Successful POST request, 'Register' page returned.");
            document.open();
            document.write(ajaxRequest.response);
            document.close();
        } 
        else if (ajaxRequest.status === 400)
        {
            document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                + "<br><br><br><br><center><p> Something went wrong with the request, send an email requesting to solve the error 400 problem\n\
                                   on Sign In</p></center>\n"
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
            console.log("POST request for register page from DB failed.");
        }
    };
    ajaxRequest.send(data);
}



