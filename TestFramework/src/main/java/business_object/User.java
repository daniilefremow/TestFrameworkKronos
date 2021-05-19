package business_object;

import utils.PropertyReader;

public class User {

    private String username;
    private String password;

    private User() {
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public static User getUser() {
        User user = new User();
        user.setUsername(PropertyReader.getProperty("username"));
        user.setPassword(PropertyReader.getProperty("password"));

        return user;
    }

}
