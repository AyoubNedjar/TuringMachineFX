package model;

import java.util.List;

/**
 * The Problem class represents a game problem with associated attributes such as number, difficulty, luck, and a secret code.
 */
public class Problem {
    private int nProblem;
    private int difficulty;
    private int luck;
    private Code secretCode;
    private List<Integer> validIndexList;

    /**
     * Retrieves the problem number.
     *
     * @return The problem number.
     */
    public int getnProblem() {
        return nProblem;
    }

    /**
     * Sets the problem number.
     *
     * @param nProblem The problem number to set.
     */
    public void setnProblem(int nProblem) {
        this.nProblem = nProblem;
    }

    /**
     * Retrieves the difficulty level of the problem.
     *
     * @return The difficulty level.
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty level of the problem.
     *
     * @param difficulty The difficulty level to set.
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Retrieves the luck factor associated with the problem.
     *
     * @return The luck factor.
     */
    public int getLuck() {
        return luck;
    }

    /**
     * Sets the luck factor associated with the problem.
     *
     * @param luck The luck factor to set.
     */
    public void setLuck(int luck) {
        this.luck = luck;
    }

    /**
     * Retrieves the secret code associated with the problem.
     *
     * @return The secret code.
     */
    public Code getSecretCode() {
        return secretCode;
    }

    /**
     * Sets the secret code for the problem.
     *
     * @param code The string representation of the secret code to set.
     */
    public void setSecretCode(String code) {
        int c1 = Integer.parseInt(code.charAt(0) + "");
        int c2 = Integer.parseInt(code.charAt(1) + "");
        int c3 = Integer.parseInt(code.charAt(2) + "");

        this.secretCode = new Code(c1, c2, c3);
    }

    /**
     * Retrieves the list of valid index values associated with the problem.
     *
     * @return The list of valid index values.
     */
    public List<Integer> getValidIndexList() {
        return validIndexList;
    }

    /**
     * Sets the list of valid index values for the problem.
     *
     * @param validList The list of valid index values to set.
     */
    public void setValidList(List<Integer> validList) {
        this.validIndexList = validList;
    }
}
