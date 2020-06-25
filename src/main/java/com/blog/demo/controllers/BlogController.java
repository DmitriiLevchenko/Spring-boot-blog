package com.blog.demo.controllers;

import com.blog.demo.models.Post;
import com.blog.demo.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blog(Model modeln) {
        Iterable<Post> posts = postRepository.findAll();
        modeln.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model modeln) {
        return "blog-add";
    }
    @PostMapping("blog/add")
    public String blogPostAdd(@RequestParam String titile,@RequestParam String anons,@RequestParam String full_text, Model model)
    {
        Post post = new Post(titile,anons,full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }
}
