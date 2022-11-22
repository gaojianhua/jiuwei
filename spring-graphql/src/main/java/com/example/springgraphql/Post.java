package com.example.springgraphql;

import lombok.Data;

@Data
public class Post {
    private int postId;
    private String title ;
    private String text;
    private String  category;
    private User user;
 
    public Post() {
 
    }
 
    public Post(int postId, String title, String text, String category) {
        this.postId = postId;
        this.title = title;
        this.text = text;
        this.category = category;
    }
 
}