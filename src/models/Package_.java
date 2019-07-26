package models;

import java.util.ArrayList;
import java.util.Vector;

import algorithm.SETTING;

public class Package_  {
	private ArrayList<File_> filelist = null;
	private ArrayList<Package_> packagelist = null;
	private String name;
	private String path;
	private String time;
	
	public Package_(String packagename){
		path="";
		this.time=SETTING.createtime();
		this.name = packagename;
		filelist = new ArrayList<File_>();
		packagelist = new ArrayList<Package_>();
	}
	public Package_(Package_ p){
		this.time=SETTING.createtime();
		this.name=p.name;
		this.filelist=new ArrayList<File_>();
		for(File_ f:p.filelist){
			File_ file_=new File_(f);
			if(file_.fcb!=-1)
			this.filelist.add(file_);
		}
		this.packagelist=new ArrayList<Package_>();
		for(Package_ p1:p.packagelist){
			this.packagelist.add(new Package_(p1));
		}
	}
	
	public ArrayList<File_> getFilelist() {
		return filelist;
	}
	
	public ArrayList<Package_> getPackagelist() {
		return packagelist;
	}
	
	public int getSize() {
		int size=0;
		for(File_ e:filelist) size+=e.getSize();
		for(Package_ e:packagelist) size+=e.getSize();
		return size;
	}
	
	public String getName() {
		return name;
	}
	
	
	public String getPath() {
		return path+name+"/";
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean check(String name,String type){
	    for(File_ e:filelist) if(e.getName().equals(name)&&e.getType().equals(type)) return false;
    	return true;
	}
	
	public boolean check(String name){
	    for(Package_ e:packagelist) if(e.getName().equals(name)) return false;
    	return true;
	}
	
	public void add(Package_ p){
		packagelist.add(p);
		setFilePath(getPath());
	}
	
	public void add(File_ f){
		filelist.add(f);
		setFilePath(getPath());
	}
	
	public void deletefile(File_ f){
		f.deletefcb();//删除前文件前先删除fcb
		filelist.remove(f);
	}
	
	public void deletepackage(Package_ p){
		for(File_ e:p.filelist) e.deletefcb();
		for(Package_ e:p.packagelist) deletepackage(e);
		packagelist.remove(p);
	}

	public void setFilePath(String childpath){
		for(File_ f:filelist){
			f.setPath(childpath+f.toString());
		}
		for(Package_ e:packagelist){
			e.setPath(childpath);
			e.setFilePath(e.getPath());
		}
	}

	public void setName(String name){
		this.name=name;
	}
	
	public ArrayList<Object> find(String name,ArrayList<Object> finded){
		for(File_ i:filelist) {
			if(i.getName().equals(name)||i.getPath().equals(name)||i.toString().equals(name)) {
				finded.add(i);
			}
		}
		for(Package_ p:packagelist) {
			if(p.getName().equals(name)||p.getPath().equals(name)) {
				finded.add(p);
			}
			p.find(name, finded);
		}
		return finded;
	}

	@Override
	public String toString() {
		return name;
	}
	public Vector<Object> getRow(){
		Vector<Object> objects=new Vector<>();
		objects.add(getName());
		objects.add("文件夹");
		objects.add(getSize());
		objects.add(time);
		objects.add("-");
		return objects;
	}
}