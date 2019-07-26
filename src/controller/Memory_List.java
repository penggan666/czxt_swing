package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Vector;

import algorithm.Memory_Comparator;
import algorithm.SETTING;
import models.Memory_;
import models.Process_;

public class Memory_List{
	public static LinkedList<Memory_> list=null;
	public static boolean vir;//是否使用虚拟内存
	public static int sum;//总页数或最大内存
	public static int defaultpage;//默认分配页数
	public static ArrayList<Integer> vList=null;
	public static Vector<String> name=null;
	public Memory_List(int sum,boolean vir,int defaultpage) {
		list=new LinkedList<>();
		list.add(new Memory_(0,0,sum));
		Memory_List.vir=vir;
		Memory_List.defaultpage=defaultpage;
		name=new Vector<>();
		if(vir) {
			vList=new ArrayList<Integer>(Collections.nCopies(sum/defaultpage, 0));
			name.add("进程号");name.add("当前页");name.add("是否缺页");
		}
		else {
			name.add("进程号");name.add("内存起始地址");name.add("内存终止地址");name.add("占用内存大小");
		}
	}
	public static boolean add(int process_id,int memorysize){
		if(vir){
			int index=vList.indexOf(0);
			if(index!=-1) {
				vList.set(index, process_id);
				return true;
			}
		}
		else{
			try {
				Collections.sort(list,(Comparator<Memory_>)Memory_Comparator.class.getField(SETTING.memory).get(null));
			} catch (IllegalAccessException | IllegalArgumentException | SecurityException | NoSuchFieldException e1) {
				System.out.println("内存分配算法问题");
			}
			for(Memory_ e:list){
				if(e.getProcess_id()!=0||e.getMemorysize()<memorysize) continue;
				Memory_ m1=new Memory_(process_id,e.getStart(), memorysize);
				Memory_ m2=null;
				if(e.getMemorysize()>memorysize){
					m2=new Memory_(0, e.getStart()+memorysize,e.getMemorysize()-memorysize);
				}
				list.remove(e);
				list.add(m1);
				if(m2!=null) list.add(m2);
				return true;
			}
		}
		return false;
	}
	public static Memory_ get(int process_id){
		for(Memory_ e:list){
			if(e.getProcess_id()==process_id) return e;
		}
		return null;
	}
	public static void remove(int process_id){
		if(vir){
			vList.set(vList.indexOf(process_id), 0);
		}
		else{
			Collections.sort(list,Memory_Comparator.FF);
			for(int i=0;i<list.size();i++){
				Memory_ m=list.get(i);
				if(process_id==m.getProcess_id()){
					Memory_ m1=null;
					Memory_ m2=null;
					if(i>0) m1=list.get(i-1);
					if(i+1<list.size()) m2=list.get(i+1);
					if(m1!=null&&m1.getProcess_id()==0) {
						m=connect(m1, m);
						if(m2!=null&&m2.getProcess_id()==0) m=connect(m, m2);
					}
					else if(m2!=null&&m2.getProcess_id()==0) m=connect(m, m2);
					else {
						m.setProcess_id(0);
						return ;
					}
					list.add(m);
					return;
				}
			}
		}
	}
	public static Memory_ connect(Memory_ m1,Memory_ m2){
		Memory_ p=new Memory_(0, m1.getStart(),m1.getMemorysize()+m2.getMemorysize());
		list.remove(m1);list.remove(m2);
		return p;
	}

	public static Vector<Vector<Object>> gettable(){
		Vector<Vector<Object>> vector=new Vector<>();
		if(vir){
			for(Process_ e:Process_List.ready_list) vector.add(e.getmRow());
			for(Process_ e:Process_List.block_list) vector.add(e.getmRow());
		}
		else{
			for(Memory_ e:list) vector.add(e.getRow());
		}
		return vector;
	}
}
