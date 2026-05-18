package edu.puj;

import java.util.ArrayList;

public class Empresa implements IEmpresa {

    private String nombre;
    public ArrayList<Cliente> Clientes = new ArrayList<>();
    public ArrayList<Cuenta> Cuentas = new ArrayList<>();

    public Empresa(String nombre, ArrayList<Cliente> clientes, ArrayList<Cuenta> cuentas) {
        this.nombre = nombre;
        Clientes = clientes;
        Cuentas = cuentas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        Clientes = clientes;
    }


    public void setCuentas(ArrayList<Cuenta> cuentas) {
        Cuentas = cuentas;
    }

    @Override
    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> clientes = null;
        for (Cliente c : Clientes) {
            return null;
        }
    return null;
    }

}
