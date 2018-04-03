/* The file is saved in UTF-8 codepage.
 * Check: Â«StereotypeÂ», Section mark-Â§, Copyright-Â©, Alpha-Î±, Beta-Î˛, Smile-â�ş
 */
package eu.pedu.adv17s._4_1100.kouo00_koutecky;

import eu.pedu.adv17s_fw.scenario.AScenarioManager;
import eu.pedu.adv17s_fw.scenario.ScenarioStep;
import eu.pedu.adv17s_fw.scenario.TypeOfScenario;
import eu.pedu.adv17s_fw.game_txt.IGame;

import static eu.pedu.adv17s_fw.scenario.TypeOfStep.*;



/*******************************************************************************
 * Instance of the {@code RUPScenarioManagerLit} class serves as
 * scenario manager, that has to manage the scenarios of the associated game.
 * These scenarios should allow to test and demonstrate
 * the functionality of the associated game.
 * <p>
 * Each manager has to offer:
 * <ul>
 *   <li>The <b>happy scenario</b> (the basic successful one)
 *     demonstrating certain successful path through the game possibilities
 *     leading to the game goal.
 *   </li>
 *   <li>The <b>mistake scenario</b>
 *     demonstrating the game reaction to the wrongly entered commands.
 *   </li>
 * </ul>
 * <p>
 * Individual managed scenarios have to differ by their names;
 * the names of the happy scenario and the mistake one
 * are firmly pre-determined and cannot be arbitrarily set.
 * <p>
 * Individual scenarios are iterable and "streamable" sequences of steps
 * specified by instances of the framework class
 * {@link eu.pedu.adv17s_fw.scenario.ScenarioStep},
 * to which the designed game should associated.
 * <p>
 * Scenario manager is a singleton, that is responsible
 * for all scenarios concerning the game associated with it.
 *
 * @author  Rudolf PECINOVSKĂť
 * @version 2017-Summer
 */
public   class PotionScenarioManager
       extends AScenarioManager
    implements IAuthorKoutecky
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
    
    private final static Class<? extends IGame> CLASS = PotionGame.class;
    private static final String REQUIRED_STEPS_SCENARIO_NAME = "REQUIRED";
