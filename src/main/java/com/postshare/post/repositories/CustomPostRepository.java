package com.postshare.post.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.postshare.post.objects.Post;

public class CustomPostRepository implements ICustomPostRepository {

	@Autowired
	PostRepository postRepo;

	@Override
	public List<Post> findByVoteScore() {
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "votescore"));
		List<Post> allPostsSorted = postRepo.find(query, Post.class);
		return allPostsSorted;
	}

}
