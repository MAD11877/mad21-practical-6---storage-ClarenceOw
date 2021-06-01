package sg.edu.np.madpractical;

public class User {
    private String name;
    private String description;
    private int id;
    private boolean followed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public User() {
    }

    public User(String name, String description, boolean followed) {
        this.name = name;
        this.description = description;
        this.followed = followed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public User(String name, String description, int id, boolean followed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.followed = followed;
    }
}
