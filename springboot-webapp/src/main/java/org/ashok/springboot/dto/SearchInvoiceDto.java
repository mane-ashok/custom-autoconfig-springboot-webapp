package org.ashok.springboot.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchInvoiceDto
{
    
	private String month;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    
   private List<String> monthOptions = List.of("jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec");
      
}
