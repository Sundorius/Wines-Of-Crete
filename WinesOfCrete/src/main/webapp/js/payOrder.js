function payOrder(orderID){
   
    var payAmount=document.getElementById("moneyInput").value;
   if(payAmount>0){
    var data="orderID="+orderID+"&payAmount="+payAmount;
    console.log("PAY DATA: "+data);
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open('POST', "PayOrder", true);
    ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange = function ()
    {
        if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200)
        {
            document.getElementById("main_div").innerHTML=ajaxRequest.responseText;
        }else if (ajaxRequest.readyState===4 && ajaxRequest.status===400){
            document.getElementById("main_div").innerHTML=ajaxRequest.responseText;
        }
            
    };
    ajaxRequest.send(data);
   }else{
       document.getElementById("main_div").innerHTML="Invalid payment amount!";
   }
}


function showPaymentMenu(orderID){
    var data="orderID="+orderID;
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open('POST', "ShowPaymentMenu", true);
    ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange = function ()
    {
        if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200)
        {
            document.getElementById("main_div").innerHTML=ajaxRequest.responseText;
        }else if (ajaxRequest.readyState===4 && ajaxRequest.status===400){
            document.getElementById("main_div").innerHTML=ajaxRequest.responseText;
        }
            
    };
    ajaxRequest.send(data);   
}


