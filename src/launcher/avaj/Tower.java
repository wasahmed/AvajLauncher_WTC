package launcher.avaj;

import java.util.ArrayList;

public abstract class Tower {
    private ArrayList<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable){
        this.observers.add(flyable);
    }

    public void unregister(Flyable flyable){
        this.observers.remove(flyable);
    }

    protected void conditionsChanged(){
        for(int i = 0; i < this.observers.size(); i++){
            this.observers.get(i).updateConditions();
        }
    }

}
