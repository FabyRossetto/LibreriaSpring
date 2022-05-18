/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaSpring.servicios;

import com.libreria.libreriaSpring.entidades.Autor;
import com.libreria.libreriaSpring.entidades.Editorial;
import com.libreria.libreriaSpring.entidades.Libro;
import com.libreria.libreriaSpring.repositorios.AutorRepositorio;
import com.libreria.libreriaSpring.repositorios.EditorialRepositorio;
import com.libreria.libreriaSpring.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fabi
 */
@Service
public class LibroServicio {
    @Autowired
    LibroRepositorio lr;
    @Autowired
    AutorRepositorio ar;
    @Autowired
    EditorialRepositorio er;

    @Transactional
    public void CrearLibro(Long isbn, String titulo, Integer anio, Autor autor, Editorial edit) throws Exception {
//se le pasa por parametro lo que el usuario llena 
        try {
            validar(isbn, titulo, anio, autor, edit);
            Libro li = new Libro();
            li.setIsbn(isbn);
            li.setTitulo(titulo);
            li.setAnio(anio);
            li.setAlta(true);
            li.setAutor(autor);
            li.setEditorial(edit);

            lr.save(li);

        } catch (Exception e) {
            System.out.println("No se a podido crear el libro");
        }
    }

    @Transactional
    public void modificarLibro(String id, Long isbn, String titulo, Integer anio, Autor autor, Editorial edit) throws Exception {

        validar(isbn, titulo, anio, autor, edit);
        //optional es una clase que puede o no puede contener un valor, se usa por las dudas que el dato ingresado sea nulo

        Optional<Libro> respuesta = lr.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            //no se crea un libro sino que se busca ,mediante el repo, el libro por id.
            //Si la respuesta is present,entonces que la traiga y setee los atributos, sino que tire una excepcion
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setAutor(autor);
            libro.setEditorial(edit);
            libro.setAlta(true);

               lr.save(libro);
        } else {
            throw new Exception("no se encontro ningun libro con ese identificador");
        }

    }

    @Transactional// cualquier manejo que se haga con la db es una transaccion
    public void darDeBajaLibro(String id) throws Exception {
        Optional<Libro> respuesta = lr.findById(id);
        if (respuesta.isPresent()) {
            Libro l = respuesta.get();
            l.setAlta(false);
            lr.save(l);
        } else {
            throw new Exception("no se encontro el libro que desea dar de baja");
        }
    }

    public void validar(Long isbn, String titulo, Integer anio, Autor autor, Editorial edit) throws Exception {

        if (isbn == null || isbn.toString().trim().isEmpty() || isbn.toString().length() < 8) {

            throw new Exception(" El isbn no puede ser nulo,ni tener menos de 8 caracteres");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception(" El titulo no puede ser nulo");
        }
        if (anio == null || anio.toString().trim().isEmpty() || anio > 2022) {
            throw new Exception(" El año no puede ser nulo,ni futuro");
        }

        if (autor == null || autor.toString().trim().isEmpty()) {
            throw new Exception(" El autor no puede ser nulo)");
        }
        if (edit == null || edit.toString().trim().isEmpty()) {
            throw new Exception(" La editorial no puede ser nula)");
        }

    }
    
    public void buscarLibroPorId(String id)throws Exception{
        
        Optional<Libro> respuesta = lr.findById(id);
        if (respuesta.isPresent()) {
            Libro l = respuesta.get();
            System.out.println(l);
    } else{
            throw new Exception ("El libro no se ha podido encontrar");
        }
    }
    
    public Libro buscarLibroPorTitulo(String titulo)throws Exception{
       
        
        Libro libro= lr.buscarLibroPorTitulo(titulo);
                
        return libro;
    }
    
     public Libro buscarLibroPorIsbn(Long isbn)throws Exception{
       
        
        Libro libro= lr.buscarLibroPorIsbn(isbn);
                
        return libro;
    }
     
     public List<Libro> buscarLibroPorAutor(String autor)throws Exception{
       
        
        List<Libro>libro= lr.buscarPorAutor(autor);
                
        return libro;
    }
     
//     public List<Libro> buscarLibroPorEditorial(Editorial editorial)throws Exception{
//       
//        
//        List<Libro>libro= lr.buscarPorEditorial(editorial);
//                
//        return libro;
//    }
    
    
    public List <Libro>listarLibro(){
        Integer aux=0;
        List<Libro> l =lr.findAll();
       
        for (Object ejemplare : l) {
        aux=+1;
        }
        System.out.println(" ejemplares totales"+ aux);
        
      
        return l;
        
    }
    
    public void calcularEjemplares( Integer ejemplares){
        Scanner Leer=new Scanner(System.in);
    
        List<Libro>ejemplaresPrestados= new ArrayList();
        List<Libro>ejemplaresRestantes= new ArrayList();
        System.out.println("Desea ingresar un nuevo prestamo?");
        String Respuesta=Leer.next();
        do{
        for (Object ejem : ejemplaresPrestados) {
           Integer EP=+1;
            for (Libro ejemp2: ejemplaresRestantes) {
               Integer ER= ejemplares-1;
            }
   
        }
       
    }while (Respuesta.equalsIgnoreCase("si"));
    
}
}
