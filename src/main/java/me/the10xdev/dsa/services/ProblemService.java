package me.the10xdev.dsa.services;

import lombok.AllArgsConstructor;
import me.the10xdev.dsa.entities.Problem;
import me.the10xdev.dsa.repositories.ProblemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProblemService {

    private ProblemRepo problemRepo;

    public Optional<Problem> getProblem(String problemId) {
        return problemRepo.findById(problemId);
    }
}
