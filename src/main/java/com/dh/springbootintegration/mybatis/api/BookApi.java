package com.dh.springbootintegration.mybatis.api;

import com.dh.springbootintegration.mybatis.entity.Book;
import com.dh.springbootintegration.mybatis.page.MyPage;
import com.dh.springbootintegration.mybatis.service.BookService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@Api(description = "书籍接口")
@Log4j2
@CrossOrigin(maxAge = 3600)
public class BookApi {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/addBook", method = RequestMethod.PUT)
    @ApiOperation(value = "添加书籍", notes = "添加一本新书籍", httpMethod = "PUT")
    public ResponseEntity<Book> addBook(final Book book){
        return bookService.addBook(book);
    }

    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    @ApiOperation(value = "更新书籍", notes = "根据条件更新书籍信息", httpMethod = "POST")
    public ResponseEntity<Integer> updateBook(final Book book){
        return bookService.updateBook(book);
    }

    @RequestMapping(value = "/deleteBook", method = RequestMethod.DELETE)
    @ApiOperation(value = "获取一本书籍", notes = "根据ID获取书籍", httpMethod = "DELETE")
    public ResponseEntity<Integer> deleteBook(final int bookId){
        return bookService.deleteBook(bookId);
    }

    @CrossOrigin("http://localhost:8081")
    @RequestMapping(value = "/getBook", method = RequestMethod.GET)
    @ApiOperation(value = "获取一本书籍", notes = "根据ID获取书籍", httpMethod = "GET")
    public ResponseEntity<Book> getBook(final int bookId){
        return bookService.getBook(bookId);
    }

    @RequestMapping(value = "/getBooks", method = RequestMethod.GET)
    @ApiOperation(value = "获取书籍", notes = "根据条件获取书籍", httpMethod = "GET")
    public ResponseEntity<List<Book>> getBooks(final Book book){
        return bookService.getBooks(book);
    }

    @RequestMapping(value = "/getBooksByPageHelper", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取书籍", notes = "通过PageHelper分页获取书籍", httpMethod = "GET")
    public ResponseEntity<PageInfo<Book>> getBooksByPageHelper(final int pageId, final int pageNum){
        return bookService.getBooksByPageHelper(pageId, pageNum);
    }

    @RequestMapping(value = "/getBooksPageByRowBounds", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取书籍", notes = "通过RowBounds分页获取书籍", httpMethod = "GET")
    public ResponseEntity<MyPage<Book>> getBooksPageByRowBounds(final int pageId, final int pageNum){
        return bookService.getBooksByRowBounds(pageId, pageNum);
    }

}
