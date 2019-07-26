package algorithm;

import java.util.Comparator;

import models.Process_;
/*降序排列*/
public class Process_Comparetor {
	
	/**
	 *优先级调度
	 **/
	public static String[] key=new String[]{"优先级调度","先来先服务","短作业优先","高响应比优先"};
	public static String[] value=new String[]{"PSA","FCFS","SJF","HRRN"};
	public static Comparator<Process_> PSA=new Comparator<Process_>() {
		@Override
		public int compare(Process_ o1, Process_ o2) {
			return o1.getPriority()-o2.getPriority();
		}
	};
	/**
	 *先来先服务
	 **/
	public static Comparator<Process_> FCFS=new Comparator<Process_>() {
		@Override
		public int compare(Process_ o1, Process_ o2) {
			return o1.getProcess_id()-o2.getProcess_id();
		}
	};
	/**
	 *短作业优先
	 **/
	public static Comparator<Process_> SJF=new Comparator<Process_>() {
		@Override
		public int compare(Process_ o1, Process_ o2) {
			return o1.getCost()-o2.getCost();
		}
	};
	/**
	 *高响应比优先
	 **/
	public static Comparator<Process_> HRRN=new Comparator<Process_>() {
		@Override
		public int compare(Process_ o1, Process_ o2) {
			if(o1.getRp()>=o2.getRp()) return -1;
			return 1;
		}
	};
}
