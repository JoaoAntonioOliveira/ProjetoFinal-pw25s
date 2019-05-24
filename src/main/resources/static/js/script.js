function comprar() {
    var valor = parseFloat($(".produtos #preco")[0].innerHTML.replace(",", "."));
    var quant = $(".produtos #qtde").val();

    var jason = new Object();
    jason.Valor = valor;
    jason.Qtde = quant;
    var str = JSON.stringify(jason);
    localStorage.removeItem("Pedido");
    localStorage.setItem("Pedido", str);
    location.href = "frete.html";
}

