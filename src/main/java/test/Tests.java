package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.parsing.Parser;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import org.eclipse.persistence.sessions.server.Server;
//import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author jakob
 */
public class Tests
{
    static Server server;
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("seedPU");
    
    public Tests()
    {
        baseURI="http://localhost:8082";
        defaultParser = Parser.JSON;
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception
    {
        server = new Server(8082);
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        
    }
    
    @Before
    public void setUp()
    {
        
    }
    
    @After
    public void tearDown()
    {
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
