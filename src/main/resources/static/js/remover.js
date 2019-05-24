var item = document.querySelectorAll('.item-remover'); //li
// console.log(item);

// Iterar sobre cada elemento do array e fazer alguma coisa
item.forEach(function(itensArray){
    var botaoRemover = itensArray.querySelector('.btnRemover');
    botaoRemover.addEventListener('click', function(e){
        e.preventDefault();
        e.target.parentNode.parentNode.parentNode.parentNode.remove();
        localStorage.removeItem("Pedido");
    });
});

