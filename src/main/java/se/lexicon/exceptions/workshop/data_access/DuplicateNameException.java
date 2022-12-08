package se.lexicon.exceptions.workshop.data_access;

public class DuplicateNameException extends Exception {

    private final String name;


    public DuplicateNameException(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
