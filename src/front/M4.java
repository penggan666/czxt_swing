package front;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import models.File_;
import models.Package_;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class M4 extends JFrame {

	private JPanel contentPane;
	private JTextField textField_name;
	private JTextField textField_type;
	private JTextField textField_size;

	public M4() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("添加文件");
		setBounds(100, 100, 250, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label_name = new JLabel("文件名");
		
		textField_name = new JTextField();
		textField_name.setColumns(10);
		
		JLabel label_type = new JLabel("文件类型");
		
		textField_type = new JTextField();
		textField_type.setColumns(10);
		
		JLabel label_size = new JLabel("文件大小");
		
		textField_size = new JTextField();
		textField_size.setColumns(10);
		
		JButton confirm = new JButton("确认");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Package_ p=(Package_)MainFile.mainFile.node.getUserObject();
				String name=textField_name.getText();
				String type=textField_type.getText();
				int size=Integer.parseInt(textField_size.getText());
				if(name.trim().equals("")) {
					JOptionPane.showMessageDialog(null,"请输入文件名",null,JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(p.check(textField_name.getText(), textField_type.getText())){
					File_ f=new File_(name, type, size);
					if(f.fcb==-1) {
						JOptionPane.showMessageDialog(null,"容量不足",null,JOptionPane.ERROR_MESSAGE);
						return ;
					}
					p.add(f);
					DefaultMutableTreeNode newnode=new DefaultMutableTreeNode(f);
					MainFile.mainFile.node.add(newnode);
					SwingUtilities.updateComponentTreeUI(MainFile.mainFile.tree);
					MainFile.mainFile.updateTable();
					setVisible(false);
				}
				else JOptionPane.showMessageDialog(null,"文件重名",null,JOptionPane.ERROR_MESSAGE);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_size)
									.addGap(18)
									.addComponent(textField_size, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(label_type)
										.addGap(18)
										.addComponent(textField_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(label_name)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(textField_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(79)
							.addComponent(confirm)))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_name)
						.addComponent(textField_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_type)
						.addComponent(textField_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_size)
						.addComponent(textField_size, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(confirm)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
