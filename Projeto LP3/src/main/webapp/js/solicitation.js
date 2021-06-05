import {user} from './login.js';


$(function() {

    document.getElementById("user-login").innerHTML = user.login;

    let url = `http://localhost:8080/ProjetoLP3/solicitacao?solicitacao-CPF_User=${user.cpf}`;
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
            text: 'Ocorreu um erro na busca de solicitacoes'
          });
      }
    })
    .then(function(data) {
      
        for(var i = 0; i < data.length ; i++){
            html += '<div>';
            html += '<div>';
            html += `<a>Título: ${data[i].anuncio.titulo}</a>`;
            html += `<p>CPF do Solicitante: ${data[i].CPF_Anunciante}</p>`;
            html += '</div>';
            html += '<div>';
            html += `<p>Descrição do Anúncio: ${data[i].anuncio.descricao}</p>`;
            html += '</div>';            
            html += '<div>';
            html += `<p>Data da Solicitação:  ${data[i].dataSolicitacao.day}/${data[i].dataSolicitacao.month}/${data[i].dataSolicitacao.year}</p>`;
            html += '</div>';
            html += '<div>';
            html += `<p>Horas Solicitadas: ${data[i].qtdHoras} hora(s)</p>`;
            html += `<p>Valor Total: ${data[i].valorTotal} hora(s)</p>`;
            html += '</div>';
            html += '<div>';
            html += `<p>Status: ${data[i].status}</p>`;
            html += '</div>';
            html += '<div>';
            html += `<a onclick="sendAccept(${data[i]})">Aceitar</a>`;
            html += `<a onclick="sendDeny(${data[i]})">Recusar</a>`;
            html += '</div>';
            html += '</div>';
  
    }

    $('#my-solicitation-div').html = html;
     
       
        
   }).catch(error => 
    console.log(error)
   );


})