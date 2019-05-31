package dao.models;

public abstract  class Model extends  Object{

    protected String table; //table contains the name of the table of the db related to the mode,
    protected String[] args; //args contains the arguments of the model, except the id
    protected int id;

    public String[] getArgs()
    {
        return args;
    }

    public String getTable() {
        return table;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract Object[] getAttributes();

}
