package com.aloha.pagehelper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.aloha.pagehelper.domain.Page;
import com.aloha.pagehelper.domain.Post;
import com.aloha.pagehelper.service.PostService;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired private PostService postService;
    
    @GetMapping("/list")
    public String list(
        Model model,
        // @RequestParam(name="page", defaultValue = "1") long page,
        // @RequestParam(name="size", defaultValue = "10") long size,
        // @RequestParam(name="count", defaultValue = "10") long count
        Page pagination
        ) throws Exception {
        // Pagination pagination = new Pagination(page, size, count, 0);
        List<Post> list = postService.page(pagination);
        model.addAttribute("pagination", pagination);
        model.addAttribute("list", list);

        // PageHelper 통한 페이징 처리
        int page = (int) pagination.getPage();
        int size = (int) pagination.getSize();
        PageInfo<Post> pageInfo = postService.page(page, size);
        log.info("pageInfo : {}", pageInfo);
        model.addAttribute("pageInfo", pageInfo);

        // Uri 빌더
        String pageUri = UriComponentsBuilder.fromPath("/posts/list")
                                            // Pagination
                                            //  .queryParam("page", pagination.getPage())
                                            //  .queryParam("size", pagination.getSize())
                                            //  .queryParam("count", pagination.getCount())
                                            // PageHelper
                                             .queryParam("size", pageInfo.getSize())
                                             .queryParam("count", pageInfo.getPageSize())
                                             .build()
                                             .toUriString();
            model.addAttribute("pageUri", pageUri);
        return "posts/list";
    }
    
    @GetMapping("/insert")
    public String createForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/insert";
    }

    @PostMapping("/insert")
    public String insert(Post post, RedirectAttributes rttr) throws Exception {
        boolean success = postService.insert(post);
        if (success) {
            rttr.addFlashAttribute("msg", "등록 성공");
            return "redirect:/posts/list";
        } else {
            rttr.addFlashAttribute("msg", "등록 실패");
            return "posts/insert";
        }
    }

    @GetMapping("/read/{no}")
    public String read(@PathVariable("no") Integer no, Model model) throws Exception {
        Post post = postService.select(no);
        model.addAttribute("post", post);
        return "posts/read";
    }

    @GetMapping("/update/{no}")
    public String editForm(@PathVariable("no") Integer no, Model model) throws Exception {
        Post post = postService.select(no);
        model.addAttribute("post", post);
        return "posts/update";
    }

    @PostMapping("/update")
    public String updatePost(Post post) throws Exception {
        boolean result = postService.update(post);
        if( result )
            return "redirect:/posts/list";
        return "redirect:/posts/update/" + post.getNo() + "error=true";
    }

    @PostMapping("/delete/{no}")
    public String delete(@PathVariable("no") Integer no) throws Exception {
        boolean result = postService.delete(no);
        if( result )
            return "redirect:/posts/list";
        return "redirect:/posts/update?error=true";
    }
}