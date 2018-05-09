/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv17s._4_1100.kouo00_koutecky.logika;

import eu.pedu.adv17s_fw.game_txt.INamed;
import eu.pedu.adv17s_fw.test_util.default_game.DATA.Texts;
import java.util.Optional;




/*******************************************************************************
 * Instances of the {@code EmptyAction} class process the commands, which
 * ???.
 * <p>
 * Instances of the action classes are effectively singletons,
 * however we do not need to ensure it explicitely, because for their creation
 * and further management the specified action manager takes care
 * which ensures the only instance of each such class.
 * </p>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2017-Summer
 */
public class Command_MAGIE_
     extends ACommand
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
    
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates the action instance for ...
     */
    public Command_MAGIE_()
    {
        super ("_MAGIE_", "kouzlo");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Processes the command composed from the given words
     * and returns the game answer to the user.
     * Number of word depends on particular action, however it must be
     * at least one, because the zeroth element contains the action name.
     * The remaining words are arguments of this action and they may differ:
     * the actions of <i>end</i> and <i>help</i> type do not have any,
     * the actions of <i>go</i> and <i>take</i> type have one,
     * the actions of <i>apply</i> type ) can have two (e.g. apply key lock)
     * or three (e.g. apply key to lock) etc.
     *
     * @param arguments Action arguments –
     *                  their number can be different for each action,
     *                  but for all execution of the same action is the same
     * @return The answer of the game after processing the command
     */
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2){
            return "Příkaz _MAGIE_ neznám.";
        }

        String kuzlo = arguments[1];
        Bag bag = Bag.getInstance();
        if(kuzlo.toLowerCase().matches("_kouzlo_")){
        bag.kouzli();
             bag.tryAddObject (new Thing("__KOUZLO_"));
             return "Získali jste kouzlo, které vám usnadní dosažení cíle.";
            }
        
            if(kuzlo.toLowerCase().matches("_konec_")){
            Thing vecicka = (bag.getObject("__KOUZLO_"));
            if(vecicka == null){
            return "Příkaz _MAGIE_ neznám.";
            }
            
            Room   currentRoom     = Potion.getInstance().getCurrentPlace();
            Optional<Room> oDestination = INamed.getO("louka",
                    Potion.getInstance().getAllPlaces());
            Room destinationRoom = oDestination.get();
            
            Potion apartman = Potion.getInstance();
            apartman.setCurrentPlace(destinationRoom);
            bag.removeAll();
            bag.tryAddObject (new Thing("_lahvicka"));
            bag.tryAddObject (new Thing("_hovno"));
            return ("Úspěšně jste se dostal z osrova a tím vyhrál hru.\n" +
                    "GRATULUJU");
        }
                return "Příkaz _MAGIE_ neznám.";
    }


}


//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================


