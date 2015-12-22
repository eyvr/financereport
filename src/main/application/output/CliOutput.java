package application.output;

public class CliOutput implements Output {

    @Override
    public void print(String string)
    {
        System.out.print(string);
    }

    @Override
    public void println(String string)
    {
        System.out.println(string);
    }
}
