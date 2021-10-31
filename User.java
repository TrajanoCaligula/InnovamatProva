public class User {
    private int id;
    private Itinerary currentItinerary;
    private Activity currentActivity;
    private Activity lastJump;


    public User(int id, Itinerary currentItinerary, Activity currentActivity) {
        this.id = id;
        this.currentItinerary = currentItinerary;
        this.currentActivity = currentActivity;
        this.lastJump = null;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Itinerary getCurrentItinerary() {
        return currentItinerary;
    }

    public void setCurrentItinerary(Itinerary currentItinerary) {
        this.currentItinerary = currentItinerary;
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public Activity getLastJump() {
        return lastJump;
    }

    public void setLastJump(Activity lastJump) {
        this.lastJump = lastJump;
    }
}
