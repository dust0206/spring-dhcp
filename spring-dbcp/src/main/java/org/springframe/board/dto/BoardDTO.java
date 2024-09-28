package org.springframe.board.dto;

import lombok.Data;

@Data
public class BoardDTO {
	
	private int seq;    
	private String title; 
	private String contents; 
	private String username;  
	private String passwd;  
	private String create_date;          
	private String modify_date;          
	private int hits;    

}
