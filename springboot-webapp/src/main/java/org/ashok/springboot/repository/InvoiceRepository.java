package org.ashok.springboot.repository;

import java.util.List;

import org.ashok.springboot.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
	
	List<Invoice> findByUserIdAndMonth(String email, String month);
	List<Invoice> findByUserId(String email);

}
