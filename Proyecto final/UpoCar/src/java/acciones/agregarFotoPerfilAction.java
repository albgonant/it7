/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import modelo.TelefonoDAO;
import modelo.UsuarioDAO;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.eclipse.persistence.tools.file.FileUtil;
import webServiceREST.entidades.Telefono;
import webServiceREST.entidades.Usuario;

public class agregarFotoPerfilAction extends ActionSupport {
    
    List<Usuario> listadoUsuarios = new ArrayList<Usuario>();
    List<Telefono> listadoTelefonos = new ArrayList<Telefono>();
    File fotoPerfil;
    
    public agregarFotoPerfilAction() {
    }
    
    public String execute() throws Exception {
        return SUCCESS;
    }
    /* 
    Devuelve una lista de usuarios y una lista de telefonos 
    para mostrarlo en la vista
    */
    public String toMisDatos(){
        //Recojo el usuario de la sesion y lo guardo en una lista
        Map sesion = (Map) ActionContext.getContext().get("session");
        Usuario u = (Usuario) sesion.get("usuario");
        UsuarioDAO uDAO = new UsuarioDAO();
        this.getListadoUsuarios().clear();
        this.getListadoUsuarios().add( uDAO.buscarUsuarioPorID(u.getIdUsuario().toString()) );
        TelefonoDAO tDao = new TelefonoDAO();
        this.setListadoTelefonos(tDao.listarTelefonosUsuarios(u.getIdUsuario()));
        return SUCCESS;
    }
    
    /*
    Obtiene el usuario de la sesion
    Monta la ruta relativa
    Copia la foto del temporal a un fichero
    Actualiza en base de datos la foto
    Modifica la foto en elusuario de la sesion
    LLama a mis datos
    */
    
    public String agregarFotoPerfil() throws IOException{
        //Obtengo el usuario de la sesion
        Map sesion = (Map) ActionContext.getContext().get("session");
        Usuario u = (Usuario) sesion.get("usuario");
        UsuarioDAO uDao = new UsuarioDAO();
        //Creo ServletContext
        ServletContext context = ServletActionContext.getServletContext();
        String nombreFichero = u.getIdUsuario() + u.getNombre() + u.getApellidos() + ".png";
        String rutaRelativa = "images/fotosPerfil/" + nombreFichero;
        String ruta = context.getRealPath("/") + rutaRelativa;
        File fichero = new File(ruta);
        FileUtils.copyFile(this.getFotoPerfil(), fichero);
        uDao.updateUsuarioFotoPerfil(u.getIdUsuario(), "./"+rutaRelativa);
        //u.setFoto("./"+rutaRelativa);
        //Llamo al metodo toMisDatos() para recargar la pagina
        this.toMisDatos();
        return SUCCESS;
    }
    
    /*
    Obtiene el usuario de la sesion
    Selecciona la imagen a guardar, male o female
    Actualiza en base de datos la foto
    Modifica la foto en elusuario de la sesion
    LLama a mis datos
    */
    public String quitarFotoPerfil() {
        //Obtengo el usuario de la sesion
        Map sesion = (Map) ActionContext.getContext().get("session");
        Usuario u = (Usuario) sesion.get("usuario");
        UsuarioDAO uDao = new UsuarioDAO();
              
        String rutaFoto = "./images/male.png";
        if(u.getSexo().equals("F")){
            rutaFoto = "./images/female.png";
        }        
        uDao.updateUsuarioFotoPerfil(u.getIdUsuario(), rutaFoto);
        //u.setFoto(rutaFoto);
        //Llamo al metodo toMisDatos() para recargar la pagina
        this.toMisDatos();
        return SUCCESS;
    }
    
    /* Getter y setter */
    
    public List<Usuario> getListadoUsuarios() {
        return listadoUsuarios;
    }

    public void setListadoUsuarios(List<Usuario> listadoUsuarios) {
        this.listadoUsuarios = listadoUsuarios;
    }

    public List<Telefono> getListadoTelefonos() {
        return listadoTelefonos;
    }

    public void setListadoTelefonos(List<Telefono> listadoTelefonos) {
        this.listadoTelefonos = listadoTelefonos;
    }

    public File getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(File fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
    
}
