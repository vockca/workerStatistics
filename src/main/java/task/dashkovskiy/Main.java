package task.dashkovskiy;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import task.dashkovskiy.workers.*;

public class Main {
    public static void main(String[] args) {

        Main app = new Main();

        app.appLifeCycle();
    }

    private void appLifeCycle() {
        startingApp();
        calculatingStatistics();
        finishingApp();
    }

    private void startingApp() {
        Scanner startingMove = new Scanner(System.in);
        System.out.print("To start showing statistics press 'Enter'");
        startingMove.nextLine();
    }

    private void calculatingStatistics() {
        ObjectMapper objectMapper = new ObjectMapper();
        File workersFile = new File("src/files/myWorkers.txt");

        try {
            if (!workersFile.exists()) {
                throw new Exception("File is not existed");
            }

            List<Worker> workersList = objectMapper.readValue(workersFile, new TypeReference<List<Worker>>() {
            });

            WorkerStatisticGetter workerStatisticGetter = new WorkerStatisticGetter(workersList);
            Scanner continueShowingStats = new Scanner(System.in);


            System.out.println("Starting calculating average age of workers that isnt involved in any project");
            System.out.println(workerStatisticGetter.getAverageAge() + " years");
            System.out.println("Success \n");


            System.out.println("Press 'Enter' to start calculating names of the workers whose age is above 25 in the alphabetical order");
            continueShowingStats.nextLine();
            System.out.println(workerStatisticGetter.getSortedNamesList());
            System.out.println("Success \n");


            System.out.println("Press 'Enter' to start calculating list of unique skills of all workers");
            continueShowingStats.nextLine();
            System.out.println(workerStatisticGetter.getUniqueSkills());
            System.out.println("Success \n");

            System.out.println("Press 'Enter' to start calculating the amount of workers that have the corresponding skill");
            continueShowingStats.nextLine();
            System.out.println(workerStatisticGetter.getSkillsVariety());
            System.out.println("Success \n");

        } catch (UnrecognizedPropertyException e) {
            System.out.println("Receiving objects from JSON file are not valid");
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }


    private void finishingApp() {
        System.out.println("All the statistics are showed");
        appLifeCycle();
    }
}
