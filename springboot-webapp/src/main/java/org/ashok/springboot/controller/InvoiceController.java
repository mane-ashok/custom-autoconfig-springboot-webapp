package org.ashok.springboot.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.ashok.springboot.dto.SearchInvoiceDto;
import org.ashok.springboot.entity.Invoice;
import org.ashok.springboot.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    
    @GetMapping(path = "/invoices")
    public String invoices(Model model) {
    	System.out.println("Entering invoices controller method");
    	SearchInvoiceDto searchInvoiceDto = new SearchInvoiceDto();
    	model.addAttribute("searchInvoice", searchInvoiceDto);
    	return "searchInvoice.html";
    	//return "invoices.html";
    	
    }
    
    @PostMapping("/searchInvoice")
    public String searchInvoice(@Valid @ModelAttribute("searchInvoice") SearchInvoiceDto invoiceDto,
                               BindingResult result,
                               Model model){
        System.out.println("Entering searchInvoice controller method");
    	
        if (result.hasErrors()) {
            model.addAttribute("searchInvoice", invoiceDto);
            System.out.println("searchInvoice found errors, so returning to searchInvoice.html");
            return "searchInvoice.html";
        }
        
    	List<Invoice> invoices = invoiceService.findOrCreateInvoice(invoiceDto);
    	System.out.println("invoices.size() =" + invoices.size());
        
                
       
        
        model.addAttribute("invoices", invoices);
        return "invoices.html";
    }
    
    @GetMapping("/download/{fileName}")
    @ResponseBody
    public void downloadInvoice(@PathVariable String fileName, HttpServletResponse response) {
    	
  		Path invoiceFile = Paths.get(System.getProperty("user.home"),"invoices", fileName);
	
		if(Files.exists(invoiceFile)) {
			
			response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try {
            	Files.copy(invoiceFile, response.getOutputStream());
                response.getOutputStream().flush();
			               
    		}
            catch (Exception e) {
    			throw new RuntimeException(e);
    		}
    	}
		else {
			System.out.println("File not found");
		}
    	
    }
   
}
