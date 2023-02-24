package com.cursoSprint.curso.dao;

import com.cursoSprint.curso.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public class UsuariodaoImp implements UsuarioDao {

    @PersistenceContext
    EntityManager entityManager; // para hacer la conexion con la BD

    @Override
    @Transactional


    public List<Usuario> getUsuario() { //consulta:  String query = "FROM Usuario";
        String query = "FROM Usuario"; // Usuario es es nombre de la clase,traetodo los usuarios de la clase
        return entityManager.createQuery(query).getResultList();
        // va a
    }

    @Override
    public void eliminar(Long id) {
//        obtener el usuario a eliminar de la base de dato
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);

    }

    @Override
    public List<Usuario> registrar(Usuario usuario) {
        entityManager.merge(usuario);
        return null;
    }


    @Override //si la credential son correcta devuelve un usuario
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {

        String query = "FROM Usuario WHERE email = :email"; //base de datos
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        //validar que no llegue vacia al lista
        if (lista.isEmpty()) {
            return null;
        }
        String passwordHashed = lista.get(0).getPassword(); // traer pass con el hash o sea desde la base de datos

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        if (argon2.verify(passwordHashed, usuario.getPassword())) {
            return lista.get(0);
        }
        return null;


    }


}
