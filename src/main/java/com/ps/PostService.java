package com.ps;

import java.util.List;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepo;
	
	//@Autowired
	//private ICustomPostRepository customPostRepo;

	public boolean validatePostId(int postId){
		if(!(postRepo.exists(postId)))
				return false;
		return true;
	}
	

	private Integer generateID(){
		Random r = new Random();
		return r.nextInt(9000) + 1000; 
	}
	
	public Post addPost(Post post) {
		
		Integer id = generateID();
		while(postRepo.exists(id))
			id = generateID();
		post.id=(id);
		post.voteScore=0;
		DateTime dt = new DateTime(DateTimeZone.UTC);
		
		post.created_on =  dt.toString(ISODateTimeFormat.dateTime().withZoneUTC());
		postRepo.save(post);
		
		return post;

	}
	public Post viewPost(int postId)  {		
		return postRepo.findById(postId);
	}
	public Post updatePost(int post_id,Post post) {		
		Post oldPost=postRepo.findById(post_id);
		if(post.headline!=null)
			oldPost.headline=(post.headline);
		if(post.content!=null)
			oldPost.content=(post.content);
		postRepo.save(oldPost);
		
		return oldPost;
		
	}
	


	public void deletePost(int post_id)  {
		
		Post post=postRepo.findById(post_id);
		postRepo.delete(post);
		
		
	}

	public boolean voteOnPost(Integer post_id)  {
		Post updatePost=postRepo.findById(post_id);
		updatePost.voteScore=(updatePost.voteScore+1);
		postRepo.save(updatePost);		
		return true;
	}


	public List<Post> listAllPosts() {
		List<Post> allPosts=postRepo.findAll();
		return allPosts;
	}


	/*public List<Post> listTopPosts() {
		List<Post> allPosts=postRepo.findAll(new Sort(Sort.Direction.DESC, "votescore"));;
		
		return allPosts;
		
		
	}*/

}
