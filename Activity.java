
public class Activity {
    private String name;
    private String id;
    private int estimatedTime;
    private String solution;
    private int difficulty;
    private int position;

    public Activity(String name, String id, int position, int difficulty, int estimatedTime, String solution) {
        this.name = name;
        this.id = id;
        this.difficulty = difficulty;
        this.estimatedTime = estimatedTime;
        this.solution = solution;
        this.position = position;
    }

    public Activity(Activity copy) {
        this.name = copy.name;
        this.id = copy.id;
        this.difficulty = copy.difficulty;
        this.estimatedTime = copy.estimatedTime;
        this.solution = copy.solution;
        this.position = copy.position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSolution() {
        return this.solution;
    }

    public int getEstimatedTime() {
        return this.estimatedTime;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public void printTerminal() {
        System.out.println(name + " --- " + id + " --- " + difficulty + " --- " + estimatedTime + " --- " + solution
                + " --- " + position);
    }

    public boolean isEqual(Activity copy) {
        return this.name == copy.name && this.id == copy.id && this.difficulty == copy.difficulty
                && this.estimatedTime == copy.estimatedTime && this.solution == copy.solution
                && this.position == copy.position;
    }
}
