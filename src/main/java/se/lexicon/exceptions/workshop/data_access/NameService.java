package se.lexicon.exceptions.workshop.data_access;

import java.util.List;
import java.util.Random;

import se.lexicon.exceptions.workshop.domain.Gender;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

public class NameService {

    private List<String> maleFirstNames;
    private List<String> femaleFirstNames;
    private List<String> lastNames;

    private static Random random = new Random();

    // should be no nulls
    public NameService(List<String> maleFirstNames, List<String> femaleFirstNames, List<String> lastNames) {
        if (maleFirstNames.isEmpty()) {
            throw new IllegalArgumentException("maleFirstNames is empty");
        } else if (femaleFirstNames.isEmpty()) {
            throw new IllegalArgumentException("femaleFirstNames is empty");
        } else if (lastNames.isEmpty()) {
            throw new IllegalArgumentException("lastNames is empty");
        }
        this.maleFirstNames = maleFirstNames;
        this.femaleFirstNames = femaleFirstNames;
        this.lastNames = lastNames;
    }

    public Person getNewRandomPerson() {
        if (getRandomGender() == Gender.MALE) {
            return new Person(getRandomMaleFirstName(), getRandomLastName(), getRandomGender());
        }
        return new Person(getRandomFemaleFirstName(), getRandomLastName(), getRandomGender());
    }


    public String getRandomFemaleFirstName() {
        return femaleFirstNames.get(random.nextInt(femaleFirstNames.size()));
    }

    public String getRandomMaleFirstName() {
        return maleFirstNames.get(random.nextInt(maleFirstNames.size()));
    }

    public String getRandomLastName() {
        return lastNames.get(random.nextInt(lastNames.size()));
    }

    public Gender getRandomGender() {
        return random.nextInt(100) > 50 ? Gender.FEMALE : Gender.MALE;
    }


    /**
     * Here you need to check if List<String> femaleFirstNames already contains the name
     * If name already exists throw a new custom exception you will have to create called
     * DuplicateNameException.
     *
     * @param name
     */
    public void addFemaleFirstName(String name) {
        if (name == null) throw new IllegalArgumentException("Name can not be null");
        if (femaleFirstNames.contains(name)) throw new DuplicateNameException("Name already exists");
        else
            femaleFirstNames.add(name);
        CSVReader_Writer.saveFemaleNames(femaleFirstNames);

    }

    /**
     * Here you need to check if List<String> maleFirstNames already contains the name
     * If name already exists throw a new custom exception you will have to create called
     * DuplicateNameException.
     *
     * @param name
     */
    public void addMaleFirstName(String name) {
        if (name == null) throw new IllegalArgumentException("Name was null");
        if (maleFirstNames.contains(name)) throw new DuplicateNameException("Name already exists");
        else
            maleFirstNames.add(name);
        CSVReader_Writer.saveMaleNames(maleFirstNames);
    }

    /**
     * Here you need to check if List<String> lastNames already contains the name
     * If name already exists throw a new custom exception you will have to create called
     * DuplicateNameException.
     *
     * @param lastName
     */
    public void addLastName(String lastName) {
        if (lastName == null) throw new IllegalArgumentException("lastName was null");
        if (lastNames.contains(lastName)) throw new DuplicateNameException("lastName already exists");
        else
            lastNames.add(lastName);
        CSVReader_Writer.saveLastNames(lastNames);
    }


}
