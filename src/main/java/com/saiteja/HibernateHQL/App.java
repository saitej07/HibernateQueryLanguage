package com.saiteja.HibernateHQL;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration con = new Configuration().configure().addAnnotatedClass(Student.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        
        session.beginTransaction();
        
        //int b = 60;
//        Random r = new Random();
//        
//        for(int i = 1; i<=50; i++)
//        {
//        	Student s = new Student();
//        	s.setRollno(i);
//        	s.setName("Name" + i);
//        	s.setMarks(r.nextInt(100));
//        	session.save(s);
//        }
        
         //Query q = session.createQuery("select rollno,name,marks from Student");
         
         //Query q = session.createQuery("select sum(marks) from Student where marks> :b");
         
         //q.setParameter("b", b);
         
//         List<Object[]> students =  (List<Object[]>) q.list();
//         
//         for(Object[] student : students)
//         {
//        	 System.out.println(student[0] + " : " + student[1] + " : " + student[2]);
//         }
         
//           Long marks = (Long) q.uniqueResult();
//           
//           System.out.println(marks);
        
        
        // Using SQL Query
        
//        SQLQuery query = session.createSQLQuery("select * from Student where marks > 60");
//        query.addEntity(Student.class);
//        
//        List<Student> students = query.list();
//        
//        for(Student o : students)
//        {
//        	System.out.println(o);
//        }
        
        // Native queries
        SQLQuery query = session.createSQLQuery("select name,marks from Student where marks > 60");
        
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
        List students = query.list();
        
        for(Object o : students)
        {
        	Map m = (Map)o;
        	System.out.println(m.get("name") + " : " + m.get("marks"));
        }
           
         
         
        
        session.getTransaction().commit();
    }
}
