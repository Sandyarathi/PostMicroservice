package com.ps;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ps/api")
public class PostController {
	
	@Autowired
	PostService service ;
	
	@RequestMapping(value="/addPost", method = RequestMethod.POST, 
			produces = "application/json", consumes= "application/json")
	public ResponseEntity<Post> createPost( @RequestBody @Valid Post post){
		return new ResponseEntity<Post>(service.addPost(post),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/posts/{post_id}", method = RequestMethod.GET, produces ="application/json")
    public ResponseEntity<Post> viewPost(@PathVariable("post_id") int postId ) {
	  if(service.validatePostId(postId))
		  return new ResponseEntity<Post>(service.viewPost(postId), HttpStatus.OK);
	  else{
		  
		  return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
	  }
    }
	
	@RequestMapping(value="/posts/{post_id}", method = RequestMethod.PUT, produces ="application/json")
	public ResponseEntity<Post> updatePost(@PathVariable int moderator_id, @RequestBody Post post) {
		if(service.validatePostId(moderator_id))
			return new ResponseEntity<Post>(service.updatePost(moderator_id,post), HttpStatus.OK);
		else
			 return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);

	}
	
	@RequestMapping(value="/posts/{post_id}", method = RequestMethod.DELETE, produces ="application/json")
	  public ResponseEntity<String>  deletePost(@PathVariable int post_id) {
		if(service.validatePostId(post_id)){
				 service.deletePost(post_id);
				 return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		else
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		 
	  }

	@RequestMapping(value="/posts/{post_id}/vote", method = RequestMethod.PUT, produces ="application/json")
	  public ResponseEntity<String> voteOnPost(@PathVariable int post_id) {
		  
		if(service.validatePostId(post_id)){
			boolean success = service.voteOnPost(post_id);
			if(success)
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			else
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

		}
		else
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	  }
	
	/*@RequestMapping(value="/topPosts", method = RequestMethod.GET, produces ="application/json")
	public ResponseEntity<List<Post>> listTopPosts() {
			return new ResponseEntity<List<Post>>(service.listTopPosts(), HttpStatus.OK);		  
	
	}*/
	
	@RequestMapping(value="/allPosts", method = RequestMethod.GET, produces ="application/json")
	public ResponseEntity<List<Post>> listTopPosts() {
			return new ResponseEntity<List<Post>>(service.listAllPosts(), HttpStatus.OK);		  
	
	}
	


}

