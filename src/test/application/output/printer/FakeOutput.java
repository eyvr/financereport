package application.output.printer;

import application.output.Output;

public class FakeOutput implements Output {

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
