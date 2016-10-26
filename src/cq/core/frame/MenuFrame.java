package cq.core.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class MenuFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private Action saveAction;
	private Action saveAsAction;
	private JPopupMenu popup;
	private JPanel panel;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new MenuFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(700,600);
				frame.setLocation(100, 100);
				frame.setVisible(true);
			}
		});
	}
	public MenuFrame() {
		this.setSize(300, 200);
		JMenu fileMenu=new JMenu("File");
		fileMenu.add(new TestAction("New"));
		
		//快捷键
		JMenuItem open=fileMenu.add(new TestAction("Open"));
		open.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		
		fileMenu.addSeparator();
		saveAction=new TestAction("save");
		JMenuItem saveItem=fileMenu.add(saveAction);
		saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl shift S"));
		
		saveAsAction=new TestAction("Save As");
		fileMenu.add(saveAsAction);
		fileMenu.addSeparator();
		
		fileMenu.add(new AbstractAction("exit") {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		//单选按钮菜单
		ButtonGroup group=new ButtonGroup();
		JRadioButtonMenuItem insertItem=new JRadioButtonMenuItem("Insert");
		insertItem.setSelected(true);
		JRadioButtonMenuItem overtypeItem=new JRadioButtonMenuItem("Overtype");
		group.add(insertItem);
		group.add(overtypeItem);
		
		
		//设置图标
		Action cutAction=new TestAction("Cut");
		cutAction.putValue(Action.SMALL_ICON, new ImageIcon("images/cut.gif"));
		Action copyAction=new TestAction("Copy");
		copyAction.putValue(Action.SMALL_ICON, new ImageIcon("images/copy.gif"));
		Action pasteAction=new TestAction("Paste");
		pasteAction.putValue(Action.SMALL_ICON, new ImageIcon("images/paste.gif"));
		JMenu editMenu=new JMenu("Edit");
		editMenu.add(cutAction);
		editMenu.add(pasteAction);
		editMenu.add(copyAction);
		editMenu.addSeparator();
		
		//子菜单
		JMenu option=new JMenu("option");
		option.add(insertItem);
		option.add(overtypeItem);
		
		editMenu.add(option);
		
		//快捷键
		JMenu help=new JMenu("Help");
		help.setMnemonic('H');
		JMenuItem index=new JMenuItem("Index");
		index.setMnemonic('I');
		help.add(index);
		//也可以给一个动作添加一个快捷键
		Action about=new TestAction("About");
		about.putValue(Action.MNEMONIC_KEY, new Integer('A'));
		help.add(about);
		
		
		
		JMenuBar menubar=new JMenuBar();
		menubar.add(fileMenu);
		menubar.add(editMenu);
		menubar.add(help);
		
		//弹出菜单
		popup=new JPopupMenu();
		popup.add(cutAction);
		popup.add(copyAction);
		popup.add(pasteAction);
		panel=new JPanel();
		panel.setComponentPopupMenu(popup);
		
		//工具菜单
		 Action blueAction = new ColorAction("Blue", new ImageIcon("images/blue-ball.gif"), Color.BLUE);
	      Action yellowAction = new ColorAction("Yellow", new ImageIcon("images/yellow-ball.gif"),
	            Color.YELLOW);
	      Action redAction = new ColorAction("Red", new ImageIcon("images/red-ball.gif"), new Color(100,10,21));

	      Action exitAction = new AbstractAction("Exit", new ImageIcon("images/exit.gif"))
	         {
				private static final long serialVersionUID = 1L;
				public void actionPerformed(ActionEvent event)
	            {
	               System.exit(0);
	            }
	         };
	      exitAction.putValue(Action.SHORT_DESCRIPTION, "Exit");

	      // populate tool bar

	      JToolBar bar = new JToolBar();
	      bar.add(blueAction);
	      bar.add(yellowAction);
	      bar.add(redAction);
	      bar.addSeparator();
	      bar.add(exitAction);
	      add(bar, BorderLayout.NORTH);
		this.add(panel);
		this.setJMenuBar(menubar);
	}
	class TestAction extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public TestAction(String name) {
			// TODO Auto-generated constructor stub
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println(getValue(Action.NAME)+" selected");
		}
	}
	class ColorAction extends AbstractAction
	   {
		private static final long serialVersionUID = 1L;
		public ColorAction(String name, Icon icon, Color c)
	      {
	         putValue(Action.NAME, name);
	         putValue(Action.SMALL_ICON, icon);
	         putValue(Action.SHORT_DESCRIPTION, name + " background");
	         putValue("Color", c);
	      }

	      public void actionPerformed(ActionEvent event)
	      {
	         Color c = (Color) getValue("Color");
	         panel.setBackground(c);
	      }
	   }
	
}
