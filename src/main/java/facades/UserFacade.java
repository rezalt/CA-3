package facades;

import security.IUserFacade;
import entity.User;
import java.util.List;
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
    
    public List<User> getUsers()
    {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM User u");
        return query.getResultList();
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
    
    public void deleteUser(String userName)
    {
        EntityManager em = getEntityManager();
        User u = em.find(User.class, userName);
        em.getTransaction().begin();
        em.remove(u);
        em.getTransaction().commit(); 
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
