package com.tamkoc.product.compendium.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Gokhan Tamkoc on 08.10.2016.
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.tamkoc.product.compendium")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.tamkoc.product.compendium.repository")
public class WebAppConfiguration extends WebMvcConfigurerAdapter {
    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";

    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.tamkoc.product.compendium";

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        //DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setJdbcUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        entityManagerFactoryBean.setJpaProperties(produceHibernateProperties());
        entityManagerFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
        return entityManagerFactoryBean;
    }

    private Properties produceHibernateProperties() {
        Properties hibernateProperties = new Properties();

        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        hibernateProperties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));

        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        hibernateProperties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));

        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        hibernateProperties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));

        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        hibernateProperties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));

        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements.
        //Setting to DefaultNamingStrategy is better, because when it is set to ImprovedNamingStrategy hibernate does not work on SQLServer
        hibernateProperties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));

        return hibernateProperties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
