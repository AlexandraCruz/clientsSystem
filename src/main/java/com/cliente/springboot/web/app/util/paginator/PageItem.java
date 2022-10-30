package com.cliente.springboot.web.app.util.paginator;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PageItem {
	
	public PageItem(int pageNumber, boolean current) {
		
		this.pageNumber = pageNumber;
		this.current 	= current;
	}
	private int pageNumber;
	private boolean current;

}
