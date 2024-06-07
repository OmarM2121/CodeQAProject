import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PostTest {

	@Test
    public void testAddPostValid() {
        String[] tags = {"tag1", "tag2"};
        Post post = new Post(1, "ValidTitle", "This is a valid body with more than 250 characters. ".repeat(5), tags, "Easy", "Ordinary");
        assertTrue(post.addPost());
    }

    @Test
    public void testAddPostInvalidTitle() {
        String[] tags = {"tag1", "tag2"};
        Post post = new Post(1, "12345", "This is a valid body with more than 250 characters. ".repeat(5), tags, "Easy", "Ordinary");
        assertFalse(post.addPost());
    }

    @Test
    public void testAddPostInvalidBody() {
        String[] tags = {"tag1", "tag2"};
        Post post = new Post(1, "ValidTitle", "Short body", tags, "Easy", "Ordinary");
        assertFalse(post.addPost());
    }

    @Test
    public void testAddPostInvalidTags() {
        String[] tags = {"tag1"};
        Post post = new Post(1, "ValidTitle", "This is a valid body with more than 250 characters. ".repeat(5), tags, "Easy", "Ordinary");
        assertFalse(post.addPost());
    }

    @Test
    public void testAddPostInvalidTypeEmergency() {
        String[] tags = {"tag1", "tag2"};
        Post post = new Post(1, "ValidTitle", "This is a valid body with more than 250 characters. ".repeat(5), tags, "Easy", "Immediately Needed");
        assertFalse(post.addPost());
    }

    @Test
    public void testAddCommentValid() {
        String[] tags = {"tag1", "tag2"};
        Post post = new Post(1, "ValidTitle", "This is a valid body with more than 250 characters. ".repeat(5), tags, "Easy", "Ordinary");
        assertTrue(post.addComment("This is a comment"));
    }

    @Test
    public void testAddCommentInvalidText() {
        String[] tags = {"tag1", "tag2"};
        Post post = new Post(1, "ValidTitle", "This is a valid body with more than 250 characters. ".repeat(5), tags, "Easy", "Ordinary");
        assertFalse(post.addComment("Short"));
    }

    @Test
    public void testAddCommentExceedCount() {
        String[] tags = {"tag1", "tag2"};
        Post post = new Post(1, "ValidTitle", "This is a valid body with more than 250 characters. ".repeat(5), tags, "Easy", "Ordinary");
        post.addComment("This is a comment.");
        post.addComment("This is another comment.");
        post.addComment("This is yet another comment.");
        assertFalse(post.addComment("Exceeding comment limit."));
    }
}
