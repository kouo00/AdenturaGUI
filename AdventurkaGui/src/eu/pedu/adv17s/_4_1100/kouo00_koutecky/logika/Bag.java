/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.pedu.adv17s._4_1100.kouo00_koutecky.logika;

import eu.pedu.adv17s_fw.game_txt.IBag;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;



/*******************************************************************************
 * Instance of the {@code EmptyBag} class represents the repository,
 * to which the players store the items picked up in individual places,
 * so that they could be moved to other places and/or used.
 * The disposal site has a final capacity defining the maximal permitted
 * sum of weights of items occuring in the repository.
 * <p>
 * In this game the bag is ...
 * with capacity ....
 * The item weight represents
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2017-Summer
 */
public   class Bag
        extends ThingContainer
    implements IBag
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============

    /** The only instance of the bag in the bag. */
    private static final Bag SINGLETON = new Bag();
    //Collection<Thing> objects;
    int CAPACITY = 4;
    int remains;

//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



//##############################################################################
//\CI== CLASS (STATIC) INITIALIZER (CLASS CONSTRUCTOR) =========================
//\CF== CLASS (STATIC) FACTORY METHODS =========================================

    /***************************************************************************
     * Factory method returning the only existing instance of the bag.
     *
     * @return The only instance of the bag
     */
    static Bag getInstance()
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
     */
    public Bag()
    {
        super("batoh");
    }



//\IA== INSTANCE ABSTRACT METHODS ==============================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================

    /***************************************************************************
     * Returns the bag capacity, i.e. the maximal permitted sum
     * of weights of items, that can be put into the bag at the same time.
     *
     * @return Capacity of the bag
     */
    @Override
    public int getCapacity()
    {
                return CAPACITY;
    }


    /***************************************************************************
     * Returns the collection of items saved in the bag.
     *
     * @return Collection of items in the bag
     */
//    @Override
//    public Collection<Thing> getItems()
//    {
//        return objects;
//    }

    void initialize(){
        String[] vec = {};
        objects = Arrays.stream(vec)
                    .map(Thing::new)
                    .collect(Collectors.toList());
        remains = 4;
        
    }

    public Collection<Thing> getObjects() {
        return objects;
    }
    
    
    
    boolean tryAddObject(Thing object)
    {
       if (object.getWeight() > remains) {
           return false;
       } 
       addObject(object);
       //remains -= object.getWeight();
       return true;
    }
@Override    
    void removeObject(Thing object)
          {
              remains +=object.getWeight();
              objects.remove(object);
          }
    void removeAll(){
        objects.removeAll(objects);
    }
    
    void kouzli()
          {
              remains +=1;
              CAPACITY +=1;
          }
@Override    
    void addObject(Thing object)
          {
              remains -=object.getWeight();
              objects.add(object);
          }    
//\IM== INSTANCE REMAINING NON-PRIVATE METHODS =================================
//\IP== INSTANCE PRIVATE AND AUXILIARY METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}

