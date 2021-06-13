package task.dashkovskiy.workers;

public class Worker {
    public String name;
    public int age;
    public String[] skills;
    public boolean isBusy;

    public Worker(String name, byte age, String[] skills, boolean isBusy) {
        this.name = name;
        this.age = age;
        this.skills = skills;
        this.isBusy = isBusy;
    }

    public Worker() {
    }
}
