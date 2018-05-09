/* Saved in UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv17s.testers;

import eu.pedu.adv17s_fw.empty_classes.ANamed;

import eu.pedu.adv17s_fw.game_txt.BasicActions;
import eu.pedu.adv17s_fw.game_txt.IAction;
import eu.pedu.adv17s_fw.game_txt.IBag;
import eu.pedu.adv17s_fw.game_txt.IGSMFactory;
import eu.pedu.adv17s_fw.game_txt.IGame;
import eu.pedu.adv17s_fw.game_txt.IWorld;

import java.util.Collection;



/*******************************************************************************
//%L+ CZ
 * Třída Instance třídy {@code ADecoratingGame} představují společného rodiče
 * verzí demonstrační hry upravených podle některého modifikačního zadání.
//%Lx EN
     * The {@code ADecoratingGame} class instances represent ...
//%L-
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 0.00.0000 — 20yy-mm-dd
 */
abstract
public class ADecoratingGame
     extends ANamed
  implements IGame
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Jediná instance dané třídy -- jedináček. */
//    private static final Game SINGLETON =
//                     new Game();



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Implicitní verze povinná metody,
     * kterou musejí implementovat všichni potomci.
     *
     * @return Instance dekorujícího scénáře
     */
    public static ADecoratingGame getInstance()
    {
//        return SINGLETON;
        throw new UnsupportedOperationException("Not supported yet.");
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Identifikátor řešeného modifikačního zadání. */
    private final String TEST_ID;

    /** Dekorovaná hra. */
    protected final IGame decoratedGame;

    /** Tovární objekt dekorujícího řešení. */
    @SuppressWarnings("rawtypes")
    protected final ADecoratingFactory decoratingFactory;

    /** Tovární třída dekorujícího řešení. */
    @SuppressWarnings("rawtypes")
    protected final Class<? extends ADecoratingFactory> decoratingFactoryClass;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří rodičovský podobjekt dekorující hry,
     * který se chová jako stejně dekorovaná hra s výjimkou modifikací
     * vyžadovaných implementovaným modifikačním zadáním.
     *
     * @param testID            Identifikátor modifikovaného zadání
     * @param decoratedFactory  Tovární objekt dekorovaného řešení
     * @param decoratingFactory Tovární třída dekorujícího řešení
     */
    @SuppressWarnings("rawtypes")
    public ADecoratingGame(String             testID,
                           IGSMFactory        decoratedFactory,
                           ADecoratingFactory decoratingFactory)
//         Class<? extends ADecoratingFactory> decoratingFactoryClass)
    {
        super(testID + " - " + decoratedFactory.getGame().getName());
        this.TEST_ID                = testID;
        this.decoratedGame          = decoratedFactory.getGame();
        this.decoratingFactory      = decoratingFactory;
        this.decoratingFactoryClass = decoratingFactory.getClass();
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the program author identification string
     * written in CAPITAL LETTERS.
     * It is mostly the login into the school information system.
     *
     * @return Identification string (login) of the program author
     */
    @Override
    public String getAuthorID()
    {
        return decoratedGame.getAuthorID();
    }


    /***************************************************************************
     * Returns the program author identification string
     * written in CAPITAL LETTERS.
     * It is mostly the login into the school information system.
     *
     * @return Identification string (login) of the program author
     */
    @Override
    public String getAuthorName()
    {
        return decoratedGame.getAuthorName();
    }


    /***************************************************************************
     * Returns the testID of the instance.
     *
     * @return Name of the instance
     */
    @Override
    public String getName()
    {
        return TEST_ID + " - " + decoratedGame.getName();
    }


    /***************************************************************************
     * Vrátí dekorovanou hru.
     *
     * @return Dekorovaná hra
     */
    public IGame getDecorated()
    {
        return decoratedGame;
    }


    /***************************************************************************
     * Returns the class-object of the factory class, the instances of which
     * can mediate receiving of all key objects of the application,
     * the part of which is also the mother class of this instance.
     *
     * @return The class-object of the factory class
     */
    @Override
    @SuppressWarnings("rawtypes")
    public Class<? extends ADecoratingFactory> getFactoryClass()
    {
        return decoratingFactoryClass;
    }


    /***************************************************************************
     * Returns the class-object of the factory class, the instances of which
     * can mediate receiving of all key objects of the application,
     * the part of which is also the mother class of this instance.
     *
     * @return The class-object of the factory class
     */
    @Override
    @SuppressWarnings("rawtypes")
    public ADecoratingFactory getFactory()
    {
        return decoratingFactory;
    }


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
        return decoratedGame.isAlive();
    }


    /***************************************************************************
     * Returns the bag to which the player will save the taken items.
     *
     * @return The bag to which the player saves the taken items
     */
    @Override
    public IBag getBag()
    {
        return decoratedGame.getBag();
    }


    /***************************************************************************
     * Returns the collection of all actions usable in the game.
     *
     * @return The collection of all actions usable in the game
     */
    @Override
    public Collection<? extends IAction> getAllActions()
    {
        return decoratedGame.getAllActions();
    }


    /***************************************************************************
     * Returns the crate with names of mandatory actions, i.e. actions for
     * <ul>
     *   <li>moving into neighboring place,</li>
     *   <li>taking item from the current place and putting it into bag,</li>
     *   <li>taking item from the bag and
     *       putting it down into the current place,</li>
     *   <li>asking for help,</li>
     *   <li>immediate game termination.</li>
     * </ul>
     *
     * @return The crate with names of mandatory actions
     */
    @Override
    public BasicActions getBasicActions()
    {
        return decoratedGame.getBasicActions();
    }


    /***************************************************************************
     * Returns the world in which the game takes place.
     *
     * @return The world in which the game takes place
     */
    @Override
    public IWorld getWorld()
    {
        return decoratedGame.getWorld();
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
        return decoratedGame.executeCommand(command);
    }


    /***************************************************************************
     * Ends the whole game and returns the allocated resources.
     */
    @Override
    public void stop()
    {
        decoratedGame.stop();
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
