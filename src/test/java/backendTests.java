
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
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import rest.ApplicationConfig;
import entity.User;

/**
 *
 * @author jakob
 */
public class backendTests
{

    static Server server;
    public EntityManagerFactory emf;

    public backendTests()
    {
        baseURI = "http://localhost:8089";
        defaultParser = Parser.JSON;
        basePath = "/api";

        deploy.DeploymentConfiguration.setTestModeOn();
        emf = Persistence.createEntityManagerFactory(deploy.DeploymentConfiguration.PU_NAME);
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        server = new Server(8089);
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

    // Tests adding a user.
    @Test
    public void test1()
    {

        EntityManager em = emf.createEntityManager();
        UserFacade uf = (UserFacade) security.UserFacadeFactory.getInstance();
        uf.deleteUser("to");
        User usr1 = new User("to", "Test");
        uf.addUser(usr1);
        String username = usr1.getUserName();
        User usr2 = em.find(User.class, username);
        System.out.println("AAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHH!!!!!!!!!!!!!!!");
        assertTrue(usr2.getUserName().equals("to"));
    }

    // Tests a wrong user login event
    @Test
    public void test2()
    {
        given().
                contentType("application/json").
                body("{'username':'jegerikkeenbruger','password':'test'}").
                when().
                post("/login").
                then().
                statusCode(401);
    }

    // Tests a correct user login event
    @Test
    public void test3()
    {
      deploy.DeploymentConfiguration.setDevModeOn();
        emf = Persistence.createEntityManagerFactory(deploy.DeploymentConfiguration.PU_NAME);
        given().
                contentType("application/json").
                body("{'username':'user','password':'test'}").
                when().
                get("/login").
                then().
                statusCode(200);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
