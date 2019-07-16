function excluir(botaoExcluir, event) {
    event.preventDefault();

    var id = $(botaoExcluir).parents('tr').prop('id');


    var lista = (localStorage.getItem("Carrinho") && JSON.parse(localStorage.getItem("Carrinho"))) || [];

    if(lista.filter((prod) => prod.produto_id == id).length > 0) {
        var prodAux = lista.map((prod) => prod.produto_id).indexOf(id);

        lista.splice(prodAux, 1);

        localStorage.setItem("Carrinho", JSON.stringify(lista));
    }

    $(botaoExcluir).parents('tr').fadeOut('slow', function () {
        $(botaoExcluir).parent('td').parent('tr').remove();
    })
}