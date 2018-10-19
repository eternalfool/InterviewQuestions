package com.agoda.main;

import com.agoda.downloader.MultiFileDownloader;
import org.apache.commons.cli.*;

/**
 * Class to parse input cli arguments.
 */
class Menu {

    void parse(String[] args) {
        Options options = new Options();

        Option input = new Option("u", "urls", true, "list of urls to download");
        input.setRequired(true);
        options.addOption(input);

        Option destination = new Option("d", "destination", true, "destination");
        destination.setRequired(true);
        options.addOption(destination);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }

        String inputFilePath = cmd.getOptionValue("urls");
        String location = cmd.getOptionValue("destination");
        String[] listOfUrls = inputFilePath.split(",");
        new MultiFileDownloader().processMultipleUrls(listOfUrls, location);
    }
}
