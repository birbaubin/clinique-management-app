package sample.dao.models;

public class Notification extends Model {

    private String text;
    private int member_id;

    public Notification()
    {
        setArgs();
        setTable();
    }

    private void setArgs()
    {
        this.args = new String[]{"text", "member_id"};
    }

    private void setTable(){
        this.table = "notifications";
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public Object[] getAttributes()
    {
        return new Object[]{text, member_id};
    }
}
