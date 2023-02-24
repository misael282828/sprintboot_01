// Call the dataTables jQuery plugin
$(document).ready(function () {

  //on raady

});

async function iniciarSesion() {

  let datos = {};
  datos.email = document.getElementById('txtEmail').value;
  datos.password = document.getElementById('txtPassword').value;

//  console.log(datos);


  const request = await fetch("api/login", {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });


  const respuesta = await request.text();
  //console.log(respuesta)

  if(respuesta != "FAIL"){
  localStorage.token = respuesta;
  localStorage.email = datos.email;
  window.location.href= "usuarios.html"
  }else{
    alert("credenciales invalidad, por favor intentalo de nuevo")
  }

}