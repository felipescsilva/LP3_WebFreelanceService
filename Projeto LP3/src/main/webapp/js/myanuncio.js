import {user} from './login.js';

$(function() {

    document.getElementById("user-login").innerHTML = user.login;

    let url = `http://localhost:8080/ProjetoLP3/anuncio?anuncio-cpf=${user.CPF}`;
    let html = '';

    const myRequest = new Request(url, {method: 'GET'});
 
      fetch(myRequest)
     .then(function(response){
      if (response.status === 200) {
            return response.json();   
      } else {
        Swal.fire({
            icon: 'error',
            title: 'Erro na API',
            text: 'Ocorreu um erro na busca de anúncios'
          });
      }
    })
    .then(function(data) {
      
        for(var i = 0; i < data.length ; i++){
            html += '<div>';
            html += '<div>';
            html += `<a>Título: ${data[i].titulo}</a>`;
            html += `<p>CPF: ${data[i].CPF}</p>`;
            html += '</div>';            
            html += '<div>';
            html += `<p>Descrição do anúncio: ${data[i].descricao}</p>`;
            html += '</div>';
            html += '<div>';
            html += `<p>Valor da hora: ${data[i].valorHora}</p>`;
            html += '</div>';
            html += '<div>';
            html += `<a class="btn btn-primary" onclick="editAnuncio(${data[i]})">Editar</a>`;
            html += `<a class="btn btn-danger" onclick="deleteAnuncio(${data[i].id})">Deletar</a>`;
            html += '</div>';
            html += '</div>';
  
    }

    $('#anuncios-div').html = html;
     
       
        
   }).catch(error => 
    console.log(error)
   );

function editAnuncio(anuncio){

    location.href = 'editanuncio.html'

    export default anuncio;

}

function deleteAnuncio(anuncioId){

    let url = 'http://localhost:8080/ProjetoLP3/anuncio';
	
	fetch(url + anuncioId, {
  			method: 'DELETE',
		 })
    .then(res => res.text())
    .then(res => console.log(res))

}

});