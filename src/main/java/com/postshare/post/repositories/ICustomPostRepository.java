package com.postshare.post.repositories;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.postshare.post.objects.Post;

@Service
public interface ICustomPostRepository {
	
	public List<Post> findByVoteScore();
}
