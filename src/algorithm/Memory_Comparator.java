package algorithm;

import java.util.Comparator;

import models.Memory_;

public class Memory_Comparator {
	public static String[] key=new String[]{"首次适应算法","最佳适应算法","最坏适应算法"};
	public static String[] value=new String[]{"FF","BF","WF"};
	/**
	 * 首次适应算法
	 **/
	public static Comparator<Memory_> FF=new Comparator<Memory_>() {
		@Override
		public int compare(Memory_ o1, Memory_ o2) {
			return o1.getStart()-o2.getStart();
		}
	};
	/**
	 * 最佳适应算法
	 **/
	public static Comparator<Memory_> BF=new Comparator<Memory_>() {
		@Override
		public int compare(Memory_ o1, Memory_ o2) {
			return o1.getMemorysize()-o2.getMemorysize();
		}
	};
	/**
	 * 最坏适应算法
	 **/
	public static Comparator<Memory_> WF=new Comparator<Memory_>() {
		@Override
		public int compare(Memory_ o1, Memory_ o2) {
			return o2.getMemorysize()-o1.getMemorysize();
		}
	};
	
}
