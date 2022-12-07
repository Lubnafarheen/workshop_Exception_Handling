package se.lexicon.exceptions.workshop.data_access;

public final class DuplicateNameException extends IllegalArgumentException {
    public DuplicateNameException(String message) {
        super(message);
    }
}
