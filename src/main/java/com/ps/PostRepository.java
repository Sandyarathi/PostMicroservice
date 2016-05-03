package com.ps;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;




@Repository
@Service
public interface PostRepository extends MongoRepository<Post, Integer> {

	public Post findById(Integer id);
	public Post save(Post saved);
	

}



