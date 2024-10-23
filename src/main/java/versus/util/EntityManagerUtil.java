package versus.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
    private static final String PERSISTENCE_UNIT_NAME = "versus";
    private static EntityManagerFactory factory;

    static {
        try{
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }catch (Throwable ex){
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory(){
        return factory;
    }

    public static void closeEntityManagerFactory(){
        if(factory != null){
            factory.close();
        }
    }

}
