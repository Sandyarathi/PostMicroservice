package com.postshare.post.objects;

import javax.validation.constraints.Null;



public class Post {
	
	private int post_id;
	private String headline;
	private String content;
	private String user_id;
	private int votescore;
	private String category;
	@Null
	private String created_on;
	
	@Override
    public String toString() {
        return String.format(
                "Post[id=%d, headline=%s, content=%s, votescore=%d, category=%s, created_on=%s]",
                post_id, headline,content,votescore,category,created_on);
    }
	
	
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getPost_headline() {
		return headline;
	}
	public void setPost_headline(String post_headline) {
		this.headline = post_headline;
	}
	public String getPost_content() {
		return content;
	}
	public void setPost_content(String post_content) {
		this.content = post_content;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public int getVotescore() {
		return votescore;
	}
	public void setVotescore(int votescore) {
		this.votescore = votescore;
	}
	

}
