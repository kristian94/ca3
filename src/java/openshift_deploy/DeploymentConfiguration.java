package openshift_deploy;

import entity.Role;
import entity.User;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import security.PasswordStorage;

@WebListener
public class DeploymentConfiguration implements ServletContextListener {

    public static String PU_NAME = "PU_OPENSHIFT";
//    public static String PU_NAME = "PU-Local";
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Running ContextInit");
        //If we are testing, then this:
        if (sce.getServletContext().getInitParameter("testEnv") != null) {
            PU_NAME = "PU_TEST";
        }
        Map<String, String> env = System.getenv();
        //If we are running in the OPENSHIFT environment change the pu-name 
        if (!env.keySet().contains("OPENSHIFT_MYSQL_DB_HOST")) {
            PU_NAME = "PU-Local";
        }
        try {
            ServletContext context = sce.getServletContext();
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
            EntityManager em = emf.createEntityManager();

            //This flag is set in Web.xml -- Make sure to disable for a REAL system
            boolean makeTestUsers = context.getInitParameter("makeTestUsers").toLowerCase().equals("true");
//      boolean makeTestUsers = false;
            if (!makeTestUsers
              || (em.find(User.class, "user") != null && em.find(User.class, "admin") != null)) {
        return;
      }
            Role userRole = new Role("User");
            Role adminRole = new Role("Admin");
            

            User user = new User("user", PasswordStorage.createHash("test"));
            User admin = new User("admin", PasswordStorage.createHash("test"));
            user.AddRole(userRole);
            admin.AddRole(adminRole);

            try {
                em.getTransaction().begin();
                em.persist(userRole);
                em.persist(adminRole);

                em.persist(user);
                em.persist(admin);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        } catch (PasswordStorage.CannotPerformOperationException ex) {
            Logger.getLogger(DeploymentConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("ContextInit Done");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
