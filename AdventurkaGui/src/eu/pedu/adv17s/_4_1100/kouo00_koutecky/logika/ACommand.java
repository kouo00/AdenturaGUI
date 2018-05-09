/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv17s._4_1100.kouo00_koutecky.logika;

import eu.pedu.adv17s_fw.empty_classes.ANamed;
import eu.pedu.adv17s_fw.game_txt.IAction;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



/*******************************************************************************
 * The {@code AAction} abstract class is a common superclass of all classes,
 * the instances of which are responsible for interpretation of commands
 * entered by the user playing the game.
 * Name of the executed action is usually the first word of the entered command.
 * The further words are interpreted as arguments.
 * <p>
 * You can define also a command that opens the conversation
 * (e.g. with a person present in the room) and thus switch to the mode,
 * in which the entered texts are not interpreted as commands,
 * but are passed to the defined object up to moment,
 * when the conversation ends and the object controlling the dialogue
 * switches the game back to the basic command mode.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2017-Summer
 */
abstract class ACommand
       extends ANamed
    implements IAction
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
    private static final Map<String, ACommand> NAME_2_COMMAND = new HashMap<>();
     static boolean isAlive = false;
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
    static {
        new CommandHelp();
        new CommandMove();
        new CommandPickUp();
        new CommandPutDown();
        new CommandNaplnit();
        new CommandSmichat();
        new CommandUvarit();
        new Commanddat_Napit();
        new CommandPolibit();
        new Command_MAGIE_();
        new CommandExit();
        }

//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================

//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================




//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============



    /** Stručný popis daného příkazu. */
        private final String description;


//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Creates the parent sub-object of the created action.
     *
     * @param name  Name of the created action = text, which the player has to
     *              enter as an initial word of the entered command
     * @param description Brief description of the created action
     */
    
            
    public ACommand(String name, String description)
    {
        super(name);
        this.description = description;

        ACommand previous = NAME_2_COMMAND.put(name.toLowerCase(), this);
        if (previous != null)
        {
            throw new IllegalArgumentException(
                    "\nAkce s názvem «" + name + "» byl již vytvořena");
        }
    }
    
    
    static String executeCommand(String command)
             {
           command = command.trim();
           String answer;
           if (isAlive){
               answer = executeCommonComand(command);
           }
           else {
               answer = startGame(command);
           }
          // answer = answer + "\n" + Potion.getInstance().getVeciKolekce();
           return answer;
       }     

    private static String executeCommonComand(String commandLine)
    {
        String[] words = commandLine.toLowerCase().split("\\s+");
        String commandName = words[0];
        ACommand command = NAME_2_COMMAND.get(commandName);
        String answer;
        if (command == null) {
            answer = "prazdny retezec.";
            }
        else {
            answer = command.execute(words);
        }
        return answer;
    }
    
    private static String startGame(String command)
    {
        String answer;
            if (command.isEmpty()) {
            answer = ("Vaším úkolem je najít věci potřebné k uvaření lektvaru, poté ho"
            		+ " uvařit nad ohništěm, dát ho vypít \nblondýnce a na závěr ji "
            		+ "věnovat polibek. Pozor! Ne všechny předměty, které lze"
            		+ " najít, do lektvaru patří!");
                    isAlive = true;
               initialize();
            }
            else {
                answer = "\nThe first command is not the starting one.\n"
          + "Game that does not run can be started "
          + "only with a starting command.\n";
                 }
        return answer;
        }

    static Collection<ACommand> getAllActions(){
        return Collections.unmodifiableCollection(NAME_2_COMMAND.values());
    }

//\IA== INSTANCE ABSTRACT METHODS ==============================================

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
    abstract
    public String execute(String... arguments)
    ;

   static void stopGame()
    {
        isAlive = false;
    }
  
    static boolean isAlive()
    {
        return isAlive;
    }
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the action description with explanation of its function
     * and the meaning of individual parameters.
     *
     * @return Action description
     */
    
    @Override
    public String getDescription()
    {
        return description;
    }

    static void initialize(){
        Potion.getInstance().initialize();
        Bag.getInstance().initialize();
    }
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}

