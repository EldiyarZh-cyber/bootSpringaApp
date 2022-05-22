package com.example.bootspring.controller;
import com.example.bootspring.models.Post;
import com.example.bootspring.models.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;


@Controller
public class MainController {
@Autowired
PostRepository postRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Main page");
        return "main";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About Us");
        return "about";

    }
    @GetMapping("blog")
    public String house(Model model) {
        model.addAttribute("posts",postRepository.findAll());
        return "blog";
    }
    @GetMapping(value = "/post-img/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getphotoofpost(@PathVariable long id) {
        return postRepository.findById(id).orElseThrow().getPhoto();
    }
    @GetMapping("blog-add")
    public String houses(Model model) {
        Post newPost = new Post();
        model.addAttribute("posts", newPost);
        return "blogadd";
    }
    @PostMapping(value = "savepost", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String savePost(
            @ModelAttribute Post post,
            @RequestPart("photofile") MultipartFile photo,
            Principal principal
    ) {
        try {
            post.setPhoto(photo.getBytes());
            postRepository.save(post);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

}