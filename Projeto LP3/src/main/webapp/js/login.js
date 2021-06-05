
$(function() {

let user = {cpf: '', login: '', senha: '', dataNascimento: {},tel: '',email: ''};  

function sendLogin(){

    let cpf = $("user-cpf").val();
    let password = $("user-password").val();

    let url = `http://localhost:8080/ProjetoLP3/user?user-cpf=${cpf}`;
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
            text: 'Ocorreu um erro no Login'
          });
      }
    })
    .then(function(data) {

      if(password != data.password){
      Swal.fire({
        icon: 'error',
        title: 'Erro',
        text: 'Usuário ou senha incorreta'
      });
        return;
    }

     user.cpf = data.CPF;
     user.login= data.login;
     user.senha = data.password;
     user.dataNascimento = data.dataNascimento;
     user.tel = data.telefone;
     user.email = data.email;

     export default user;

     location.href = 'anunciolist.html';
        
       
        
   }).catch(error => 
    console.log(error)
   );

  

    //chamar Api de consulta da tabela usuario


}



});