package com.example.uploadingfiles.config;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import com.example.uploadingfiles.model.VideoInfo;
import com.example.uploadingfiles.repositories.VideoInfoRepository;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableTransactionManagement
public class AppConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
//        dataSourceBuilder.url("jdbc:postgresql://pg:5431/studs");
//        dataSourceBuilder.username("s265096");
//        dataSourceBuilder.password("vrm232");

        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/postgres");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("Postgreshehmda");

        return dataSourceBuilder.build();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true); //Auto creating scheme when true
        return hibernateJpaVendorAdapter;

    }

//    @Bean(name = "bitronixTransactionManager")
//    @DependsOn
//    public BitronixTransactionManager
//    bitronixTransactionManager() throws Throwable {
//        BitronixTransactionManager bitronixTransactionManager =
//                TransactionManagerServices.getTransactionManager();
//        bitronixTransactionManager.setTransactionTimeout(10000);
//        CustomJtaPlatform
//                .setUserTransaction(bitronixTransactionManager);
//        CustomJtaPlatform
//                .setTransactionManager(bitronixTransactionManager);
//        return bitronixTransactionManager;
//    }
//
//    @Bean(name = "transactionManager")
//    @DependsOn({"bitronixTransactionManager"})
//    public PlatformTransactionManager
//    transactionManager(TransactionManager
//                               bitronixTransactionManager) throws Throwable {
//        return
//                new JtaTransactionManager(bitronixTransactionManager);
//    }

}
