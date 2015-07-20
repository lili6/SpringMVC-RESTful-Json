package com.xg.springmvc.controller;

import com.xg.springmvc.model.BookListVO;
import com.xg.springmvc.model.BookVO;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * spring实现基于RESTful风格架构
 * 
 */
@Controller
public class HomeController {
	//logger 日志
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	// URI:	http://localhost:8080/SpringMVC-RESTful-Json/hi
	@RequestMapping(value = "/hi", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String hello() {
		logger.info("测试hi");
		return "Hello World !!!";
	}
	
	// URI:	http://localhost:8080/SpringMVC-RESTful-Json/say/hello world
	@RequestMapping(value = "/say/{msg}", produces = "application/json;charset=UTF-8")
	public @ResponseBody String say(@PathVariable("msg") String msg) {
		return "{\"msg\":\"you say:'" + msg + "'\"}";
	}
	
	// URI: http://localhost:8080/SpringMVC-RESTful-Json/book/1/Complete Code/Steve McConnell/10/2013
	@RequestMapping(value = "/book/{id}/{name}/{author}/{price}/{time}", method = RequestMethod.GET)
	public @ResponseBody BookVO getProductInfo(BookVO book, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return book;
	}
	
//	@RequestMapping(value="/book",method=RequestMethod.POST)
//    public BookVO insertBook(BookVO book, HttpServletRequest request,HttpServletResponse response) throws Exception {
//        return book;
//         
//    }
	
	// URI:	http://localhost:8080/SpringMVC-RESTful-Json/book/1
	//GET
	@RequestMapping(value = "/book/{id:\\d+}", method = RequestMethod.GET)
	public @ResponseBody BookVO getBook(@PathVariable("id") int id) {
		logger.info("获取书本信息id = " + id);
		BookVO book = new BookVO();
		book.setId(id);
		book.setName("深入理解Java虚拟机－JVM高级特性与最佳实践");
		book.setAuthor("周志明");
		book.setPrice(79);
		book.setTime("2013");
		return book;
	}
	//DELETE
	@RequestMapping(value = "/book/{id:\\d+}", method = RequestMethod.DELETE)
	public @ResponseBody
	Object deleteBook(@PathVariable("id") int id) {
		logger.info("删除书本信息id=" + id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "删除书本信息成功");
		return jsonObject;
	}
	//POST
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public @ResponseBody Object addBook(BookVO book) {
		logger.info("添加信息成功id=" + book.getId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "添加信息成功！");
		return jsonObject;
	}
	//PUT
	@RequestMapping(value = "/book", method = RequestMethod.PUT)
	public @ResponseBody Object updateBook(BookVO book) {
		logger.info("更新书本信息id=" + book.getId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "更新书本信息成功");
		return jsonObject;
	}
	//PATCH
	@RequestMapping(value = "/book", method = RequestMethod.PATCH)
	public @ResponseBody
	List<BookVO> listBook(@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		logger.info("查询书本name like " + name);
		List<BookVO> books = new ArrayList<BookVO>();
		BookVO book = new BookVO();
		book.setId(1);
		book.setName("深入理解Java虚拟机－JVM高级特性与最佳实践");
		book.setAuthor("周志明");
		book.setPrice(79);
		book.setTime("2013");
		books.add(book);
		return books;
	}

	
	private BookListVO getBookList() {
		BookListVO bookList = new BookListVO();
		for (int i = 0; i < 10; i++) {
			BookVO book = new BookVO();
			book.setId(i);
			book.setName("BOOK" + i);
			book.setAuthor("Author" + i);
			book.setPrice(i * 10);
			book.setTime("201" + i + "年");
			bookList.getBooks().add(book);
		}
		return bookList;		
	}
	
	//URI: http://localhost:8080/SpringMVC-RESTful-Json/displaybooks
	@RequestMapping(value = "/displaybooks", method = RequestMethod.GET)
	public String displayBookList(Model model) {
		model.addAttribute("books", getBookList().getBooks());
		return "booksListDisplay";
	}
	
	//URI: http://localhost:8080/SpringMVC-RESTful-Json/books.json
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public @ResponseBody List<BookVO> listBook() {
		return getBookList().getBooks();
	}
	
	//URI: http://localhost:8080/SpringMVC-RESTful-Json/books/1.json
	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public @ResponseBody BookVO getBook(@PathVariable(value="id") Integer id) {
		List<BookVO> books = getBookList().getBooks();
		if(id >= 0 && id < books.size()) {
			return books.get(id);
		}
		return null;
	}
	
}
