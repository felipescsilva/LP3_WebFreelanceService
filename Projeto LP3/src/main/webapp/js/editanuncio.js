import {user} from './login.js';
import {anuncio} from './myanuncio.js';

$(function() {

    document.getElementById("user-login").innerHTML = user.login;

    $('#anuncio-cpf').html = anuncio.CPF;
    $('#anuncio-titulo').html = anuncio.titulo;
    $('#anuncio-descricao').html = anuncio.descricao;
    $('#anuncio-precohora').html = anuncio.valorHora;


    function editarAnuncio(){

    let cpf = $('#anuncio-cpf').val();
    let titulo = $('#anuncio-titulo').val();
    let descricao = $('#anuncio-descricao').val();
    let precoHora = $('#anuncio-precohora').val();
    

    anuncio.CPF = cpf;
    anuncio.titulo = titulo;
    anuncio.descricao = descricao;
    anuncio.valorHora = precoHora;
   
    let url = 'http://localhost:8080/ProjetoLP3/anuncio';		
	
	const putMethod = {
 	method: 'PUT', 
 	headers: {
  	'Content-type': 'application/json'  
 	},
 	body: JSON.stringify(anuncio) 
	}

	
	fetch(url, putMethod)
	.then(response => response.json())
	.then(data => console.log(data))
	.catch(err => console.log(err))


    Swal.fire({
        icon: 'success',
        title: 'Sucesso',
        text: 'Seu anúncio foi editado!'
    });


    }
   

});