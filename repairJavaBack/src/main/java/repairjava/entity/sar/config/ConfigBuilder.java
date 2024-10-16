package repairjava.entity.sar.config;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import repairjava.entity.program.ProgramBuilder;
import repairjava.entity.program.TesterBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigBuilder {
    private Config config;
    public Config getConfig() {
        return config;
    }

    public void buildConfig(CommandLine commandLine) throws IOException {
        Properties properties;//在命令行上由其名称及其对应的属性表示
        properties = getPropertiesFromCommandLine(commandLine);//get属性
        config = buildConfigFromProperties(properties);//构建配置
    }

    private static Properties getPropertiesFromCommandLine(CommandLine commandLine) {
        Properties properties = new Properties();
        for (Option opt : commandLine.getOptions()) {
            String propertyID = opt.getArgName();
            properties.setProperty(propertyID, commandLine.getOptionValue(propertyID));
        }

        return properties;
    }

    private Config buildConfigFromProperties(Properties properties) throws IOException {
        Config config = new Config();
        initMethodToFix(config, properties);
        initPrograms(config, properties);
        initTestProgram(config, properties);
        return config;
    }

    private static String getProperty(Properties properties, String name) {
        if (properties.containsKey(name)) {
            return properties.getProperty(name);
        }
        else {
            throw new IllegalStateException("Error: Property not specified in configuration (" + name + ").");
        }
    }

    private static void initMethodToFix(Config config, Properties properties){
        config.setMethodToFix(getProperty(properties, CmdOptions.METHOD_TO_FIX_OPT));
    }

    /*创建program*/
    public static ProgramBuilder getProgram(Config config, String sourceDir) throws IOException {
        String className = config.getClassName();//通过fixMethod中的类名构建
        return new ProgramBuilder(sourceDir, className);
    }



    private static void initPrograms(Config config, Properties properties) throws IOException {
        String buggySourceDir = getProperty(properties, CmdOptions.BUGGY_PROGRAM_SOURCE_DIR);
        String correctSourceDir = getProperty(properties, CmdOptions.CORRECT_PROGRAM_SOURCE_DIR);
        config.setBuggyProgram(getProgram(config, buggySourceDir));
        config.setCorrectProgram(getProgram(config, correctSourceDir));
    }

    private static void initTestProgram(Config config, Properties properties) throws FileNotFoundException {
        String testSourceDir = getProperty(properties, CmdOptions.PROGRAM_TEST_SOURCE_DIR_OPT);
        String className = getProperty(properties, CmdOptions.PROGRAM_TEST_CLASS);
        config.setTesterProgram(new TesterBuilder(testSourceDir, className));
    }
}

