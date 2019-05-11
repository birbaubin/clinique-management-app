package sample.Models;

public class Cotisation extends Model{

    private double amount;
    private String description;
    private String timeLimit;


    private void setArgs(){
        this.args = new String[]{"amount", "description", "timeLimit"};
    }

    private void setTable(){
        this.table = "cotisations";
    }

    public Cotisation(){
        setTable();
        setArgs();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Object[] getAttributes()
    {
        return new Object[]{amount, description, timeLimit};
    }

}
