package com.xg.springmvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookListVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<BookVO> books = new ArrayList<BookVO>();
	public List<BookVO> getBooks() {
		return books;
	}
	public void setBooks(List<BookVO> books) {
		this.books = books;
	}
	
}
