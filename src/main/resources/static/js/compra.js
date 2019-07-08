let compra = {
    "fornecedor": {},
    "usuario": {},
    "dataCompra": String(),
    "compraProduto": new Array
};

let valorCompra = 0;
let totaltCompra = 0;

$('#quantidade').on('input', function (e) {
    let precoProduto = Number($('#produto option:selected').attr('precoProduto'));
    let quantidade = Number($('#quantidade').val());

    if (quantidade < 0) {
        $('#quantidade').val(0);
    } else if (quantidade > 100) {
        $('#quantidade').val(100);
    }

    let totalPreco = getProdutoTotalPreco(precoProduto, quantidade);

    $('#totalProdutoPreco').val(isNaN(totalPreco) ? '0.00' : totalPreco);
});

function getProdutoTotalPreco(preco, quantidade) {
    return Number((preco * quantidade).toFixed(2));
}

function saveCompra() {
    if ($('#dataCompra').val() === "") {
        swal('Atenção!',
            'Informe a data de compra!',
            'error');
        return;
    } else {
        let dataCompra = $('#dataCompra').val().split('/').reverse().join('-')
        compra.dataCompra = dataCompra;
    }

    if ($('#fornecedor').val() === "") {
        swal('Atenção!',
            'Informe o fornecedor!',
            'error');
        return;
    } else {
        compra.fornecedor.id = $('#fornecedor').val();
    }

    if ($('#usuarioCompra').val() === "") {
        swal('Atenção!',
            'Informe o usuário!',
            'error');
        return;
    } else {
        compra.usuario.id = $('#usuarioCompra').val();
    }

    if (compra.compraProduto.length === 0) {
        swal('Atenção!',
            'Adicione pelo menos um produto!',
            'error');
        return;
    }

    $.ajax({
        url: '/compra/json',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(compra),
        success: function () {
            swal({
                title: 'Sucesso!',
                text: 'Compra feita com sucesso!',
                type: 'success'
            }, () => {
                window.location = 'page';

            });
        },
        error: function () {
            swal('Atenção!',
                'Não foi possível salvar a compra!',
                'error');
        }
    });//Fim ajax
};

function addCompraProduto() {
    let produto = {
        produto: {
            id: Number
        },
        quantidade: Number
    };

    if ($('#produto').val() === "") {
        swal('Atenção!',
            'Selecione um produto!',
            'error');
        return;
    } else {
        produto.produto.id = $('#produto').val();
    }

    if ($('#quantidade').val() === "" || $('#quantidade').val() === "0") {
        swal('Atenção!',
            'Informe a quantidade!',
            'error');
        return;
    } else {
        produto.quantidade = $('#quantidade').val();
    }

    compra.compraProduto.push(produto);
    valorCompra = Number($('#totalPreco').val());
    totaltCompra = Number($('#totalProdutoPreco').val());
    
    $('#totalPreco').val(valorCompra + totaltCompra);
    
//    Number($('#totalPreco').val(isNaN(valorCompra) ? 'R$ 0.00' : 'R$ ' + valorCompra));

    swal('Sucesso!',
        'Produto adicionado à compra!',
        'success');

    $('#produto').val("");
    $('#quantidade').val("");
    $('#totalProdutoPreco').val("");

    $('#modal-product').modal('hide');


}
