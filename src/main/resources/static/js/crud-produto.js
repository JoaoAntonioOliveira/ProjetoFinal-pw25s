"use strict";
function editProduto(url){
	$.get(url, function(entity, status){
		$('#id').val(entity.id);
		$('#nome').val(entity.nome);
		$('#descricao').val(entity.descricao);
		$('#valor').val(entity.valor);
		$('#categoria').val(entity.categoria.id);
		$('#marca').val(entity.marca.id);
		
	});
	$('#modal-form').modal();
}

function saveUpload(urlDestino) {
	var formData = new FormData($('#frm')[0]);
	$.ajax({
		type: $('#frm').attr('method'),
		url: $('#frm').attr('action'), 
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,		
		success: function () { 
			swal({ title: 'Salvo!',
				text: 'Registro salvo com sucesso!',
				type: 'success' 
					}, () => { 
					window.location = urlDestino; 
					});
			},
			error: function () {
				swal('Errou!', 'Falha ao salvar registro!', 'error'); 
				}, 
		}); 
}