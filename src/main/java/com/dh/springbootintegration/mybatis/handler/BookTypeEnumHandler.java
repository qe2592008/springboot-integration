package com.dh.springbootintegration.mybatis.handler;

import com.dh.springbootintegration.mybatis.enums.BookType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookTypeEnumHandler extends BaseTypeHandler<BookType> {

    /**
     * 用于定义设置参数时，该如何把Java类型的参数转换为对应的数据库类型
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BookType parameter, JdbcType jdbcType) throws SQLException {
        int j = 0;
        for (BookType bookType : BookType.values()){
            if(bookType.equals(parameter)){
                ps.setString(i, j +"");
                return;
            }
            j++;
        }
    }

    /**
     * 用于定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的Java类型
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     */
    @Override
    public BookType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int j = Integer.valueOf(rs.getString(columnName));
        if(j >= BookType.values().length) {
            return null;
        }
        int i = 0;
        for(BookType bookType:BookType.values()){
            if(j == i){
                return bookType;
            }
            i++;
        }
        return null;
    }

    /**
     * 用于定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的Java类型
     * @param rs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public BookType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    /**
     * 用定义调用存储过程后，如何把数据库类型转换为对应的Java类型
     * @param cs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public BookType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
