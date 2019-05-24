function atribuiFrete() {
    var selecionado = $("#frete option:selected").val();

    if (selecionado == 0) {
        $(".produtos #valorFrete")[0].innerHTML = "R$ 0,00";
        $(".produtos #valorFrete")[0].innerHTML.replace(".", ",");
        $(".produtos #valorFrete").val(0);
        $(".produtos #totalCompra")[0].innerHTML = "R$ " + subtotal.toFixed(2);
        $(".produtos #totalCompra")[0].innerHTML.replace(".", ",");
        $(".produtos #totalCompra").val(subtotal);
    } else if (selecionado == 1) {
        var no = "0.00";
        $(".produtos #valorFrete")[0].innerHTML = "R$ " + no;
        $(".produtos #valorFrete")[0].innerHTML.replace(".", ",");
        $(".produtos #valorFrete").val(no);
        totalCompra(no);

    } else if (selecionado == 2) {
        var ex = "9.99";
        $(".produtos #valorFrete")[0].innerHTML = "R$ " + ex;
        $(".produtos #valorFrete")[0].innerHTML.replace(".", ",");
        $(".produtos #valorFrete").val(ex);
        totalCompra(ex);
    } else if (selecionado == 3) {
        var pr = "2.50";
        $(".produtos #valorFrete")[0].innerHTML = "R$ " + pr;
        $(".produtos #valorFrete")[0].innerHTML.replace(".", ",");
        $(".produtos #valorFrete").val(pr);
        totalCompra(pr);
    }
}

function totalCompra(estado) {
    var TotalComprav1 = subtotal + parseFloat(estado);
    $(".produtos #totalCompra")[0].innerHTML = "R$ " + TotalComprav1.toFixed(2);
    $(".produtos #totalCompra")[0].innerHTML.replace(".", ",");
    $(".produtos #totalCompra").val(estado);
}
