package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

import algorithm.Banker_;
import algorithm.SETTING;
import front.MainProcess;
import algorithm.Process_Comparetor;
import models.Request_;
import models.Resourse_;
import models.Process_;

public class Process_List {
	public static LinkedList<Process_> ready_list=null;//就绪队列
	public static LinkedList<Process_> block_list=null;//阻塞队列
	public static ArrayList<Process_> run_list=null;
	public static int auto_process_id;//进程号自增主键
	private static int degree;//允许调入内存的作业数
	private static int batch;//允许同时处理的作业数
	public static int step;//运行时间
	public static Vector<Object> name=null;
	public Process_List(int degree,int batch) {
		ready_list=new LinkedList<>();
		block_list=new LinkedList<>();
		run_list=new ArrayList<>();
		auto_process_id=1;
		step=0;
		Process_List.degree=degree;
		Process_List.batch=batch;
		name=new Vector<>();
		name.add("进程号");
		name.add("进程名");
		name.add("状态");
		name.add("优先级");
		name.add("总运行时间");
		name.add("需要时间");
		name.add("已运行时间");
		name.add("响应比");
	}

	public static void run(){
		MainProcess.m3.run.setEnabled(false);

		try {
			Collections.sort(block_list,(Comparator<Process_>)Process_Comparetor.class.getField(SETTING.block).get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			System.out.println("作业调度算法问题");
		}
		int s=block_list.size();
		/*若阻塞队列不为空且就绪队列未满，从阻塞队列末尾移动到就绪队列*/
		for(int i=0;i<s&&ready_list.size()<degree;i++){
			Process_ p=block_list.getFirst();
			block_list.removeFirst();
			Resourse_ request=p.haveRequest();
			if(request!=null) {
				/*若满足银行家算法，*/
				if(Banker_.judge(p, request, ready_list)){
					p.getAllocation().add(request);
					p.getNeed().sub(request);
					Device_List.resourse.sub(request);
					p.getRequest().removeFirst();
					p.ready();
				}
				else block_list.add(p);
			}
			else {
				p.ready();
			}
		}
		try {
			MainProcess.m3.setTitle("银行家算法检查");
			MainProcess.m3.update();
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			Collections.sort(ready_list,(Comparator<Process_>)Process_Comparetor.class.getField(SETTING.ready).get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			// TODO 自动生成的 catch 块
			System.out.println("进程调度算法问题");
		}
		for(int i=0;i<batch&&i<ready_list.size();i++){
			Process_ p=ready_list.get(i);
			p.run();
			run_list.add(p);
		}
		try {
			MainProcess.m3.setTitle("运行中...");
			MainProcess.m3.update();
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		for(Process_ e:ready_list){
			e.setWait();
		}
		for(Process_ e:block_list){
			e.setWait();
		}
		step++;
		for(int i=run_list.size()-1;i>=0;i--){
			Process_ e=run_list.get(i);
			/*销毁进程*/
			if(e.getCost()==e.getPc()) {
				remove(e.getProcess_id());
				continue;
			}
			if(e.haveRequest()==null) e.ready();
			else e.block();
		}
		run_list.clear();
		MainProcess.m3.setTitle("等待运行...");
		MainProcess.m3.update();
		MainProcess.m3.run.setEnabled(true);
	}
	public static boolean add(int pri,String name,int cost,LinkedList<Request_> request,Object o){
		Resourse_ need=new Resourse_();
		for(Request_ e:request){
			need.add(e.getRequest());
		}
		if(Device_List.total.compareTo(need)==-1) return false;
		ArrayList<Integer> havelist=null;
		if(Memory_List.vir){
			havelist=(ArrayList<Integer>)o;
			if(Memory_List.add(auto_process_id, 0)==false) return false;
		}
		else{
			if(Memory_List.add(auto_process_id, (int)o)==false) return false;
		}
		Process_ p=new Process_(auto_process_id, pri, name, cost,request,need,havelist,Memory_List.defaultpage);
		block_list.add(p);
		auto_process_id++;
		return true;
	}

	public static void remove(int process_id){
		for(Process_ e:ready_list){
			if(e.getProcess_id()==process_id) {
				/*归还资源*/
				Device_List.resourse.add(e.getAllocation());
				ready_list.remove(e);
				Memory_List.remove(e.getProcess_id());
				return ;
			}
		}
	}
	public static Process_ get(int process_id){
		for(Process_ e:ready_list) if(e.getProcess_id()==process_id) return e;
		for(Process_ e:block_list) if(e.getProcess_id()==process_id) return e;
		return null;
	}
	
	
	public static Vector<Vector<Object>> gettable(){
		Vector<Vector<Object>> vector=new Vector<>();
		for(Process_ e:ready_list) vector.add(e.getRow());
		for(Process_ e:block_list) vector.add(e.getRow());
		return vector;
	}
}
