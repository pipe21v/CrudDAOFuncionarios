
package modelo;

/**
 *
 * @author Luis Felipe
 */
public class Database {
    
    String url;
    String username;
    String password;
    String driver;
    
    public Database(){
        this.url = "jdbc:mysql://localhost:3306/funcionariosiud2";
        this.username = "root";
        this.password = "12345";
        this.driver = "com.mysql.cj.jdbc.Driver";
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }

    
    
    
    
}
