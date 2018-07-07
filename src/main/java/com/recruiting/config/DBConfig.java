package com.recruiting.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Marta Ginosyan
 */

@Configuration
@EnableTransactionManagement
@ComponentScan("com.recruiting")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.recruiting")
public class DBConfig {

    @Bean
    public DataSource dataSource() {

        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setPoolName("springHikariCP");
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost/hr");
        hikariConfig.setMaximumPoolSize(35);
        hikariConfig.setMinimumIdle(10);

        final Properties dataSourceProperties = new Properties();
        dataSourceProperties.setProperty("url", "jdbc:postgresql://localhost/hr");
        dataSourceProperties.setProperty("user", "postgres");
        dataSourceProperties.setProperty("password", "postgres");

        hikariConfig.setDataSourceProperties(dataSourceProperties);
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        final HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        hikariDataSource.getHikariPoolMXBean().softEvictConnections();
        return hikariDataSource;
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }

    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter() {

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();

        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setPrepareConnection(true);
        hibernateJpaVendorAdapter.setShowSql(true);

        return hibernateJpaVendorAdapter;
    }


    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.recruiting");
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.globally_quoted_identifiers", true);
//        properties.put("hibernate.cache.use_query_cache", true);
//        properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.jcache.JCacheRegionFactory");
//        properties.put("hibernate.javax.cache.provider","rg.ehcache.jsr107.EhcacheCachingProvider");
//        properties.put("hibernate.javax.cache.uri", "ehCacheUri");

        localContainerEntityManagerFactoryBean.setJpaProperties(properties);
        localContainerEntityManagerFactoryBean.afterPropertiesSet();

        return localContainerEntityManagerFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }


}
