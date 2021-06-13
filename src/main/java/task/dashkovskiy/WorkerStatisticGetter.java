package task.dashkovskiy;

import task.dashkovskiy.workers.Worker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

abstract class StatisticGetter {
    private List<Worker> workersList;

    public StatisticGetter(List<Worker> workersList) {
        this.workersList = workersList;
    }

    public abstract double getAverageAge();

    public abstract List<String> getSortedNamesList();

    public abstract Set<String> getUniqueSkills();

    public abstract Map<String, Integer> getSkillsVariety();
}

public class WorkerStatisticGetter extends StatisticGetter {
    private List<Worker> workersList;

    WorkerStatisticGetter(List<Worker> workersList) {
        super(workersList);
        this.workersList = workersList;
    }


    public double getAverageAge() {
        Stream<Worker> workersListStream = this.workersList.stream();

        OptionalInt sumOfUnbusyAges = workersListStream.filter(s -> !s.isBusy)
                .flatMapToInt(s -> IntStream.of(s.age))
                .reduce((acc, x) -> acc + x);

        double averageAgeOfFreeWorkers = sumOfUnbusyAges.getAsInt()
                / (double) this.workersList.stream().filter(s -> !s.isBusy).toArray().length;

        return averageAgeOfFreeWorkers;
    }


    public List<String> getSortedNamesList() {
        Stream<Worker> workersListStream = this.workersList.stream();

        List<String> names = workersListStream.filter(s -> s.age > 25)
                .map(s -> s.name).sorted().collect(Collectors.toList());

        return names;
    }

    public Set<String> getUniqueSkills() {
        Stream<Worker> workersListStream = this.workersList.stream();

        HashSet<String> uniqueSkills = new HashSet<>();

        workersListStream.map(s -> s.skills)
                .forEach(s -> {
                    for (int i = 0; i < s.length; i++) {
                        uniqueSkills.add(s[i]);
                    }
                });

        return uniqueSkills;
    }

    public Map<String, Integer> getSkillsVariety() {
        Stream<Worker> workersListStream = this.workersList.stream();

        HashMap<String, Integer> skillsVariety = new HashMap<String, Integer>();

        workersListStream.map(s -> s.skills)
                .forEach(s -> {
                    for (int i = 0; i < s.length; i++) {
                        if (skillsVariety.containsKey(s[i])) {

                            skillsVariety.put(s[i], skillsVariety.get(s[i]) + 1);
                        } else
                        skillsVariety.put(s[i], 1);
                    }
                });

        return skillsVariety;
    }
}
