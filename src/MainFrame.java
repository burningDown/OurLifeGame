import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame implements ActionListener{
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;
	public static final int CELLSIZE = 40;
	public static final int DELAY = 60;
	
	
	private MapPanel map = new MapPanel();

	private JMenuBar mMenubar = new JMenuBar();
	private JMenu mMenu = new JMenu();
	private JMenuItem mItemDrawCell = new JMenuItem();
	private JMenuItem mItemStart = new JMenuItem();
	
	public MainFrame() {
		super();
		this.setSize(WIDTH+50, HEIGHT+50);
		
		mItemDrawCell.setText("画细胞");
		mItemDrawCell.addActionListener(this);
		mItemStart.setText("开始");
		mItemStart.addActionListener(this);
		mMenu.setText("某菜单");
		mMenu.add(mItemDrawCell);
		mMenu.add(mItemStart);
		mMenubar.add(mMenu);
		this.setLayout(new BorderLayout());
		this.add(mMenubar, BorderLayout.NORTH);
		this.add(map, BorderLayout.CENTER);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setLocationRelativeTo(null);  
        this.setResizable(false);  
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource().equals(mItemDrawCell)) {
			map.setStarting(false);
			map.setDrawable(true);
		}
		else if(arg0.getSource().equals(mItemStart)) {
			map.setDrawable(false);
			map.setStarting(true);
		}
	}
}
