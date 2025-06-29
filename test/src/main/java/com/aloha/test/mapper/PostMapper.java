package com.aloha.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.test.domain.Page;
import com.aloha.test.domain.Posts;

@Mapper
public interface PostMapper {

    // 목록
    public List<Posts> list() throws Exception;
    // 페이징 목록
    public List<Posts> page(Page page) throws Exception;
    // 데이터 수
    public long count() throws Exception;
    // 조회
    public Posts select(Integer no) throws Exception;
    // 등록
    public int insert(Posts post) throws Exception;
    // 수정
    public int update(Posts post) throws Exception;
    // 삭제
    public int delete(Integer no) throws Exception;
    
}
