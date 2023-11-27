package com.emp;

import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication(scanBasePackages = "com.emp")
public class EmpApplication {

    @Autowired
    Environment env;

    public static void main(String[] args) {
        SpringApplication.run(EmpApplication.class, args);
    }

//    @Bean(value = "dataSource")
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//        dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
//        dataSource.setUrl(env.getRequiredProperty("db.url"));
//        dataSource.setUsername(env.getRequiredProperty("db.username"));
//        dataSource.setPassword(env.getRequiredProperty("db.password"));
//        return dataSource;
//
//    }
//
//    @Bean(value = "sessionFactory")
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        sessionFactory.setPackagesToScan(new String[]{"com.hb.app.entity"});
//        return sessionFactory;
//    }
//
//    @Bean
//    Properties hibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", env.getRequiredProperty("db.jpa.hibernate.dialect"));
//        properties.put("hibernate.show_sql", env.getRequiredProperty("db.jpa.hibernate.show-sql"));
//        properties.put("hibernate.format_sql", env.getRequiredProperty("db.jpa.hibernate.format-sql"));
//        properties.put("hibernate.generate.ddl", env.getRequiredProperty("db.jpa.generate-ddl"));
//        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("db.jpa.hibernate.ddl-auto"));
//        return properties;
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setDatabasePlatform(env.getRequiredProperty("db.jpa.hibernate.dialect"));
//        vendorAdapter.setGenerateDdl(Boolean.parseBoolean(env.getRequiredProperty("db.jpa.hibernate.generate-ddl")));
//        vendorAdapter.setShowSql(Boolean.parseBoolean(env.getRequiredProperty("db.jpa.hibernate.show-sql")));
//        return vendorAdapter;
//    }
//
//    @Bean
//    public JpaDialect jpaDialect() {
//        return new HibernateJpaDialect();
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory);
//        jpaTransactionManager.setDataSource(dataSource());
//        jpaTransactionManager.setJpaDialect(jpaDialect());
//        return jpaTransactionManager;
//    }
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }

}
