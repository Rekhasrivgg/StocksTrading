package com.app.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.stock.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	boolean existsByPanno(String panno);
	
	boolean existsByBankAcntNo(String bankAcntNo);
	
}
