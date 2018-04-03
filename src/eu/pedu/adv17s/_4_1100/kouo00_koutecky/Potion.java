/* The file is saved in UTF-8 codepage.
 * Check: Â«StereotypeÂ», Section mark-Â§, Copyright-Â©, Alpha-Î±, Beta-Î˛, Smile-â�ş
 */
package eu.pedu.adv17s._4_1100.kouo00_koutecky;

import eu.pedu.adv17s_fw.game_txt.INamed;
import java.util.Collection;
import eu.pedu.adv17s_fw.game_txt.IWorld;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;




/*******************************************************************************
 * An instance of the {@code Potion} class represents the game world.
 * It should be defined as a singleton.
 * It is responsible for arrangement of individual places and keeps information,
 * in which place the player is just situated.
 * The mutual arrangement may change during the game,
 * the places can gain and/or lose their neighbors.
 * <p>
 * In this game the world is ...
 *
 * @author  Rudolf PECINOVSKĂť
 * @version 2017-Summer
 */
public   class Potion
    implements IWorld
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** The only instance (singleton) of this world. */
    private static final Potion SINGLETON = new Potion();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * The factory method returning the only existing instance of the game.
     *
     * @return The only instance of the given game
     */
    static Potion getInstance()
    {
        return SINGLETON;
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

        
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================

private final Collection<Room> prostory;
private Room currentPlace;
private Collection<String> nevideno;



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * The private constructor creating the only instance of the place world.
     * Within this manager definition it creates all game places.
     */
    private Potion()
    {
        prostory = new ArrayList<>();
        prostory.add(new Room("stan", 
                            new String[] {"louka"},
                            "_lahvicka","#spacak", "#polstarek"));
        prostory.add(new Room("louka", 
                            new String[] {"stan", "uskali", "haj", "plaz"},
                            "#blondynka"));
        prostory.add(new Room("haj", 
                            new String[] {"louka", "hvozd"},
                            "_ruze"));
        prostory.add(new Room("hvozd", 
                            new String[] {"haj", "jeskyne"},
                            "_mech"));
        prostory.add(new Room("jeskyne", 
                            new String[] {"hvozd"},
                            "_drahokam"));
        prostory.add(new Room("plaz", 
                            new String[] {"louka"},
                            "#jezero"));
        prostory.add(new Room("uskali", 
                            new String[] {"louka"},
                            "#ohniste","_kamen"));
        

    }
    
//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the collection of all places of the game.
     *
     * @return Collection of all places performing in the game
     */
    @Override
    public Collection<Room> getAllPlaces()
    {
        return Collections.unmodifiableCollection(prostory);
    }


    /***************************************************************************
     * Returns the current place,
     * i.e. to the place in which the player is just situated.
     *
     * @return The place in which the player is just situated
     */
    @Override
    public Room getCurrentPlace()
    {
        return currentPlace;
    }
    
    public void setCurrentPlace(Room Budova)
    {
        currentPlace = Budova;
    }

  void initialize()
   {  
       currentPlace = INamed.getO("stan", prostory).get();
       prostory.forEach(Room::initialize);
       nevideno = new ArrayList<>();
        nevideno.add("blondynka");
        nevideno.add("ruze");
        nevideno.add("mech");
        nevideno.add("drahokam");
        nevideno.add("jezero");
        nevideno.add("kamen");
        nevideno.add("ohniste");
     }
    
    public String getVeciKolekce(){
        String odpoved = nevideno.stream()
                .collect(Collectors.joining(", ", "\nPředměty, na které jste zaím nenarazili: [", "]"
                		+ "\n__________________________________DALŠÍ PŘÍKAZ__________________________________\n"));
        return odpoved;
    }
    
    public void odebraniPredmetu(Thing nazev){
        nevideno.remove(nazev.getName());
    }
  
  Room getProstory(String name)
        {  
            Optional<Room> prostor = INamed.getO(name, prostory);
            Room result = prostor.orElse(null);
            return result;
        }
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}

