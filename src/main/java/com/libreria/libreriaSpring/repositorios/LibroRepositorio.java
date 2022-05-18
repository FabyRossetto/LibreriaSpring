/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaSpring.repositorios;



import com.libreria.libreriaSpring.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fabi
 */
@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{

    
     @Query("SELECT a FROM Libro a WHERE a.isbn= :isbn")
    public Libro buscarLibroPorIsbn(@Param("isbn")Long isbn);
    
     @Query("SELECT l FROM Libro l WHERE l.titulo= :titulo")
    public Libro buscarLibroPorTitulo(@Param("titulo")String titulo);
   //mover a otros repo
    @Query("SELECT a FROM Libro a WHERE a.autor.nombre LIKE %:nombre% ")
    public List<Libro> buscarPorAutor(@Param ("nombre") String autor);
    
    @Query("SELECT e FROM Libro e WHERE e.editorial.nombre= :nombre")
    public List<Libro> buscarPorEditorial(@Param ("nombre") String editorial);
    
}
