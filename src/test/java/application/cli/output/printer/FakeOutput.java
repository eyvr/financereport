package application.cli.output.printer;

import application.cli.output.OutputInterface;

public class FakeOutput implements OutputInterface {

    private String output;

    public FakeOutput()
    {
        this.output = "";
    }

    @Override
    public void print(String string)
    {
        this.output += string;
    }

    @Override
    public void println(String string)
    {
        this.output += string + "\n";
    }

    public String getOutput() {
        return output;
    }
}
