package front;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import models.File_;
import models.MyDefaultTreeCellRenderer;
import models.Package_;
import models.Storage_;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;

public class MainFile extends JFrame {

	public static MainFile mainFile=null;
	private JPanel contentPane;
	public static Package_ root=null;
	public static Vector<String> columnName=null;
	public static JTree tree;
	private JScrollPane scrollPane_tree;
	private JScrollPane scrollPane_table;
	public static DefaultMutableTreeNode top;
	public DefaultMutableTreeNode node;
	private JTable table;
	private JPopupMenu menu;
	private JTextField textField_path;
	private JButton search_btn;
	private ArrayList<File_> copyf=null;
	private ArrayList<Package_> copyp=null;
	private JScrollPane scrollPane_fat;
	private JScrollPane scrollPane_bit;
	private JTable table_fat;
	private JTable table_bit;
	public MainFile() {
		mainFile=this;
		root=new Package_("root");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		scrollPane_tree = new JScrollPane();
		scrollPane_table = new JScrollPane();
		scrollPane_fat = new JScrollPane();
		scrollPane_bit = new JScrollPane();
		setTitle("文件管理");
		columnName=new Vector<>();
		columnName.add("文件名");columnName.add("文件类型");columnName.add("文件大小");columnName.add("修改时间");columnName.add("FAT头");
		top = getFileTree(root);
		tree = new JTree(top);
		//设置自定义描述类  
        tree.setCellRenderer(new MyDefaultTreeCellRenderer()); 
		//添加树节点的点击事件
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				node=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				updateTable();
				menu=getMenu();
			}
		});
		copyf=new ArrayList<>();
		copyp=new ArrayList<>();		
		
		textField_path = new JTextField();
		textField_path.setColumns(10);
		
		search_btn = new JButton("搜索");
		search_btn.setBackground(SystemColor.menu);
		search_btn.setFont(new Font("宋体", Font.PLAIN, 12));
		search_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		
		JButton button = new JButton("进程管理器");
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		button.setBackground(SystemColor.menu);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainProcess.m3.setVisible(true);
			}
		});
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_tree, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane_table, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField_path))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(search_btn)
							.addPreferredGap(ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
							.addComponent(button))
						.addComponent(scrollPane_bit, Alignment.TRAILING)
						.addComponent(scrollPane_fat, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_path, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(button)
						.addComponent(search_btn))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(scrollPane_table, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE))
						.addComponent(scrollPane_tree, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_fat, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane_bit, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		scrollPane_table.setColumnHeaderView(table);
		
		contentPane.setLayout(gl_contentPane);

        tree.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
            	if(node==null) return;
            	menu=getMenu();
            	if (evt.getButton() == MouseEvent.BUTTON3) {
            		menu.show(tree, evt.getX(), evt.getY());
                }
            }
		});
	}
	public JPopupMenu getMenu(){//添加右击菜单
		JPopupMenu menu=new JPopupMenu();
		Object object=node.getUserObject();
		if(object instanceof Package_){
			JMenuItem renamep=new JMenuItem("重命名");
			JMenuItem newf=new JMenuItem("新建文件");
			JMenuItem newp=new JMenuItem("新建文件夹");
			JMenuItem mcopy=new JMenuItem("复制");
			JMenuItem mpaste=new JMenuItem("粘贴");
			JMenuItem mremovep=new JMenuItem("删除");
			newf.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					createFile();
				}
			});
			newp.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					createPackage();
				}
			});
			mcopy.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					copyPackage();
					updateTable();
				}
			});
			mpaste.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					paste();
					updateTable();
				}
			});
			mremovep.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					removePackage();
					updateTable();
				}
			});
			renamep.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					renamePackage();
					updateTable();
				}
			});
			menu.add(newf);
			menu.add(newp);
			menu.add(mcopy);
			menu.add(mpaste);
			menu.add(mremovep);
			menu.add(renamep);
		}
		else{		
			File_ f=(File_)object;
			JMenuItem renamef=new JMenuItem("重命名");
			JMenuItem mcopy=new JMenuItem("复制");
			JMenuItem mremovef=new JMenuItem("删除");
			JMenuItem resizef=new JMenuItem("修改大小");
			mcopy.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					copyFile();
					updateTable();
				}
			});

			mremovef.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					removeFile();
					updateTable();
				}
			});
			renamef.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					renameFile();
					updateTable();
				}
			});
			resizef.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					setFile();
					updateTable();
				}
			});
			menu.add(mcopy);
			menu.add(mremovef);
			menu.add(renamef);
			menu.add(resizef);
			if(f.getType().equals("exe")){
				JMenuItem run=new JMenuItem("运行");
				run.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						M2 m=new M2(f.getName());
						m.setVisible(true);
					}
				});
				menu.add(run);
			}
			
		}	
		return menu;
	}
	public void removeFile(){
		int i=javax.swing.JOptionPane.showConfirmDialog(this,"确认要删除吗？");
		if(i==0){
			File_ file = (File_) node.getUserObject();
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
			Package_ p = (Package_) parent.getUserObject();
			p.deletefile(file);
			node.removeFromParent();
			node=null;
			SwingUtilities.updateComponentTreeUI(tree);
		}
	}
	public void removePackage(){
		if(node.getUserObject()==root){
			JOptionPane.showMessageDialog(null,"无法删除根目录", null, JOptionPane.ERROR_MESSAGE);
			return;
		}
		int i=javax.swing.JOptionPane.showConfirmDialog(this,"确认要删除吗？");
		if(i==0){
			Package_ pac=(Package_)node.getUserObject();
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
			Package_ p = (Package_)parent.getUserObject();
			p.deletepackage(pac);
			node.removeFromParent();
			node=null;
			SwingUtilities.updateComponentTreeUI(tree);
		}
	}	
	public void setFile(){
		try{
			String line=JOptionPane.showInputDialog("大小");
			if(line==null) return;
			int size=Integer.parseInt(line);
			File_ file = (File_) node.getUserObject();
			if(file.resize(size)) return;
			else JOptionPane.showMessageDialog(null,"容量不足",null,JOptionPane.ERROR_MESSAGE);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"非整数",null,JOptionPane.ERROR_MESSAGE);
		}
	}
	public void renamePackage(){
		String pname=JOptionPane.showInputDialog("文件夹名");
		if(pname==null) return;
		pname=pname.trim();
		if(pname.trim().equals("")) {
			JOptionPane.showMessageDialog(null,"请输入文件夹名",null,JOptionPane.ERROR_MESSAGE);
			return;
		}
		Package_ p=(Package_) node.getUserObject();
		if(pname.equals(p.getName())) return ;
		Package_ parent=(Package_) ((DefaultMutableTreeNode)node.getParent()).getUserObject();
		if(parent.check(pname)){
			p.setName(pname);
			return;
		}
		else JOptionPane.showMessageDialog(null,"文件夹重名",null,JOptionPane.ERROR_MESSAGE);
	}
	public void renameFile(){
		String string=JOptionPane.showInputDialog("文件名和类型名");
		if(string==null) return;
		string=string.trim();
		int index=string.lastIndexOf(".");
		String fname=index==-1?string:string.substring(0, index);
		if(fname.equals("")) {
			JOptionPane.showMessageDialog(null,"请输入文件名",null,JOptionPane.ERROR_MESSAGE);
			return;
		}
		String ftype=index==-1?"":string.substring(index+1);
		DefaultMutableTreeNode parent=(DefaultMutableTreeNode) node.getParent();
		File_ f=(File_) node.getUserObject();
		if(fname.equals(f.getName())&&ftype.equals(f.getType())) return;
		Package_ p=(Package_) parent.getUserObject();
		if(p.check(fname,ftype)) {
			f.SetFile(fname, ftype);
			return;
		}
		else JOptionPane.showMessageDialog(null,"文件重名",null,JOptionPane.ERROR_MESSAGE);
	}
	public void createFile(){
		M4 m4=new M4();
		m4.setVisible(true);
	}
	public void createPackage(){	
		String pname=JOptionPane.showInputDialog("文件夹名");
		if(pname==null) return;
		if(pname.trim().equals("")) {
			JOptionPane.showMessageDialog(null,"请输入文件夹名",null,JOptionPane.ERROR_MESSAGE);
			return;
		}
		Package_ p=(Package_)node.getUserObject();
		if(p.check(pname)) {
			Package_ newp=new Package_(pname);
			p.add(newp);
			DefaultMutableTreeNode newnode=new DefaultMutableTreeNode(newp);
			node.add(newnode);
			SwingUtilities.updateComponentTreeUI(tree);
			MainFile.mainFile.updateTable();
		}
		else JOptionPane.showMessageDialog(null,"文件夹重名",null,JOptionPane.ERROR_MESSAGE);
	}

	public void paste(){
		Package_ parent=(Package_)node.getUserObject();
		for(File_ e:copyf){
			if(parent.check(e.getName(),e.getType())==false){
				int i=JOptionPane.showConfirmDialog(this,"是否覆盖文件？");
				if(i == 0)
				{	File_ f_copy=new File_(e);
					if(f_copy.fcb==-1){
						JOptionPane.showMessageDialog(null,e.getName()+"文件过大", null, JOptionPane.ERROR_MESSAGE);
						continue;
					}
					parent.add(f_copy);
					Enumeration<?> enumeration;
					enumeration=node.children();
					while(enumeration.hasMoreElements()){
						DefaultMutableTreeNode node;
						node=(DefaultMutableTreeNode) enumeration.nextElement();
						Object object1=node.getUserObject();
						if(object1 instanceof File_){
							File_ f1=(File_)object1;
							if(f1.getName().equals(f_copy.getName())){
								parent.deletefile(f1);
								node.removeFromParent();
								break;
							}
						}
					}
					DefaultMutableTreeNode newnode = new DefaultMutableTreeNode(f_copy);
					node.add(newnode);
				}
			}
			else{
				File_ file_Copy=new File_(e);
				if(file_Copy.fcb==-1){
					JOptionPane.showMessageDialog(null,e.getName()+"文件过大", null, JOptionPane.ERROR_MESSAGE);
					continue;
				}
				parent.add(file_Copy);
				DefaultMutableTreeNode newnode = new DefaultMutableTreeNode(file_Copy);
				node.add(newnode);
			}
		}
		for(Package_ e:copyp){
			if(parent.check(e.getName())==false){
				int i=javax.swing.JOptionPane.showConfirmDialog(this,"是否覆盖文件夹？");
				if(i == 0){	
					Package_ p_copy=new Package_(e);
					Enumeration<?> enumeration;
					enumeration=node.children();
					while(enumeration.hasMoreElements()){
						DefaultMutableTreeNode node=(DefaultMutableTreeNode) enumeration.nextElement();
						Object object=node.getUserObject();
						if(object instanceof Package_){
							Package_ p=(Package_)object;
							if(p.getName().equals(p_copy.getName())){
								node.removeFromParent();
								parent.deletepackage(p);
								break;
							}
						}
					}
					parent.add(p_copy);
					DefaultMutableTreeNode newnode =getFileTree(p_copy);
					node.add(newnode);
				}
			}else{
				Package_ pac_Copy=new Package_(e);
				parent.add(pac_Copy);
				DefaultMutableTreeNode newnode =getFileTree(pac_Copy);
				node.add(newnode);
			}
		}
		SwingUtilities.updateComponentTreeUI(tree);
	}
		
	public void copyFile(){
		copyf.clear();
		copyp.clear();
		File_ copyfile=(File_)node.getUserObject();
		copyf.add(copyfile);
	}
	public void copyPackage(){
		copyp.clear();
		copyf.clear();
		Package_ package_=(Package_)node.getUserObject();
		copyp.add(package_);
	}
	public void search(){
		Vector<Vector<Object>> vector=new Vector<>();
		ArrayList<Object> objects=new ArrayList<Object>();
		root.find(textField_path.getText(),objects);
		for(Object object:objects){
			if(object instanceof File_) {
				vector.add(((File_)object).getRow());
				textField_path.setText(((File_)object).getPath());
			}
			else if(object instanceof Package_){
				Package_ p=(Package_)object;
				vector.add(p.getRow());
				for(File_ e:p.getFilelist()) vector.add(e.getRow());
				for(Package_ e:p.getPackagelist()) vector.add(e.getRow());
				textField_path.setText(p.getPath());
			}
		}
		DefaultTableModel tableModel=new DefaultTableModel(vector, columnName);
		table=new JTable(tableModel);
		scrollPane_table.setViewportView(table);
	}
	public static DefaultMutableTreeNode getFileTree(Package_ p){
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(p);
		for(Package_ e:p.getPackagelist()) node.add(getFileTree(e));
		for(File_ e:p.getFilelist()) node.add(new DefaultMutableTreeNode(e));
		return node;
	}
	public void updateTree(){
		DefaultMutableTreeNode defaultMutableTreeNode= getFileTree(root);
		DefaultTreeModel newmodel=new DefaultTreeModel(defaultMutableTreeNode);
		tree.setModel(newmodel);
		scrollPane_tree.setViewportView(tree);
	}
	public void updateTable(){
		DefaultTableModel tableModel=new DefaultTableModel(gettable(), columnName);
		table=new JTable(tableModel);
		scrollPane_table.setViewportView(table);
		
		DefaultTableModel tableModel_fat=new DefaultTableModel(Storage_.gettable_fat(), Storage_.name);
		table_fat=new JTable(tableModel_fat){
		    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {  
		        Component comp = super.prepareRenderer(renderer, row, column);
		        if (column == 1){
		        	switch ((int)getValueAt(row,1)) {
					case -1:
						comp.setBackground(java.awt.Color.green);
						break;
					case -2:
						comp.setBackground(java.awt.Color.red);
						break;
					default:
						comp.setBackground(table_fat.getBackground());
						break;
					}
		        }
		        else {
		        	comp.setBackground(table_fat.getBackground());
				}
		        return comp;
		    }
		    public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		scrollPane_fat.setViewportView(table_fat);
		
		DefaultTableModel tableModel_bit=new DefaultTableModel(Storage_.gettable_bit(), Storage_.name_bit);
		table_bit=new JTable(tableModel_bit){
		    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {    
		        Component comp = super.prepareRenderer(renderer, row, column);  
		        int a=(int) getValueAt(row,column) ;
                if (a==1)                   //此处加入条件判断
                	comp.setBackground(java.awt.Color.RED);
                else                                                     //不符合条件的保持原表格样式
                	comp.setBackground(java.awt.Color.green);
		        return comp;
		    }
		    public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		scrollPane_bit.setViewportView(table_bit);
	}
	public Vector<Vector<Object>> gettable(){
		Vector<Vector<Object>> vector=new Vector<>();
		if(node==null) return vector;
		Object object=node.getUserObject();
		if(object instanceof File_) {
			vector.add(((File_)object).getRow());
			textField_path.setText(((File_)object).getPath());
		}
		else if(object instanceof Package_){
			Package_ p=(Package_)object;
			vector.add(p.getRow());
			for(File_ e:p.getFilelist()) vector.add(e.getRow());
			for(Package_ e:p.getPackagelist()) vector.add(e.getRow());
			textField_path.setText(p.getPath());
		}
		return vector;
	}
}
