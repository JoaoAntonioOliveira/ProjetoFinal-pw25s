function comprar(idProd) {
	var quant = $("#qtde").val();
//    debugger;
    var jason = new Object();
    jason.IdProd = idProd;
    jason.Qtde = quant;
    var str = JSON.stringify(jason);
    
    var jsss = (localStorage.getItem("Carrinho") && JSON.parse(localStorage.getItem("Carrinho"))) || [];
    var achou = Number(0);
    if(localStorage.getItem("Carrinho")){
    	$.each(jsss, function(index){
    		console.log( jsss[index].IdProd + ": " + jsss[index].Qtde);
    		if(jsss[index].IdProd == idProd){
    			jsss[index].Qtde = Number(jsss[index].Qtde) + Number(quant);
    			achou = achou + 1;
    		}
    	})
    }
    if(achou == 0){
    	jsss.push(jason);    	    		
    }
	
	localStorage.setItem("Carrinho", JSON.stringify(jsss));
	
	swal({
		text: "Item Adicionado no Carrinho",
		title: 'Sucesso',
		type: 'success'
	});

}
