package se.lexicon.exceptions.workshop.fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.file.Files.newBufferedReader;
import static java.util.stream.Collectors.toList;

public class CSVReader_Writer {

    private static List<String> readToList(BufferedReader reader) {
        return reader.lines()
                .flatMap(line -> Stream.of(line.split(",")))
                .collect(toList());
    }

    /**
     * This method getMaleFirstNames should use a try-catch-finally without resources
     * Should catch FileNotFoundException and IOException
     * You should also close the Buffered reader in the finally block
     *
     * @return List<String> of male firstnames
     */
    public static List<String> getMaleFirstNames() {
        BufferedReader reader = null;
        try {
            Path path = Paths.get("firstname_males.txt");
            reader = newBufferedReader(path);
            return readToList(reader);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        return Collections.emptyList();
    }

    /**
     * This method getFemaleFirstNames should make use of a try-catch with resources
     *
     * @return
     */
    public static List<String> getFemaleFirstNames() {
        Path path = Paths.get("firstname_female.txt");
        try (BufferedReader reader = newBufferedReader(path)) {
            return readToList(reader);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return Collections.emptyList();
    }


    /**
     * This method fetches strings from a file and put them into a list
     * This method might throw IOException which due to the throws clause need to
     * be handled by the caller.
     *
     * @return List <String> of last names
     * @throws IOException
     */
    public static List<String> getLastNames() throws IOException {
        try (BufferedReader reader = newBufferedReader(Paths.get("lastnames.txt"))) {
            return readToList(reader);
        }
    }


    public static void saveLastNames(List<String> lastNames) {
        Path path = Paths.get("lastnames.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            for (String toWrite : lastNames) {
                writer.append(toWrite + ",");
            }
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void saveFemaleNames(List<String> femaleNames) {
        Path path = Paths.get("firstname_female.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            for (String toWrite : femaleNames) {
                writer.append(toWrite + ",");
            }
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }


    public static void saveMaleNames(List<String> maleNames) {
        Path path = Paths.get("firstname_males.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            for (String toWrite : maleNames) {
                writer.append(toWrite + ",");
            }
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
