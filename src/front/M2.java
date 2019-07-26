package front;

import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Memory_List;
import controller.Process_List;
import models.Request_;
import models.Resourse_;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class M2 extends JFrame {

	private JPanel contentPane;
	public static Vector<String> name=null;
	public static int process_id;
	public static Vector<Vector<Object>> vector=null;
	private JTable table;
	private JButton add;
	private TableModel tableModel=null;
	private JScrollPane tp;
	private JTextField textField_cost;
	private JTextField textField_ms;
	private JTextField textField_pri;
	private JLabel label_pri;
	public M2(String pname) {
		this.setTitle(pname);
		setBounds(200, 200, 348, 406);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		vector=new Vector<>();
		name=new Vector<>();
		name.add("时间片");name.add("设备名");name.add("设备数量");
		tp= new JScrollPane();
		
		add = new JButton("添加行");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Object> objects=new Vector<>();
				objects.add("");objects.add("");objects.add("");
				vector.add(objects);
				update();
			}
		});
		update();	
		JLabel label = new JLabel("设备需求");
		JButton confirm = new JButton("确认");
		//添加进程
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LinkedList<Request_> request=getRequest();
				int pri=Integer.parseInt(textField_pri.getText());//优先级
				if(Memory_List.vir)
				{
					String page[]=textField_cost.getText().trim().split(" ");
					ArrayList<Integer> pagelist=new ArrayList<>();
					for(String s:page)
						pagelist.add(Integer.valueOf(s));
					if(Process_List.add(pri,pname,pagelist.size(),request,pagelist)==false) 
						JOptionPane.showMessageDialog(null, "无法给该进程分配内存或资源",null, JOptionPane.ERROR_MESSAGE);
				}
				else{
					int msize=Integer.parseInt(textField_ms.getText());
					int cost=Integer.parseInt(textField_cost.getText());
					if(Process_List.add(pri, pname,cost, request, msize)==false)
						JOptionPane.showMessageDialog(null, "无法给该进程分配内存或资源",null, JOptionPane.ERROR_MESSAGE);
				}
				setVisible(false);
				MainProcess.m3.update();
			}
		});
		confirm.setBounds(194, 154, 123, 40);
		contentPane.add(confirm);
		
		JLabel label_cost = new JLabel("需要时间片");
		JLabel label_ms = new JLabel("最大内存");
		
		textField_cost = new JTextField();
		textField_cost.setText("5");
		textField_cost.setColumns(10);
		
		textField_ms = new JTextField();
		textField_ms.setText("100");
		textField_ms.setColumns(10);
		if(Memory_List.vir){
			label_cost.setText("页面走向");
			textField_cost.setText("1 2 3 4 5");
			label_ms.setVisible(false);
			textField_ms.setVisible(false);
		}
		
		textField_pri = new JTextField();
		textField_pri.setText("1");
		textField_pri.setColumns(10);
		
		label_pri = new JLabel("优先级");
		
		JButton remove = new JButton("删除行");
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!vector.isEmpty()) vector.remove(vector.size()-1);
				update();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(label)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(label_cost)
									.addComponent(label_ms)
									.addComponent(label_pri))
								.addGap(45)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_pri, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_ms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_cost, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(confirm, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(tp, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(remove)
									.addComponent(add)))))
					.addGap(245))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_pri, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_pri))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_cost)
						.addComponent(textField_cost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_ms)
						.addComponent(textField_ms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tp, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(add)
							.addGap(9)
							.addComponent(remove)))
					.addGap(18)
					.addComponent(confirm, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(107))
		);
		contentPane.setLayout(gl_contentPane);
		

	}
	public void update(){	
		tableModel=new DefaultTableModel(vector,name);
		table=new JTable(tableModel);
		tp.setViewportView(table);
	}
	public LinkedList<Request_> getRequest(){
		LinkedList<Request_> list=new LinkedList<>();
		HashMap<Integer,Resourse_> map=new HashMap<>();
		for(Vector<Object> e:vector){
			int pc=Integer.parseInt(e.get(0).toString());
			Resourse_ resourse_=(Resourse_)map.getOrDefault(pc,new Resourse_());
			resourse_.add((String)e.get(1), Integer.parseInt(e.get(2).toString())); 
			map.put(pc, resourse_);
		}
		for(Entry<Integer, Resourse_> e:map.entrySet()){
			list.add(new Request_(e.getKey(), e.getValue()));
		}
		return list;
	}
}
