package com.cliente.springboot.web.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import lombok.Data;

@Data

public class PageRender<T> {

	private String url;
	private Page<T> page;
	private int totalPages;
	private int numElementsperPage;
	private int currentPage;
	private List<PageItem> pages;
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean hasNext() {
		return page.hasNext();
	}
	
	public boolean hasPrevious() {
		return page.hasPrevious();
	}
	
	public PageRender(String url, Page<T> page) {
		
		this.url = url;
		this.page = page;
		int from, to;
		
		numElementsperPage 	= page.getSize();
		totalPages			= page.getTotalPages();
		currentPage			= page.getNumber()  + 1;
		pages				= new ArrayList<PageItem>();
		
		if(totalPages <=numElementsperPage) {
			from = 1;
			to = totalPages;
			
		}else {
			if(currentPage <= numElementsperPage/2) {
				from = 1;
				to = numElementsperPage;
			} else if (currentPage >= totalPages - numElementsperPage / 2) {
				from = totalPages - numElementsperPage + 1;
				to = numElementsperPage;
			}else {
				from = currentPage - numElementsperPage/2;
				to = numElementsperPage;
			}
		}
		
		for(int i=0; i < to; i++) {
			pages.add(new PageItem(from + i, currentPage == from + i));
		}
	}
}