//    private static final String _T17s12__SCENARIO_NAME = "TEST";
       
    /***************************************************************************
     * The initial game step identical for all scenarios.
     * <p>
     * Constructor of the full-fledged {@link ScenarioStep} class instance
     * requires the following parameters:
     <pre> {@code
TypeOfStep typeOfStep; //Type of the given scenario step
String     command;    //Command realizing this scenario step
String     message;    //Message written after entering the command
String     place;      //Current place after entering the command
String[]   neighbors;  //Neighbors of the current place (= exits)
String[]   items;      //Items occuring in the current place
String[]   bag;        //Current bag content
     }</pre>
     =======================================================================<br>
     * Scenario steps have to comply with the following requirements:
     * <ul>
     *   <li>None the items may contain the {@code null} value.</li>
     *   <li>With the exception of {@code command} no string may be
     *     empty (i.e. {@code ""})</li>
     *   <li>Empty string may occur neither as an item in the array
     *     {@code neighbors}, nor {@code items} nor {@code bag}</li>
     * </ul>
     * <br>
     **************************************************************************/
     public static final ScenarioStep START_STEP =
        new ScenarioStep(0, tsSTART, "", //NĂˇzev spouĹˇtÄ›cĂ­ho pĹ™Ă­kazu = ""
            "najdete veci k uvareni lektvaru, pote ho "
                    + "dejte blondynce a polibte ji"
            ,
            "stan",
            new String[] { "louka"},
            new String[] { "lahvicka", "spacak", "polstarek" },
            new String[] { }
        );


    /***************************************************************************
     * Steps of the happy scenario
     * describing the expectable successful game running. It is not necessary
     * for the scenario compiled of these steps to be the shortest possible
     * (it implies, that it has not to be the true basic successful scenario),
     * but it has to comply with all marginal conditions of the assignment,
     * i.e. it has to contain minimal number of steps,
     * pass through the required minimal number of places
     * and demonstrate using of all required actions.
     */
    private static final ScenarioStep[] HAPPY_SCENARIO_STEPS =
    {
        START_STEP,

        new ScenarioStep(tsTAKE, "sebrat lahvicka",
            "predmet lahvicka byl umisten do batohu."
            ,
            "stan",
            new String[] { "louka"},
            new String[] { "spacak", "polstarek" },
            new String[] { "lahvicka" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit louka",
            "presunul jste se do oblasti louka."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "blondynka" },
            new String[] { "lahvicka" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit haj",
            "presunul jste se do oblasti haj."
            ,
            "haj",
            new String[] { "louka", "hvozd"},
            new String[] { "ruze" },
            new String[] { "lahvicka" }
        )
        ,
        new ScenarioStep(tsTAKE, "sebrat ruze",
            "predmet ruze byl umisten do batohu."
            ,
            "haj",
            new String[] { "louka", "hvozd"},
            new String[] {  },
            new String[] { "ruze", "lahvicka" }
        )
        , 
        new ScenarioStep(tsMOVE, "jit hvozd",
            "presunul jste se do oblasti hvozd."
            ,
            "hvozd",
            new String[] { "haj", "jeskyne"},
            new String[] { "mech" },
            new String[] { "lahvicka", "ruze" }
        )
        ,
        new ScenarioStep(tsTAKE, "sebrat mech",
            "predmet mech byl umisten do batohu."
            ,
            "hvozd",
            new String[] { "haj", "jeskyne"},
            new String[] {  },
            new String[] { "lahvicka", "ruze", "mech" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit jeskyne",
            "presunul jste se do oblasti jeskyne."
            ,
            "jeskyne",
            new String[] { "hvozd" },
            new String[] { "drahokam", },
            new String[] { "lahvicka", "ruze", "mech" }
        )
        ,
        new ScenarioStep(tsTAKE, "sebrat drahokam",
            "predmet drahokam byl umisten do batohu."
            ,
            "jeskyne",
            new String[] { "hvozd"},
            new String[] {  },
            new String[] { "lahvicka", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit hvozd",
            "presunul jste se do oblasti hvozd."
            ,
            "hvozd",
            new String[] { "haj", "jeskyne"},
            new String[] {  },
            new String[] { "lahvicka", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit haj",
            "presunul jste se do oblasti haj."
            ,
            "haj",
            new String[] { "louka", "hvozd"},
            new String[] {  },
            new String[] { "lahvicka", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit louka",
            "presunul jste se do oblasti louka."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "blondynka" },
            new String[] { "lahvicka", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit plaz",
            "presunul jste se do oblasti plaz."
            ,
            "plaz",
            new String[] { "louka"},
            new String[] { "jezero" },
            new String[] { "lahvicka", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD2 , "naplnit lahvicka jezero",
            "naplnili jste predmet lahvicka vodou z jezero."
            ,
            "plaz",
            new String[] { "louka"},
            new String[] { "jezero" },
            new String[] { "lahvicka_s_vodou", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit louka",
            "presunul jste se do oblasti louka."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "blondynka" },
            new String[] { "lahvicka_s_vodou", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit uskali",
            "presunul jste se do oblasti uskali."
            ,
            "uskali",
            new String[] { "louka"},
            new String[] { "ohniste", "kamen" },
            new String[] { "lahvicka_s_vodou", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD0, "smichat",
            "smiichali jste vsechny suroviny v lahvicce."
            ,
            "uskali",
            new String[] { "louka"},
            new String[] { "ohniste", "kamen" },
            new String[] { "lahvicka_se_surovinami" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD2, "uvarit lahvicka_se_surovinami"
                + " ohniste",
            "uvarili jste lektvar."
            ,
            "uskali",
            new String[] { "louka"},
            new String[] { "ohniste", "kamen" },
            new String[] { "lektvar" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit louka",
            "presunul jste se do oblasti louka."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "blondynka" },
            new String[] { "lektvar" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD2, "dat_napit lektvar blondynka",
            "dali jste napit lektvar blondynce."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "zamilovana_blondynka" },
            new String[] { "lahvicka" }
        )
        ,
        new ScenarioStep(tsPUT_DOWN, "polozit lahvicka",
            "polozili jste lahvicka."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "zamilovana_blondynka", "lahvicka" },
            new String[] {  }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, "polibit zamilovana_blondynka",
            "polibili jste zamilovanou blondynku."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "zamilovana_blondynka", "lahvicka" },
            new String[] {  }
        )
        ,
    };


    /** Step testing the incorrect game starting is defined separately,
     *  so that the indexes of the following steps could be simply adjusted. */
    private static final ScenarioStep WRONG_START =
    new ScenarioStep(-1,
            tsNOT_START, "Start",
            "\nThe first command is not the starting one.\n"
          + "Game that does not run can be started "
          + "only with a starting command.\n"
            ,
            "",
            new String[] {},
            new String[] {},
            new String[] {}
        );


    static { ScenarioStep.setIndex(1); }


    /***************************************************************************
     * Mistake scenario defining reactions
     * to mandatory set of types of incorrectly given commands.
     */
    private static final ScenarioStep[] MISTAKE_SCENARIO_STEPS =
    {
        WRONG_START,

        START_STEP,
        
        
        new ScenarioStep(tsEMPTY, "",
            "prazdny retezec."
            ,
            "stan",
            new String[] { "louka"},
            new String[] { "lahvicka", "polstarek", "spacak" },
            new String[] {  }
        )
        ,
        
        new ScenarioStep(tsBAD_ITEM, "sebrat polstar",
            "zadali jste spatny tvar predmetu."
            ,
            "stan",
            new String[] { "louka"},
            new String[] { "lahvicka", "polstarek", "spacak" },
            new String[] {  }
        )
        ,
        new ScenarioStep(tsTAKE_WA, "sebrat ",
            "nezadali jste predmet."
            ,
            "stan",
            new String[] { "louka"},
            new String[] { "lahvicka", "polstarek", "spacak" },
            new String[] {  }
        )
        ,
        new ScenarioStep(tsTAKE, "sebrat lahvicka",
            "predmet lahvicka byl umisten do batohu."
            ,
            "stan",
            new String[] { "louka"},
            new String[] { "polstarek", "spacak" },
            new String[] { "lahvicka" }
        )
        ,
        new ScenarioStep(tsPUT_DOWN_WA, "polozit ",
            "nezadali jste predmet."
            ,
            "stan",
            new String[] { "louka"},
            new String[] { "polstarek", "spacak" },
            new String[] { "lahvicka" }
        )
        ,
                   
        new ScenarioStep(tsBAD_NEIGHBOR, "jit plaz",
            "cilovy prostor neni sousedem tohoto prostoru."
            ,
            "stan",
            new String[] { "louka"},
            new String[] { "polstarek", "spacak" },
            new String[] { "lahvicka" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit louka",
            "presunul jste se do oblasti louka."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "blondynka" },
            new String[] { "lahvicka" }
        )
        ,
        new ScenarioStep(tsUNMOVABLE, "sebrat blondynka",
            "tento predmet nelze sebrat."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "blondynka" },
            new String[] { "lahvicka" }
        )        
        ,
        new ScenarioStep(tsNOT_IN_BAG, "polozit lektvar",
            "tento predmet neni v inventari."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "blondynka" },
            new String[] { "lahvicka" }
        )        
        ,
        
        new ScenarioStep(tsMOVE_WA, "jit ",
            "nezdali jste cil."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "blondynka" },
            new String[] { "lahvicka" }
        )        
        ,
        new ScenarioStep(tsHELP, "help",
            "Help \n" +
"              help\n" +
"              zadali jste prikaz pro napovedu\n" +
"              \n" +
"              jit\n" +
"              presunul jste se do oblasti\n" +
"              \n" +
"              smichat\n" +
"              smichat predvety z batohu\n" +
"              \n" +
"              polibit\n" +
"              polibite blondynku\n" +
"              \n" +
"              dat_napit\n" +
"              date napit blondynce\n" +
"              \n" +
"              polozit\n" +
"              PomocĂ­ tohoto pĹ™Ă­kazu polozite vec do prostoru.\n" +
"              \n" +
"              naplnit\n" +
"              naplnuju jste lahvicku vodou z jezera\n" +
"              \n" +
"              end\n" +
"              ukoncili jste hru predcasne\n" +
"              \n" +
"              uvarit\n" +
"              uvarit item lahvicka_se_surovinami\n" +
"              \n" +
"              sebrat\n" +
"              PomocĂ­ tohoto pĹ™Ă­kazu vezme hrĂˇÄŤ vÄ›c a vloĹľĂ­ ji dobatohu."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "blondynka" },
            new String[] { "lahvicka" }
        )
        ,                
        new ScenarioStep(tsUNKNOWN, "blablabla",
            "prazdny retezec."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "blondynka" },
            new String[] { "lahvicka" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit haj",
            "presunul jste se do oblasti haj."
            ,
            "haj",
            new String[] { "louka", "hvozd"},
            new String[] { "ruze" },
            new String[] { "lahvicka" }
        )
        ,
        new ScenarioStep(tsTAKE, "sebrat ruze",
            "predmet ruze byl umisten do batohu."
            ,
            "haj",
            new String[] { "louka", "hvozd"},
            new String[] {  },
            new String[] { "ruze", "lahvicka" }
        )
        , 
        new ScenarioStep(tsMOVE, "jit hvozd",
            "presunul jste se do oblasti hvozd."
            ,
            "hvozd",
            new String[] { "haj", "jeskyne"},
            new String[] { "mech" },
            new String[] { "lahvicka", "ruze" }
        )
        ,
        new ScenarioStep(tsTAKE, "sebrat mech",
            "predmet mech byl umisten do batohu."
            ,
            "hvozd",
            new String[] { "haj", "jeskyne"},
            new String[] {  },
            new String[] { "lahvicka", "ruze", "mech" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit jeskyne",
            "presunul jste se do oblasti jeskyne."
            ,
            "jeskyne",
            new String[] { "hvozd" },
            new String[] { "drahokam" },
            new String[] { "lahvicka", "ruze", "mech" }
        )
        ,
        new ScenarioStep(tsTAKE, "sebrat drahokam",
            "predmet drahokam byl umisten do batohu."
            ,
            "jeskyne",
            new String[] { "hvozd"},
            new String[] { },
            new String[] { "lahvicka", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit hvozd",
            "presunul jste se do oblasti hvozd."
            ,
            "hvozd",
            new String[] { "haj", "jeskyne"},
            new String[] {  },
            new String[] { "lahvicka", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit haj",
            "presunul jste se do oblasti haj."
            ,
            "haj",
            new String[] { "louka", "hvozd"},
            new String[] {  },
            new String[] { "lahvicka", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit louka",
            "presunul jste se do oblasti louka."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "blondynka" },
            new String[] { "lahvicka", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit plaz",
            "presunul jste se do oblasti plaz."
            ,
            "plaz",
            new String[] { "louka"},
            new String[] { "jezero" },
            new String[] { "lahvicka", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD2 , "naplnit lahvicka jezero",
            "naplnili jste predmet lahvicka vodou z jezero."
            ,
            "plaz",
            new String[] { "louka"},
            new String[] { "jezero" },
            new String[] { "lahvicka_s_vodou", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit louka",
            "presunul jste se do oblasti louka."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "blondynka" },
            new String[] { "lahvicka_s_vodou", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit uskali",
            "presunul jste se do oblasti uskali."
            ,
            "uskali",
            new String[] { "louka"},
            new String[] { "ohniste", "kamen" },
            new String[] { "lahvicka_s_vodou", "ruze", "mech", "drahokam" }
        )
        ,
        
        
        new ScenarioStep(tsBAG_FULL, "sebrat kamen",
            "batoh je plny."
            ,
            "uskali",
            new String[] { "louka"},
            new String[] { "ohniste", "kamen" },
            new String[] { "lahvicka_s_vodou", "ruze", "mech", "drahokam" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD0, "smichat",
            "smiichali jste vsechny suroviny v lahvicce."
            ,
            "uskali",
            new String[] { "louka"},
            new String[] { "ohniste", "kamen" },
            new String[] { "lahvicka_se_surovinami" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD2, "uvarit lahvicka_se_surovinami "
                + "ohniste",
            "uvarili jste lektvar."
            ,
            "uskali",
            new String[] { "louka"},
            new String[] { "ohniste", "kamen" },
            new String[] { "lektvar" }
        )
        ,
        new ScenarioStep(tsMOVE, "jit louka",
            "presunul jste se do oblasti louka."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "blondynka" },
            new String[] { "lektvar" }
        )
        ,
        new ScenarioStep(tsNON_STANDARD1, "polibiT blondynka",
            "toto libat nechces"
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "blondynka" },
            new String[] { "lektvar" }
        ),       
                new ScenarioStep(tsEND, "end",
            "ukoncili jste hru."
            ,
            "louka",
            new String[] { "stan", "uskali", "haj", "plaz"},
            new String[] { "blondynka" },
            new String[] { "lektvar" }
        )

    };
    
    static {ScenarioStep.setIndex(1);}

private static final ScenarioStep[] REQUIRED_STEPS =
{
    START_STEP,
       
    new ScenarioStep(tsHELP, "help",
        "Help \n" +
"              help\n" +
"              zadali jste prikaz pro napovedu\n" +
"              \n" +
"              jit\n" +
"              presunul jste se do oblasti\n" +
"              \n" +
"              smichat\n" +
"              smichat predvety z batohu\n" +
"              \n" +
"              polibit\n" +
"              polibite blondynku\n" +
"              \n" +
"              dat_napit\n" +
"              date napit blondynce\n" +
"              \n" +
"              polozit\n" +
"              PomocĂ­ tohoto pĹ™Ă­kazu polozite vec do prostoru.\n" +
"              \n" +
"              naplnit\n" +
"              naplnuju jste lahvicku vodou z jezera\n" +
"              \n" +
"              end\n" +
"              ukoncili jste hru predcasne\n" +
"              \n" +
"              uvarit\n" +
"              uvarit item lahvicka_se_surovinami\n" +
"              \n" +
"              sebrat\n" +
"              PomocĂ­ tohoto pĹ™Ă­kazu vezme hrĂˇÄŤ vÄ›c a vloĹľĂ­ ji dobatohu.",
        "stan",
            new String[] { "louka"},
            new String[] {"lahvicka" , "spacak" , "polstarek"},
            new String[] {}
            ),
    
        new ScenarioStep(tsTAKE, "sebrat lahvicka",
            "predmet lahvicka byl umisten do batohu."
            ,
            "stan",
            new String[] { "louka"},
            new String[] { "spacak", "polstarek" },
            new String[] { "lahvicka" }
        )
        ,    
    
    new ScenarioStep(tsMOVE, "jit louka",
        "presunul jste se do oblasti louka.",
            "louka",
        new String[] { "stan", "uskali", "haj", "plaz" },
        new String[] { "blondynka" },
        new String[] { "lahvicka"}
        ),
    
    new ScenarioStep(tsPUT_DOWN, "polozit lahvicka",
        "polozili jste lahvicka."
        ,
        "louka",
        new String[] { "stan", "uskali", "haj", "plaz"},
        new String[] { "blondynka", "lahvicka" },
        new String[] {  }
        ),
                
    new ScenarioStep(tsEND, "end",
        "Ukoncili jste hru."
        ,
        "louka",
        new String[] { "stan", "uskali", "haj", "plaz"},
        new String[] { "blondynka", "lahvicka" },
        new String[] {  }
        )
      
};


    /** The only instance of this class.
     *  It manages all scenarios of the associated game. */
    private static final PotionScenarioManager MANAGER =
                                          new PotionScenarioManager();



//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================

    /***************************************************************************
     * Static constructor is placed before definitions of constants
     * {@link #AGE}, {@link #THIS_YEAR} and {@link #BORN_YEAR}
     * and once again before the definition of a constant
     * {@link MISTAKE_SCENARIO_STEPS}.
     * Such initialization should be before each further constant
     * defining the steps of the following scenario.
     */



//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Returns scenario manager - the only instance of this class.
     *
     * @return Scenario manager
     */
    public static PotionScenarioManager getInstance()
    {
        return MANAGER;
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
     * Creates an instance representing the game scenario manager.
     * Within the constructor framework it is suitable to create all scenarios
     * and seal the scenario manager after it.
     * <p>
     * Individual managed scenarios have to differ by their names,
     * the names of the happy scenario and the mistake one
     * are firmly pre-determined and cannot be changed.
     * Names given to them in the
     * {@link #addScenario(String, TypeOfScenario, ScenarioStep...)} method
     * are therefore only formal, because the called method assignes to them
     * the names defined in advance in appropriate constants.
     * <p>
     *Â´Contract of the
     * {@link #addScenario(String, TypeOfScenario, ScenarioStep...)} method
     * requires so that the happy scenario, i.e. scenario of the
     * {@link TypeOfScenario.scHAPPY}) type, would be added as the first one,
     * and the mistake scenario, i.e. the scenario of the
     * {@link MISTAKE_SCENARIO_NAME} type, as the second one.
     * The order of the subsequently added scenarios is not decisive.
      */
    private PotionScenarioManager()
    {
        super(FACTORY_CLASS);

        addScenario(HAPPY_SCENARIO_NAME,
                    TypeOfScenario.scHAPPY,    HAPPY_SCENARIO_STEPS);
        addScenario(MISTAKE_SCENARIO_NAME,
                    TypeOfScenario.scMISTAKES, MISTAKE_SCENARIO_STEPS);
        addScenario(REQUIRED_STEPS_SCENARIO_NAME,
                    TypeOfScenario.scGENERAL,  REQUIRED_STEPS);
//        addScenario(_T17s12__SCENARIO_NAME,
//                TypeOfScenario.scGENERAL, _T17s12_STEPS);
        seal();
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================



//##############################################################################
//== TEST METHODS AND CLASSES ==================================================

    /***************************************************************************
     * Method verifying the given scenario manager and the associated game
     * by scenarios of this manager.
     * <p>
     * The scenario manager is verified if it complies
     * with the following conditions:
     * <ul>
     *   <li>It knows to return properly formated name of the game author
     *       and his/her ID.</li>
     *   <li>It defines the happy scenario and the mistake one.</li>
     *   <li>The mistake scenario has the following properties:
     *     <ul>
     *       <li>Starting command, the name of which is an empty string</li>
     *       <li>Minimal required number of steps</li>
     *       <li>Minimal number of visited places</li>
     *       <li>Minimal number of "glimpsed" places</li>
     *       <li>Minimal number of own (optional) actions</li>
     *       <li>Usage of actions for moving from the current place
     *         to a neighboring place, taking item and putting down item</li>
     *       <li>Cross consistence of actions and states after execution
     *         of the actions</li>
     *     </ul>
     *   </li>
     *   <li>The mistake scenario has the following properties:
     *     <ul>
     *       <li>Incorrect starting by a not empty command,</li>
     *       <li>Starting command the name of which is an empty string</li>
     *       <li>Usage of all mandatory error step types defined in the<br>
     *         {@link eu.pedu.adv17s_fw.scenario.TypeOfStep} enum type</li>
     *       <li>Asking for a help</li>
     *       <li>Premature game termination</li>
     *     </ul>
     *   </li>
     * </ul>
     * <p>
     * The game is verified if it can be played exactly
     * as is planned in scenarios.
     *
     * @param args Command line parameters - unused.
     */
    public static void main(String[] args)
    {
        //Tests if the scenario manager and its scenarios
        //comply with requirements
//        MANAGER.autoTest();

        //Simulates playing the game according to happy scenario
//        MANAGER.getHappyScenario().simulate();

        //Game testing made gradually according to both mandatory scenarios,
        //the happy scenario is passed twice one after the other
        MANAGER.testGame();

        //Game testing according to scenarios with the given names
//        MANAGER.testGameByScenarios(HAPPY_SCENARIO_NAME);

        //Playing the game according to the scenario with the given name
//        MANAGER.playGameByScenario("???");

        System.exit(0);
    }

}