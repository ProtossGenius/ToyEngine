package com.suremoon.game.door.tools;

import java.util.HashMap;

/**
 * Created by Water Moon on 2017/11/28.
 */
public class IDManager {
    static {
        ids = new HashMap<>();
        sid = new HashMap<>();
    }
    public static HashMap<String, Integer> ids;
    public static HashMap<Integer, String> sid;
    /**
     * //this method should call in static code-block while init;
     * @param class_name
     * classname cant must cant repeat, must cannot repeat.
     * @return ×Ö·û´®¶ÔÓ¦µÄID
     */
    public static int getID(String class_name){
        if(ids.containsKey(class_name)){
            return ids.get(class_name);
        }else{
            ids.put(class_name, ids.size());//start from 0
            sid.put(ids.size()-1, class_name);
            return ids.size()-1;
        }
    }
    public static String getName(int id){
        return sid.get(id);
    }
    public static int size() {
        return ids.size();
    }
    public static void clear(){
        ids.clear();
        sid.clear();
    }
}
