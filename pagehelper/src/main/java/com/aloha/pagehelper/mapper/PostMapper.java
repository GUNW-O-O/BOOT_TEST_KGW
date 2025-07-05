package com.aloha.pagehelper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.pagehelper.domain.Page;
import com.aloha.pagehelper.domain.Post;

@Mapper
public interface PostMapper {
    
    // 목록
    public List<Post> list() throws Exception;
    // 페이징 목록
    public List<Post> page(Page pagination) throws Exception;
    // 데이터 수
    public long count() throws Exception;
    // 조회
    public Post select(Integer no) throws Exception;
    // 등록
    public int insert(Post post) throws Exception;
    // 수정
    public int update(Post post) throws Exception;
    // 삭제
    public int delete(Integer no) throws Exception;

}
