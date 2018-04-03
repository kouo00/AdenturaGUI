/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package eu.pedu.adv17s.testers;

import eu.pedu.adv17s_fw.game_txt.IGSMFactory;
import eu.pedu.adv17s_fw.scenario.AScenarioManager;
import eu.pedu.adv17s_fw.scenario.Scenario;



/*******************************************************************************
//%L+ CZ
     * Instance třídy {@code ADecoratingScenarioManager} představují
     * správce scénářů dekorujícího jiného správce.
//%Lx EN
     * The {@code ADecoratingScenarioManager} class instances represent ...
//%L-
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 0.00.0000 — 20yy-mm-dd
 */
abstract
public class ADecoratingScenarioManager
     extends AScenarioManager
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** Jediná instance dané třídy -- jedináček. */
//    private static final ScenarioManager SINGLETON =
//                     new ScenarioManager();



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Implicitní verze povinná metody,
     * kterou musejí implementovat všichni potomci.
     *
     * @return Instance dekorujícího scénáře
     */
    public static ADecoratingScenarioManager getInstance()
    {
//        return SINGLETON;
        throw new UnsupportedOperationException("Not supported yet.");
    }



//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== CLASS (STATIC) REMAINING NON-PRIVATE METHODS ===========================
//\CP== CLASS (STATIC) PRIVATE AND AUXILIARY METHODS ===========================



//##############################################################################
//\IC== INSTANCE CONSTANTS (CONSTANT INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Dekorovaný správce scénářů. */
    protected final AScenarioManager delegate;

    /** Tovární třída dekorujícího řešení. */
    @SuppressWarnings("rawtypes")
    protected final Class<? extends ADecoratingFactory> decoratingFactoryClass;



//\IV== INSTANCE VARIABLES (VARIABLE INSTANCE ATTRIBUTES/FIELDS) ===============

    /** Tovární objekt dekorujícího řešení. */
    @SuppressWarnings("rawtypes")
    protected ADecoratingFactory decoratingFactory;



//##############################################################################
//\II== INSTANCE INITIALIZERS (CONSTRUCTORS) ===================================

    /***************************************************************************
     * Vytvoří rodičovský podobjekt správce scénářů dekorujícího jiného správce.
     *
     * @param decoratedFactory       Tovární objekt dekorovaného řešení
     * @param decoratingFactoryClass Class-objekt dekorujícího řešení
     */
    @SuppressWarnings("rawtypes")
    public ADecoratingScenarioManager(IGSMFactory  decoratedFactory,
               Class<? extends ADecoratingFactory> decoratingFactoryClass)
    {
        super(decoratingFactoryClass);
        this.delegate               = decoratedFactory.getScenarioManager();
        this.decoratingFactoryClass = decoratingFactoryClass;
//        this.decoratingFactory      = decoratingFactory;
    }


    /***************************************************************************
     * Vytvoří rodičovský podobjekt správce scénářů dekorujícího jiného správce.
     *
     * @param decoratedFactory  Tovární objekt dekorovaného řešení
     * @param decoratingFactory Tovární objekt dekorujícího řešení
     */
    @SuppressWarnings("rawtypes")
    public ADecoratingScenarioManager(IGSMFactory        decoratedFactory,
                                      ADecoratingFactory decoratingFactory)
//         Class<? extends ADecoratingFactory> decoratingFactoryClass)
    {
        super(decoratingFactory.getClass());
        this.delegate               = decoratedFactory.getScenarioManager();
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
        return delegate.getAuthorID();
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
        return delegate.getAuthorName();
    }


    /***************************************************************************
     * Vrátí dekorovaného správce scénářů.
     *
     * @return Dekorovaný správce scénářů
     */
    public AScenarioManager getDecorated()
    {
        return delegate;
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
        if (decoratingFactory == null) {
            try {
                decoratingFactory = decoratingFactoryClass.newInstance();
            }
            catch (InstantiationException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
        return decoratingFactory;
    }



//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================

    /***************************************************************************
     * Akceptuje všechny scénáře dekorovaného správce scénářů.
     */
    protected final void addBasicScenarios()
    {
        addScenario(new Scenario(this, delegate.getHappyScenario()));
        addScenario(new Scenario(this, delegate.getMistakeScenario()));
    }



//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
