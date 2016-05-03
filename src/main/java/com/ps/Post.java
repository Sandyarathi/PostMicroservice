package com.ps;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "posts")
public class Post {
	
	@Null
	@Id public Integer id;
	@NotNull
	public String headline;
	@NotNull
	public String content;
	@NotNull
	public String category;
	@Null
	public String created_on;
	@Null
	public Integer voteScore;
	
	@Override
    public String toString() {
        return String.format(
                "Post[id=%d, headline=%s, content=%s, category=%s]",
                id, headline,content, category);
    }
	public Post(Post post){
		this.id=post.id;
		this.headline=post.headline;
		this.content=post.content;
		this.category=post.category;
		this.created_on= post.created_on;
		this.voteScore=post.voteScore;
	}
	public Post() {
		// TODO Auto-generated constructor stub
	}
	
	

}

