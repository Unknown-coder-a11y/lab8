import java.util.ArrayList;

// Абстракт класс
abstract class Livestock {
    String name;
    int age;

    public Livestock(String name, int age) {
        this.name = name;
        this.age = age;
    }

    abstract String makeSound();
    
    // Хэт ачаалсан метод
    String makeSound(int volume) {
        String sound = makeSound();
        if (volume > 5) {
            return sound.toUpperCase() + "!!!";
        }
        return sound;
    }

    void graze() {
        System.out.println(name + " talbaid belcine.");
    }

    void graze(String food) {
        System.out.println(name + " " + food + "-iig belcine.");
    }
}

// Интерфейс
interface WorkRole {
    String performTask();
}

// Морь класс
class Horse extends Livestock implements WorkRole {
    public Horse(String name, int age) {
        super(name, age);
    }

    @Override
    String makeSound() {
        return "Yntsgaana!";
    }

    @Override
    public String performTask() {
        return "Mori talbaid unalgad hereglegdene.";
    }
}

// Хонь класс
class Sheep extends Livestock {
    public Sheep(String name, int age) {
        super(name, age);
    }

    @Override
    String makeSound() {
        return "Maa!";
    }
}

// Тэмээ класс
class Camel extends Livestock implements WorkRole {
    public Camel(String name, int age) {
        super(name, age);
    }

    @Override
    String makeSound() {
        return "Builna!";
    }

    @Override
    public String performTask() {
        return "Temee goviin teevert hereglegdene.";
    }
}

// Ямаа класс (шинээр нэмэгдсэн)
class Goat extends Livestock implements WorkRole {
    public Goat(String name, int age) {
        super(name, age);
    }

    @Override
    String makeSound() {
        return "Mee!";
    }
    
    // Хэт ачаалсан makeSound
    String makeSound(boolean angry) {
        if (angry) {
            return "MEEEE!!!";
        }
        return makeSound();
    }

    @Override
    public String performTask() {
        return "Ymaa suu gargahad herglegdene.";
    }
}

// Сүрэг класс
class Herd {
    ArrayList<Livestock> livestock = new ArrayList<>();

    void addLivestock(Livestock animal) {
        livestock.add(animal);
    }

    void dailyRoutine() {
        for (Livestock animal : livestock) {
            System.out.println(animal.name + ": " + animal.makeSound());
            if (animal instanceof WorkRole) {
                System.out.println(((WorkRole) animal).performTask());
            }
        }
    }
}

// Үндсэн класс
public class NomadLivestockDemo {
    public static void main(String[] args) {
        Herd herd = new Herd();
        herd.addLivestock(new Horse("Bayn", 4));
        herd.addLivestock(new Sheep("Chuluun", 2));
        herd.addLivestock(new Camel("Temur", 6));
        herd.addLivestock(new Goat("Bilguun", 3));

        System.out.println("Odriin uil ajillagaa:");
        herd.dailyRoutine();
        
        System.out.println("\nPlimorphism turshilt:");
        Livestock[] animals = {
            new Horse("Tuul", 5),
            new Sheep("Huren", 1),
            new Camel("Govi", 7),
            new Goat("Tsagaan", 2)
        };
        
        for (Livestock animal : animals) {
            animal.graze();
            // Хэт ачаалсан graze метод дуудах
            if (animal instanceof Goat) {
                ((Goat)animal).graze("but");
            } else {
                animal.graze("uvs");
            }
            
            // Хэт ачаалсан makeSound дуудах
            System.out.println(animal.name + " guugarahdaa: " + animal.makeSound(6));
        }
        
        // Ямааны онцгой хэт ачаалсан метод
        Goat goat = new Goat("Shar", 4);
        System.out.println("\nYmaani duu:");
        System.out.println("Engiin duu: " + goat.makeSound());
        System.out.println("Uurtai duu: " + goat.makeSound(true));
    }
}