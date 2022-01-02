
package db;

import db.model.Address;
import db.model.Child;
import db.model.Parent;
import db.model.School;
import org.apache.commons.lang.NotImplementedException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("default");
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Main m = new Main();
        m.printOutObjects(Child.class);
        System.out.println("Введите id ребёнка которому нужно изменить адрес:");
        int id = in.nextInt();
        m.editChild(id);
        emfactory.close();
    }

    private void editChild(int id) {
        EntityManager em = emfactory.createEntityManager();
        Child child = em.find(Child.class, id);
        printOutObjects(Address.class);
        System.out.println("Введите id адреса по которому теперь проживает " + child.getName() + ":");
        int addressId = in.nextInt();
        Address address = em.find(Address.class, addressId);
        List<School> schools = getRecommendedSchools(address);
        System.out.println("В этом районе есть следующие школы:");
        for (School s : schools) {
            System.out.println(objString(s));
        }
        System.out.println("Введите id новой школы где учится " + child.getName() + ":");
        int schoolId = in.nextInt();
        School school = em.find(School.class, schoolId);
        changeAddress(child, address, school);
        em.close();
    }

    private String objString(Child c) {
        return "Child " + c.getId().toString() + ": " + c.getName() + ", школа " + c.getSchool().getNum();
    }

    private String objString(Address a) {
        return "Address " + a.getId().toString() + ": " + a.getAddress();
    }

    private String objString(School s) {
        return "School " + s.getId().toString() + ": номер " + Integer.toString(s.getNum());
    }

    private String objString(Object o) {
        if (o instanceof Child) {
            return objString((Child) o);
        } else if (o instanceof Address) {
            return objString((Address) o);
        } else if(o instanceof School) {
            return objString((School)o);
        }
        throw new NotImplementedException("objString not implemented for objects of type " + o.getClass().getSimpleName());
    }

    private <T> void printOutObjects(Class clazz) {
        EntityManager em = emfactory.createEntityManager( );
        TypedQuery<T> q = em.createQuery("SELECT o FROM " + clazz.getSimpleName() + " o", clazz);
        for (T o: q.getResultList()) {
            System.out.println(objString(o));
        }
        em.close();
    }

    private List<School> getRecommendedSchools(Address address) {
        EntityManager em = emfactory.createEntityManager( );
        TypedQuery<School> q = em.createQuery("SELECT s FROM School s WHERE s.address.district = :district", School.class);
        q.setParameter("district", address.getDistrict());
        List<School> schools = q.getResultList();
        em.close();
        return schools;
    }

    private Child addChild(String name, int age, List<Parent> parents, School school) {
        EntityManager em = emfactory.createEntityManager( );
        em.getTransaction( ).begin( );
        Child c = new Child();
        for (Parent p: parents) {
            p.addChild(c);
        }
        c.setAge(age);
        c.setName(name);
        c.setParents(parents);
        c.setSchool(school);
        em.persist(c);
        em.getTransaction().commit();
        em.close();
        return c;
    }

    private Parent addParent(String name, Address address) {
        EntityManager em = emfactory.createEntityManager( );
        em.getTransaction( ).begin( );
        Parent p = new Parent();
        p.setName(name);
        p.setAddress(address);
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        return p;
    }

    private Child changeAddress(Child child, Address address, School school) {
        EntityManager em = emfactory.createEntityManager( );
        em.getTransaction().begin();
        child = em.find(Child.class, child.getId());
        address = em.find(Address.class, address.getId());
        school = em.find(School.class, school.getId());
        List<Parent> ps = child.getParents();
        for (Parent p: ps) {
            p.setAddress(address);
        }
        List<Child> cs = ps.get(0).getChildren();
        for (Child c: cs) {
            c.setSchool(school);
        }
        em.getTransaction().commit();
        em.close();
        return child;
    }
}
