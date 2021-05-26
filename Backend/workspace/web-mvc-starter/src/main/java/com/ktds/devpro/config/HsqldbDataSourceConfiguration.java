package com.ktds.devpro.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 *
 * DataSource Config 관련 설정
 * <p>
 *
 * <pre>
 * 개정이력(Modification Information)·
 * 수정일   수정자    수정내용
 * ------------------------------------
 * 2017. 3. 16.   kt ds     최초작성
 * </pre>
 *
 * @author kt ds A.CoE(blue.park@kt.com)
 * @since 2017. 3. 16.
 * @version 1.0.0
 * @see
 *
 */
@Configuration
@EnableConfigurationProperties
@EnableTransactionManagement
@MapperScan(basePackages="com.ktds.devpro.other", sqlSessionFactoryRef="hsqldbSqlSessionFactory")
public class HsqldbDataSourceConfiguration {

    @Bean(name="hsqldbDataSource")
    @ConfigurationProperties(prefix="spring.datasource.hikari.hsql")
    public DataSource hsqldbDatasource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "hsqldbSqlSessionFactory")
    public SqlSessionFactory hsqldbSqlSessionFactory(@Qualifier("hsqldbDataSource") DataSource hsqldbDataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(hsqldbDataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/**/other/**/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "hsqldbSqlSessionTemplate", destroyMethod = "clearCache")
    public SqlSessionTemplate hsqldbSqlSessionTemplate(SqlSessionFactory hsqldbSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(hsqldbSqlSessionFactory);
    }
}