package personal_practice.test;

public class Teacher extends Person {
    private String status;

    public Teacher() {
        this.status = "teacher";
    }

    public Teacher(String name, Integer age, String status) {
        super(name, age);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    static class gamePlayer{
        private String gameName="Dota2";
    }
}
