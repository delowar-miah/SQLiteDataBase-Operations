package model;

public class Student {
    private int id;
    private String name;
    private String image;
    private String phone;
    private String email;
    private Float cgpa;

    public Student(String name, String email, String phone, Float cgpa, int id) {
        this.name = name;
        this.image = image;
        this.phone = phone;
        this.email = email;
        this.cgpa = cgpa;
        this.id = id;
    }

    public Student(String name, String phoneNo, String email, Float cgpaF) {
        this.name=name;
        this.phone=phoneNo;
        this.email=email;
        this.cgpa=cgpaF;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getCgpa() {
        return cgpa;
    }

    public void setCgpa(Float cgpa) {
        this.cgpa = cgpa;
    }
}
