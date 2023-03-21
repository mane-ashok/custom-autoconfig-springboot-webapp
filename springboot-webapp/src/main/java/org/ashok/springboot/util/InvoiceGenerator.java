package org.ashok.springboot.util;

import org.ashok.springboot.entity.Invoice;

public interface InvoiceGenerator {
	
	Invoice generate(String userId, String month);
}
