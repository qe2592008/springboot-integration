package com.dh.springbootintegration.mybatis.repository;

import com.dh.springbootintegration.mybatis.entity.Book;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BookRepository {
    int addBook(Book book);
    int updateBook(Book book);
    int deleteBook(int id);
    Book getBook(int id);
    List<Book> getBooks(Book book);
    int count(Book book);
    List<Book> getBooks(Book book, RowBounds rowBounds);
}
