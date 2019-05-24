package dao.models;


public class Event extends Model {

    private String name;
    private String date;
    private String description;

    public Event()
    {
        setArgs();
        setTable();
    }

    private void setTable()
    {
        table = "events";
    }
    private void setArgs()
    {
        args = new String[]{"name", "date", "description"};
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
