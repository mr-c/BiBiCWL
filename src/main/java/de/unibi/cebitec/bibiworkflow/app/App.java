package de.unibi.cebitec.bibiworkflow.app;

import de.unibi.cebitec.bibiworkflow.converter.Converter;
import de.unibi.cebitec.bibiworkflow.converter.IConverter;
import de.unibi.cebitec.bibiworkflow.gui.IMainGui;
import de.unibi.cebitec.bibiworkflow.gui.MainGui;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.ParserException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;





/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App
{
    
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    
    
    
    
    
    public static void main(String[] args) throws ParseException
    {
        Options options = new Options();
        
        // create option groups and add them to the options
//        OptionGroup cmdLineModeGroup = new OptionGroup();
//        options.addOptionGroup(cmdLineModeGroup);
//        OptionGroup guiModeGroup = new OptionGroup();
//        options.addOptionGroup(guiModeGroup);
        

        // options for the gui group
        Option useGuiMode = new Option("g", "gui-mode", false, "whether or not "
                + "the application should start as CMD-Line tool or as GUI "
                + "application");
        useGuiMode.setRequired(false);
        options.addOption(useGuiMode);
        
        
        // options for the cmd line group
        Option bs2file = new Option("i", "input", true, "bs2 file which should "
                + "be converted to a CWL tool");
        options.addOption(bs2file);
        
        Option outputFolder = new Option("o", "output", true, "Defines the "
                + "output folder. If none is provided, the output files will"
                + "be placed in /tmp/");
        options.addOption(outputFolder);
        
        Option suppressShellQuote = new Option("q", "noShellQuote", false, 
                "suppress the use of shellQuotes in the CWL CommandlineTool");
        options.addOption(suppressShellQuote);
        
        // set up command line parser and formatting
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;
        
        
        try
        {
            cmd = parser.parse(options, args);
        }
        catch (ParserException e)
        {
            System.out.println(e.getMessage());
            formatter.printHelp("bibi-workflow", options);
            
            System.exit(1);
            return;
        }
        
        
        // do somthing with the input
        if (cmd.hasOption("gui-mode"))
        {
            LOGGER.info("Starting application in GUI mode. All other parameters will be ignored.");
            startGuiMode();
        }
        else
        {
            LOGGER.info("Starting application in command line mode. Some parameters might not yet work as intended.");
            startCmdLineMode(cmd);
        }
        
        
    }
    
    
    
    
    public static void startGuiMode()
    {
        IConverter converter = new Converter();
        IMainGui mainGui = new MainGui();
        GuiControl controller = new GuiControl(converter, mainGui);
    }
    
    
    
    
    public static void startCmdLineMode(CommandLine cmd)
    {
        IConverter converter = new Converter();
        IControl controller = new CmdControl(converter, cmd);
        
        controller.convertBs2ToCWL();
    }

    
}
