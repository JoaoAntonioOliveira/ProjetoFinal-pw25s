function comprar(idProd) {
	var quant = $("#qtde").val();
    debugger;
    var jason = new Object();
    jason.IdProd = idProd;
    jason.Qtde = quant;
    var str = JSON.stringify(jason);
//    localStorage.removeItem("Pedido");
    localStorage.setItem("Pedido", str);
//    location.href = "";
}

