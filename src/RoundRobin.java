import java.util.Scanner;

public class RoundRobin {
    public static void main(String[] args) {
   	 int numOfProcesses, remain,timeQuantum;
   	 boolean flag = false;
   	 double avgWaitingTime, avgTurnAroundTime;
   	 int waitTime=0, turnaroundTime=0;
   	 
   	 Scanner scan = new Scanner(System.in);
   	 System.out.print("Enter the number of Processes:");
   	 numOfProcesses = scan.nextInt();
   	 remain = numOfProcesses;
   	 
   	 int[] arrivalTime = new int[10];
   	 int[] burstTime = new int[10];  
   	 int[] completionTime = new int[10];  
   	 int[] waitingTime = new int[10];
   	 int[] turnAroundTime = new int[10];
   	 int[] tempBurstTime = new int[10];  
   	 
   	 //inputs
   	 for(int i=0; i<numOfProcesses; i++) {
   		 System.out.print("Enter the arrival time of process "+(i+1)+": ");
   		 arrivalTime[i] = scan.nextInt();
   		 System.out.print("Enter the burst time of process "+(i+1)+": ");
   		 burstTime[i] = scan.nextInt();
   		 tempBurstTime[i] = burstTime[i];
   	 }
   	 System.out.print("Enter Time Quantum: ");
   	 timeQuantum = scan.nextInt();
   	 
   	 //round robin scheduling algorithm
   	 System.out.println("PROCESS\t|\tAT\t|\tBT\t|\tCT\t|\tWT\t|\tTAT");
   	 for(int i=0, time=0; remain!=0;) {
   		 if(tempBurstTime[i] <= timeQuantum && tempBurstTime[i] > 0) {
   			 time=time+tempBurstTime[i];
   			 tempBurstTime[i]=0;
   			 flag=true;
   		 }
   		 else if(tempBurstTime[i] > 0) {
   			 tempBurstTime[i] = tempBurstTime[i] - timeQuantum;
   			 time = time + timeQuantum;
   		 }
   		 if(tempBurstTime[i]==0 && flag == true){
   			 remain--;
   			 waitingTime[i] = time - arrivalTime[i] - burstTime[i];
   			 turnAroundTime[i] = time - arrivalTime[i];
   			 completionTime[i] = turnAroundTime[i] + arrivalTime[i];
   			 System.out.println("P["+(i+1)+"]\t|\t"+arrivalTime[i]+"\t|\t" +burstTime[i] +"\t|\t"+ completionTime[i] +"\t|\t" + waitingTime[i] +"\t|\t" + turnAroundTime[i]);
   			 waitTime += time - arrivalTime[i] - burstTime[i];
   			 turnaroundTime += time - arrivalTime[i];
   			 flag = false;
   		 }
   		 if(i==numOfProcesses-1)
   			 i = 0;
   		 else if(arrivalTime[i+1] <= time)
   			 i++;
   		 else
   			 i = 0;
   	 }
   	 avgWaitingTime = waitTime * 1.0 / numOfProcesses;
   	 avgTurnAroundTime = turnaroundTime * 1.0 / numOfProcesses;
   	 System.out.println("\nAverage Waiting Time: " + String.format("%.2f", avgWaitingTime));
   	 System.out.println("Average Turn Around: " + String.format("%.2f", avgTurnAroundTime));
    }
}
