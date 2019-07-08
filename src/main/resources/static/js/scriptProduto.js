let quantidade;

$(document).ready(function () {
    const produtoId = $('h1').attr('produtoId');
    let result;
    
    $.ajax({
        url: '/produto/imagem/' + produtoId,
        method: 'GET',
        success: function (data, status) {
            $('#foto-grande').attr('src', 'data:image/png;base64, '+ data);
            result = data;
        },
        error: (e) => alert('errou' + String().valueOf(this.url))

    });
});
