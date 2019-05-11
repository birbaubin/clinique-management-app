package sample.Models;

public abstract  class Model {

    protected String table;
    protected String[] args;

    public String[] getArgs()
    {
        return args;
    }

    public String getTable() {
        return table;
    }

    public abstract Object[] getAttributes();

}
