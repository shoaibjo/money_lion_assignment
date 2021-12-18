package com.moneylion.assignment.authorization.repository;

import com.moneylion.assignment.authorization.model.dao.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Authorization, Integer> {
    List<Authorization> findByEmail(String email);
    @Query("SELECT u FROM Authorization u WHERE u.email = ?1 and u.feature = ?2")
    Authorization findFeatureByEmail(String email, String feature);
}
