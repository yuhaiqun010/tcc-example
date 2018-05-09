package cn.com.yusys.yusp.example.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import io.github.jhipster.config.JHipsterConstants;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.bytesoft.bytejta.supports.jdbc.LocalXADataSource;
import org.h2.tools.Server;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@MapperScan("cn.com.yusys.yusp.example.repository.mapper")
public class DatabaseConfiguration {

    public DatabaseConfiguration() {
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        LocalXADataSource dataSource = new LocalXADataSource();
        dataSource.setDataSource(this.invokeGetDataSource());
        return dataSource;
    }

    public DataSource invokeGetDataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        bds.setUrl("jdbc:oracle:thin:@192.168.251.158:1521:ycorcl");
        bds.setUsername("yusp");
        bds.setPassword("yusp");
        bds.setMaxTotal(50);
        bds.setInitialSize(2);
        bds.setMaxWaitMillis(60000);
        bds.setMinIdle(6);
        bds.setLogAbandoned(true);
        bds.setRemoveAbandonedOnBorrow(true);
        bds.setRemoveAbandonedOnMaintenance(true);
        bds.setRemoveAbandonedTimeout(1800);
        bds.setTestWhileIdle(true);
        bds.setTestOnBorrow(false);
        bds.setTestOnReturn(false);
        bds.setValidationQuery(" SELECT 1 FROM DUAL  ");
        bds.setValidationQueryTimeout(1);
        bds.setTimeBetweenEvictionRunsMillis(30000);
        bds.setNumTestsPerEvictionRun(20);
        return bds;
    }
    /**
     * Open the TCP port for the H2 database, so it is available remotely.
     *
     * @return the H2 database TCP server
     * @throws SQLException if the server failed to start
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
    public Server h2TCPServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers");
    }

    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }
    
//    @Bean
//    public DatabaseIdProvider getDatabaseIdProvider(){
//        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
//        Properties properties = new Properties();
//        properties.setProperty("Oracle","oracle");
//        properties.setProperty("MySQL","mysql");
//        databaseIdProvider.setProperties(properties);
//        return databaseIdProvider;
//    }

}
