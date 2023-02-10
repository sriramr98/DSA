package me.the10xdev.dsa.repositories;

import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import me.the10xdev.dsa.entities.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepo extends JpaRepository<Problem, String> {
}
