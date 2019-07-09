package com.geekykel.crmrestapi;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@SpringBootApplication
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
@EnableTransactionManagement
public class CrmRestApiApplication {

    private Logger logger = Logger.getLogger(getClass().getName());

    public static void main(String[] args) {
        SpringApplication.run(CrmRestApiApplication.class, args);
    }


    @Autowired
    private Environment env;

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsps/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.geekykel.crmrestapi.entities"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.show-sql", env.getProperty("hibernate.show-sql"));
        //hibernateProperties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
        //hibernateProperties.setProperty("hibernate.ddl-auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.temp.use_jdbc_metadata_defaults",
                env.getProperty("hibernate.temp.use_jdbc_metadata_defaults"));
        //hibernateProperties.setProperty("current_session_context_class",
        //env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
        //hibernateProperties.setProperty("hibernate.jdbc.lob.non_contextual_creation",
        //env.getProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation"));

        return hibernateProperties;

    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactoryBean().getObject());
        return transactionManager;
    }

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        comboPooledDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        try {
            comboPooledDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        comboPooledDataSource.setUser(env.getProperty("jdbc.user"));
        comboPooledDataSource.setPassword(env.getProperty("jdbc.password"));

        // set connection pool props
        //comboPooledDataSource.setInitialPoolSize(Integer.parseInt("5"));
        comboPooledDataSource.setInitialPoolSize(
                getIntProperty("connection.pool.initialPoolSize"));

        comboPooledDataSource.setMinPoolSize(
                getIntProperty("connection.pool.minPoolSize"));

        comboPooledDataSource.setMaxPoolSize(
                getIntProperty("connection.pool.maxPoolSize"));

        comboPooledDataSource.setMaxIdleTime(
                getIntProperty("connection.pool.maxIdleTime"));

        return comboPooledDataSource;
    }

    private int getIntProperty(String propName) {

        String propVal = env.getProperty(propName);

        // now convert to int
        int intPropVal = Integer.parseInt(propVal);

        return intPropVal;
    }


}
