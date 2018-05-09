/* The file is saved in UTF-8 codepage.
 * Check: Â«StereotypeÂ», Section mark-Â§, Copyright-Â©, Alpha-Î±, Beta-Î˛, Smile-â�ş
 */
package eu.pedu.adv17s._4_1100.kouo00_koutecky.logika;

import eu.pedu.adv17s_fw.game_txt.INamed;
import java.util.Collection;
import eu.pedu.adv17s_fw.game_txt.IWorld;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
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
public   class Potion extends Observable
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
    public Potion()
    {
        prostory = new ArrayList<>();
        
        Room stan = new Room("stan",0,0,this, 
                            new String[] {"louka"},
                            "_lahvicka","#spacak", "#polstarek");
        Room louka = new Room("louka",0,0,this, 
                            new String[] {"stan", "uskali", "haj", "plaz"},
                            "#blondynka");
        Room haj = new Room("haj",0,0,this, 
                            new String[] {"louka", "hvozd"},
                            "_ruze");
        Room hvozd = new Room("hvozd",0,0,this, 
                            new String[] {"haj", "jeskyne"},
                            "_mech");
        Room jeskyne = new Room("jeskyne",0,0,this, 
                            new String[] {"hvozd"},
                            "_drahokam");
        Room plaz = new Room("plaz",0,0,this, 
                            new String[] {"louka"},
                            "#jezero");
        Room uskali = new Room("uskali",0,0,this, 
                            new String[] {"louka"},
                            "#ohniste","_kamen");
        
        
        louka.setVychod(haj);
        louka.setVychod(stan);
        louka.setVychod(plaz);
        haj.setVychod(louka);
        stan.setVychod(louka);
        plaz.setVychod(louka);
        haj.setVychod(hvozd);
        hvozd.setVychod(haj);
        hvozd.setVychod(jeskyne);
        jeskyne.setVychod(hvozd);
        
        
        prostory.add(stan);
        prostory.add(louka);
        prostory.add(haj);
        prostory.add(hvozd);
        prostory.add(jeskyne);
        prostory.add(plaz);
        prostory.add(uskali);
 
        
        setCurrentPlace(stan);
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
  
  @Override
    public void notifyObservers(){
        setChanged();
        super.notifyObservers();
    }
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}

