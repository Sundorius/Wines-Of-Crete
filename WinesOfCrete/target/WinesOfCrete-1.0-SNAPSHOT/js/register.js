/* 
 @file:   register.js
 */

/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */


"use strict";

function validateUsernameFromDB()
{
    var result = "fail";
    var username = document.getElementById("username");
    if (document.getElementById("username").validity.valid)
    {
        var data = "option=register&username=" + username.value;

        var ajaxRequest = new XMLHttpRequest();
        ajaxRequest.open('POST', "ValidateData", false);
        ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajaxRequest.onreadystatechange = function ()
        {
            if (ajaxRequest.readyState === 4 && (ajaxRequest.status === 200
                                                || ajaxRequest.status === 404))
            {
                console.log("Successful validateUsernameFromDB() POST request for username check on DB.");
                var response = JSON.parse(ajaxRequest.response);
                if (response["username"])
                {
                    result = response["username"];
                    if (document.getElementById("username_result") !== null)
                    {
                        if (response["username"] === "exist")
                        {
                            document.getElementById("username_result").innerHTML = "<strong>Username already exists!</strong>";
                        } else
                        {
                            document.getElementById("username_result").innerHTML = "";
                        }
                    }
                } 
                else
                {
                    result = response["error"];
                }
            } 
            else if (ajaxRequest.status === 400)
            {
                document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                    + "<br><br><br><br><center><p> Something went wrong with the request, send an email requesting to solve the error 400 problem\n\
                                       on Register</p></center>\n"
                                    + "<center><a id=\"emailRef\" href = \"mailto:csd3195@csd.uoc.gr\">Send Email to Manos</a></center>\n"
                                    + "<center><a id=\"emailRef\" href = \"mailto:csd3208@csd.uoc.gr\">Send Email to John</a></center>\n"
                                    + "<center><a id=\"emailRef\" href = \"mailto:csd3280@csd.uoc.gr\">Send Email to Laertis</a></center>\n"
                                    + "</div>\n";
                console.log("Something wrong with the request parameters.");
            }
            else
            {
                document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                + "<br><br><br><br><center><p> Something went wrong, try again </p></center>\n"
                                + "</div>\n";
                console.log("validateUsernameFromDB() POST request for username check on DB failed.");
            }
        };
        ajaxRequest.send(data);
    }
    return result;
}


function validateEmailFromDB()
{
    var result = "fail";
    var email = document.getElementById("email");
    if (document.getElementById("email").validity.valid)
    {
        var data = "option=register&email=" + email.value;
        var ajaxRequest = new XMLHttpRequest();
        ajaxRequest.open('POST', "ValidateData", false);
        ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajaxRequest.onreadystatechange = function ()
        {
            if (ajaxRequest.readyState === 4 && (ajaxRequest.status === 200
                                                || ajaxRequest.status === 404))
            {
                console.log("Successful validateEmailFromDB() POST request for email check on DB.");
                var response = JSON.parse(ajaxRequest.response);
                if (response["email"])
                {
                    result = response["email"];
                    if (document.getElementById("email_result") !== null)
                    {
                        if (response["email"] === "exist")
                        {
                            document.getElementById("email_result").innerHTML = "<strong>Email already exists!</strong>";
                        } else
                        {
                            document.getElementById("email_result").innerHTML = "";
                        }
                    }
                } else
                {
                    console.log("Error in validateEmailFromDB() response: " + response["error"]);
                    result = response["error"];
                }
            } 
            else if (ajaxRequest.status === 400)
            {
                document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                    + "<br><br><br><br><center><p> Something went wrong with the request, send an email requesting to solve the error 400 problem\n\
                                       on Register</p></center>\n"
                                    + "<center><a id=\"emailRef\" href = \"mailto:csd3195@csd.uoc.gr\">Send Email to Manos</a></center>\n"
                                    + "<center><a id=\"emailRef\" href = \"mailto:csd3208@csd.uoc.gr\">Send Email to John</a></center>\n"
                                    + "<center><a id=\"emailRef\" href = \"mailto:csd3280@csd.uoc.gr\">Send Email to Laertis</a></center>\n"
                                    + "</div>\n";
                console.log("Something wrong with the request parameters.");
            }
            else
            {
                document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                + "<br><br><br><br><center><p> Something went wrong, try again </p></center>\n"
                                + "</div>\n";
                console.log("validateEmailFromDB() POST request for email check on DB failed.");
            }
        };
        ajaxRequest.send(data);
    }
    return result;
}


