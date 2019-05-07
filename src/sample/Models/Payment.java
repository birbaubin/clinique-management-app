package sample.Models;

public class Payment {

    private String date;
    private int cotisation_id;
    private int member_id;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCotisation_id() {
        return cotisation_id;
    }

    public void setCotisation_id(int cotisation_id) {
        this.cotisation_id = cotisation_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }
}
