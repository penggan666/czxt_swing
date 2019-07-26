package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;

import models.Process_;

public class Page_ {
	public static String[] key=new String[]{"最近最久未使用","先进先出"};
	public static String[] value=new String[]{"LRU","FIFO"};
	public static boolean LRU(Process_ p,ArrayList<Integer> pagelist,LinkedList<Integer> havelist) {
		boolean flag=true;
		int page=pagelist.get(p.getPc());
		if(!havelist.contains(page)) {
			flag=false;
			if(havelist.size()==p.getMaxpage()) havelist.removeFirst();
		}
		else havelist.remove(havelist.indexOf(page));
		havelist.add(page);
		return flag;
	}
	public static boolean FIFO(Process_ p,ArrayList<Integer> pagelist,LinkedList<Integer> havelist){
		boolean flag=true;
		int page=pagelist.get(p.getPc());
		if(!havelist.contains(page)){
			flag=false;
			if(havelist.size()==p.getMaxpage()) havelist.removeFirst();	
			havelist.add(page);
		}
		return flag;
	}
}
