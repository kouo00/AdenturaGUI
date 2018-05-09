/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv17s._4_1100.kouo00_koutecky.logika;

import java.util.Collection;
import eu.pedu.adv17s_fw.game_txt.IPlace;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;



/*******************************************************************************
 * Instances of the {@code Room} class represent the places in the game.
 *
 * We can take the place visiting as a partial goal,
 * which the player tries to reach.
 * The places can be rooms, planets, life stages etc.
 * The places can contain various items.that may help user to reach the goal.
 * Each place knows its current neighboring places and it knows
 * which items it currently contains.
 * The neighbors as well as the contained items can change during the game.
 * <p>
 * In this program the places are ...
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2017-Summer
 */
public   class Room
       extends ThingContainer
    implements IPlace
{
    private double x;
    private double y;
    private Collection<Room> neighbors;
    private Collection<Thing> veci;
    private Set<Room> vychody;
    private Potion potion;
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT ATTRIBUTES/FIELDS) ===============
String mix = "lahvicka_s_vodou";


/** Názvy sousedů místnosti na počátku hry. */
    private  String[] neighborNames;

/** Názvy objektů v místnosti na počátku hry. */
    private  String[] objectNames;
    
   
  
  //\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) =============

    Room(String name, double x, double y,Potion potion, String[] neighborNames, String... objectNames)
    {
        super(name, objectNames);
        this.neighborNames  = neighborNames;
        this.objectNames    = objectNames;
        this.x = x;
        this.y = y;
        this.potion = potion;
        vychody = new HashSet<>();
        initialize();
    }
    
    public void setVychod(Room vedlejsi) {
        vychody.add(vedlejsi);
    }
  
    
  

//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================


//\IA== INSTANCE ABSTRACT METHODS ==============================================

    
  
    
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the collection of current neighbors of this place, i.e. the
     * collection of places, to which we can move from this place with the
     * command of the {@link eu.pedu.adv17s_fw.scenario.TypeOfStep#tsMOVE
     * TypeOfStep.tsMOVE} type.
     *
     * @return Collection of neighbors
     */
    @Override
    public Collection<Room> getNeighbors()
    {
        return Collections.unmodifiableCollection(neighbors);
    }

    

    /***************************************************************************
     * Returns a collection of items located in the given place.
     *
     * @return Collection of items located in the given place
     */
 
    void initialize()
   { 
       initializeNeighbors();
       initializeItems();
   }
  
  private void initializeNeighbors()
    {
        Potion danceworld = potion;
        neighbors = Arrays.stream(neighborNames)
            .map(danceworld::getProstory)
            .collect(Collectors.toList());
    }
  
  public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
        
        public String seznamVychodu() 
    {
        String vracenyText = "vychody:";
        for (Room sousedni : vychody) {
             vracenyText += " " + sousedni.getName();
        }
        return vracenyText;
    }

        
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
