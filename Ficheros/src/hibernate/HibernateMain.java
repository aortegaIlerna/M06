package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateMain {

    public static void main(String[] args){
        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Alumno a = new Alumno();
        a.setNombre("Juanito de las Arenas");
        session.save(a);
        Query query = session.createQuery("FROM Alumno WHERE nombre =:nombre");
        query.setParameter("nombre","Juanito de las Arenas");
        List list = query.list();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).toString());
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

}
