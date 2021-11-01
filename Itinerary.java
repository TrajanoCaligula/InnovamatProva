import java.util.LinkedList;

public class Itinerary {
    private int id;
    private LinkedList<Activity> activities;

    public Itinerary(LinkedList<Activity> activities) {
        this.activities = activities;
    }

    public Activity nextActivity(Activity activity) {
        for (int i = 0; i < this.activities.size(); ++i) {
            if (this.activities.get(i).isEqual(activity)) {
                Activity result = new Activity(activities.get(i + 1));
                return result;
            }
        }
        return null;
    }

    public Activity getFirstElement() {
        return activities.getFirst();
    }

    public Activity getLastElement() {
        return activities.getLast();
    }

    public Activity nextDifficulty(Activity activity) {
        for (int i = 0; i < activities.size(); ++i) {
            if (activities.get(i).getDifficulty() == activity.getDifficulty() + 1) {
                Activity result = new Activity(activities.get(i));
                return result;
            }
        }
        return null;
    }

    public Activity goBack(Activity activity, Activity lastJump) {
        if(activity.getDifficulty() == lastJump.getDifficulty()+1) return lastJump;
        return activity;
    }

    public boolean isLastActivity(Activity activity){
        return activity.isEqual(getLastElement());
    }
}
