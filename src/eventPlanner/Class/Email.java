package eventPlanner.Class;

public class Email {

    //Attributes
    private String username, domain, fullAddress;

    public Email(String un, String dom) {
        username = un;
        domain = dom;
        fullAddress = username + "@" + domain;
    }

    public Email(String emailAddress) {
        fullAddress = emailAddress;
        splitEmail();
    }

    //Splits an email address around the @ and assigns username and domain
    private void splitEmail() {
        String [] tokens = fullAddress.split("@");
        username = tokens[0];
        domain = tokens[1];
    }

    //Validates a full address passed into it
    public static boolean validate(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    //Validates the full address already stored in the object
    public boolean validate() {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return fullAddress.matches(regex);
    }

    //Getters
    public String getEmail() { return fullAddress; }
    public String getUsername() { return username; }
    public String getDomain() { return domain; }

    //Setters
    public void setEmail(String emailAddress) { fullAddress = emailAddress; }
    public void setUsername(String un) { username = un; }
    public void setDomain(String d) { domain = d; }
}
