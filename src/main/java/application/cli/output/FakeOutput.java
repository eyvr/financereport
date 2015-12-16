package application.cli.output;

public class FakeOutput implements OutputInterface {

    private String output;

    public FakeOutput()
    {
        this.output = "";
    }

    @Override
    public void write(String string)
    {
        this.output += string;
    }

    @Override
    public void writeln(String string)
    {
        this.output += string + "\n";
    }

    public String getOutput() {
        return output;
    }
}
