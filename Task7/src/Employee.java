import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {
    private String fullName;
    private String email;
    private String department;
    private Date dateOfBirth;

    public Employee(String fullName, String email, String department, Date dateOfBirth) {
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getFormattedDob() {
        return new SimpleDateFormat("dd-MM-yyyy").format(dateOfBirth);
    }
}

