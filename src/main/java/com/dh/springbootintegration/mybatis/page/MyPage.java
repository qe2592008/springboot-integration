package com.dh.springbootintegration.mybatis.page;

import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MyPage<T> {
    private Integer pageId;//当前页
    private Integer pageNum;//总页数
    private Integer pageSize;//每页数
    private Integer totalNum;//总数目
    private List<T> body;//分页结果
    private Integer srartIndex;//开始索引
    private boolean isMore;//是否有下一页
}
