package com.aloha.pagehelper.service;

import java.util.List;

import com.aloha.pagehelper.domain.Page;
import com.aloha.pagehelper.domain.Post;
import com.github.pagehelper.PageInfo;

public interface PostService {

    // 목록
    public List<Post> list() throws Exception;
    // 페이징 목록
    public List<Post> page(Page pagination) throws Exception;
    // ⭐ pagehelper 를 이용한 페이징 목록
    public PageInfo<Post> page(int page, int size) throws Exception;
    // 조회
    public Post select(Integer no) throws Exception;
    // 등록
    public boolean insert(Post post) throws Exception;
    // 수정
    public boolean update(Post post) throws Exception;
    // 삭제
    public boolean delete(Integer no) throws Exception;
    
}
