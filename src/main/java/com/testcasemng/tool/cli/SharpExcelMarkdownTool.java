package com.testcasemng.tool.cli;

import com.testcasemng.tool.utils.*;
import org.apache.commons.cli.*;

public class SharpExcelMarkdownTool {
    public static void main(String[] args) throws Exception {
        Options options = new Options();
        options.addOption(Option.builder("c")
                .longOpt("convert")
                .hasArg(false)
                .desc("Convert test specifications between Excel and Markdown")
                .required(false)
                .build());
        options.addOption(Option.builder("m2e")
                .longOpt("markdown2excel")
                .hasArg(false)
                .desc("Convert Markdown test specifications to Excel")
                .required(false)
                .build());
        options.addOption(Option.builder("e2m")
                .longOpt("excel2markdown")
                .hasArg(false)
                .desc("Convert Excel test specifications to Markdown")
                .required(false)
                .build());
        options.addOption(Option.builder("t")
                .longOpt("type")
                .hasArg(true)
                .desc("Sharp template type: automation=0, manual=1")
                .required(false)
                .build());
        options.addOption(Option.builder("i")
                .longOpt("input")
                .hasArg(true)
                .desc("[REQUIRED] input file or directory")
                .numberOfArgs(Option.UNLIMITED_VALUES)
                .required(true)
                .build());
        options.addOption(Option.builder("o")
                .longOpt("output")
                .hasArg(true)
                .desc("[REQUIRED] output file or directory")
                .required(false)
                .build());

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("c") && cmd.hasOption("t") && cmd.hasOption("i") && cmd.hasOption("o")) {
                String input = cmd.getOptionValue("i");
                String output = cmd.getOptionValue("o");
                if (cmd.hasOption("m2e")) {
                    // implement later
                } else if (cmd.hasOption("e2m")) {
                    Conversion.convertSharpAutomationExcelToMarkdown(input, output);
                } else {
                    System.out.println("Error parsing command-line arguments!. Run -h for help");
                }
            }
        } catch (ParseException pe) {
            System.out.println("Error parsing command-line arguments!");
            System.out.println("Please, follow the instructions below:");
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar sharp-testcasemng-tool.jar [option] -c -t<type> -i <input> -o <output>\n"
                    , options);
            System.exit(1);
        }
    }


}
