function atribuiFrete() {
    var selecionado = $("#frete option:selected").val();
    var subtotal = $("#subTotal").text();
    
    if (selecionado == 0) {
        $("#valorFrete")[0].innerHTML = "R$ 0,00";
        $("#valorFrete")[0].innerHTML.replace(".", ",");
        $("#valorFrete").val(0);
        $("#totalCompra")[0].innerHTML = "R$ " + subtotal.toFixed(2);
        $("#totalCompra")[0].innerHTML.replace(".", ",");
        $("#totalCompra").val(subtotal);
    } else if (selecionado == 1) {
        var no = "0.00";
        $("#valorFrete")[0].innerHTML = "R$ " + no;
        $("#valorFrete")[0].innerHTML.replace(".", ",");
        $("#valorFrete").val(no);
        totalCompra(no);

    } else if (selecionado == 2) {
        var ex = "9.99";
        $("#valorFrete")[0].innerHTML = "R$ " + ex;
        $("#valorFrete")[0].innerHTML.replace(".", ",");
        $("#valorFrete").val(ex);
        totalCompra(ex);
    } else if (selecionado == 3) {
        var pr = "2.50";
        $("#valorFrete")[0].innerHTML = "R$ " + pr;
        $("#valorFrete")[0].innerHTML.replace(".", ",");
        $("#valorFrete").val(pr);
        totalCompra(pr);
    }
}

function totalCompra(estado) {
	var subtotal = Number($("#subTotal").text());
    var TotalComprav1 = subtotal + parseFloat(estado);
    $("#totalCompra")[0].innerHTML = "R$ " + TotalComprav1.toFixed(2);
    $("#totalCompra")[0].innerHTML.replace(".", ",");
    $("#totalCompra").val(estado);
}
