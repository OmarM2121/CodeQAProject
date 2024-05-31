package com.omarm.codeqaproject;

import java.util.ArrayList;
import java.util.List;

public class Post {
	    private String title;
	    private String body;
	    private List<String> tags;
	    private String difficulty;
	    private String emergency;
	    private List<String> comments;

	    public Post() {
	        comments = new ArrayList<>();
	    }

	    public boolean addPost(String title, String body, List<String> tags, String difficulty, String emergency) {
	        if (title.length() < 10 || title.length() > 250 || !title.matches("^[^0-9\\W]{5}.*")) {
	            return false;
	        }
	        if (body.length() < 250 || (difficulty.equals("Easy") && body.length() < 300)) {
	            return false;
	        }
	        if (tags.size() < 2 || tags.size() > 5 || tags.stream().anyMatch(tag -> tag.length() < 2 || tag.length() > 10 || !tag.matches("^[a-z]+$"))) {
	            return false;
	        }
	        if (difficulty.equals("Easy") && tags.size() > 3) {
	            return false;
	        }
	        if (!difficulty.matches("Very Difficult|Difficult|Easy")) {
	            return false;
	        }
	        if ((difficulty.equals("Easy") && !emergency.equals("Ordinary")) || 
	            (!difficulty.equals("Easy") && emergency.equals("Ordinary"))) {
	            return false;
	        }
	        if (!emergency.matches("Immediately Needed|Highly Needed|Ordinary")) {
	            return false;
	        }
	        this.title = title;
	        this.body = body;
	        this.tags = tags;
	        this.difficulty = difficulty;
	        this.emergency = emergency;
	        return true;
	    }

	    public boolean addComment(String comment) {
	        if (comment.split("\\s+").length < 4 || comment.split("\\s+").length > 10 || !comment.matches("^[A-Z].*")) {
	            return false;
	        }
	        if (this.comments.size() >= 5 || 
	            (this.difficulty.equals("Easy") || this.emergency.equals("Ordinary")) && this.comments.size() >= 3) {
	            return false;
	        }
	        this.comments.add(comment);
	        return true;
	    }
	}
