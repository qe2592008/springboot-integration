package com.dh.springbootintegration.mybatis.entity;

import com.dh.springbootintegration.mybatis.enums.BookType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@ApiModel("书籍模型")
public class Book {

    @ApiModelProperty(value = "书籍ID", notes = "书籍ID",example = "1")
    private Integer bookId;

    @ApiModelProperty(value = "书籍页数", notes = "书籍页数",example = "100")
    private Integer pageNum;

    @ApiModelProperty(value = "书籍名称", notes = "书籍名称",example = "Java编程思想")
    private String bookName;

    @ApiModelProperty(value = "书籍类型", notes = "书籍类型",hidden = false)
    private BookType bookType;

    @ApiModelProperty(value = "书籍简介")
    private String bookDesc;

    @ApiModelProperty(value = "书籍价格")
    private Double bookPrice;

    @ApiModelProperty(value = "创建时间",hidden = true)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间",hidden = true)
    private LocalDateTime modifyTime;

}
