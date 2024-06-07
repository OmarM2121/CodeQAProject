
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Post {
    private int postID;
    private String postTitle;
    private String postBody;
    private String[] postTags;
    private String postType;
    private String postEmergency;
    private ArrayList<String> postComments = new ArrayList<>();

    public Post(int postID, String postTitle, String postBody, String[] postTags, String postType, String postEmergency) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postType = postType;
        this.postEmergency = postEmergency;
    }

    public boolean addPost() {
        if (isValidPostTitle() && isValidPostBody() && isValidPostTags() && isValidPostTypeAndEmergency()) {
            try (FileWriter writer = new FileWriter("post.txt", true)) {
                writer.write(toString());
                writer.write(System.lineSeparator());
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private boolean isValidPostTitle() {
        if (postTitle.length() < 10 || postTitle.length() > 250) return false;
        for (int i = 0; i < 5; i++) {
            if (!Character.isLetter(postTitle.charAt(i))) return false;
        }
        return true;
    }

    private boolean isValidPostBody() {
        return postBody.length() >= 250;
    }

    private boolean isValidPostTags() {
        if (postTags.length < 2 || postTags.length > 5) return false;
        for (String tag : postTags) {
            if (tag.length() < 2 || tag.length() > 10 || !tag.equals(tag.toLowerCase())) return false;
        }
        return true;
    }

    private boolean isValidPostTypeAndEmergency() {
        if (postType.equals("Easy")) {
            if (postTags.length > 3 || postEmergency.equals("Immediately Needed") || postEmergency.equals("Highly Needed")) return false;
        }
        if (postType.equals("Very Difficult") || postType.equals("Difficult")) {
            if (postBody.length() < 300 || postEmergency.equals("Ordinary")) return false;
        }
        return true;
    }

    public boolean addComment(String comment) {
        if (isValidCommentText(comment) && isValidCommentCount()) {
            try (FileWriter writer = new FileWriter("comment.txt", true)) {
                writer.write(comment);
                writer.write(System.lineSeparator());
                postComments.add(comment);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private boolean isValidCommentText(String comment) {
        String[] words = comment.split(" ");
        if (words.length < 4 || words.length > 10) return false;
        if (!Character.isUpperCase(words[0].charAt(0))) return false;
        return true;
    }

    private boolean isValidCommentCount() {
        if ((postType.equals("Easy") || postEmergency.equals("Ordinary")) && postComments.size() >= 3) return false;
        if (postComments.size() >= 5) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", postTitle='" + postTitle + '\'' +
                ", postBody='" + postBody + '\'' +
                ", postTags=" + String.join(", ", postTags) +
                ", postType='" + postType + '\'' +
                ", postEmergency='" + postEmergency + '\'' +
                '}';
    }
}
