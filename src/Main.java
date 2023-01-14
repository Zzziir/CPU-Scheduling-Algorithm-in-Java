import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean flag1 = false;
		int numberOfProcesses;
		String choice1;
		while(flag1 != true) {
	        System.out.print("CPU Scheduling Algorithm\n\n");
	        System.out.println("Choose CPU Scheduling Algorithm to Simulate");
	        System.out.println("[0] First Come First Serve (FCFS)");
	        System.out.println("[1] Shortest-Job-First (SJF)");
	        System.out.println("[2] Shortest Remaining Time (SRTF/SRJF)");
	        System.out.println("[3] Non Preemptive Priority");
	        System.out.println("[4] Preemptive Priority");
	        System.out.println("[5] Round Robin");
	        System.out.println("[6] Exit Program");
	        System.out.print("Choose: ");
	        int choice = sc.nextInt();
	        sc.nextLine();
	        System.out.println();
	        
	        switch(choice) {
	        	case 0:
	        		System.out.println("FIRST COME FIRST SERVE (FCFS)");
	                System.out.print("Enter number of Process: ");
	                numberOfProcesses = sc.nextInt();
	        		for(int i = 0; i < numberOfProcesses; i++){
	                    System.out.print("Enter the arrival time of Process " + (i+1) + ": ");
	                    int arrivalTime = sc.nextInt();
	                    System.out.print("Enter the burst time of Process " + (i+1) + ": ");
	                    int burstTime = sc.nextInt();
	                    sc.nextLine();
	                    FCFS process = new FCFS(i+1, arrivalTime, burstTime);
	                    FCFS.FCFSList.add(process);
	                }
	                FCFS.compute();
	                FCFS.displayEverything();

	                System.out.println();
	                System.out.println("Do you want to try other Algorithms? y/n");
	                System.out.println("Choice: ");
	                choice1 = sc.nextLine();
	                
	                if(choice1.equals("y")) {
	                	break;
	                }
	                else {
	                	System.exit(0);
	                }
	                
	        	case 1:
	        		System.out.println("SHORTEST JOB FIRST");
	                System.out.print("Enter number of Process: ");
	                numberOfProcesses = sc.nextInt();
	        		for (int i = 0; i < numberOfProcesses; i++) {
	                    System.out.print("Enter the arrival time of Process " + (i + 1) + ": ");
	                    int arrivalTime = sc.nextInt();
	                    System.out.print("Enter the burst time of Process " + (i + 1) + ": ");
	                    int burstTime = sc.nextInt();
	                    sc.nextLine();
	                    SJF sjf = new SJF(i+1, arrivalTime, burstTime);
	                    SJF.SJFList.add(sjf);
	                }
	                SJF.compute();
	                SJF.displayEverything();

	                System.out.println();
	                System.out.println("Do you want to try other Algorithms? y/n");
	                System.out.println("Choice: ");
	                choice1 = sc.nextLine();
	                
	                if(choice1.equals("y")) {
	                	break;
	                }
	                else {
	                	System.exit(0);
	                }
	        	case 2:
	        		System.out.println("SHORTEST REMAINING JOB FIRST");
	                System.out.print("Enter number of Process: ");
	                numberOfProcesses = sc.nextInt();
	        		for (int i = 0; i < numberOfProcesses; i++) {
	                    System.out.print("Enter the arrival time of Process " + (i + 1) + ": ");
	                    int arrivalTime = sc.nextInt();
	                    System.out.print("Enter the burst time of Process " + (i + 1) + ": ");
	                    int burstTime = sc.nextInt();
	                    sc.nextLine();
	                    SRJF srjf = new SRJF(i+1, arrivalTime, burstTime);
	                    SRJF.SRJFList.add(srjf);
	                }
	                SRJF.compute();
	                SRJF.displayEverything();
	                
	                System.out.println();
	                System.out.println("Do you want to try other Algorithms? y/n");
	                System.out.println("Choice: ");
	                choice1 = sc.nextLine();
	                
	                if(choice1.equals("y")) {
	                	break;
	                }
	                else {
	                	System.exit(0);
	                }
	        	case 3:
	        		System.out.println("NON PREEMPTIVE PRIORITY");
	                System.out.print("Enter number of Process: ");
	                numberOfProcesses = sc.nextInt();
	        		for (int i = 0; i < numberOfProcesses; i++) {
	                    System.out.print("Enter the arrival time of Process " + (i + 1) + ": ");
	                    int arrivalTime = sc.nextInt();
	                    System.out.print("Enter the burst time of Process " + (i + 1) + ": ");
	                    int burstTime = sc.nextInt();
	                    System.out.print("Enter the Priority of Process " + (i + 1) + ": ");
	                    int priority = sc.nextInt();
	                    sc.nextLine();
	                    NonPreemptivePriority npp = new NonPreemptivePriority(i+1, arrivalTime, burstTime, priority);
	                    NonPreemptivePriority.NonPreemptivePriorityList.add(npp);
	                }
	
	                NonPreemptivePriority.compute();
	                NonPreemptivePriority.displayEverything();
	                
	                System.out.println();
	                System.out.println("Do you want to try other Algorithms? y/n");
	                System.out.println("Choice: ");
	                choice1 = sc.nextLine();
	                
	                if(choice1.equals("y")) {
	                	break;
	                }
	                else {
	                	System.exit(0);
	                }
	        	case 4:
	        		System.out.println("PREEMPTIVE PRIORITY");
	                System.out.print("Enter number of Process: ");
	                numberOfProcesses = sc.nextInt();
	        		for (int i = 0; i < numberOfProcesses; i++) {
	                    System.out.print("Enter the arrival time of Process " + (i + 1) + ": ");
	                    int arrivalTime = sc.nextInt();
	                    System.out.print("Enter the burst time of Process " + (i + 1) + ": ");
	                    int burstTime = sc.nextInt();
	                    System.out.print("Enter the Priority of Process " + (i + 1) + ": ");
	                    int priority = sc.nextInt();
	                    sc.nextLine();
	                    PreemptivePriority pp = new PreemptivePriority(i+1, arrivalTime, burstTime, priority);
	                    PreemptivePriority.PreemptivePriorityList.add(pp);
	                }
	
	                PreemptivePriority.compute();
	                PreemptivePriority.displayEverything();

	                System.out.println();
	                System.out.println("Do you want to try other Algorithms? y/n");
	                System.out.println("Choice: ");
	                choice1 = sc.nextLine();
	                
	                if(choice1.equals("y")) {
	                	break;
	                }
	                else {
	                	System.exit(0);
	                }
	        	case 5:
	        		System.out.println("ROUND ROBIN");
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
	        	   	 
	        	   	System.out.println();
	                System.out.println("Do you want to try other Algorithms? y/n");
	                System.out.println("Choice: ");
	                choice1 = sc.nextLine();
	                
	                if(choice1.equals("y")) {
	                	break;
	                }
	                else {
	                	System.exit(0);
	                }
	        	case 6:
	        		System.exit(0);
	        }
		}
	}
}
