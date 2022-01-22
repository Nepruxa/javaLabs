
package db;

import db.models.AddressModel;
import db.models.ChildModel;
import db.models.ParentModel;
import db.models.SchoolModel;
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
        m.printOutObjects(ChildModel.class);
        System.out.println("Enter child id");
        int id = in.nextInt();
        m.editChild(id);
        emfactory.close();
    }

    private void editChild(int id) {
        EntityManager em = emfactory.createEntityManager();
        ChildModel childModel = em.find(ChildModel.class, id);
        printOutObjects(AddressModel.class);
        System.out.println("Enter address id" + childModel.getName() + ":");
        int addressId = in.nextInt();
        AddressModel addressModel = em.find(AddressModel.class, addressId);
        List<SchoolModel> schools = getRecommendedSchools(addressModel);
        System.out.println("Schools in this area:");
        for (SchoolModel s : schools) {
            System.out.println(objString(s));
        }
        System.out.println("Enter school id" + childModel.getName() + ":");
        int schoolId = in.nextInt();
        SchoolModel schoolModel = em.find(SchoolModel.class, schoolId);
        changeAddress(childModel, addressModel, schoolModel);
        em.close();
    }

    private String objString(ChildModel c) {
        return "Child " + c.getId().toString() + ": " + c.getName() + ", school " + c.getSchool().getNum();
    }

    private String objString(AddressModel a) {
        return "Address " + a.getId().toString() + ": " + a.getAddress();
    }

    private String objString(SchoolModel s) {
        return "School " + s.getId().toString() + ": number " + Integer.toString(s.getNum());
    }

    private String objString(Object o) {
        if (o instanceof ChildModel) {
            return objString((ChildModel) o);
        } else if (o instanceof AddressModel) {
            return objString((AddressModel) o);
        } else if(o instanceof SchoolModel) {
            return objString((SchoolModel)o);
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

    private List<SchoolModel> getRecommendedSchools(AddressModel addressModel) {
        EntityManager em = emfactory.createEntityManager( );
        TypedQuery<SchoolModel> q = em.createQuery("SELECT s FROM School s WHERE s.address.district = :district", SchoolModel.class);
        q.setParameter("district", addressModel.getDistrict());
        List<SchoolModel> schools = q.getResultList();
        em.close();
        return schools;
    }

    private ChildModel addChild(String name, int age, List<ParentModel> parents, SchoolModel schoolModel) {
        EntityManager em = emfactory.createEntityManager( );
        em.getTransaction( ).begin( );
        ChildModel c = new ChildModel();
        for (ParentModel p: parents) {
            p.addChild(c);
        }
        c.setAge(age);
        c.setName(name);
        c.setParents(parents);
        c.setSchool(schoolModel);
        em.persist(c);
        em.getTransaction().commit();
        em.close();
        return c;
    }

    private ParentModel addParent(String name, AddressModel addressModel) {
        EntityManager em = emfactory.createEntityManager( );
        em.getTransaction( ).begin( );
        ParentModel p = new ParentModel();
        p.setName(name);
        p.setAddress(addressModel);
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        return p;
    }

    private ChildModel changeAddress(ChildModel childModel, AddressModel addressModel, SchoolModel schoolModel) {
        EntityManager em = emfactory.createEntityManager( );
        em.getTransaction().begin();
        childModel = em.find(ChildModel.class, childModel.getId());
        addressModel = em.find(AddressModel.class, addressModel.getId());
        schoolModel = em.find(SchoolModel.class, schoolModel.getId());
        List<ParentModel> ps = childModel.getParents();
        for (ParentModel p: ps) {
            p.setAddress(addressModel);
        }
        List<ChildModel> cs = ps.get(0).getChildren();
        for (ChildModel c: cs) {
            c.setSchool(schoolModel);
        }
        em.getTransaction().commit();
        em.close();
        return childModel;
    }
}
