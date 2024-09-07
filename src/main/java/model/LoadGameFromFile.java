package model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The LoadGameFromFile class handles loading problems from files and manages a collection of problems.
 * It implements the Singleton pattern to ensure a single instance throughout the application.
 */
public class LoadGameFromFile {

    private static final LoadGameFromFile INSTANCE = new LoadGameFromFile();
    private HashMap<Integer, Problem> problemHashMap;

    /**
     * Private constructor to initialize the problemHashMap and load problems from files.
     */
    private LoadGameFromFile() {
        this.problemHashMap = new HashMap<>();
        loadProblemsFromFiles();
    }

    /**
     * Returns the singleton instance of LoadGameFromFile.
     *
     * @return The singleton instance of LoadGameFromFile.
     */
    public static LoadGameFromFile getInstance() {
        return INSTANCE;
    }

    /**
     * Loads problems from files and populates the problemHashMap.
     */
    private void loadProblemsFromFiles() {
        try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("Known_problems.csv")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
            int i = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (i != 0) {
                    Problem pb = getProblemFromText(line);
                    problemHashMap.put(pb.getnProblem(), pb);
                }
                i++;
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Creates a Problem object from a text line read from the file.
     *
     * @param line The line containing problem details as a comma-separated string.
     * @return A Problem object created from the input text.
     */
    private Problem getProblemFromText(String line) {
        // Implementation details for creating a Problem object from text
        String[] split = line.split(",");

        Problem pb = new Problem();
        pb.setnProblem(Integer.parseInt(split[0]));
        pb.setDifficulty(Integer.parseInt(split[1]));
        pb.setLuck(Integer.parseInt(split[2]));
        pb.setSecretCode(split[3]);

        List<Integer> myListIndexValidator = new ArrayList<>();
        for (int i = 4; i < split.length; i++) {
            myListIndexValidator.add(Integer.parseInt(split[i]));
        }
        pb.setValidList(myListIndexValidator);

        return pb;
    }

    /**
     * Retrieves a specific problem based on its ID.
     *
     * @param id The ID of the problem to retrieve.
     * @return The Problem object associated with the provided ID.
     */
    public Problem getProblem(int id) {
        return getProblemHashMap().get(id);
    }

    /**
     * Retrieves a list of problems based on the difficulty level.
     *
     * @param nbDifficulty The difficulty level of problems to retrieve.
     * @return A list of Problem objects with the specified difficulty.
     */
    public List<Problem> getProblemsFromDifficulty(int nbDifficulty) {
        return problemHashMap.values().stream()
                .filter(problem -> problem.getDifficulty() == nbDifficulty)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the HashMap containing all loaded problems.
     *
     * @return The HashMap with problem IDs mapped to Problem objects.
     */
    public HashMap<Integer, Problem> getProblemHashMap() {
        return problemHashMap;
    }
}
