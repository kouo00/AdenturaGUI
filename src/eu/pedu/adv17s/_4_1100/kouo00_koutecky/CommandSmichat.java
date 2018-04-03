/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv17s._4_1100.kouo00_koutecky;




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
public class CommandSmichat
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
    public CommandSmichat()
    {
        super ("smichat", "smichat predvety z batohu");
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
//        if (arguments.length <3) {
//            return "Není na co čekat!";
//        }
//        String ObjectName1 = arguments[1];
//        String ObjectName2 = arguments[2];
//        if (!ObjectName1.equals("na"))
//        {return "Špatně zadaný příkaz!";}
//        if (!ObjectName2.equals("soutěži"))
//        {return "Špatně zadaný příkaz!";}
//        Room currentProstory = Potion.getInstance().getCurrentPlace();
//        Thing object = currentProstory.getObject("schopnosti");
//        Thing object2 = currentProstory.getObject("šaty");
//        Thing object3 = currentProstory.getObject("taneční_boty");
//        if(object2 == null || object3 == null || object == null){
//            return "Schopnosti, šaty nebo boty ti chybí!";}
//        else 
//        { 
//            
//            CommandExit.stopGame();
//            return "Vyhrál jsi soutěž!";}

        
        if (arguments.length <1) {
            return "spatny format prikazu";
        }
            Bag batoh = Bag.getInstance();
            Thing object1 = batoh.getObject("mech");
            Thing object2 = batoh.getObject("lahvicka_s_vodou");
            Thing object3 = batoh.getObject("ruze");
            Thing object4 = batoh.getObject("drahokam");
            Thing object5 = batoh.getObject("kamen");
            if(object5 != null){
            ACommand.stopGame();  
            return("smichal jsi kamen, prohral jsi!");    
            }
            if(object1 == null || object2 == null || object3 == null ||
                    object4 == null){
            return("nejaky predmet ti chybi!");    
            }
           
            batoh.removeObject (object1);
            batoh.removeObject (object2);
            batoh.removeObject (object3);
            batoh.removeObject (object4);
            Thing object6 = new Thing("_lahvicka_se_surovinami");
            batoh.addObject (object6);
            return "smiichali jste vsechny suroviny v lahvicce."; 
       
    }


//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
