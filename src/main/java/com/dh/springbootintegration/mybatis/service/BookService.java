package com.dh.springbootintegration.mybatis.service;

import com.dh.springbootintegration.mybatis.entity.Book;
import com.dh.springbootintegration.mybatis.page.MyPage;
import com.dh.springbootintegration.mybatis.repository.BookRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<Book> addBook(final Book book) {
        int num = bookRepository.addBook(book);
        return ResponseEntity.ok(book);
    }

    public ResponseEntity<Integer> updateBook(final Book book){
        return ResponseEntity.ok(bookRepository.updateBook(book));
    }

    public ResponseEntity<Integer> deleteBook(final int bookId){
        return ResponseEntity.ok(bookRepository.deleteBook(bookId));
    }

    public ResponseEntity<Book> getBook(final int bookId) {
        Book book = bookRepository.getBook(bookId);
        return ResponseEntity.ok(book);
    }

    public ResponseEntity<List<Book>> getBooks(final Book book){
        return ResponseEntity.ok(bookRepository.getBooks(book));
    }

    // 使用PageHelper分页插件实现分页
    public ResponseEntity<PageInfo<Book>> getBooksByPageHelper(int pageId, int pageSize) {
        PageHelper.startPage(pageId, pageSize);
        List<Book> books = bookRepository.getBooks(Book.builder().build());
        int totalNum  = bookRepository.count(Book.builder().build());
        PageInfo<Book> page = new PageInfo<>();
        page.setPageNum(pageId);
        page.setPageSize(pageSize);
        page.setSize(totalNum);
        page.setList(books);
        return ResponseEntity.ok(page);
    }

    // 使用RowBounds实现分页
    public ResponseEntity<MyPage<Book>> getBooksByRowBounds(int pageId,int pageSize){
        MyPage<Book> myPage = new MyPage<>();
        myPage.setPageId(pageId);
        myPage.setPageSize(pageSize);
        List<Book> books = bookRepository.getBooks(Book.builder().build(), new RowBounds(pageId,pageSize));
        int totalNum = bookRepository.count(Book.builder().build());
        myPage.setBody(books);
        myPage.setTotalNum(totalNum);
        return ResponseEntity.ok(myPage);
    }

}
