package com.example.gs_java.repository;

import com.example.gs_java.model.Insight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsightRepository extends JpaRepository<Insight, Long> {

}