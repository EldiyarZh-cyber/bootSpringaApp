package com.example.bootspring.models.repository;

import com.example.bootspring.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface  PostRepository extends CrudRepository<Post, Long> {

}
