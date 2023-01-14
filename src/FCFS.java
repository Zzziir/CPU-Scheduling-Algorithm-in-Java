import java.util.*;

public class FCFS{

    //FIELDS
    public int processNumber, arrivalTime, burstTime, completionTime, turnAroundTime, waitingTime;
    public static GanttChart ganttChart;

    //Arraylist of Objects
    static List<FCFS> FCFSList = new ArrayList<>();

    //CONSTRUCTORS

    public FCFS(int processNumber, int arrivalTime, int burstTime){
        this.processNumber = processNumber;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.completionTime = 0;
        this.turnAroundTime = 0;
        this.waitingTime = 0;
        this.ganttChart = new GanttChart();
    }

    public FCFS(){} //empty constructor

    /* ============================== METHODS ============================== */

    public static void sortArrivalTime(){

        FCFS temp;
        for(int i = 0; i < FCFSList.size()-1 ; i++){
            for(int j = i+1; j < FCFSList.size(); j++){
                if(FCFSList.get(i).arrivalTime > FCFSList.get(j).arrivalTime) {
                    temp = (FCFSList.get(i));
                    FCFSList.set(i, FCFSList.get(j));
                    FCFSList.set(j, temp);
                }
            }
        }
    }

    public static void compute(){

        sortArrivalTime();

        for(int i = 0; i < FCFSList.size(); i++){
            if( i == 0){
                FCFSList.get(i).completionTime = FCFSList.get(i).arrivalTime + FCFSList.get(i).burstTime;
                ganttChart.processList.add(String.valueOf(FCFSList.get(i).processNumber));
                ganttChart.timeList.add(String.valueOf(FCFSList.get(i).completionTime));
            }
            else{
                if(FCFSList.get(i).arrivalTime > FCFSList.get(i-1).completionTime){
                    FCFSList.get(i).completionTime = FCFSList.get(i).arrivalTime + FCFSList.get(i).burstTime;
                    ganttChart.processList.add(String.valueOf(FCFSList.get(i).processNumber));
                    ganttChart.timeList.add(String.valueOf(FCFSList.get(i).completionTime));
                }
                else{
                    FCFSList.get(i).completionTime = FCFSList.get(i-1).completionTime + FCFSList.get(i).burstTime;
                    ganttChart.processList.add(String.valueOf(FCFSList.get(i).processNumber));
                    ganttChart.timeList.add(String.valueOf(FCFSList.get(i).completionTime));
                }
            }
            FCFSList.get(i).turnAroundTime = FCFSList.get(i).completionTime - FCFSList.get(i).arrivalTime;
            FCFSList.get(i).waitingTime = FCFSList.get(i).turnAroundTime - FCFSList.get(i).burstTime;
        }
    }

    public static void displayGanttChart(){

        String firstLine = "PROCESSES: \t";
        String secondLine = "TIME:      \t";
        System.out.println("=================================== GANTT CHART ===================================");
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

        float averageWaitingTime = 0;
        float averageTurnAroundTime = 0;
        System.out.println("\nP#" + " \t" + "AT" + " \t" + "BT" + " \t" + "CT" + " \t" + "WT" + "\t" + "TAT");
        for(int i = 0; i < FCFSList.size(); i++){

            averageWaitingTime += FCFSList.get(i).waitingTime;
            averageTurnAroundTime += FCFSList.get(i).turnAroundTime;

            System.out.println(FCFSList.get(i).processNumber + " \t" + FCFSList.get(i).arrivalTime
                    + " \t" + FCFSList.get(i).burstTime + " \t" + FCFSList.get(i).completionTime + " \t"
                    + FCFSList.get(i).waitingTime + " \t" + FCFSList.get(i).turnAroundTime);
        }
        System.out.println("\nAverage Waiting Time: "+ (averageWaitingTime/ FCFSList.size()));
        System.out.println("Average Turnaround Time: "+(averageTurnAroundTime/ FCFSList.size()));
        
    }
}
