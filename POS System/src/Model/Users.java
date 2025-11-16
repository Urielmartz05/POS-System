package Model;

public class Users {

    private String password;
    private String name;
    private String role;
    
    public Users(String password, String name, String role) {
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
