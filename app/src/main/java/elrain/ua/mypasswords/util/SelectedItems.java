package elrain.ua.mypasswords.util;

import java.util.ArrayList;

/**
 * Created by Denys.Husher on 28.11.2014.
 */
public class SelectedItems {
    private static SelectedItems instance;
    private ArrayList<Integer> items;

    private SelectedItems() {
        items = new ArrayList<Integer>();
    }

    public static SelectedItems getInstance() {
        if (null == instance) {
            instance = new SelectedItems();
        }
        return instance;
    }

    public void clear() {
        items.clear();
    }

    public void remove(int position) {
        items.remove(position);
    }

    public void add(int position) {
        if(!items.contains(position)){
            items.add(position);
        }
    }

    public int size(){
        return items.size();
    }

    public boolean contains(int position){
        return items.contains(position);
    }
}
