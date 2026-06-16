import java.util.ArrayList;
import java.util.Scanner;

/**
 * File Name: ContentManagementSystemForBlog.java
 * Description: A complete console-based Content Management System (CMS) for managing blog posts.
 */

// Model class representing a unique blog post
class BlogPost {
    private final int id;
    private String title;
    private String content;
    private final String author;

    // Constructor to initialise fields
    public BlogPost(int id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getAuthor() { return author; }

    // Utility method to cleanly display post details
    public void printPostDetails() {
        System.out.println("\n==================================================");
        System.out.println("ID:      " + id);
        System.out.println("Title:   " + title);
        System.out.println("Author:  " + author);
        System.out.println("--------------------------------------------------");
        System.out.println(content);
        System.out.println("==================================================");
    }
}

// Core execution engine for the CMS Application
public class ContentManagementSystemForBlog {
    private static final ArrayList<BlogPost> blogs = new ArrayList<>();
    private static int idGenerator = 101; // Starts IDs at 101
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Seed default template data
        blogs.add(new BlogPost(idGenerator++, "Introduction to Java", "Java is a robust, class-based object-oriented language.", "Admin"));

        while (true) {
            printMenuOptions();
            int choice = readIntegerSelection();

            switch (choice) {
                case 1 -> createNewBlogPost();
                case 2 -> displayAllBlogPosts();
                case 3 -> modifyExistingBlogPost();
                case 4 -> removeBlogPost();
                case 5 -> terminateApplication();
                default -> System.out.println("⚠️ Invalid option selected. Please pick a number from 1 to 5.");
            }
        }
    }

    // Displays available interactive features
    private static void printMenuOptions() {
        System.out.println("\n📝 --- CMS SYSTEM MAIN MENU ---");
        System.out.println("1. Create New Post");
        System.out.println("2. View All Posts");
        System.out.println("3. Update A Post");
        System.out.println("4. Delete A Post");
        System.out.println("5. Exit System");
        System.out.print("Enter choice (1-5): ");
    }

    // Logic to build a brand new post
    private static void createNewBlogPost() {
        System.out.print("Enter Blog Title: ");
        String title = input.nextLine();
        System.out.print("Enter Content Text: ");
        String content = input.nextLine();
        System.out.print("Enter Author Name: ");
        String author = input.nextLine();

        BlogPost post = new BlogPost(idGenerator++, title, content, author);
        blogs.add(post);
        System.out.println("✅ Success! Created blog post entry with generated ID: " + post.getId());
    }

    // Logic to fetch and read out the full list
    private static void displayAllBlogPosts() {
        if (blogs.isEmpty()) {
            System.out.println("📭 The system contains zero active posts right now.");
            return;
        }
        for (BlogPost post : blogs) {
            post.printPostDetails();
        }
    }

    // Logic to select, fetch and modify fields of an object instance 
    private static void modifyExistingBlogPost() {
        System.out.print("Provide the target Blog ID to edit: ");
        int id = readIntegerSelection();
        BlogPost post = lookupPostById(id);

        if (post == null) {
            System.out.println("❌ Match Error: Unable to locate a post linked to ID: " + id);
            return;
        }

        System.out.print("New Title (Or press Enter to keep current): ");
        String textTitle = input.nextLine();
        if (!textTitle.strip().isEmpty()) {
            post.setTitle(textTitle);
        }

        System.out.print("New Content (Or press Enter to keep current): ");
        String textContent = input.nextLine();
        if (!textContent.strip().isEmpty()) {
            post.setContent(textContent);
        }

        System.out.println("🔄 Update Completed Successfully!");
    }

    // Logic to discard an index object out of scope
    private static void removeBlogPost() {
        System.out.print("Provide target Blog ID to delete: ");
        int id = readIntegerSelection();
        BlogPost post = lookupPostById(id);

        if (post == null) {
            System.out.println("❌ Match Error: Unable to locate a post linked to ID: " + id);
            return;
        }

        blogs.remove(post);
        System.out.println("🗑️ Post successfully wiped out of internal system tracking.");
    }

    // Iterative search block
    private static BlogPost lookupPostById(int id) {
        for (BlogPost post : blogs) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    // Traps alphabetic inputs preventing application crashes
    private static int readIntegerSelection() {
        while (!input.hasNextInt()) {
            System.out.print("⚠️ Bad Input. Please supply an integer index integer value: ");
            input.next();
        }
        int out = input.nextInt();
        input.nextLine(); // consume trailing character feed
        return out;
    }

    // Gracefully drops references and kills system loop processes
    private static void terminateApplication() {
        System.out.println("🚪 Turning off Blog CMS engine safely. Have a wonderful day!");
        input.close();
        System.exit(0);
    }
}
