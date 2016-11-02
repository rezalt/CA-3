package facades;

import security.IUserFacade;
import entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import security.IUser;
import security.PasswordStorage;

public class UserFacade implements IUserFacade
{

    EntityManagerFactory emf;

    /*When implementing your own database for this seed, you should NOT touch any of the classes in the security folder
    Make sure your new facade implements IUserFacade and keeps the name UserFacade, and that your Entity User class implements 
    IUser interface, then security should work "out of the box" with users and roles stored in your database */
    public UserFacade()
    {
 try
        {
            EntityManager em = getEntityManager();

            //Test Users
            User user = new User("user", PasswordStorage.createHash("test"));
            user.addRole("User");

            User admin = new User("admin", PasswordStorage.createHash("test"));
            admin.addRole("Admin");

            User both = new User("user_admin", PasswordStorage.createHash("test"));
            both.addRole("User");
            both.addRole("Admin");

            try
            {
                em.getTransaction().begin();
                em.persist(user);
                em.persist(admin);
                em.persist(both);
                em.getTransaction().commit();
            }
            finally
            {
                if (em != null)
                {
                    em.close();
                }
            }
        }
        catch (PasswordStorage.CannotPerformOperationException ex)
        {
            System.out.println("problem when logging in" + ex);
        }
    }

    @Override
    public IUser getUserByUserId(String id)
    {
        EntityManager em = getEntityManager();

        Query query = em.createQuery("SELECT u FROM User u WHERE u.userName = :username", User.class);
        query.setParameter("username", id);
        List<User> result = query.getResultList();
        User user = result.get(0);

        return user;

    }

    public User addUser(User u)
    {

            EntityManager em = getEntityManager();
 
            u.addRole("User");

            try
            {

                em.getTransaction().begin();
                em.persist(u);
                em.getTransaction().commit();
                return u;
                
            }
            finally
            {
                if (em != null)
                {
                    em.close();
                }
            }
        
    }

    /*
  Return the Roles if users could be authenticated, otherwise null
     */
    @Override
    public List<String> authenticateUser(String username, String password)
    {

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM User u WHERE u.userName = :username", User.class);
        query.setParameter("username", username);
        List<User> result = query.getResultList();
        User user = result.get(0);
        try
        {
            if (PasswordStorage.verifyPassword(password, user.getPassword()))
            {
                return user.getRolesAsStrings();
            }
            else
            {
                return null;
            }
        }
        catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException ex)
        {
            System.out.println("problem when logging in" + ex);
        }
        //  IUser user = users.get(userName);
        //  return user != null && user.getPassword().equals(password) ? user.getRolesAsStrings() : null;
        return null;
    }

    private EntityManager getEntityManager()
    {
        if (emf == null)
        {
            emf = Persistence.createEntityManagerFactory("seedPU");
        }
        return emf.createEntityManager();
    }

}
