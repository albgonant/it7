/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import modelo.PuntuacionDAO;
import modelo.UsuarioDAO;
import webServiceREST.entidades.Puntuacion;
import webServiceREST.entidades.Usuario;

/**
 *
 * @author Antonio Jose Herrera Tabaco
 */
public class verPerfilAction extends ActionSupport {

    private String idUsuario;
    private String nombre;
    private String apellidos;
    private String foto;
    private List<Puntuacion> listaPuntuacionRecibidas = new ArrayList<Puntuacion>();
    List<Puntuacion> listaPuntuacionRealizadas = new ArrayList<Puntuacion>();
    private String puntuacion;
    private String comentario;
    private String voto;

    UsuarioDAO usuarioDao = new UsuarioDAO();
    PuntuacionDAO puntuacionDao = new PuntuacionDAO();

    public List<Puntuacion> getListaPuntuacionRealizadas() {
        return listaPuntuacionRealizadas;
    }

    public void setListaPuntuacionRealizadas(List<Puntuacion> listaPuntuacionRealizadas) {
        this.listaPuntuacionRealizadas = listaPuntuacionRealizadas;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<Puntuacion> getListaPuntuacionRecibidas() {
        return listaPuntuacionRecibidas;
    }

    public void setListaPuntuacionRecibidas(List<Puntuacion> listaPuntuacionRecibidas) {
        this.listaPuntuacionRecibidas = listaPuntuacionRecibidas;
    }

    public String getIdPuntuacion() {
        return puntuacion;
    }

    public void setIdPuntuacion(String idPuntuacion) {
        this.puntuacion = idPuntuacion;
    }

    public verPerfilAction() {
    }

    public String execute() throws Exception {                
        return SUCCESS;
    }
    
    /*
    Accede al perfil del usuario seleccionado mediante un ID
    */
    public String verPerfilUsuario(){        
        Usuario u = usuarioDao.buscarUsuarioPorID(idUsuario);
        
        this.foto = u.getFoto();
        this.nombre = u.getNombre();
        this.apellidos = u.getApellidos();
        this.puntuacion = String.valueOf(u.getTotalPuntuacion());
        this.listaPuntuacionRecibidas = puntuacionDao.listarPuntuacionRecibidas(Integer.parseInt(idUsuario));
        return SUCCESS;
    }
    
    /*
    Recoge la sesion del usuario actual, y del usuario seleccionado. Seleccionando un numero entre 1 y 5
    y comentando al usuario, se le asigna la votacion con su correspondiente comentario
    */
    public String votar(){
        Puntuacion p = new Puntuacion(null, Integer.parseInt(voto), comentario);
        Map sesion = (Map) ActionContext.getContext().get("session");
        Usuario u = (Usuario) sesion.get("usuario");
        p.setIdUsuarioPuntua(u);
        Usuario uRecibe = usuarioDao.buscarUsuarioPorID(idUsuario);
        p.setIdUsuarioRecibe(uRecibe);
        listaPuntuacionRealizadas = puntuacionDao.listarPuntuacionRealizadas(u.getIdUsuario());
        puntuacionDao.puntuar(p);
        /*
        Recalcular la media de la puntuacion
        */
        return SUCCESS;
    }

}
