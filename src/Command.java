import java.io.IOException;

public interface Command {
    void execute(String[] args) throws IOException, ClassNotFoundException;
}
