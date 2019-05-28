package dao.models;


public class Event extends Model {

    private String name;
    private String date;
    private String description;

    public Event()
    {
        setArgs();
        setTable();
        this.id = 0;
    }

    public Event(int id, String name, String date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.id = id;
        setTable();
        setArgs();
    }

    private void setTable()
    {
        table = "events";
    }
    private void setArgs()
    {
        args = new String[]{"name", "date", "description"};
    }

    public Event(String name, String date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
        setArgs();
        setTable();
    }

    public String getDate()
    {
        return date;
    }


    public String getName()
    {
        return name;
    }


    public String getDescription()
    {
        return description;
    }


    public void setName(String _name)
    {
        name = _name;
    }


    public void setDate(String _date)
    {
        date = _date;
    }

    public void setDescription(String _desc)
    {
        description = _desc;
    }

    public String[] getAttributes()
    {
        return new String[]{name, date, description};
    }
}
