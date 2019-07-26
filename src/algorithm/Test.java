package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import controller.Device_List;
import controller.Memory_List;
import controller.Process_List;
import front.MainFile;
import models.File_;
import models.Package_;
import models.Request_;
import models.Resourse_;

public class Test {
	public Test(){
		
		Package_ p=MainFile.root;
		p.add(new File_("QQ", "exe", 10));
		p.add(new File_("微信", "exe", 4));
		p.add(new File_("音乐", "exe", 7));
		p.add(new File_("作业", "txt", 4));
		Package_ p2 = new Package_("D");
		p.add(p2);
		p2.add(new File_("写一半的实验报告",  "doc",  3));
		p2.add(new File_("host", "", 3));
		Package_ ePackage_=new Package_("E");
		ePackage_.add(new File_("算法答案", "pdf", 3));
		p2.add(ePackage_);
		MainFile.mainFile.updateTree();
		MainFile.mainFile.updateTable();
		Device_List.add("a", 3);
		Device_List.add("b", 12);
		Device_List.add("c", 14);
		Device_List.add("d", 14);
		if(Memory_List.vir){
	    	{
	    		ArrayList<Integer> pagelist=new ArrayList<>();
	    		pagelist.add(4);
		    	pagelist.add(3);
		    	pagelist.add(2);
		    	pagelist.add(1);
		    	pagelist.add(4);
		    	pagelist.add(3);
		    	pagelist.add(5);
		    	pagelist.add(4);
		    	pagelist.add(3);
		    	pagelist.add(2);
		    	pagelist.add(1);
		    	pagelist.add(5);
		    	Process_List.add(3, "P1", pagelist.size(), new LinkedList<Request_>(),pagelist);
	    	}
	    	{
	    		ArrayList<Integer> pagelist=new ArrayList<>();
	    		pagelist.add(1);
		    	pagelist.add(2);
		    	pagelist.add(3);
		    	pagelist.add(4);
		    	Process_List.add(0, "P2", pagelist.size(), new LinkedList<Request_>(),pagelist);
	    	}
	    	{
	    		ArrayList<Integer> pagelist=new ArrayList<>();
	    		pagelist.add(1);
		    	pagelist.add(1);
		    	Process_List.add(1, "P3", pagelist.size(), new LinkedList<Request_>(),pagelist);
	    	}
	    	{
	    		ArrayList<Integer> pagelist=new ArrayList<>();
	    		pagelist.add(1);
		    	pagelist.add(1);
		    	Process_List.add(2, "P4", pagelist.size(), new LinkedList<Request_>(),pagelist);
	    	}
	    }
	    else{
	    	{
				LinkedList<Request_> request0=new LinkedList<Request_>();
				Resourse_ request=new Resourse_();
				request.put("c", 3);
				request.put("d", 2);
				request0.add(new Request_(0, request));
				request=new Resourse_();
				request.put("c", 1);
				request.put("d", 2);
				request0.add(new Request_(1, request));
				Process_List.add(1, "P0", 2, request0,500);
			}
			{
				LinkedList<Request_> request0=new LinkedList<Request_>();
				Resourse_ request=new Resourse_();
				request.put("a", 1);
				request0.add(new Request_(0, request));
				request=new Resourse_();
				request.put("a", 1);
				request.put("b", 7);
				request.put("c", 5);
				
				request0.add(new Request_(1, request));
				Process_List.add(0, "P1", 2, request0,600);
			}
			{
				LinkedList<Request_> request0=new LinkedList<Request_>();
				Resourse_ request=new Resourse_();
				request.put("a", 1);
				request.put("b", 3);
				request.put("c", 5);
				request.put("d", 4);
				request0.add(new Request_(0, request));
				request=new Resourse_();
				request.put("a", 2);
				request.put("b", 3);
				request.put("c", 5);
				request.put("d", 6);
				request0.add(new Request_(1, request));
				Process_List.add(2, "P2", 2, request0,500);
			}
			{
				LinkedList<Request_> request0=new LinkedList<Request_>();
				Resourse_ request=new Resourse_();
				request.put("b", 3);
				request.put("c", 3);
				request.put("d", 2);
				request0.add(new Request_(0, request));
				request=new Resourse_();
				request.put("b", 6);
				request.put("c", 5);
				request.put("d", 2);
				request0.add(new Request_(1, request));
				Process_List.add(0, "P3", 2, request0,500);
			}
			{
				LinkedList<Request_> request0=new LinkedList<Request_>();
				Resourse_ request=new Resourse_();
				request.put("c", 1);
				request.put("d", 4);
				request0.add(new Request_(0, request));
				request=new Resourse_();
				request.put("b", 6);
				request.put("c", 5);
				request.put("d", 6);
				request0.add(new Request_(1, request));
				Process_List.add(0, "P4", 2, request0,500);
			}
			{
				LinkedList<Request_> request0=new LinkedList<Request_>();
				Process_List.add(0, "No_request", 2, request0,500);
			}
	    }
	}
}
