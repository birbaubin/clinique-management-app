package dao.models;

public class Payment extends Model{

    private String date;
    private int cotisation_id;
    private int member_id;


    public Payment()
    {
        setArgs();
        setTable();
    }

    //see Model
    private void setArgs(){
        this.args = new String[]{"date", "cotisation_id", "member_id"};
    }

    private void setTable() {
        this.table = "payments";
    }

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

    public Object[] getAttributes()
    {
        return new Object[]{date, cotisation_id, member_id};
    }
}
