function go() {
    //window.alert("Hi");
    var customerType = document.getElementById("customers").value;
    var operation = document.getElementById("operations").value;
    //window.alert(customerType + " "+ operation);
    if (customerType == "real") {
        if (operation == "add")
            window.location = "insertRealCustomer.jsp";
        else window.location = "selectRealCustomer.jsp";
    }
    else if (customerType == "legal"){
        if (operation == "add")
            window.location = "insertLegalCustomer.jsp";
        else window.location = "selectLegalCustomer.jsp";
    }


}
