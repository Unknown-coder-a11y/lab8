import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.logging.LogManager;

interface WorkRole {
    String performTask();
}

abstract class Livestock {
    private static final Logger logger = LogManager.getLogger(Livestock.class);

    public static Logger getLogger() {
        return logger;
    }
    
    String name;
    int age;

    public Livestock(String name, int age) {
        this.name = name;
        this.age = age;
        logger.info("Livestock created: name={}, age={}", name, age);
    }

    abstract String makeSound();

    String makeSound(int volume) {
        String sound = makeSound();
        String result = (volume > 5) ? sound.toUpperCase() + "!!!" : sound;
        logger.debug("makeSound invoked: name={}, volume={}, result={}", name, volume, result);
        return result;
    }

    void graze() {
        logger.info("{} is grazing.", name);
        System.out.println(name + " talbaid belcine.");
    }

    void graze(String food) {
        logger.info("{} is grazing on {}.", name, food);
        System.out.println(name + " " + food + "-iig belcine.");
    }
}

class Herd {
    private static final Logger logger = LogManager.getLogger(Herd.class);

    ArrayList<Livestock> livestock = new ArrayList<>();

    void addLivestock(Livestock animal) {
        livestock.add(animal);
        logger.info("Added livestock to herd: name={}, age={}", animal.name, animal.age);
    }

    void dailyRoutine() {
        logger.info("Starting daily routine for herd.");
        for (Livestock animal : livestock) {
            logger.info("Performing routine for {}", animal.name);
            System.out.println(animal.name + ": " + animal.makeSound());
            if (animal instanceof WorkRole) {
                String task = ((WorkRole) animal).performTask();
                logger.info("Task performed by {}: {}", animal.name, task);
                System.out.println(task);
            }
        }
    }
}

public class NomadLivestockDemo {
    private static final Logger logger = LogManager.getLogger(NomadLivestockDemo.class);

    public static void main(String[] args) {
        logger.info("NomadLivestockDemo started.");
        Herd herd = new Herd();
        herd.addLivestock(new Horse("Bayn", 4));
        herd.addLivestock(new Sheep("Chuluun", 2));
        herd.addLivestock(new Camel("Temur", 6));
        herd.addLivestock(new Goat("Bilguun", 3));

        logger.info("Executing daily routine.");
        System.out.println("Odriin uil ajillagaa:");
        herd.dailyRoutine();

        logger.info("Performing polymorphism tests.");
        System.out.println("\nPlimorphism turshilt:");
        Livestock[] animals = {
            new Horse("Tuul", 5),
            new Sheep("Huren", 1),
            new Camel("Govi", 7),
            new Goat("Tsagaan", 2)
        };

        for (Livestock animal : animals) {
            animal.graze();
            if (animal instanceof Goat) {
                ((Goat)animal).graze("but");
            } else {
                animal.graze("uvs");
            }

            logger.debug("Sound with volume for {}: {}", animal.name, animal.makeSound(6));
            System.out.println(animal.name + " guugarahdaa: " + animal.makeSound(6));
        }

        Goat goat = new Goat("Shar", 4);
        logger.info("Testing goat sounds.");
        System.out.println("\nYmaani duu:");
        System.out.println("Engiin duu: " + goat.makeSound());
        System.out.println("Uurtai duu: " + goat.makeSound(true));
        logger.info("NomadLivestockDemo completed.");
    }
}
