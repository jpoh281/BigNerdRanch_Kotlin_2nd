import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
public class Jhava {

    private int hitPoints = 52489112;
    private String greeting = "BLARGH";

    public static void main(String[] args){
        System.out.println(Hero.makeProclamation());

        System.out.println("Spells:");
        Spellbook spellbook = new Spellbook();
        for (String spell : spellbook.spells){
            System.out.println(spell);
        }
        System.out.println("Max spell count: " + Spellbook.maxSpellCount);
        Spellbook.getSpellbookGreeting();
    }

    @NotNull
    public String utterGreeting() {
        return greeting;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Nullable
    public String determineFriendshipLevel() {
        return null;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void offerFood() {
        Hero.handOverFood("pizza");
    }
}
