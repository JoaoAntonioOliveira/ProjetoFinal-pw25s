let valorTotal = Number(0);

$(document).ready(function () {
	var produtos = (localStorage.getItem("Carrinho") && JSON.parse(localStorage.getItem("Carrinho"))) || [];
	
	produtos.forEach((prod) => {
        $('#tableCarrinho').append('<tr id="' + prod.produto_id + '">'
            + '<td class="text-center w-25" style="font-size: 35px;"">' + prod.produto_id + '</td>'
            + '<td class="text-center"><img width="50" height="50" src="' + prod.foto + '"></td>'
            + '<td class="text-center" style="font-size: 35px;">' + prod.produtoNome + '</td>'
            + '<td class="text-center" style="font-size: 35px;">' + prod.quantidade + '</td>'
            + '<td class="text-center" style="font-size: 35px;">R$ ' + prod.valorProduto + '</td>'
			+ '<td class="text-center item-remover"> <a href="#" style="color:#d60000" onclick="excluir(this, event)"><i class="fa fa-times"></i></a></td>'
//			+ '<td class="text-center item-remover"> <a href="#" style="color:#d60000" onclick="excluir(this, event," + x.produtoId + " )"><i class="fa fa-times"></i></a></td>'
//			+ '<td class="text-center item-remover"> <a class="btnRemover" style="color:#d60000"><i class="fa fa-times"></i></a></td>'
//			+ '<td class="text-center item-remover"> <a class="btn btn-menu btnRemover" th:href="javascript:removerCarrinho();" style="color:#d60000"><i class="fa fa-times"></i></a></td>'
            + '</tr>');
        valorTotal += (prod.valorProduto*prod.quantidade) ;
    })
	
    $("#subTotal").text(valorTotal);
});

function salvarPedido() {
// debugger;
	if($("#frete option:selected").val() == 0){
		swal({
			title: "Atenção!",
			text: "Informe a forma do frete",
			type: 'warning'
		});
	}else if($("#pagamento option:selected").val() == 0){
		swal({
			title: "Atenção!",
			text: "Informe a forma de pagamento",
			type: 'warning'
		});
	}else if($("#subTotal").text() == "0"){
		swal({
			title: "Atenção!",
			text: "Informe ao menos 1 produto no carrinho",
			type: 'warning'
		});
	}else{
		let pedido = {
				"pedidoProduto": new Array,
				formaPag: String,
				total: Number
			};	
		
			    
			    var jsss = (localStorage.getItem("Carrinho") && JSON.parse(localStorage.getItem("Carrinho"))) || [];
			    if(localStorage.getItem("Carrinho")){
			    	$.each(jsss, function(index){
			    		console.log( jsss[index].produto_id + ": " + jsss[index].quantidade);
			    		let pedidoProduto = {
			    				produto: {
			    					id: Number,
			    					nome: String
			    				},
			    				quantidade: Number
			    		};
			    		
			    			pedidoProduto.produto.id = jsss[index].produto_id;
			    			pedidoProduto.produto.nome = jsss[index].produtoNome;
			    			pedidoProduto.quantidade = Number(jsss[index].quantidade);
	
			    			pedido.pedidoProduto.push(pedidoProduto);
			    	})
			    	
			    	pedido.formaPag = $("#pagamento option:selected").text();
			    	pedido.total = Number($("#subTotal").text());
			    	pedido.frete = Number($("#valorFrete").val());
			    }
	
			
			$.ajax({
		        url: '/pedido/json',
		        method: 'POST',
		        contentType: 'application/json',
		        data: JSON.stringify(pedido),
		        success: function () {
		            swal({
		                title: 'Sucesso!',
		                text: 'Pedido salvo com sucesso!',
		                type: 'success'
		            },()=>{ window.location = "/"; })
		            
		            localStorage.removeItem("Carrinho");
		        },
		        error: function () {
		            swal('Atenção!',
		                'Não foi possível salvar a compra!',
		                'error');
		        }
		        
		    });// Fim ajax
	}
}

