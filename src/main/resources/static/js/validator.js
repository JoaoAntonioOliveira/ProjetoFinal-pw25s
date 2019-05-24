$(function validar(){
    $('#dadosPessoais').validate({
        rules:{
            //Dados Pessoa
            nome:{required:true, minlength: 2},
            sobrenome:{required:true, minlength: 1},
            nasc:{required:true},
            cpf:{required:true},
            //Endereço
            rua:{required:true},
            bairro:{required:true},
            cidade:{required:true},
            estado:{required:true},
            cep:{required:true},
            email:{required:true},
            cEmail:{required:true,equalTo:'#email'},
            password:{required:true,minlength:8},
            cPassword:{required:true,equalTo:'#password'}
        },
        
        messages:{
            //Dados Pessoa
            nome:{required: 'Este campo é obrigatório!', minlength:'Necessário ao menos 2 caracteres!'},
            sobrenome:{required: 'Este campo é obrigatório!', minlength:'Necessário ao menos 1 caracter!'},
            nasc:{required:'Este campo é obrigatório!'},
            cpf:{required:'Informe o cpf'},
            rua:{required:'Informe sua rua!'},
            bairro:{required:'Informe seu bairro!'},
            cidade:{required:'Informe sua cidade!'},
            estado:{required:'Informe seu estado!'},
            cep:{required:'Informe seu CEP!'},
            email:{required:'Este campo é obrigatório!', email:'Teste!!'},
            cEmail:{required:'Corfirme o e-mail',equalTo:'E-mails diferentes!'},
            password:{required:'Campo obrigatorio',minlength:'Mínimo 8 caracteres'},
            cPassword:{required:'Informe a senha', equalTo:'Senha errada!'}
    },
        errorElement: 'span',  
        errorClass: 'help-block'
    });
});

