/* The file is saved in UTF-8 codepage.
 * Check: Â«StereotypeÂ», Section mark-Â§, Copyright-Â©, Alpha-Î±, Beta-Î˛, Smile-â�ş
 */
package eu.pedu.adv17s._4_1100.kouo00_koutecky;

import eu.pedu.adv17s_fw.empty_classes.ANamed;
import eu.pedu.adv17s_fw.game_txt.BasicActions;
import eu.pedu.adv17s_fw.game_txt.IGame;

import java.util.Collection;



/*******************************************************************************
 * Instances of the {@code PotionGame} class are responsible for the game
 * logics. They are able to accept individual commands and provide information
 * on current state of the game and its parts.
 * <p>
 * The game class has to be defined as a singleton and,
 * besides methods declared in the {@link IGame} interface,
 * it has to define the {@code getInstance()} static factory method.
 * Fulfilling of this condition cannot be verified by the compiler, but they
 * can by verified by test utilizing the associated scenario manager.
 * <p>
 * {@code PotionGame} instances represent prototypes of game instances
 * which are not yet fully defined and serve only for completion
 * of the scenario managers features, which needs cooperation with the game.
 *
 * @author  Rudolf PECINOVSKĂť
 * @version 2017-Summer
 */
public   class PotionGame
       extends ANamed
    implements IGame, IAuthorKoutecky
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    private static final BasicActions BASIC_ACTIONS =
    new BasicActions("jit", "sebrat", "polozit", "help", "end");
                    
    /** The reference to the only instance (singleton) of this game. */
    private static final PotionGame SINGLETON = new PotionGame();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * The factory method returning the only instance of the given game.
     *
     * @return The instance of the given game
     */
    public static PotionGame getInstance()
    {
        return SINGLETON;
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============
//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * The private constructor defining the only instance of the game class.
     * Because it is private, it has to be defined despite its body is empty.
     */
    private PotionGame()
    {
        super("Vaším úkolem je najít věci potřebné k uvaření lektvaru, poté ho"
        		+ " uvařit nad ohništěm, dát ho vypít blondýnce a na závěr ji "
        		+ "věnovat polibek. Pozor! Ne všechny předměty, které lze"
        		+ " najít, do lektvaru patří!");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns information if the game is currently running.
     * The once started game cannot be started again.
     * If you want to start the game again you have to finish it firstly.
     *
     * @return If the game is running, it returns {@code true},
     *         otherwise it returns {@code false}
     */
    @Override
    public boolean isAlive()
    {
        return ACommand.isAlive();
    }


    /***************************************************************************
     * Returns the bag to which the player will save the taken items.
     *
     * @return The bag to which the player saves the taken items
     */
    @Override
    public Bag getBag()
    {
        return Bag.getInstance();
    }


    /***************************************************************************
     * Returns the collection of all actions usable in the game.
     *
     * @return The collection of all actions usable in the game
     */
    @Override
    public Collection<ACommand> getAllActions()
    {
        return ACommand.getAllActions();
    }


    /***************************************************************************
     * Returns the crate with names of mandatory actions, i.e. actions for
     * <ul>
     *   <li>moving into another place,</li>
     *   <li>taking item from the place and putting it into the bag,</li>
     *   <li>taking item from the bag and putting it down
     *       in the current place,</li>
     *   <li>asking for help,</li>
     *   <li>immediate game termination.</li>
     * </ul>
     *
     * @return The crate with names of mandatory actions
     */
    @Override
    public BasicActions getBasicActions()
    {
        return BASIC_ACTIONS;
    }
    

    /***************************************************************************
     * Returns the world in which the game takes place.
     *
     * @return The world in which the game takes place
     */
    @Override
    public Potion getWorld()
    {
        return Potion.getInstance();
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Processes the given command and returns the answer to the user.
     *
     * @param command The entered command
     * @return The answer of the game after processing the command
     */
    @Override
    public String executeCommand(String command)
    {
            return ACommand.executeCommand(command);
    }


    /***************************************************************************
     * Ends the whole game and returns the allocated resources.
     */
    @Override
    public void stop()
    {
        ACommand.stopGame();
    }

//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================

    @Override
    public String getAuthorName() 
    {
        return getScenarioManager().getAuthorName();
    }


    @Override
    public String getAuthorID() {
        return getScenarioManager().getAuthorID();
    }

    //@Override
    public PotionScenarioManager getScenarioManager()
    {
        return PotionScenarioManager.getInstance();
    }
}
