package edu.puj;

import java.io.*;
import java.util.ArrayList;


public class ManejoArchivos {

    private ManejoArchivos() {}



    public static ArrayList<Cliente> leerClientes(String ruta) throws Exception {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    if (linea.equals("#FIN")) break;
                    continue;
                }
                String[] partes = linea.split("\\*");
                if (partes.length < 3) {
                    System.out.println("Línea ignorada (formato incorrecto): " + linea);
                    continue;
                }
                String nombre     = partes[0].trim();
                String cedula     = partes[1].trim();
                String direccion  = partes[2].trim();
                clientes.add(new Cliente(nombre, cedula, "CC", direccion));
            }
        } catch (FileNotFoundException e) {
            throw new JavemovilException("Archivo no encontrado: " + ruta);
        } catch (IOException e) {
            throw new JavemovilException("Error al leer el archivo: " + e.getMessage());
        }
        return clientes;
    }


    public static void salvarSistema(String ruta, IEmpresa empresa) throws Exception {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(empresa);
        } catch (IOException e) {
            throw new JavemovilException("Error al guardar el sistema: " + e.getMessage());
        }
    }


    public static IEmpresa cargarSistema(String ruta) throws Exception {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(ruta))) {
            return (IEmpresa) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new JavemovilException("Archivo no encontrado: " + ruta);
        } catch (IOException e) {
            throw new JavemovilException("Error al leer el archivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new JavemovilException(
                    "El archivo no contiene un sistema válido: " + e.getMessage());
        }
    }
}
