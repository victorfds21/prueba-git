package base;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static final String host = "localhost";
    static final String port = "3306";
    static final String database = "TIENDA2";
    static final String user = "root";
    static final String password = "";
    // static final String urlparameters = "?serverTimezone=UTC"; 

    static final String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

    public static void main(String[] args) {
        try {
            // driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Se ha cargado el driver Mysql");

            //conectar
            Connection conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Se ha conectado coreectamente a la base de datos");

            // crear objeto sentencia 
            Statement sentencia = conexion.createStatement();

            // ejecutar consulta y almacenar resultado
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM empleados");

//             recorrer resultado
            while (resultado.next()) {
                int codigo = resultado.getInt("e_codigo");
                String nombre = resultado.getString("e_nombre");
                String apellidos = resultado.getString("e_apellidos");
                String contraseña = resultado.getString("e_contraseña");

                System.out.printf("%d %s %s %s%n", codigo, nombre, apellidos, contraseña);

            }
                System.out.println(""); // espacio entre consultas

            resultado = sentencia.executeQuery("SELECT * FROM productos");

            while (resultado.next()) {
                int codigo = resultado.getInt("p_codigo");
                String nombre = resultado.getString("p_nombre");
                String descripcion = resultado.getString("p_descripcion");
                double precio = resultado.getDouble("p_precio");

                System.out.printf("%d %s %s %.2f%n", codigo, nombre, descripcion, precio);
            }
            
            sentencia.close();
            resultado.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("Error al  cargar el driver");
        } catch (SQLException ex) {
            System.out.println("Error en la conexion a la base de datos");
        }
    }
// esto va muy bienn
    
}
