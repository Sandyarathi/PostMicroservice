package com.postshare.post.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.postshare.post.objects.Post;
import com.postshare.post.services.PostService;;

@RestController
@RequestMapping("/postshare")
@Configuration
@ComponentScan("com.postshare.post.services")
public class PostController {
		
	@Autowired
	private PostService postService;
	
	public PostController() {
		System.out.println("PostController()");
	}
	
	@RequestMapping(value="/createPost", method = RequestMethod.POST, 
			produces = "application/json", consumes= "application/json")
	public ResponseEntity<Post> createPost( @RequestBody @Valid Post post){
		return new ResponseEntity<Post>(postService.createPost(post),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/posts/{post_id}", method = RequestMethod.GET, produces ="application/json")
    public ResponseEntity<Post> viewPost(@PathVariable("post_id") int postId ) {
	  if(postService.validatePostId(postId))
		  return new ResponseEntity<Post>(postService.viewPost(postId), HttpStatus.OK);
	  else{
		  
		  return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
	  }
    }
	
	@RequestMapping(value="/posts/{post_id}", method = RequestMethod.PUT, produces ="application/json")
	public ResponseEntity<Post> updatePost(@PathVariable int post_id, @RequestBody Post post) {
		if(postService.validatePostId(post_id))
			return new ResponseEntity<Post>(postService.updatePost(post_id,post), HttpStatus.OK);
		else
			 return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);

	}
	
	
	
	
	@RequestMapping(value="/posts/{post_id}", method = RequestMethod.PUT, produces ="application/json")
	  public ResponseEntity<String> votePost(@PathVariable Integer post_id, @RequestParam("choice") int voteChoice) {
		  
		if(postService.validatePostId(post_id)){
			boolean success = postService.voteOnPost(post_id,voteChoice);
			if(success)
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			else
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

		}
		else
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	  }
	
	
	@RequestMapping(value="/posts/{post_id}", method = RequestMethod.DELETE, produces ="application/json")
	  public ResponseEntity<String>  deletePoll(@PathVariable int post_id) {
		if(postService.validatePostId(post_id)){
				 postService.deletePost(post_id);
				 return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		else
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		 
	  }
	
	@RequestMapping(value="/topPosts", method = RequestMethod.GET, produces ="application/json")
	public ResponseEntity<List<Post>> listTopPolls() {
			return new ResponseEntity<List<Post>>(postService.listTopPosts(), HttpStatus.OK);		  
	
	}
	

}
