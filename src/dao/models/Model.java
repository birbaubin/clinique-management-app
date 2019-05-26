package dao.models;

public abstract  class Model {

    protected String table;
    protected String[] args;
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

    public abstract Object[] getAttributes();

}
