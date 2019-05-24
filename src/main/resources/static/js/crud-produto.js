"use strict";
function editSerie(url){
	$.get(url, function(entity, status){
		$('#id').val(entity.id);
		$('#nome').val(entity.nome);
		$('#descricao').val(entity.descricao);
		$('#valor').val(entity.valor);
		$('#categoria').val(entity.cateogira.id);
		$('#marca').val(entity.marca.id);
		
	});
	$('#modal-form').modal();
}
