import java.util.List;
import java.util.ArrayList;

/** Class to check an allergy score against a list of known
    allergens.
*/
public class Allergies {
    /**
       Construct an Allergies object
       @param allergyScore a bitfield of positive allergies.
    */
    public Allergies(int allergyScore) {
        this.allergyScore = allergyScore;
    }

    /**
       Determine if is allergic to the specified allergen.
       @param allergen the enumerated Allergen bit mask.
       @return true if allergic to the specified allergen, false if not
    */
    boolean isAllergicTo(Allergen allergen) {
        boolean isAllergic = false;
        if ((allergen.getScore() & this.allergyScore) != 0) {
            isAllergic = true;
        }
        return isAllergic;
    }

    /** Get list of all known allergies for the current allergy score.
        @return all known allergies for the current allergy score.
     */
    public List<Allergen> getList() {
        List<Allergen> allergyList = new ArrayList<Allergen>();
        for (Allergen allergen : Allergen.values()) {
            if (isAllergicTo(allergen)) {
                allergyList.add(allergen);
            }
        }
        return allergyList;
    }

    /** allergyScore bit-field of all known allargies */
    private int allergyScore;

}
