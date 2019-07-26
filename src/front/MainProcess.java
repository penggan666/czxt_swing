package front;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.Device_List;
import controller.Memory_List;
import controller.Process_List;
import models.Process_;
import models.Resourse_;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;

public class MainProcess extends JFrame {

	public static MainProcess m3=null;
	private JPanel contentPane;
	private JScrollPane scrollPane_m;//内存
	private JTable table_m;
	private JScrollPane scrollPane_p;//进程
	private JScrollPane scrollPane_pd;
	private JScrollPane scrollPane_d;//设备
	private JTable table_d;
	private JTable table_p;
	private JTable table_pd;
	public JButton run;
	private JButton button_f;
	private JTextField textField_dname;
	private JTextField textField_dcount;
	private JLabel label_pd;
	
	/**
	 * Create the frame.
	 */
	public MainProcess() {
		m3=this;
		setTitle("进程管理器");	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 700, 750, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		scrollPane_m = new JScrollPane();
		scrollPane_p = new JScrollPane();
		scrollPane_pd = new JScrollPane();
		
		run = new JButton("模拟时间片运行");
		run.setBackground(SystemColor.menu);
		run.setFont(new Font("宋体", Font.PLAIN, 12));
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				new Thread(new Runnable() {
					public void run() {
						Process_List.run();
					}
				}).start();
			}
		});
		
		JLabel label_p = new JLabel("进程");
		label_p.setFont(new Font("宋体", Font.PLAIN, 12));
		JLabel label_m = new JLabel("内存");
		label_m.setFont(new Font("宋体", Font.PLAIN, 12));
		JLabel label_d = new JLabel("设备");
		label_d.setFont(new Font("宋体", Font.PLAIN, 12));
		
		button_f = new JButton("文件管理");
		button_f.setBackground(SystemColor.menu);
		button_f.setFont(new Font("宋体", Font.PLAIN, 12));
		button_f.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFile.mainFile.setVisible(true);
			}
		});
		
		scrollPane_d = new JScrollPane();
		
		JButton button_add = new JButton("添加设备");
		button_add.setBackground(SystemColor.menu);
		button_add.setFont(new Font("宋体", Font.PLAIN, 12));
		button_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(Device_List.add(textField_dname.getText(),Integer.parseInt(textField_dcount.getText()))) {
						JOptionPane.showMessageDialog(null,"添加设备成功",null,JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null,"无法删除设备", null, JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"请输入整数", null, JOptionPane.ERROR_MESSAGE);
				}				
				update();
			}
		});
		
		JButton button_remove = new JButton("删除设备");
		button_remove.setBackground(SystemColor.menu);
		button_remove.setFont(new Font("宋体", Font.PLAIN, 12));
		button_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(Device_List.add(textField_dname.getText(),-Integer.parseInt(textField_dcount.getText()))) {
						JOptionPane.showMessageDialog(null,"删除设备成功",null,JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null,"无法删除设备", null, JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"请输入整数", null, JOptionPane.ERROR_MESSAGE);
				}
				update();
			}
		});
		
		textField_dname = new JTextField();
		textField_dname.setFont(new Font("宋体", Font.PLAIN, 12));
		textField_dname.setColumns(10);
		
		textField_dcount = new JTextField();
		textField_dcount.setFont(new Font("宋体", Font.PLAIN, 12));
		textField_dcount.setColumns(10);
		
		JLabel label_dname = new JLabel("设备名");
		label_dname.setFont(new Font("宋体", Font.PLAIN, 12));
		
		JLabel label_dcount = new JLabel("设备数量");
		label_dcount.setFont(new Font("宋体", Font.PLAIN, 12));
		
		label_pd = new JLabel("进程资源");
		label_pd.setFont(new Font("宋体", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_p, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_d, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_dname)
									.addGap(18)
									.addComponent(textField_dname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(button_add))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_dcount)
									.addGap(18)
									.addComponent(textField_dcount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(button_remove))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(button_f, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(run, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_m)
								.addComponent(scrollPane_m, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_pd)
								.addComponent(scrollPane_pd, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)))
						.addComponent(label_d)
						.addComponent(label_p))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_p)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_p, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_m)
						.addComponent(label_pd))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane_m, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_pd, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(label_d)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_add)
								.addComponent(textField_dname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_dname))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_remove)
								.addComponent(textField_dcount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_dcount))
							.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
							.addComponent(button_f, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(run, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_d, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		update();
		contentPane.setLayout(gl_contentPane);
		
	}
	public void update(){
		table_d=new JTable(Device_List.gettable(),Device_List.Device_name);
        table_d.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		textField_dname.setText(table_d.getValueAt(table_d.getSelectedRow(), 0).toString());
        	}
        });
        table_d.setFont(new Font("宋体", Font.PLAIN, 12));
		scrollPane_d.setViewportView(table_d);
		
		DefaultTableModel tableModel_p=new DefaultTableModel(Process_List.gettable(),Process_List.name);
		table_p = new JTable(tableModel_p){
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		        Component comp = super.prepareRenderer(renderer, row, column);  
		        switch ((String)getValueAt(row,2)) {
				case "就绪":
					comp.setBackground(java.awt.Color.yellow);break;
				case "阻塞":
					comp.setBackground(java.awt.Color.red);break;
				default:
					comp.setBackground(java.awt.Color.green);
				}
		        return comp;
		    }
		};
		table_p .addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String Process_id=table_p.getValueAt(table_p.getSelectedRow(), 0).toString();
        		Process_ process_=Process_List.get(Integer.valueOf(Process_id));
        		System.out.println(Integer.valueOf(Process_id));
        		Resourse_ need=process_.getNeed();
        		Resourse_ allocation_=process_.getAllocation();
        		Vector<Vector<Object>> vec=new Vector<Vector<Object>>();
        		for(Entry<String, Integer> m:need.entrySet()){
        			Vector<Object> objects=new Vector<Object>();
        			objects.add(m.getKey());
        			objects.add(m.getValue());
        			objects.add(allocation_.getOrDefault(m.getKey(),0));
        			vec.add(objects);
        		}
				DefaultTableModel tableModel_d=new DefaultTableModel(vec,Device_List.Dname);
				table_pd=new JTable(tableModel_d);
				scrollPane_pd.setViewportView(table_pd); 
        	}
        });
		DefaultTableModel tableModel_pd=new DefaultTableModel(new Vector<>(),Device_List.Dname);
		table_pd=new JTable(tableModel_pd);
		table_pd.setFont(new Font("宋体", Font.PLAIN, 12));
		scrollPane_pd.setViewportView(table_pd); 
		scrollPane_p.setViewportView(table_p);
		DefaultTableModel tableModel_m=new DefaultTableModel(Memory_List.gettable(), Memory_List.name);
		table_m=new JTable(tableModel_m){
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		        Component comp = super.prepareRenderer(renderer, row, column);  
		        if(getValueAt(row,0).toString().equals("0")) comp.setBackground(java.awt.Color.green);
		        else comp.setBackground(table_m.getBackground());
		        return comp;
		    }
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		scrollPane_m.setViewportView(table_m);
	}
}
