package com.springbootrestgraphjpa.repository;

import com.springbootrestgraphjpa.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
