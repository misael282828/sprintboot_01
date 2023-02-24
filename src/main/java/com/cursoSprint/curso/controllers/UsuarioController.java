package com.cursoSprint.curso.controllers;

import com.cursoSprint.curso.dao.UsuarioDao;
import com.cursoSprint.curso.models.Usuario;
import com.cursoSprint.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.yaml.snakeyaml.tokens.Token.ID.Value;

// netstat -ano | findstr LISTENING | findstr 8080
// taskkill -PID 9600 -F
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.GET) //endpoint de la ruta
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();

        usuario.setId(id);
        usuario.setNombre("misael");
        usuario.setApellido("guzman");
        usuario.setEmail("test@test.com");
        usuario.setTelefono("8092459897");

        return usuario;
    }

    //   Mostrar todos los usuarios
    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET) //endpoint de la ruta
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {

        if (!validarToken(token)){return null;}
        return usuarioDao.getUsuario();

    }

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getkey(token);
        return usuarioId != null;
    }


    //    Registrar un usuario
    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST) //endpoint de la ruta
    public void registrarUsuario(@RequestBody Usuario usuario) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());

        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);

    }

    //    Eliminar un usuario
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token,@PathVariable Long id) {
        if (!validarToken(token)){return;}

        usuarioDao.eliminar(id);
    }

}


