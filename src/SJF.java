import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SJF {

    //FIELDS
    public int processNumber, arrivalTime, burstTime, completionTime, turnAroundTime, waitingTime, isComplete;
    public static GanttChart ganttChart;

    //Arraylist of Objects
    static List<SJF> SJFList = new ArrayList<>();

    //Constructor
    public SJF(int processNumber, int arrivalTime, int burstTime){
        this.processNumber = processNumber;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.completionTime = 0;
        this.turnAroundTime = 0;
        this.waitingTime = 0;
        this.isComplete = 0;
        this.ganttChart = new GanttChart();
    }

    public SJF(){} //empty constructor

    /* ============================== METHODS ============================== */

    public static void compute(){
        int systemTime = 0, totalProcess = 0;
        int curr, min;

        while(true){
            curr = SJFList.size() ;
            min = 999;

            //loop terminator
            if(totalProcess == SJFList.size()){
                break;
            }
            for(int i = 0; i < SJFList.size(); i++){
                if((SJFList.get(i).arrivalTime <= systemTime) && (SJFList.get(i).isComplete == 0) && (SJFList.get(i).burstTime < min)){
                    min = SJFList.get(i).burstTime;
                    curr = i;
                }
            }
            SJFList.get(curr).completionTime = systemTime + SJFList.get(curr).burstTime;
            systemTime += SJFList.get(curr).burstTime;
            SJFList.get(curr).turnAroundTime = SJFList.get(curr).completionTime - SJFList.get(curr).arrivalTime;
            SJFList.get(curr).waitingTime = SJFList.get(curr).turnAroundTime - SJFList.get(curr).burstTime;
            SJFList.get(curr).isComplete = 1;
            ganttChart.processList.add(String.valueOf(SJFList.get(curr).processNumber));
            ganttChart.timeList.add(String.valueOf(SJFList.get(curr).completionTime));
            totalProcess++;
        }
    }

    public static void sortBasedOnCompletionTime(){
        SJF temp;
        for(int i = 0; i < SJFList.size()-1 ; i++) {
            for (int j = i + 1; j < SJFList.size(); j++) {
                if(SJFList.get(i).completionTime > SJFList.get(j).completionTime) {
                    temp = (SJFList.get(i));
                    SJFList.set(i, SJFList.get(j));
                    SJFList.set(j, temp);
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
        for(int i = 0; i < SJFList.size(); i++){

            averageWaitingTime += SJFList.get(i).waitingTime;
            averageTurnAroundTime += SJFList.get(i).turnAroundTime;
            System.out.println(SJFList.get(i).processNumber + " \t\t" + SJFList.get(i).arrivalTime
                    + " \t\t" + SJFList.get(i).burstTime + " \t\t" + SJFList.get(i).completionTime + " \t\t"
                    + SJFList.get(i).waitingTime + " \t\t" + SJFList.get(i).turnAroundTime);
        }
        System.out.println("\nAverage Waiting Time: "+ (averageWaitingTime/SJFList.size()));
        System.out.println("Average Turnaround Time: "+ (averageTurnAroundTime/SJFList.size()));
    }
}
