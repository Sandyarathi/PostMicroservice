package com.postshare.post.services;


import java.util.List;

import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postshare.post.objects.Post;
import com.postshare.post.repositories.CustomPostRepository;
import com.postshare.post.repositories.PostRepository;




@Service
public class PostService {

	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private CustomPostRepository customPostRepo;
	
	
	public boolean validatePostId(int postId){
		if(!(postRepo.exists(postId)))
				return false;
		return true;
	}
	

	private Integer generateID(){
		Random r = new Random();
		return r.nextInt(9000) + 1000; 
	}
	
	public Post createPost(Post post) {
		
		Integer id = generateID();
		while(postRepo.exists(id))
			id = generateID();
		post.setPost_id(id);
		
		return post;

	}
	public Post viewPost(int postId)  {		
		return postRepo.findById(postId);
	}
	public Post updatePost(int post_id,Post post) {		
		Post oldPost=postRepo.findById(post_id);		
		return oldPost;
		
	}
	


	public void deletePost(int post_id)  {
		
		Post post=postRepo.findById(post_id);
		postRepo.delete(post);
		
		
	}

	public boolean voteOnPost(Integer post_id, int vote)  {
		Post updatePost=postRepo.findById(post_id);
		updatePost.setVotescore(updatePost.getVotescore()+vote);
		postRepo.save(updatePost);		
		return true;
	}


	public List<Post> listAllPosts() {
		List<Post> allPosts=postRepo.findAll();
		return allPosts;
	}


	public List<Post> listTopPosts() {
		List<Post> allPosts=customPostRepo.findByVoteScore();
		
		return allPosts;
		
		
		
		
	}
	
	
		
		

	

}
