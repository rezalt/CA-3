/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deploy;

import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 *
 * @author josephawwal
 */
public class DeploymentConfiguration implements ServletContextListener {
    
    
    public static String PU_NAME = "pu_dev";
    
    
    @Override
    public void contextDestroyed(ServletContextEvent sce){
        
    }
    
    public static void setTestModeOn(){
        PU_NAME = "pu_test";
    }
    
    public static void setDevModeOn(){
        PU_NAME = "pu_dev";
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
          Map<String, String> env = System.getenv();
        if (env.keySet().contains("OPENSHIFT_MYSQL_DB_HOST")){
            PU_NAME = "pu_openshift";
        }
    }
}
