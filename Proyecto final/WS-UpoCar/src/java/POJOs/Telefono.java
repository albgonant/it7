package POJOs;
// Generated 13-may-2018 13:13:31 by Hibernate Tools 4.3.1



/**
 * Telefono generated by hbm2java
 */
public class Telefono  implements java.io.Serializable {


     private Integer idTel;
     private Usuario usuario;
     private int numTel;

    public Telefono() {
    }

    public Telefono(Usuario usuario, int numTel) {
       this.usuario = usuario;
       this.numTel = numTel;
    }
   
    public Integer getIdTel() {
        return this.idTel;
    }
    
    public void setIdTel(Integer idTel) {
        this.idTel = idTel;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public int getNumTel() {
        return this.numTel;
    }
    
    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }




}


