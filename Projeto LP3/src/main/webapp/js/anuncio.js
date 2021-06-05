import {user} from './login.js';

$(function() {

    document.getElementById("user-login").innerHTML = user.login;

    let url = `http://localhost:8080/ProjetoLP3/anuncio`;
    let html = '';
    //let conversao = 0;

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
            html += `<p>Horas Solicitadas</p>`;
            html += `<input id="horas-solicitadas" type="number" class="form-control"></input>`;
            html += '</div>';
            html += '<div>';
            html += `<a onclick="sendSolicitation(${data[i]})">Contratar</a>`;
            html += '</div>';
            html += '</div>';
  
    }

    $('#anuncios-div').html = html;
     
       
        
   }).catch(error => 
    console.log(error)
   );

function sendSolicitation(anuncio){

  let solicitation = {idAnuncio: '', qtdHoras: 0, dataSolicitacao: '', status: '',valorTotal: 0, cpfUser: '', cpfAnunciante: ''};

  let url = 'http://localhost:8080/ProjetoLP3/solicitacao';
  let horasSolicitadas = $('#horas-solicitadas').val();

  if(horasSolicitadas == undefined)
      return;  

  let dataAtual = new Date(); 
  var datetime =  dataAtual.getFullYear() + "/" 
                  + (dataAtual.getMonth()+1)  + "/"
                  + dataAtual.getDate() + " "
                  + dataAtual.getHours() + ":"  
                  + dataAtual.getMinutes() + ":" 
                  + dataAtual.getSeconds();


  solicitation.idAnuncio = anuncio.id;
  solicitation.qtdHoras = horasSolicitadas;
  solicitation.dataSolicitacao = datetime;
  solicitation.status = 'PENDENTE';
  solicitation.valorTotal = horasSolicitadas * anuncio.valorHora;    
  solicitation.cpfUser = anuncio.CPF;
  solicitation.cpfAnunciante = user.cpf;

  fetch(url, {
  method: "POST",
  headers: {
    "Content-Type": "application/json",
  },
  body: JSON.stringify(solicitation),
})
  .then((response) => response.json())
  .then((data) => {
    console.log("Success:", data);
  })
  .catch((error) => {
    console.error(error);
  });

}

});