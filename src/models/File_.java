package models;

import java.util.Vector;

import algorithm.SETTING;

public class File_  {
	public String name;
	public String type;
	public String time;
	public int size;
	public String path;
	public int fcb;

	public File_(String file_name,String file_type,int size){
		this.name = file_name;
		this.type = file_type;
		this.time=SETTING.createtime();
		this.size = size;
		fcb=Storage_.allotByBit(size);
	}
	public File_(File_ f){
		this.name=f.name;
		this.type=f.type;
		this.time=SETTING.createtime();
		this.size=f.size;
		fcb=Storage_.allotByBit(size);
	}
	public void deletefcb(){
		//删除申请的内存块
		Storage_.delete(fcb);
	}
	public void SetFile(String name,String type){
		this.name=name;
		this.type=type;
	}
	public boolean resize(int size){
		int fcb=Storage_.allotByBit(size);
		if(fcb==-1) return false;
		deletefcb();
		this.fcb=fcb;
		this.size=size;
		this.time=SETTING.createtime();
		return true;
	}
	public String getName(){
		return name;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		if(type.equals("")) return name;
		else return name+"."+type;
	}
	
	public Vector<Object> getRow(){
		Vector<Object> objects=new Vector<>();
		objects.add(getName());
		objects.add(getType());
		objects.add(size);
		objects.add(time);
		objects.add(fcb);
		return objects;
	}
	public int getSize(){
		return size;
	}

}
