import com.github.rvesse.airline.annotations.Cli;
import com.github.rvesse.airline.help.Help;
import commandline.SetupAppCommand;

@Cli(name = "text-transformer",
        description = "RSS text converter",
        defaultCommand = Help.class,
        commands = { SetupAppCommand.class, Help.class})
public class Main {

    public static void main(String[] args) {
        com.github.rvesse.airline.Cli<Runnable> cli = new com.github.rvesse.airline.Cli<>(Main.class);
        Runnable cmd = cli.parse(args);
        cmd.run();
    }
}