package libreria;

import java.sql.SQLException;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class Almacen {

    Session session = null;

    public Almacen() {
    }

    public List<Libro> consultaListaLibrosSolicitados(List<String> listaIsbns) throws
            Exception {
        Map<String, Libro> conjuntoLibros = new HashMap<String, Libro>(listaIsbns.size());
        for (String isbn : listaIsbns) {
            Libro libro = null;
            if (!conjuntoLibros.containsKey(isbn)) {
                libro = this.consultaLibro(Integer.parseInt(isbn));
            } else {
                libro = conjuntoLibros.get(isbn);
                conjuntoLibros.remove(isbn);
                libro.incrementaCantidad();
            }
            conjuntoLibros.put(isbn, libro);
        }
//pasamos a lista
        Set<String> conjuntoIsbns = conjuntoLibros.keySet();
        Iterator<String> it = conjuntoIsbns.iterator();
        List<Libro> listaLibros = new ArrayList<Libro>(conjuntoIsbns.size());
        while (it.hasNext()) {
            listaLibros.add(conjuntoLibros.get(it.next()));
        }
//pasamos a
        return listaLibros;
    }

    public Libro consultaLibro(int isbn) throws SQLException {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        Libro libro = null;
//abrimos una transaccion en hibernate para poder ejecutar consultas
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Libro where isbn = '" + isbn + "'");
        libro = (Libro) q.uniqueResult();
        libro.setCantidad(1);
//muy importante hacer commit para que se termine de ejecutar y cerrar la transaccion.Sino se especifica, la transaccion queda abierta y no permite ejecutar otras 
        tx.commit();
        return libro;
    }

    public List<Libro> consultaLibrosDisponibles() throws SQLException {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Libro");
        List resultados = (List<Libro>) q.list();
//muy importante hacer commit para que se termine de ejecutar y cerrar la transaccion.Sino se especifica, la transaccion queda abierta y no permite ejecutar otras 
        tx.commit();
        return resultados;
    }

    public List<Editorial> consultaEditoriales() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Editorial");
        List resultados = (List<Libro>) q.list();

        tx.commit();
        return resultados;
    }

    public Editorial obtenerEditorial(int id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Editorial editorial = null;
        Query q = session.createQuery("from Editorial where id = '" + id + "'");
        editorial = (Editorial) q.uniqueResult();
        
        tx.commit();
        return editorial;
    }

    public boolean agregar(Editorial editorial, String autor, String titulo, float precio, int isbn) {
        boolean isbnRepeat = false;
        
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        
        //para evitar duplicidad en el ID, cogemos el último id insertado en la tabla y le añadimos 1
        Query q = session.createQuery("From Libro order by id desc");
        List<Libro> librosID = (List<Libro>)q.list();
        
        for (int i = 0; i < librosID.size(); i++) {
            if(librosID.get(i).getIsbn() == isbn){
                isbnRepeat = true;
            }
            
        }
        if(!isbnRepeat){
        Libro libro = new Libro((librosID.get(0).getId()+1),editorial, autor, titulo, precio, isbn);
        
        session.save(libro);

        
        }
        tx.commit();
        return isbnRepeat;
    }
}
