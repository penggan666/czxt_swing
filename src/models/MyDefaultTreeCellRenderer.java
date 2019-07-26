package models;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class MyDefaultTreeCellRenderer extends DefaultTreeCellRenderer {
	/** 
     * ID 
     */  
    private static final long   serialVersionUID    = 1L;  
  
    /** 
     * 重写父类DefaultTreeCellRenderer的方法 
     */  
    @Override  
    public Component getTreeCellRendererComponent(JTree tree, Object value,  
            boolean sel, boolean expanded, boolean leaf, int row,  
            boolean hasFocus)  
    {  
  
        //执行父类原型操作  
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,  
                row, hasFocus);  
  
        setText(value.toString());  
          
        if (sel)  
        {  
            setForeground(getTextSelectionColor());  
        }  
        else  
        {  
            setForeground(getTextNonSelectionColor());  
        }  
          
        //得到每个节点的TreeNode  
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;  
          
        //得到每个节点的text  
        Object object = node.getUserObject();
        if(object instanceof File_){//被选中的是文件
        	File_ file_=(File_)object;
        	this.setIcon(new ImageIcon("img/"+file_.getType()));      
        }
        else this.setIcon(new ImageIcon("img/package"));
        
        return this;  
    }  
}
