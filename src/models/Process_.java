package models;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;

import algorithm.Page_;
import algorithm.SETTING;
import algorithm.Request_Comparator;
import controller.Memory_List;
import controller.Process_List;

public class Process_ {
	private int process_id;
	private String name;
	private int priority;
	private int status=0;//阻塞、就绪、运行
	private int cost;
	private int pc=0;
	private int spend=0;
	private LinkedList<Request_> request;
	private Process_Resourse resourse;
	public LinkedList<Integer> havelist;
	public ArrayList<Integer> pagelist;
	private int maxpage;//最大页数
	private boolean flag=false;//是否缺页
	public Process_(int process_id,int priority,String name,int cost,LinkedList<Request_> request,Resourse_ need,ArrayList<Integer> pagelist,int maxpage) {
		setProcess_id(process_id);
		this.priority=priority;
		this.cost=cost;
		setName(name);
		resourse=new Process_Resourse(need, new Resourse_());
		this.request=request;
		Collections.sort(request, Request_Comparator.comparator);
		this.pagelist=pagelist;
		this.maxpage=maxpage;
		havelist=new LinkedList<>();
	}
	public void block(){
		if(status!=0) {
			Process_List.block_list.add(this);
			Process_List.ready_list.remove(this);
		}
		status=0;
	}
	public void ready(){
		if(status==0) {
			Process_List.ready_list.add(this);
			Process_List.block_list.remove(this);
		}
		status=1;
	}
	public int getSpend(){
		return spend;
	}
	public int getPc(){
		return pc;
	}
	public int getCost() {
		return cost;
	}
	public int getMaxpage(){
		return maxpage;
	}

	public void run(){
		if(Memory_List.vir){
			try {
				flag=(boolean)Page_.class.getMethod(SETTING.page,new Class[]{Process_.class,ArrayList.class,LinkedList.class}).invoke(null, new Object[]{this,pagelist,havelist});
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e1) {
				System.out.println("页面置换算法问题");
			}
		}
		status=2;
		pc++;
	}

	public int getPriority() {
		return priority;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}

	public void setWait() {
		spend++;
	}
	public double getRp(){
		return (spend)/1.0/cost;
	}
	public static final String sta_name[]=new String[]{"阻塞","就绪","运行"};
	public String getStatus() {
		return sta_name[status];
	}
	public LinkedList<Request_> getRequest() {
		return request;
	}
	public void setRequest(LinkedList<Request_> request) {
		this.request = request;
	}
	public Resourse_ haveRequest(){
		if(!request.isEmpty()){
			Request_ r=request.getFirst();
			if(r.getPc()==pc) return r.getRequest();
		}
		return null;
	}
	public Resourse_ getNeed(){
		return resourse.need;
	}
	public Resourse_ getAllocation(){
		return resourse.allocation;
	}
	public Vector<Object> getRow(){
		Vector<Object> objects=new Vector<>();
		objects.add(process_id);
		objects.add(name);
		objects.add(getStatus());
		objects.add(priority);
		objects.add(spend);
		objects.add(cost);
		objects.add(pc);
		objects.add(getRp());
		return objects;
	}
	public Vector<Object> getmRow(){
		Vector<Object> objects=new Vector<>();
		objects.add(process_id);
		String hString="";
		for(Integer e:havelist) hString+=e.toString()+" ";
		objects.add(hString);
		if(flag) objects.add("不缺页");
		else objects.add("缺页");
		return objects;
	} 
}
