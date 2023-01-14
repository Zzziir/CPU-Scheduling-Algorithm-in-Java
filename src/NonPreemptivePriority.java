import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NonPreemptivePriority {

    //FIELDS
    public int processNumber, arrivalTime, burstTime, completionTime, turnAroundTime, waitingTime, isComplete, priorityNum;
    public static GanttChart ganttChart;

    //Arraylist of Objects
    static List<NonPreemptivePriority> NonPreemptivePriorityList = new ArrayList<>();

    //Constructor
    public NonPreemptivePriority(int processNumber, int arrivalTime, int burstTime, int priorityNum){
        this.processNumber = processNumber;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.completionTime = 0;
        this.turnAroundTime = 0;
        this.waitingTime = 0;
        this.isComplete = 0;
        this.priorityNum = priorityNum;
        this.ganttChart = new GanttChart();
    }

    public NonPreemptivePriority(){} //empty constructor

    /* ============================== METHODS ============================== */

    public static void compute(){
        int systemTime = 0, totalProcess = 0;
        int curr, min;

        while(true){
            curr = 0;
            min = NonPreemptivePriorityList.size()+1;

            //loop terminator
            if(totalProcess == NonPreemptivePriorityList.size()){
                break;
            }

            for(int i = 0; i < NonPreemptivePriorityList.size(); i++){
                if((NonPreemptivePriorityList.get(i).arrivalTime <= systemTime) && (NonPreemptivePriorityList.get(i).isComplete == 0) && (NonPreemptivePriorityList.get(i).priorityNum < min)){
                    min = NonPreemptivePriorityList.get(i).priorityNum;
                    curr = i;
                }
            }

            NonPreemptivePriorityList.get(curr).completionTime = systemTime + NonPreemptivePriorityList.get(curr).burstTime;
            systemTime += NonPreemptivePriorityList.get(curr).burstTime;
            NonPreemptivePriorityList.get(curr).turnAroundTime = NonPreemptivePriorityList.get(curr).completionTime - NonPreemptivePriorityList.get(curr).arrivalTime;
            NonPreemptivePriorityList.get(curr).waitingTime = NonPreemptivePriorityList.get(curr).turnAroundTime - NonPreemptivePriorityList.get(curr).burstTime;
            NonPreemptivePriorityList.get(curr).isComplete = 1;
            ganttChart.processList.add(String.valueOf(NonPreemptivePriorityList.get(curr).processNumber));
            ganttChart.timeList.add(String.valueOf(NonPreemptivePriorityList.get(curr).completionTime));
            totalProcess++;
        }
    }

    public static void sortBasedOnCompletionTime(){
        NonPreemptivePriority temp;
        for(int i = 0; i < NonPreemptivePriorityList.size()-1 ; i++) {
            for (int j = i + 1; j < NonPreemptivePriorityList.size(); j++) {
                if(NonPreemptivePriorityList.get(i).completionTime > NonPreemptivePriorityList.get(j).completionTime) {
                    temp = (NonPreemptivePriorityList.get(i));
                    NonPreemptivePriorityList.set(i, NonPreemptivePriorityList.get(j));
                    NonPreemptivePriorityList.set(j, temp);
                }
            }
        }
    }

    public static void displayGanttChart(){

        sortBasedOnCompletionTime();

        String firstLine = "PROCESSES: \t";
        String secondLine = "TIME:      \t";
        System.out.println("\n=================================== GANTT CHART ===================================");
        for(int i = 0; i < ganttChart.processList.size(); i++){
            if(i == 0){
                firstLine += "|P" + ganttChart.processList.get(i) + "|" + " --- ";
                secondLine += "(0)" + "      ";
            }
            else {
                firstLine += "|P" + ganttChart.processList.get(i) + "|" + " --- ";
                secondLine += "(" + ganttChart.timeList.get(i-1) + ")" + "     ";
            }
            if(i == ganttChart.processList.size()-1){
                firstLine += "|END|";
                secondLine += "(" + ganttChart.timeList.get(i) + ")";
            }
        }
        System.out.println(firstLine + "\n" + secondLine);

        System.out.println("===================================================================================");
    }

    public static void displayEverything(){

        displayGanttChart();

        float averageWaitingTime = 0, averageTurnAroundTime = 0;
        System.out.println("\nP#" + " \t\t" + "AT" + " \t\t" + "BT" + " \t\t" + "CT" + " \t\t" + "WT" + "\t\t" + "TAT");
        for(int i = 0; i < NonPreemptivePriorityList.size(); i++){

            averageWaitingTime += NonPreemptivePriorityList.get(i).waitingTime;
            averageTurnAroundTime += NonPreemptivePriorityList.get(i).turnAroundTime;
            System.out.println(NonPreemptivePriorityList.get(i).processNumber + " \t\t" + NonPreemptivePriorityList.get(i).arrivalTime
                    + " \t\t" + NonPreemptivePriorityList.get(i).burstTime + " \t\t" + NonPreemptivePriorityList.get(i).completionTime + " \t\t"
                    + NonPreemptivePriorityList.get(i).waitingTime + " \t\t" + NonPreemptivePriorityList.get(i).turnAroundTime);
        }
        System.out.println("\nAverage Waiting Time: "+ (averageWaitingTime/ NonPreemptivePriorityList.size()));
        System.out.println("Average Turnaround Time: "+ (averageTurnAroundTime/ NonPreemptivePriorityList.size()));
    }
}
