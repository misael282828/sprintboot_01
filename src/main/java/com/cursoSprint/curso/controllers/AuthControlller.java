package com.cursoSprint.curso.controllers;

import com.cursoSprint.curso.dao.UsuarioDao;
import com.cursoSprint.curso.models.Usuario;
import com.cursoSprint.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


// netstat -ano | findstr LISTENING | findstr 8080
// taskkill -PID 9600 -F
@RestController
public class AuthControlller {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST) //endpoint de la ruta
    public String login(@RequestBody Usuario usuario) {

        Usuario usuarioLogeado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if (usuarioLogeado != null) {

       String tokenJWT = jwtUtil.create(String.valueOf(usuarioLogeado.getId()), usuarioLogeado.getEmail());
            return tokenJWT;
        }
        return "error en la autenticacion";

    }

}
