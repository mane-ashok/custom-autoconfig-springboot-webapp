package org.ashok.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="invoices")
public class Invoice {

	@Id
    private Long id;
	
	@Column(name="user_id")
    private String userId;
	
	@Column(name = "pdf_url")
    private String pdfUrl;

    private Integer amount;
    
    @Column(name = "invoice_month")
    private String month;

}
