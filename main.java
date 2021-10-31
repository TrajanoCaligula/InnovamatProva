import java.util.LinkedList;

import javax.sql.StatementEventListener;

public class main {
    public static void main(String[] args) {
        int[] difficulty = { 1, 1, 1, 1, 2, 2, 3, 3, 4, 5, 6, 7, 8, 9, 10 };
        int[] time = { 120, 60, 120, 180, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120 };
        String[] solution = { "1_0_2", "-2_40_56", "1_0", "1_0_2_-5_9", "1_0_2", "1_0_2", "1_-1_'Yes'_34_-6", "1_2",
                "1_0_2", "1_b_2", "2", "25_0_2", "1_0_3", "1_0_2", "1_0_2" };
        LinkedList<Activity> activities = new LinkedList<Activity>();

        for (int i = 0; i < 15; ++i) {
            String name = "Activity " + String.valueOf(i + 1);
            String id = "A" + String.valueOf(i + 1);
            Activity adding = new Activity(name, id, i, difficulty[i], time[i], solution[i]);
            activities.addLast(adding);
        }
        Itinerary itinerary = new Itinerary(activities);
        User user = new User(1, itinerary, null);

        /* Inici sequencia */
        user.setCurrentActivity(nextActivity(itinerary, null, "", 0, user));
        System.out.println(user.getCurrentActivity().getId());
        user.setCurrentActivity(nextActivity(user.getCurrentItinerary(), user.getCurrentActivity(), "1_0_2", 90, user));
        user.setCurrentActivity(nextActivity(user.getCurrentItinerary(), user.getCurrentActivity(), "-2_40_56", 15, user));
        user.setCurrentActivity(nextActivity(user.getCurrentItinerary(), user.getCurrentActivity(), "0_2_1", 180, user));
        user.setCurrentActivity(nextActivity(user.getCurrentItinerary(), user.getCurrentActivity(), "1_1", 100, user));
        user.setCurrentActivity(nextActivity(user.getCurrentItinerary(), user.getCurrentActivity(), "1_0", 80, user));
        user.setCurrentActivity(nextActivity(user.getCurrentItinerary(), user.getCurrentActivity(), "1_0_2_-4_9", 100, user));
        user.setCurrentActivity(nextActivity(user.getCurrentItinerary(), user.getCurrentActivity(), "1_0_2", 50, user));
        user.setCurrentActivity(nextActivity(user.getCurrentItinerary(), user.getCurrentActivity(), "1_-1_'Yes'_34_-6", 50, user));
        user.setCurrentActivity(nextActivity(user.getCurrentItinerary(), user.getCurrentActivity(), "1_0_2", 10, user));
        user.setCurrentActivity(nextActivity(user.getCurrentItinerary(), user.getCurrentActivity(), "1_b_2", 10, user));
        user.setCurrentActivity(nextActivity(user.getCurrentItinerary(), user.getCurrentActivity(), "2", 10, user));
        user.setCurrentActivity(nextActivity(user.getCurrentItinerary(), user.getCurrentActivity(), "25_0_2", 110, user));
        user.setCurrentActivity(nextActivity(user.getCurrentItinerary(), user.getCurrentActivity(), "1_0_3", 110, user));
        user.setCurrentActivity(nextActivity(user.getCurrentItinerary(), user.getCurrentActivity(), "1_0_2", 10, user));
        user.setCurrentActivity(nextActivity(user.getCurrentItinerary(), user.getCurrentActivity(), "1_0_2", 10, user));

    }

    public static int score(Activity activity, String response) {
        String[] correctSolution = activity.getSolution().split("_");
        String[] userSolution = response.split("_");
        int count = 0;

        for (int i = 0; i < correctSolution.length; i++) {
            if (correctSolution[i].equals(userSolution[i]))
                ++count;
        }
        int result = cal_percentage(count, correctSolution.length);
        System.out.print("Score= " + result + "% -> ");
        return result;
    }
    //tot i que a l'enunciat no apareix el parametre usuari en el mètode, l'he posat ja que com diu l'enunciat, a la API envia l'id de l'usuari
    //i per tant, he suposat que desde "qualsevol" punt del model puc accedir a la seva informació
    public static Activity nextActivity(Itinerary itinerary, Activity activity, String result, int time, User user) {

        if (activity == null)
            return itinerary.getFirstElement();
        /*
         * Option 1 
         *  int score = score(activity, result); 
         *  if (score < 75){
         *      System.out.println("Next Activity: " + activity.getId());
         *      return activity;
         *  }
         *  else{ 
         *      Activity resultActivity = new Activity(itinerary.nextActivity(activity));
         *      System.out.println("Next Activity: " + resultActivity.getId());
         *      return resultActivity; 
         *  }
         */

        /* Option 2 */
        int score = score(activity, result);
        int timePercentage = cal_percentage(time, activity.getEstimatedTime());

        if (score < 20) {
            Activity resultActivity = new Activity(itinerary.goBack(activity, user.getLastJump()));
            System.out.println("Next Activity: " + resultActivity.getId());
            return resultActivity;
        } else if (score < 75) {
            System.out.println("Next Activity: " + activity.getId());
            return activity;
        } else if(!itinerary.isLastActivity(activity)){
            if (timePercentage < 50) {
                Activity resultActivity = new Activity(itinerary.nextDifficulty(activity));
                System.out.println("Next Activity: " + resultActivity.getId());
                user.setLastJump(itinerary.nextActivity(activity));
                return resultActivity;
            } else {
                Activity resultActivity = new Activity(itinerary.nextActivity(activity));
                System.out.println("Next Activity: " + resultActivity.getId());
                return resultActivity;
            }
        } else{
            System.out.println("Next Activity: ~");
            return null;
        }

    }

    public static int cal_percentage(int obtained, int total) {
        return obtained * 100 / total;
    }
}