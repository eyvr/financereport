package application.cli.output;

public class CliOutput implements OutputInterface {

    @Override
    public void write(String string)
    {
        System.out.print(string);
    }

    @Override
    public void writeln(String string)
    {
        System.out.println(string);
    }
}
