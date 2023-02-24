package com.cursoSprint.curso.dao;

import com.cursoSprint.curso.models.Usuario;

import java.util.List;


public interface UsuarioDao {

    List<Usuario> getUsuario();

    void eliminar(Long id);

    List<Usuario> registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
