package models;

import java.util.ArrayList;
import java.util.Vector;

public class Storage_ {
	public static int fat[];//序号即为物理盘块号,-1为未使用,其他为同一文件的下一盘块,-2表示某文件的终止
	private static int dbsize;//每个盘块的大小
	public static Vector<Object> name=null;
	public static int bitmap[][];
	public static int row;
	public static int col;
	public static Vector<Object> name_bit=null;
	public Storage_(int row,int col,int dbsize){//盘块个数和盘块大小
		Storage_.row=row;
		Storage_.col=col;
		int dblength=row*col;
		fat=new int[dblength];
		for(int i=0;i<dblength;i++) fat[i]=-1;
		bitmap=new int[row][col];
		Storage_.dbsize=dbsize;
		name=new Vector<>();
		name.add("盘块号");
		name.add("下一盘块号");
		name_bit=new Vector<>();
		for(int i=0;i<col;i++) name_bit.add(i);
	}

	
	private static void updatefat(ArrayList<Integer> res) {
		for(int i=0;i<res.size();i++){
			if(i!=res.size()-1) fat[res.get(i)]=res.get(i+1);
			else fat[res.get(i)]=-2;
		}
	}
	
	public static int allotByBit(int size){
		int num;//申请盘块的个数
		int num1;
		if(size%dbsize==0)
			num=size/dbsize;
		else num=size/dbsize+1;
		num1=num;
		ArrayList<Integer> res=new ArrayList<Integer>();
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++){
				if(num==0)break;
				if(bitmap[i][j]==0){
					res.add(col*i+j);
					num--;
				}
			}
		if(num==0&&res.size()!=0){
			for(int i=0;i<row;i++)
				for(int j=0;j<col;j++){
					if(num1==0)break;
					if(bitmap[i][j]==0){
						bitmap[i][j]=1;
						res.add(col*i+j);
						num1--;
					}
				}
			updatefat(res);
			return res.get(0);
		}
		else return -1;
		
	}

	public static void delete(int fcb) {
		// TODO Auto-generated method stub
		ArrayList<Integer> allDb=new ArrayList<Integer>();
		
		while(fcb!=-2){
			allDb.add(fcb);
			fcb=fat[fcb];
		}
		
		for(Integer i:allDb){
			fat[i]=-1;
			int n=bitmap[0].length;
			bitmap[i/n][i%n]=0;
		}
	}
	public static Vector<Vector<Object>> gettable_fat(){
		Vector<Vector<Object>> vectors=new Vector<>(fat.length);
		for(int i=0;i<fat.length;i++){
			Vector<Object> objects=new Vector<>();
			objects.add(i);
			objects.add(fat[i]);
			vectors.add(objects);
		}
		return vectors;
	}
	public static Vector<Vector<Object>> gettable_bit(){
		Vector<Vector<Object>> vectors=new Vector<>(row);
		for(int i=0;i<col;i++){
			Vector<Object> objects=new Vector<>();
			for(int j=0;j<col;j++){
				objects.add(bitmap[i][j]);
			}
			vectors.add(objects);
		}
		return vectors;
	}
}
