/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv17s._4_1100.kouo00_koutecky.logika;

import eu.pedu.adv17s_fw.empty_classes.ANamed;
import eu.pedu.adv17s_fw.game_txt.IItem;




/*******************************************************************************
 * Instances of the {@code Thing} class represent the items in places.
 * These items can be things as well as persons or other entities
 * (flowers, light, charm etc.)
 * <p>
 * Some of these items can qualify certain game or place properties
 * (the light is on), others may be determined for taken and so gain a property
 * (e.g. ability to go through a strange place), or capability
 * (e.g. key for unlocking the door, sward for killing the monster,
 * money for bribing the guard etc.),
 * <p>
 * You can define items which serve simultaneously as places and can
 * therefore contain other items (e.g. safe, window etc.).
 * You have to enter in these items/places with a special command
 * (e.g. <i>open safe</i>, <i>look_from window</i>, etc.) and leave them with
 * another special command (e.g. <i>close safe</i>, <i>shut window</i> etc.).
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2017-Summer
 */

public   class Thing
        extends ANamed
       implements IItem
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

/** Příznak nepřenositelnosti objektu. */
    static final char H = '#';

/** Příznak standardního přenositelného objektu. */
    static final char S = '_';

/** Váha nepřenositelného objektu. */
    private static final int HEAVY = Integer.MAX_VALUE;

//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//@param superName Supernázev, jehož počátečním znakem je informace
//o váze a alkoholičnosti objektu a zbylé znaky
//představují vlastní název objektu
    
    Thing(String superName)
    {
        super(superName.substring(1));
        switch(superName.charAt(0))
        {
            case H:
                weight = HEAVY;
                break;
            case S:
                weight = 1;
                break;
         default:
            throw new IllegalArgumentException(
            "\nNepovolený počáteční znak supernázvu");  
        }        
      }
    
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
String mix = "lahvicka_s_vodou";

/** Váha objektu. */
    private final int weight;


//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates the item with the given name and other given properties.
     * These additional properties are entered through a prefix,
     * that is the first character of the given name.
     * The name of the item itself is created by the remaining letters.
     *
     * @param name The name of the created item
     */

//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    
    /***************************************************************************
     * Returns the item weight, and/or the corresponding characteristics.
     * The items that cannot be raised
     * have higher weight than the bag capacity is.
     *
     * @return Weight of the item
     */
    @Override
    public int getWeight()
    {
        return weight;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}

