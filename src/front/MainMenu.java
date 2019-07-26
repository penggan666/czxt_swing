package front;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Device_List;
import controller.Memory_List;
import controller.Process_List;
import models.Package_;
import models.Request_;
import models.Resourse_;
import models.Storage_;

import javax.swing.JCheckBox;
import algorithm.Memory_Comparator;
import algorithm.Page_;
import algorithm.Process_Comparetor;
import algorithm.SETTING;
import algorithm.Test;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class MainMenu {

	private JFrame frame;
	private JTextField textField_degree;
	private JTextField textField_batch;
	private JTextField textField_sum;
	private JTextField textField_defaultpage;
	private JComboBox comboBox_block;
	private JComboBox comboBox_ready;
	private JComboBox comboBox_memory;
	private JComboBox comboBox_page;
	private JCheckBox checkBox_vir;
	private JTextField textField_br;
	private JTextField textField_bc;
	private JTextField textField_bs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("操作系统");
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 400, 560);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		comboBox_page = new JComboBox(Page_.key);
		comboBox_page.setBackground(Color.WHITE);
		comboBox_page.setFont(new Font("宋体", Font.PLAIN, 12));
		comboBox_memory = new JComboBox(Memory_Comparator.key);
		comboBox_memory.setBackground(Color.WHITE);
		comboBox_memory.setFont(new Font("宋体", Font.PLAIN, 12));
		checkBox_vir = new JCheckBox("虚拟内存",true);
		checkBox_vir.setBackground(SystemColor.menu);
		checkBox_vir.setFont(new Font("宋体", Font.PLAIN, 12));
		comboBox_block = new JComboBox(Process_Comparetor.key);
		comboBox_block.setBackground(Color.WHITE);
		comboBox_block.setFont(new Font("宋体", Font.PLAIN, 12));
		comboBox_ready = new JComboBox(Process_Comparetor.key);
		comboBox_ready.setBackground(Color.WHITE);
		comboBox_ready.setFont(new Font("宋体", Font.PLAIN, 12));
		textField_degree = new JTextField();
		textField_degree.setBackground(Color.WHITE);
		textField_degree.setFont(new Font("宋体", Font.PLAIN, 12));
		textField_batch = new JTextField();
		textField_batch.setBackground(Color.WHITE);
		textField_batch.setFont(new Font("宋体", Font.PLAIN, 12));
		textField_degree.setText("5");
		textField_degree.setColumns(10);
		textField_batch.setText("2");
		textField_batch.setColumns(10);
		textField_sum = new JTextField();
		textField_sum.setBackground(Color.WHITE);
		textField_sum.setFont(new Font("宋体", Font.PLAIN, 12));
		textField_defaultpage = new JTextField();
		textField_defaultpage.setBackground(Color.WHITE);
		textField_defaultpage.setFont(new Font("宋体", Font.PLAIN, 12));
		
		JLabel label_degree = new JLabel("内存作业数");
		label_degree.setBackground(SystemColor.menu);
		label_degree.setFont(new Font("宋体", Font.PLAIN, 12));
		JLabel label_batch = new JLabel("同时处理批数");
		label_batch.setBackground(SystemColor.menu);
		label_batch.setFont(new Font("宋体", Font.PLAIN, 12));
		JLabel label_page = new JLabel("页面置换算法");
		label_page.setBackground(SystemColor.menu);
		label_page.setFont(new Font("宋体", Font.PLAIN, 12));
		JLabel label_memory = new JLabel("内存分配算法");
		label_memory.setBackground(SystemColor.menu);
		label_memory.setFont(new Font("宋体", Font.PLAIN, 12));
		JLabel label_block = new JLabel("作业调度");
		label_block.setBackground(SystemColor.menu);
		label_block.setFont(new Font("宋体", Font.PLAIN, 12));
		JLabel label_ready = new JLabel("进程调度");
		label_ready.setBackground(SystemColor.menu);
		label_ready.setFont(new Font("宋体", Font.PLAIN, 12));
		JLabel label_br = new JLabel("位示图行");
		label_br.setFont(new Font("宋体", Font.PLAIN, 12));
		JLabel label_bc = new JLabel("位示图列");
		label_bc.setFont(new Font("宋体", Font.PLAIN, 12));
		JLabel label_bs = new JLabel("盘块大小");
		label_bs.setFont(new Font("宋体", Font.PLAIN, 12));
		
		textField_br = new JTextField();
		textField_br.setBackground(Color.WHITE);
		textField_br.setFont(new Font("宋体", Font.PLAIN, 12));
		textField_br.setText("16");
		textField_br.setColumns(10);
		
		textField_bc = new JTextField();
		textField_bc.setBackground(Color.WHITE);
		textField_bc.setFont(new Font("宋体", Font.PLAIN, 12));
		textField_bc.setText("16");
		textField_bc.setColumns(10);
			
		textField_bs = new JTextField();
		textField_bs.setBackground(Color.WHITE);
		textField_bs.setFont(new Font("宋体", Font.PLAIN, 12));
		textField_bs.setText("1");
		textField_bs.setColumns(10);		
		textField_sum.setColumns(10);
		textField_defaultpage.setColumns(10);
		
		JLabel label_m1 = new JLabel();
		label_m1.setBackground(SystemColor.menu);
		label_m1.setFont(new Font("宋体", Font.PLAIN, 12));
		JLabel label_m2 = new JLabel();
		label_m2.setBackground(SystemColor.menu);
		label_m2.setFont(new Font("宋体", Font.PLAIN, 12));
		
		checkBox_vir.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(checkBox_vir.isSelected()) {
					label_m2.setVisible(true);
					textField_defaultpage.setVisible(true);
					textField_sum.setText("20");
					textField_defaultpage.setText("4");
					label_m1.setText("最大页数");
					label_m2.setText("进程页数");
					comboBox_memory.setVisible(false);
					comboBox_page.setVisible(true);
					label_page.setVisible(true);
					label_memory.setVisible(false);
				}
				else {
					textField_sum.setText("8000");
					textField_defaultpage.setText("0");
					label_m1.setText("内存容量");
					label_m2.setVisible(false);
					textField_defaultpage.setVisible(false);
					comboBox_page.setVisible(false);
					comboBox_memory.setVisible(true);	
					label_page.setVisible(false);
					label_memory.setVisible(true);
				}
			}
		});
		comboBox_page.setSelectedIndex(0);
		comboBox_memory.setSelectedIndex(0);
		checkBox_vir.setSelected(false);
		comboBox_block.setSelectedIndex(0);
		comboBox_ready.setSelectedIndex(1);
				
		
		JButton startos = new JButton("初始化系统");
		startos.setBackground(SystemColor.menu);
		startos.setFont(new Font("宋体", Font.PLAIN, 12));
		startos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SETTING.block=Process_Comparetor.value[comboBox_block.getSelectedIndex()];
				SETTING.ready=Process_Comparetor.value[comboBox_ready.getSelectedIndex()];
				SETTING.page=Page_.value[comboBox_page.getSelectedIndex()];
				SETTING.memory=Memory_Comparator.value[comboBox_memory.getSelectedIndex()];

				new Memory_List(Integer.parseInt(textField_sum.getText().toString()),checkBox_vir.isSelected(),Integer.parseInt(textField_defaultpage.getText().toString()));
				new Process_List(Integer.parseInt(textField_degree.getText().toString()),Integer.parseInt(textField_batch.getText().toString()));
				new Device_List();
				new Storage_(Integer.parseInt(textField_br.getText()),Integer.parseInt(textField_bc.getText()),Integer.parseInt(textField_bs.getText()));
				startos.setEnabled(false);
				comboBox_block.setEnabled(false);
				comboBox_memory.setEnabled(false);
				comboBox_ready.setEnabled(false);
				comboBox_page.setEnabled(false);
				textField_batch.setEditable(false);
				textField_bc.setEditable(false);
				textField_br.setEditable(false);
				textField_bs.setEditable(false);
				textField_defaultpage.setEditable(false);
				textField_degree.setEditable(false);
				textField_sum.setEditable(false);
				checkBox_vir.setEnabled(false);
				new MainFile();
				new Test();
			    new MainProcess();
				MainFile.mainFile.setVisible(true);
				MainProcess.m3.setVisible(true);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(startos, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkBox_vir)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_page)
							.addGap(18)
							.addComponent(comboBox_page, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_m1)
							.addGap(18)
							.addComponent(textField_sum, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(label_m2)
							.addGap(18)
							.addComponent(textField_defaultpage, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_memory)
							.addGap(18)
							.addComponent(comboBox_memory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_block)
								.addComponent(label_ready))
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox_block, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_ready, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(label_batch)
								.addGap(19)
								.addComponent(textField_batch))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(label_degree)
								.addGap(31)
								.addComponent(textField_degree, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_br)
								.addComponent(label_bc)
								.addComponent(label_bs))
							.addGap(39)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_bs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_bc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_br, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_degree)
						.addComponent(textField_degree, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_batch)
						.addComponent(textField_batch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_block)
						.addComponent(comboBox_block, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_ready)
						.addComponent(comboBox_ready, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(checkBox_vir)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_page)
						.addComponent(comboBox_page, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_memory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_memory))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_m1)
						.addComponent(textField_sum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_m2)
						.addComponent(textField_defaultpage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_br)
						.addComponent(textField_br, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_bc)
						.addComponent(textField_bc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_bs)
						.addComponent(textField_bs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(startos, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
