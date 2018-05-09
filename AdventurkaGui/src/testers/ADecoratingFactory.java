/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv17s.testers;

import eu.pedu.adv17s_fw.game_txt.IGSMFactory;
import java.util.function.Supplier;



/*******************************************************************************
 * Instance třídy {@code ADecoratingFactory} představují ...
 * The {@code ADecoratingFactory} class instances represent ...
 *
 * @param <SM> Typ dekorujícího správce scénářů
 * @param <G> Typ dekorující hry
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 0.00.0000 — 20yy-mm-dd
 */
abstract
public class ADecoratingFactory
//                               <SM extends ADecoratingScenarioManager,
//                                G  extends ADecoratingGame>
  implements IGSMFactory
{
    static {
        System.out.println("+++++Start loading the class: ADecoratingFactory");
    }
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//
//    /***************************************************************************
//     * Returns the instance of the given factory class with a nullary
//     * (=&nbsp;parameterless) constructor.
//     *
//     * @param <DSM> The decorating scenario type
//     * @param <DG>  The decorating game type
//     * @param <DF>  The decorating factory class type
//     * @param factoryClass  Factory class' class-object
//     * @return Factory object
//     * @throws IllegalArgumentException Creation of the instance failed
//     */
//    public static <DSM extends ADecoratingScenarioManager,
//                   DG  extends ADecoratingGame,
//                   DF  extends ADecoratingFactory<DSM, DG>>
//           DF getInstanceOfFactory(Class<DF> factoryClass)
//    {
//        DF result;
//        try {
//            result = factoryClass.newInstance();
//        }
//        catch (InstantiationException | IllegalAccessException ex) {
//            throw new IllegalArgumentException(
//                "\nFailed creation of instance of the " + factoryClass, ex);
//        }
//        return result;
//    }
//


//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Tovární objekt dekorovaných instancí. */
    protected IGSMFactory decoratedFactory;

    /** Poskytovatel dekorujícího správce scénářů. */
    protected final Supplier<ADecoratingScenarioManager> managerSupplier;

    /** Poskytovatel dekorující hry. */
    protected final Supplier<ADecoratingGame> gameSupplier;


//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří rodičovský podobjekt dekorujících továrních objektů.
     *
     * @param decoratedFaftory  Tovární objekt dekorovaných instancí
     * @param managerSupplier   Poskytovatel dekorujícího správce scénářů
     * @param gameSupplier      Poskytovatel dekorující hry
     */
    public
    ADecoratingFactory(IGSMFactory decoratedFaftory,
                       Supplier<ADecoratingScenarioManager> managerSupplier,
                       Supplier<ADecoratingGame>            gameSupplier)
    {
        this.decoratedFactory = decoratedFaftory;
        this.managerSupplier  = managerSupplier;
        this.gameSupplier     = gameSupplier;
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
        return decoratedFactory.getAuthorID();
    }


    /***************************************************************************
     * Returns the program author name in format <b>SURNAME First-name</b>,
     * i.e. firstly the surname in capital letters followed by first name,
     * with only the first capital letter, other letters will be small.
     * If the program author has more first names, he can quote all of them.
     *
     * @return Program author name in format SURNAME First-name
     */
    @Override
    public String getAuthorName()
    {
        return decoratedFactory.getAuthorName();
    }


    /***************************************************************************
     * Vrátí dekorovaný tovární objekt.
     *
     * @return Dekorovaný tovární objekt
     */
    public IGSMFactory getDecoratedFactory()
    {
        return decoratedFactory;
    }


    /***************************************************************************
     * Returns the instance of the scenario manager.
     * Until the appropriate class is not fully defined,
     * it throws the {@link UncompletedMethodException}.
     *
     * @return Required scenario manager
     */
    @Override
    public ADecoratingScenarioManager getScenarioManager()
    {
        return managerSupplier.get();
    }


    /***************************************************************************
     * Returns the instance of text version of the game.
     * Until the appropriate class is not fully defined,
     * it throws the {@link UncompletedMethodException}.
     *
     * @return Required game
     */
    @Override
    public ADecoratingGame getGame()
    {
        return gameSupplier.get();
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
    static {
        System.out.println("-----End loading the class: ADecoratingFactory");
    }
}
