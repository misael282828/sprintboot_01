// Call the dataTables jQuery plugin
$(document).ready(function () {

  //on raady

});

async function registrarUsuario() {

  let datos = {};
  datos.nombre = document.getElementById('txtNombre').value;
  datos.apellido = document.getElementById('txtApellido').value;
  datos.email = document.getElementById('txtEmail').value;
  datos.password = document.getElementById('txtPassword').value;

  let RepetirPassword = document.getElementById('txtRepetirPassword').value;

  if (RepetirPassword != datos.password) {
    alert("contraseñas no coinciden!")
    return
  }

  const request = await fetch('api/usuarios', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
    alert("La cuenta fue creada exitosamente!")
    window.location.href = "login.html"

}