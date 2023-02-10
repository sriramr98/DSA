package me.the10xdev.dsa.configurations;

import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
        basePackages = {
                "io.hypersistence.utils.spring.repository",
                "me.the10xdev.dsa.entities"
        },
        repositoryBaseClass = BaseJpaRepositoryImpl.class
)
public class JPAConfig {
}
