package org.ashok.springboot.service;


import java.util.List;

import org.ashok.springboot.dto.SearchInvoiceDto;
import org.ashok.springboot.entity.Invoice;
import org.ashok.springboot.repository.InvoiceRepository;
import org.ashok.springboot.util.InvoiceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;

@Service
public class InvoiceService {

    
	private InvoiceRepository invoiceRepository;
	private InvoiceGenerator invoiceGenerator;
		
    
	@Autowired
	public InvoiceService(InvoiceRepository invoiceRepository, InvoiceGenerator invoiceGenerator) {
		this.invoiceRepository = invoiceRepository;
		this.invoiceGenerator = invoiceGenerator;
	}
	
	
    @PostConstruct
    public void init() {
        // TODO download from s3 and save locally
    }

    @PreDestroy
    public void shutdown() {
        // TODO actual deletion of PDFs
    }

    @Transactional
    public List<Invoice> findOrCreateInvoice(@Valid SearchInvoiceDto invoiceDto) {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
        if(invoiceDto.getMonth().equalsIgnoreCase("all")) {
        	return invoiceRepository.findByUserId(invoiceDto.getEmail());
        }
        else {
        	List<Invoice> invoices = invoiceRepository.findByUserIdAndMonth(invoiceDto.getEmail(), invoiceDto.getMonth());
        	if(invoices.isEmpty()) {
        		Invoice invoice = invoiceGenerator.generate(invoiceDto.getEmail(), invoiceDto.getMonth());
        		invoiceRepository.save(invoice);
        		invoices.add(invoice);
        	}
        	
        	return invoices;
        }
         
    }
       
}
