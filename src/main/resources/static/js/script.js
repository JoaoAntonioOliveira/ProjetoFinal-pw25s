function comprar(idProd) {
	var quant = $("#qtde").val();
	var nomeProduto = $("#nomeProduto").text();
	var valorProduto = Number($("#valorProduto").text());
	var fotoGrande = $("#foto-grande").attr("src");

//	debugger;
    var jason = new Object();
    jason.produto_id = idProd;
    jason.quantidade = quant;
    jason.produtoNome = nomeProduto;
    jason.foto = fotoGrande;
    jason.valorProduto= valorProduto;
    
    var str = JSON.stringify(jason);
    
    var jsss = (localStorage.getItem("Carrinho") && JSON.parse(localStorage.getItem("Carrinho"))) || [];
    var achou = Number(0);
    if(localStorage.getItem("Carrinho")){
    	$.each(jsss, function(index){
    		console.log( jsss[index].IdProd + ": " + jsss[index].Qtde);
    		if(jsss[index].produto_id == idProd){
    			jsss[index].quantidade = Number(jsss[index].quantidade) + Number(quant);
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
