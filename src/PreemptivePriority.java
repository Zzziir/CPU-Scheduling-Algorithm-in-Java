import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PreemptivePriority {

    //FIELDS
    public int processNumber, arrivalTime, burstTime, origBurstTime, completionTime, turnAroundTime, waitingTime, isComplete, priorityNum;
    public static GanttChart ganttChart;

    //Arraylist of Objects
    static List<PreemptivePriority> PreemptivePriorityList = new ArrayList<>();

    //Constructor
    public PreemptivePriority(int processNumber, int arrivalTime, int burstTime, int priorityNum){
        this.processNumber = processNumber;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.origBurstTime = this.burstTime;
        this.completionTime = 0;
        this.turnAroundTime = 0;
        this.waitingTime = 0;
        this.isComplete = 0;
        this.priorityNum = priorityNum;
        this.ganttChart = new GanttChart();
    }

    public PreemptivePriority(){} //empty constructor

    /* ============================== METHODS ============================== */

    public static void compute(){

        int systemTime = 0, totalProcess = 0, curr = 0, min, lastCurr = 0;

        while(true){
            min = PreemptivePriorityList.size()+1;

            //loop terminator
            if(totalProcess == PreemptivePriorityList.size()){
                break;
            }
            for(int i = 0; i < PreemptivePriorityList.size(); i++){
                if((PreemptivePriorityList.get(i).arrivalTime <= systemTime) && (PreemptivePriorityList.get(i).isComplete == 0) && (PreemptivePriorityList.get(i).priorityNum < min)){
                    min = PreemptivePriorityList.get(i).priorityNum;
                    curr = i;
                }
            }

            //GANTT CHART
            if((PreemptivePriorityList.get(lastCurr).priorityNum > PreemptivePriorityList.get(curr).priorityNum) && PreemptivePriorityList.get(lastCurr).isComplete == 0){
                ganttChart.processList.add(String.valueOf(PreemptivePriorityList.get(lastCurr).processNumber));
                ganttChart.timeList.add(String.valueOf(systemTime));
            }
            else if((PreemptivePriorityList.get(lastCurr).priorityNum < PreemptivePriorityList.get(curr).priorityNum) && PreemptivePriorityList.get(lastCurr).isComplete == 1){
                ganttChart.processList.add(String.valueOf(PreemptivePriorityList.get(lastCurr).processNumber));
                ganttChart.timeList.add(String.valueOf(systemTime));
            }
            else if(totalProcess == PreemptivePriorityList.size()-1 && (systemTime == countTotalBurstTime()-1)){
                ganttChart.processList.add(String.valueOf(PreemptivePriorityList.get(curr).processNumber));
                ganttChart.timeList.add(String.valueOf(systemTime+1));
            }

            PreemptivePriorityList.get(curr).burstTime--;
            systemTime++;
            lastCurr = curr;
            if(PreemptivePriorityList.get(curr).burstTime == 0){
                PreemptivePriorityList.get(curr).completionTime = systemTime;
                PreemptivePriorityList.get(curr).isComplete = 1;
                totalProcess++;
            }
        }
    }

    public static int countTotalBurstTime(){

        int totalBurstTime = 0;
        for(int i = 0; i < PreemptivePriorityList.size(); i++){
            totalBurstTime += PreemptivePriorityList.get(i).origBurstTime;
        }
        return totalBurstTime;
    }

    public static void sortBasedOnCompletionTime(){
        PreemptivePriority temp;
        for(int i = 0; i < PreemptivePriorityList.size()-1 ; i++) {
            for (int j = i + 1; j < PreemptivePriorityList.size(); j++) {
                if(PreemptivePriorityList.get(i).completionTime > PreemptivePriorityList.get(j).completionTime) {
                    temp = (PreemptivePriorityList.get(i));
                    PreemptivePriorityList.set(i, PreemptivePriorityList.get(j));
                    PreemptivePriorityList.set(j, temp);
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

    public static void displayEverything() {

        displayGanttChart();
        sortBasedOnCompletionTime();

        float averageWaitingTime = 0;
        float averageTurnAroundTime = 0;

        System.out.println("\nP#" + " \t\t" + "AT" + " \t\t" + "BT" + " \t\t" + "CT" + " \t\t" + "WT" + "\t\t" + "TAT");
        for(int i = 0; i < PreemptivePriorityList.size(); i++){
            PreemptivePriorityList.get(i).turnAroundTime = PreemptivePriorityList.get(i).completionTime - PreemptivePriorityList.get(i).arrivalTime;
            PreemptivePriorityList.get(i).waitingTime = PreemptivePriorityList.get(i).turnAroundTime - PreemptivePriorityList.get(i).origBurstTime;
            averageWaitingTime += PreemptivePriorityList.get(i).waitingTime;
            averageTurnAroundTime += PreemptivePriorityList.get(i).turnAroundTime;

            System.out.println( PreemptivePriorityList.get(i).processNumber + "\t\t" +  PreemptivePriorityList.get(i).arrivalTime + "\t\t" +  PreemptivePriorityList.get(i).origBurstTime + "\t\t"
                    +  PreemptivePriorityList.get(i).completionTime + "\t\t" + PreemptivePriorityList.get(i).waitingTime + "\t\t" +  PreemptivePriorityList.get(i).turnAroundTime);
        }

        System.out.println("\nAverage Waiting Time: "+ (averageWaitingTime/PreemptivePriorityList.size()));
        System.out.println("Average Turnaround Time: "+ (averageTurnAroundTime/PreemptivePriorityList.size()));
    }
}
