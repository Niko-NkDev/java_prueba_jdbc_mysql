package com.nikonkd;

import com.nikonkd.entity.Persona;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //creando una instancia de una clase o un objeto con un constructor vacio o sin parametros
        // se agregan los valores de a uno en uno -
        Persona persona = new Persona();
        persona.setIdPersona(1);
        persona.setNombre("Nicolas");
        persona.setApellido("Cortes");
        persona.setNumeroDocumento(1039697699);
        persona.setEmail("javier_9890@hotmail.com");
        persona.setTelefono("+57 3147258925");
        // comando para imprimir como el console.log
        System.out.println(persona.toString());
        // creacion de instancia con un constructor con parametros en la clase Persona.java
        Persona persona1 = new Persona(2, "Emma", "Gaviria", 1039697633, "emma@gmail.com", "+57 3146029181");
        persona1.setNombre("Juan");
        System.out.println(persona1.toString());
        // se crea una objeto de tipo arreglo - [array] de personas
        Persona[] personas = {persona,persona1};
        System.out.println(Arrays.toString(personas));
        //se crea una objeto de lista de personas - List es una interfaz
        List<Persona> personaList = new ArrayList<>();
        personaList.add(persona);
        personaList.add(persona1);
        System.out.println(personaList);
    }
}