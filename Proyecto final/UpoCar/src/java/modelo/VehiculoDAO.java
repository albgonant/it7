/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;
import webServiceREST.JerseyClientVehiculo;
import webServiceREST.entidades.Usuario;
import webServiceREST.entidades.Vehiculo;

/**
 * 
 * @author Antonio Jose Herrera Tabaco 
 */
public class VehiculoDAO {
    private JerseyClientVehiculo clienteVehiculo = new JerseyClientVehiculo();

    public JerseyClientVehiculo getClienteVehiculo() {
        return clienteVehiculo;
    }

    public void setClienteVehiculo(JerseyClientVehiculo clienteVehiculo) {
        this.clienteVehiculo = clienteVehiculo;
    }
    
    public List<Vehiculo> listadoVehiculosUsuario(int idUsuario){
        GenericType<List<Vehiculo>> genericType = new GenericType<List<Vehiculo>>(){};
        
        List<Vehiculo> listaVehiculos = getClienteVehiculo().findAll_XML(genericType);
        List<Vehiculo> listaVehiculoUser = new ArrayList<Vehiculo>();
        
        FotoVehiDAO fDAO = new FotoVehiDAO();
        
        for(int i = 0; i < listaVehiculos.size(); i++){
            Vehiculo v = listaVehiculos.get(i);
            
            Usuario u = v.getIdUsuario();
            
            if(u.getIdUsuario().equals(idUsuario)){
                
                v.setListaFotosVehiculos( fDAO.fotosVehiculo(v.getIdVehiculo().toString()) );
                
                listaVehiculoUser.add(v);
            }            
        }
        return listaVehiculoUser;
    }
    
    public Vehiculo getVehiculoPorId(String id){
        GenericType<Vehiculo> genericType = new GenericType<Vehiculo>(){};
        Vehiculo v = this.clienteVehiculo.find_XML(genericType, id);
        FotoVehiDAO fDAO = new FotoVehiDAO();
        v.setListaFotosVehiculos( fDAO.fotosVehiculo(v.getIdVehiculo().toString()) );
        return v;
    }
    
    public void createVehiculo(Vehiculo v){
        this.getClienteVehiculo().create_XML(v);
    }
    
    public void updateVehiculo(String idVehiculo,Vehiculo v) {

        GenericType<Vehiculo> genericTypeVehiculo = new GenericType<Vehiculo>() {
        };
        this.getClienteVehiculo().edit_XML(v, idVehiculo);
    }
    
    public void deleteVehiculo(String id) {
        this.getClienteVehiculo().remove(id);
    }
}
