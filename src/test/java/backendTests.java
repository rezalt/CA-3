
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.parsing.Parser;
import facades.UserFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import org.eclipse.persistence.sessions.server.Server;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import rest.ApplicationConfig;
import entity.User;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author jakob
 */
public class backendTests
{
    static Server server;
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("seedPU");
    
    public backendTests()
    {
        baseURI="http://localhost:8082";
        defaultParser = Parser.JSON;
        basePath ="/api";
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception
    {
        server = new Server(8082);
        ServletHolder servletHolder = new ServletHolder(org.glassfish.jersey.servlet.ServletContainer.class);
        servletHolder.setInitParameter("javax.ws.rs.Application", ApplicationConfig.class.getName());
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        contextHandler.addServlet(servletHolder, "/api/*");
        server.setHandler(contextHandler);
        server.start();
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception
    {
        server.stop();
        server.join();
    }
    
    @Before
    public void setUp()
    {
        
    }
    
    @After
    public void tearDown()
    {
        
    }

    @Test
    public void test1()
    {
        EntityManager em = emf.createEntityManager();
        UserFacade uf = (UserFacade) security.UserFacadeFactory.getInstance();
        User usr1 = new User("John","Arne");
        int id = usr1.getId();
        uf.addUser(usr1);
        assertTrue(em.find(User.class, id).getUserName().equals("John"));
    }
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
