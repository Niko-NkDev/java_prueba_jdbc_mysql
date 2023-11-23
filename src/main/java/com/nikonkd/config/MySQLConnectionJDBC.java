package com.nikonkd.config;

//JAVA

import com.nikonkd.entity.Persona;

import java.sql.*;
import java.util.Scanner;

// JDBC JAVA DATA BASE CONECTIVITY
public class MySQLConnectionJDBC {
    // se crea la base de datos en el PHPMyadmin(se abre primero el XAMP y luego en config),
    // de igual forma en privilegios se pone lo que es el usuario y la contraseña.
    static final String URL = "jdbc:mysql://localhost:3306/bd_prueba";
    // variables - static - final (por que es una variable constante - las constantes son variables fijas que no cambian el valor)
    static final String USUARIO = "admin";
    static  final String CONTRASENA = "admin123";

    // main es un metodo o funcion de ejecucion en JAVA - tiene que llamarse main para que se sepa que es un ejecutable
    public static void main(String[] args) {

        Connection conexion = null;

        // el try(intentar) es una funcionalidad de angular que sirve para tu controlar errores. el try-cathc sirve para controlar errores
        // los errores en JAVA son excepciones
        try {
            // Cargar el controlador JDBC - el className: es por defecto - llamar la libreria
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión con la base de datos - getConnection es un METODO - LO QUE ESTA DENTRO DEL METODO (PARAMETROS,) SON PARAMETRO
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);

            if (conexion != null) {
                System.out.println("¡Conexión exitosa con la base de datos!");
                Scanner scanner = new Scanner(System.in);
                System.out.println("Selecione la operacion a la cual desea ejecutar \n1.consultar \n2.insertar");
                System.out.print("Opcion: ");
                int operacion = scanner.nextInt();
                if (operacion == 1){
                    // Funcion donde esta la logica de la consulta - esta dentro del main y crea la conexion dentro del try
                    consultaSQL(conexion);
                } else if (operacion == 2) {
                    System.out.println("Ingrese los datos");
                    Persona persona = new Persona();
                    System.out.print("Nombre: ");
                    persona.setNombre(scanner.next());
                    System.out.print("Apellido: ");
                    persona.setApellido(scanner.next());
                    System.out.print("Numero de documento: ");
                    persona.setNumeroDocumento(scanner.nextInt());
                    System.out.print("Email: ");
                    persona.setEmail(scanner.next());
                    System.out.print("Telefono: ");
                    persona.setTelefono(scanner.next());
                    // funcion donde esta la logica de insertar - dentro del main
                    insertSQL(conexion, persona);
                }else {
                    System.out.println("La operacion ingresada no es correcta.");
                }
                // Aquí puedes realizar operaciones con la base de datos
            } else {
                System.out.println("Error al establecer la conexión con la base de datos.");
            }
            // el catch cactura la excepcion del try - lo que intenten dentro del try
        } catch (ClassNotFoundException | SQLException e) {
            //
            e.printStackTrace();
            // EL FINALLY ES CUANDO YA FINALIZA LO QUE ESTA DENTRO DEL TRY, Y REALIZA UN CLOSE, UN CIERRE A LA BASE DE DATOS
        } finally {
            try {
                // Cerrar la conexión en el bloque finally para asegurar que se cierre incluso si ocurre una excepción
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
        // cuando aparece azul es por que se esta usando
        // eso es un metod(JAVA)o que es lo mismo que una funcion en javascript - los naranjados son palabras reservadas de JAVA -
        // especificadores (private y static)
        // el void es un tipo de devolucion - y luego sigue el nombre del metodo
        // luego sigue la lista de parametros
        // [especificadores] tipoDevuelto nombreMetodo([lista parámetros]) [throws listaExcepciones]
        //{
        //    // instrucciones
        //   [return valor;]
        //}
    private static void consultaSQL(Connection conexion){

        Statement statement = null;

        try {
            statement = conexion.createStatement();

            String consultaSQL = "SELECT * FROM persona";
            ResultSet resultSet = statement.executeQuery(consultaSQL);

            while (resultSet.next()) {
                // Obtener datos del resultado
                Integer idPersona = resultSet.getInt("id_persona");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String numeroDocumento = resultSet.getString("numero_documento");
                String email = resultSet.getString("email");
                String telefono = resultSet.getString("telefono");

                // Realizar operaciones con los datos obtenidos
                System.out.println("id_persona: " + idPersona + ", nombre: " + nombre + ", apellido: " + apellido + ", numero_documento: " + numeroDocumento +
                        ", email: " + email + ", telefono: " + telefono);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void insertSQL(Connection conexion, Persona persona){

        PreparedStatement preparedStatement = null;

        try {

            String insertarSQL = "INSERT INTO `persona`(`nombre`, `apellido`, `numero_documento`, `email`, `telefono`) VALUES (?,?,?,?,?)";
            // el prepareStatement es una clase propia de SQL que se encarga de preparar un enunciada o la consulta.
            preparedStatement = conexion.prepareStatement(insertarSQL);

            // Establecer los parámetros de la consulta -- get es obtener - set es agregar :##
            preparedStatement.setString(1, persona.getNombre());
            preparedStatement.setString(2, persona.getApellido());
            preparedStatement.setInt(3, persona.getNumeroDocumento());
            preparedStatement.setString(4, persona.getEmail());
            preparedStatement.setString(5,persona.getTelefono());

            // Ejecutar la consulta de inserción
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Insercion exitosa. Filas guardadas: " + filasAfectadas);
            } else {
                System.out.println("La insercion no truvo exito.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

