package sample.Models;


import sample.DAO.Model;

public class Event extends Model {

    private String name;
    private String date;
    private String description;

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
}
