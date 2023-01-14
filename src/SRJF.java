// preemptive version of the Shortest Job First algorithm.
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SRJF {

    //FIELDS
    public int processNumber, arrivalTime, burstTime, completionTime, turnAroundTime, waitingTime, isComplete, origBurstTime;
    public static GanttChart ganttChart;

    //Arraylist of Objects
    static List<SRJF> SRJFList = new ArrayList<>();

    //Constructor
    public SRJF(int processNumber, int arrivalTime, int burstTime){
        this.processNumber = processNumber;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.origBurstTime = this.burstTime;
        this.completionTime = 0;
        this.turnAroundTime = 0;
        this.waitingTime = 0;
        this.isComplete = 0;
        this.ganttChart = new GanttChart();
    }

    public SRJF(){} //empty constructor

    /* ============================== METHODS ============================== */

    public static void compute(){

        int systemTime = 0, totalProcess = 0, curr = 0, min, lastCurr = 0;

        while(true){
            min = 999;

            //loop terminator
            if(totalProcess == SRJFList.size()){
                break;
            }
            for(int i = 0; i < SRJFList.size(); i++){
                if((SRJFList.get(i).arrivalTime <= systemTime) && (SRJFList.get(i).isComplete == 0) && (SRJFList.get(i).burstTime < min)){
                    min = SRJFList.get(i).burstTime;
                    curr = i;
                }
            }
            if((SRJFList.get(lastCurr).burstTime > SRJFList.get(curr).burstTime) && SRJFList.get(lastCurr).isComplete == 0){
                ganttChart.processList.add(String.valueOf(SRJFList.get(lastCurr).processNumber));
                ganttChart.timeList.add(String.valueOf(systemTime));
            }
            else if((SRJFList.get(lastCurr).burstTime < SRJFList.get(curr).burstTime) && SRJFList.get(lastCurr).isComplete == 1){
                ganttChart.processList.add(String.valueOf(SRJFList.get(lastCurr).processNumber));
                ganttChart.timeList.add(String.valueOf(systemTime));
            }
            else if(totalProcess == SRJFList.size()-1 && (systemTime == countTotalBurstTime()-1)){
                ganttChart.processList.add(String.valueOf(SRJFList.get(curr).processNumber));
                ganttChart.timeList.add(String.valueOf(systemTime+1));
            }
            SRJFList.get(curr).burstTime--;
            systemTime++;
            lastCurr = curr;
            if(SRJFList.get(curr).burstTime == 0){
                SRJFList.get(curr).completionTime = systemTime;
                SRJFList.get(curr).isComplete = 1;
                totalProcess++;
            }
        }
    }

    public static int countTotalBurstTime(){

        int totalBurstTime = 0;
        for(int i = 0; i < SRJFList.size(); i++){
            totalBurstTime += SRJFList.get(i).origBurstTime;
        }
        return totalBurstTime;
    }

    public static void displayGanttChart(){

        String firstLine = "PROCESSES: \t";
        String secondLine = "TIME:      \t";
        System.out.println("====================================== GANTT CHART ======================================");
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


        System.out.println("=========================================================================================");
    }

    public static void sortBasedOnCompletionTime(){
        SRJF temp;

        for(int i = 0; i < SRJFList.size()-1 ; i++) {
            for (int j = i + 1; j < SRJFList.size(); j++) {
                if(SRJFList.get(i).completionTime > SRJFList.get(j).completionTime) {
                    temp = (SRJFList.get(i));
                    SRJFList.set(i, SRJFList.get(j));
                    SRJFList.set(j, temp);
                }
            }
        }
    }

    public static void displayEverything() {

        displayGanttChart();
        sortBasedOnCompletionTime();

        float averageWaitingTime = 0;
        float averageTurnAroundTime = 0;

        System.out.println("\nP#" + " \t\t" + "AT" + " \t\t" + "BT" + " \t\t" + "CT" + " \t\t" + "WT" + "\t\t" + "TAT");
        for(int i = 0; i < SRJFList.size(); i++){
            SRJFList.get(i).turnAroundTime = SRJFList.get(i).completionTime - SRJFList.get(i).arrivalTime;
            SRJFList.get(i).waitingTime = SRJFList.get(i).turnAroundTime - SRJFList.get(i).origBurstTime;
            averageWaitingTime += SRJFList.get(i).waitingTime;
            averageTurnAroundTime += SRJFList.get(i).turnAroundTime;

            System.out.println( SRJFList.get(i).processNumber + "\t\t" +  SRJFList.get(i).arrivalTime + "\t\t" +  SRJFList.get(i).origBurstTime + "\t\t"
            +  SRJFList.get(i).completionTime + "\t\t" + SRJFList.get(i).waitingTime + "\t\t" +  SRJFList.get(i).turnAroundTime);
        }

        System.out.println("\nAverage Waiting Time: "+ (averageWaitingTime/SRJFList.size()));
        System.out.println("Average Turnaround Time: "+ (averageTurnAroundTime/SRJFList.size()));
    }
}
