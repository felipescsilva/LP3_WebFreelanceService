import {user} from './login.js';

$(function() {

    $('#user-login').html = user.login;
    $('#user-password').html = user.password;
    $('#user-email').html = user.password;
    $('#user-dataNascimento').html = user.dataNascimento;
    $('#user-phone').html = user.telefone;
    $('#user-cpf').html = user.cpf;

function editUser(){

    let login = $('#user-login').val();
    let password = $('#user-password').val();
    let email = $('#user-email').val();
    let dataNascimento = $('#user-dataNascimento').val();
    let telefone = $('#user-phone').val();
    let cpf = $('#user-cpf').val();

    user.login = login;
    user.password = password;
    user.email = email;
    user.dataNascimento = dataNascimento;
    user.telefone = telefone;
    user.cpf = cpf;

    let url = 'http://localhost:8080/ProjetoLP3/user';		
	
	const putMethod = {
 	method: 'PUT', 
 	headers: {
  	'Content-type': 'application/json'  
 	},
 	body: JSON.stringify(user) 
	}

	
	fetch(url, putMethod)
	.then(response => response.json())
	.then(data => console.log(data))
	.catch(err => console.log(err))


    Swal.fire({
        icon: 'success',
        title: 'Sucesso',
        text: 'Seu perfil foi editado!'
    });



}

function removeUser(){

    let url = `http://localhost:8080/ProjetoLP3/user?user-cpf=${user.cpf}`;

    fetch(url, {
        method: 'DELETE',
   })
    .then(res => res.text()) 
    .then(res => console.log(res))

    Swal.fire({
        icon: 'success',
        title: 'Sucesso',
        text: 'Seu perfil foi excluído!'
    });


}


});