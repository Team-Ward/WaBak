package com.woowacourse.woowaquiz.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@Configuration
@Profile("local")
public class H2ServerConfiguration {

    private static Server server;

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource dataSource() throws SQLException {
        if (server == null) {
            server = Server.createTcpServer("-tcp",
                    "-tcpPort",
                    "9092",
                    "-tcpAllowOthers",
                    "-ifNotExists"
            ).start();
            log.info("local h2 tcp server on.");
        }
        return new HikariDataSource();
    }
}