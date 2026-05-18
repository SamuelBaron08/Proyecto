package edu.puj;

import java.util.ArrayList;

public class Empresa implements IEmpresa{


    public ArrayList<Cliente> Clientes = new ArrayList<>();
    public ArrayList<Cuenta> Cuentas = new ArrayList<>();

    @Override
    public ArrayList<Cliente> getClientes() {
        return null;
    }
}
