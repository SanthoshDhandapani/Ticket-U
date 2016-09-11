package com.ticketu.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.util.Arrays;
import java.util.List;

import io.paperdb.Paper;

/**
 * Created by santhosh on 3/25/15.
 */
public class PaperNoDbController {

    private static PaperNoDbController instance = null;

    private Context mContext;

    public PaperNoDbController(Context context) {
        this.mContext = context;
    }

    public static PaperNoDbController getInstance(Context context) {
        if(instance == null) instance = new PaperNoDbController(context);
        return instance;
    }

    public void initPaper() {
        Paper.init(mContext);
    }

    public void destroyPaper(){
        Paper.book().destroy();
    }

    public void putJSON(String key, Object value) {
        try {
            Paper.book().write(key, new Gson().toJson(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putJSONList(String key, List<?> list) {
        try {
            JsonElement element = new Gson().toJsonTree(list, new TypeToken<List<?>>() {}.getType());
            if (! element.isJsonArray()) {
                // fail appropriately
                    throw new JsonListArrayException("Could not create json array list");
            }
            JsonArray jsonArray = element.getAsJsonArray();
            Paper.book().write(key, jsonArray);
        } catch (JsonListArrayException e) {
            e.printStackTrace();
        }
    }

    class JsonListArrayException extends Exception {
        JsonListArrayException(String s){
            super(s);
        }
    }

    public <T> List<T> getListFromJsonArray(String key, Class<T[]> clazz) {
        JsonArray jsonArray = Paper.book().read(key);
        T[] objectArray = new Gson().fromJson(jsonArray, clazz);
        return (objectArray!=null)?Arrays.asList(objectArray):null;
    }

    public <T> T getFromJSON(String key, Class<T> className) {
        T resModel = null;
        try {
            resModel =  new Gson().fromJson((String) Paper.book().read(key), className);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resModel;
    }

    public void deleteKey(String key){

        try {
            Paper.book().delete(key);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
