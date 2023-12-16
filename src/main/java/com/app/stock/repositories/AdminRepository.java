package com.app.stock.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.stock.entities.AdminEntity;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

	@Query("SELECT a FROM AdminEntity a WHERE a.adminEmail = :adminEmail")
	Optional<AdminEntity> findByAdminEmail(String adminEmail);

	@Query("SELECT COUNT(a) > 0 FROM AdminEntity a WHERE a.adminEmail = :adminEmail")
	boolean existsByAdminEmail(String adminEmail);

	@Query("SELECT COUNT(a) > 0 FROM AdminEntity a WHERE a.adminMobileno = :adminMobileno")
	boolean existsByAdminMobileno(@Param("adminMobileno") String adminMobileno);
}