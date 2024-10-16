package repairjava.entity.sar;

import org.apache.commons.cli.*;

public class Main {
    /*使用命令行解析器传递参数产生命令行*/
    public static CommandLine parseCommandLine(Options options, String[] args){
        try{
            CommandLineParser cmdLineParser = new DefaultParser();
            return cmdLineParser.parse(options, args);
        }
        catch(ParseException e){
            throw new IllegalStateException();
        }
    }

}
