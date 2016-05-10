package org.bildit.lingua.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages="org.bildit.lingua.repository") // scan for "repository" package
                                                             // default name of implementation of the interface
                                                             // must be: InterfaceNameImpl (suffix has to be "Impl")
public class JpaConfiguration {

}
