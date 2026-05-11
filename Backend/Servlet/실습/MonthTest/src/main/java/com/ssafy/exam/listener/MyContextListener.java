package com.ssafy.exam.listener;


import com.ssafy.exam.model.dao.RestaurantDaoImpl;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyContextListener
 * 이 파일에는 수정할 부분이 없습니다.
 */
@WebListener
public class MyContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized() called");
        RestaurantDaoImpl.getInstance().load(); // 초기 정보 로딩
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed() called");
        RestaurantDaoImpl.getInstance().save(); // 정보 저장
    }

}
