package com.postshare.post.repositories;



import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.postshare.post.objects.Post;

@Service
public interface PostRepository extends MongoRepository<Post, Integer> {

	public Post findById(Integer id);

	public List<Post> find(Query query, Class<Post> class1);

	
}


