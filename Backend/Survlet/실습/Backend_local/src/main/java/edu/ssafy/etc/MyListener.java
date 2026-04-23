package edu.ssafy.etc;

import edu.ssafy.repository.MemberRepository;
import edu.ssafy.repository.MemberRepositoryImpl;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyListener
 *
 */
@WebListener
public class MyListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyListener() {
        	
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    //상태를 알 수 있는것이다
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println("ServletContextListener");
    	try {
    		MemberRepositoryImpl.getInstance().load();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	try {
    		MemberRepositoryImpl.getInstance().save();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
	
}
