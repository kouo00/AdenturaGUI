/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.pedu.adv17s._4_1100.kouo00_koutecky;

import eu.pedu.adv17s_fw.empty_classes.ANamed;
import eu.pedu.adv17s_fw.game_txt.IItemContainer;
import eu.pedu.adv17s_fw.game_txt.INamed;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 *
 * @author Oldřich
 */
public abstract class ThingContainer
        extends ANamed
        implements IItemContainer
        
{
    /** Names of items in the given place at the game beginning. */
    private final String[] objectNames;

    /** Names of items currently present in the given place. */
    public Collection<Thing> objects;


    
    @Override
    public Collection<Thing> getItems()
    {
        return Collections.unmodifiableCollection(objects);
    }
    
    Thing getObject(String objectName)
          {
          Thing result = INamed.getO(objectName, objects).orElse(null);
          return result;
          }
  
  void removeObject(Thing object)
          {
              objects.remove(object);
          }
  void removeAll(Thing object)
          {
              objects.remove(objects);
          }
  
  void addObject (Thing object)
         {
             objects.add(object);
         } 


   void initializeItems(){
        objects = Arrays.stream(objectNames)
                    .map(Thing::new)
                    .collect(Collectors.toList());
        
//        remains = 4;
        
    }
    
   
    

    ThingContainer(String name, String... objectNames)
    {
        super(name);
        this.objectNames = objectNames;
        this.objects = new ArrayList<>();
    }      
}

