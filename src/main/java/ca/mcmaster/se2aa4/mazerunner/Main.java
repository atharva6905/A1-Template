package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        // Set up Apache CLI options
        Options options = new Options();
        options.addOption("i", "input", true, "Input file for the maze");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            // Check for the required '-i' flag
            if (!cmd.hasOption("i")) {
                logger.error("Missing required option: -i");
                System.err.println("Usage: java -jar mazerunner.jar -i <maze file>");
                System.exit(1);
            }

            String inputFile = cmd.getOptionValue("i");
            logger.info("Reading the maze from file: " + inputFile);

            // Read and process the maze file
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        System.out.print("PASS ");
                    }
                }
                System.out.print(System.lineSeparator());
            }
            reader.close();
        } catch (ParseException e) {
            logger.error("Error parsing command line arguments", e);
        } catch (Exception e) {
            logger.error("An error occurred while processing the maze", e);
        }

        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