function SubmitRegisterInputsToDB()
{
    var valid = 0;
    var username = document.getElementById("username");
    var password = document.getElementById("password");
    var password_confirm = document.getElementById("password_confirm");
    var email = document.getElementById("email");
    var name = document.getElementById("onoma");
    var account_type = document.getElementById("account_type_div");
    var address = document.getElementById("address");
    var debit_card_number = document.getElementById("debit_card_number");
    var verification_code = document.getElementById("verification_code");
    var expiration_date = document.getElementById("expiration_date");
    var type = document.getElementById("type");
    var phoneNumber = document.getElementById("phone");

    var account;
    console.log(username.value);
    console.log(password.value);
    console.log(password_confirm.value);
    console.log(email.value);
    console.log(name.value);
    console.log(address.value);
    console.log(debit_card_number.value);
    console.log(verification_code.value);
    console.log(expiration_date.value);
    console.log(type.value);
    console.log(account_type);
    console.log(account_type.children);
    for (var i = 0, length = account_type.children.length; i < length; i++)
    {
        if (account_type.children[i].checked === true)
        {
            account = account_type.children[i].value;
            break;
        }
    }
    console.log("account_type=="+account);

    if (username.validity.valid)
    {
        var usernameResponse = validateUsernameFromDB();
        if (usernameResponse === "Dexist")
        {
            valid++;
            username.style.borderColor = "black";
        } else
        {
            username.style.borderColor = "red";
        }
    } else
    {
        username.style.borderColor = "red";
    }
    if (password.validity.valid)
    {
        valid++;
        password.style.borderColor = "black";
    } else
    {
        password.style.borderColor = "red";
    }
    if (password_confirm.validity.valid)
    {
        if (password.value === password_confirm.value)
        {
            valid++;
            password_confirm.style.borderColor = "black";
        } else
        {
            password_confirm.style.borderColor = "red";
        }
    } else
    {
        password_confirm.style.borderColor = "red";
    }
    if (email.validity.valid)
    {
        var emailResponse = validateEmailFromDB();
        if (emailResponse === "Dexist")
        {
            valid++;
            email.style.borderColor = "black";
        } else
        {
            email.style.borderColor = "red";
        }
    } else
    {
        email.style.borderColor = "red";
    }
    if (name.validity.valid)
    {
        valid++;
        name.style.borderColor = "black";
    } else
    {
        name.style.borderColor = "red";
    }
    if (debit_card_number.validity.valid)
    {
        valid++;
        debit_card_number.style.borderColor = "black";
    } else
    {
        debit_card_number.style.borderColor = "red";
    }
    if (expiration_date.validity.valid)
    {
        valid++;
        expiration_date.style.borderColor = "black";
    } else
    {
        expiration_date.style.borderColor = "red";
    }
    if (verification_code.validity.valid)
    {
        valid++;
        verification_code.style.borderColor = "black";
    } else
    {
        verification_code.style.borderColor = "red";
    }
    if (type.validity.valid)
    {
        valid++;
        type.style.borderColor = "black";
    } else
    {
        type.style.borderColor = "red";
    }
    if (phoneNumber.validity.valid)
    {
        valid++;
        phoneNumber.style.borderColor = "black";
    } else
    {
        phoneNumber.style.borderColor = "red";
    }
    if (valid === 10)
    {
        var data = "option=register&username=" + username.value + "&password=" + password.value;
        data = data + "&password_confirm=" + password_confirm.value + "&email=" + email.value;
        data = data + "&name=" + name.value + "&account_type=" + account;
        data = data + "&address=" + address.value +"&debitCardNumber=" + debit_card_number.value;
        data = data + "&verificationCode=" + verification_code.value +"&expirationDate=" + expiration_date.value;
        data = data + "&type=" + type.value + "&phoneNumber=" +phoneNumber.value;
       

        var ajaxRequest = new XMLHttpRequest();
        ajaxRequest.open('POST', "ValidateData", true);
        ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        ajaxRequest.onreadystatechange = function ()
        {
            if (ajaxRequest.readyState === 4 && (ajaxRequest.status === 200
                                                || ajaxRequest.status === 404))
            {
                console.log("Successful SubmitInputsToDB() POST request for input fields check on DB.");
                document.getElementById("main_div").innerHTML = ajaxRequest.responseText;    
            }
            else if (ajaxRequest.status === 400)
            {
                document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                    + "<br><br><br><br><center><p> Something went wrong with the request, send an email requesting to solve the error 400 problem\n\
                                       on User Voting</p></center>\n"
                                    + "<center><a id=\"emailRef\" href = \"mailto:csd3195@csd.uoc.gr\">Send Email to Manos</a></center>\n"
                                    + "<center><a id=\"emailRef\" href = \"mailto:csd3208@csd.uoc.gr\">Send Email to John</a></center>\n"
                                    + "<center><a id=\"emailRef\" href = \"mailto:csd3280@csd.uoc.gr\">Send Email to Laertis</a></center>\n"
                                    + "</div>\n";
                console.log("Something wrong with the request parameters.");
            }
            else
            {
                document.getElementById("main_div").innerHTML = "<br><div id=\"inner_main_div\">\n"
                                + "<br><br><br><br><center><p> Something went wrong, try again </p></center>\n"
                                + "</div>\n";
                console.log("SubmitInputsToDB() POST request for input fields check on DB failed.");
            }
        };
        ajaxRequest.send(data);
    } 
    else
    {
        console.log("Some inputs were not valid!");
    }
}


function checkPassword()
{
    if (document.getElementById("password").value === document.getElementById("password_confirm").value)
    {
        document.getElementById("password_result").innerHTML = "<strong>Password match.</strong>";
    } else
    {
        document.getElementById("password_result").innerHTML = "<strong>Password does not match.</strong>";
    }
}
