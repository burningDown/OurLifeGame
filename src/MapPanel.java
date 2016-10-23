import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;  
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class MapPanel extends JPanel { 
    
    int mCountX;
    private int mCountY;
    private int[][] mMap;		//这里方括号[]位置
    private int[][] mNextMap;		//这里方括号[]位置
    
    private boolean isDrawing = false;

    private Timer timer;
    
    public MapPanel () {
        super();
        
        mCountX = MainFrame.WIDTH / MainFrame.CELLSIZE;
        mCountY = MainFrame.HEIGHT/MainFrame.CELLSIZE;
        mMap = new int[mCountY][mCountX];
        mNextMap = new int[mCountY][mCountX];

        timer = new Timer(MainFrame.DELAY, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                changeCellStatus();
                repaint();
            }
            
        });
        
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                // TODO 自动生成的方法存根
                
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // TODO 自动生成的方法存根
                
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // TODO 自动生成的方法存根
                
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO 自动生成的方法存根
                
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO 自动生成的方法存根
                if(isDrawing) {
                    int x = arg0.getX();
                    int y = arg0.getY();
                    if(x > 0 && x < MainFrame.WIDTH && y > 0 && y < MainFrame.HEIGHT) {
                        mMap[y / MainFrame.CELLSIZE][x / MainFrame.CELLSIZE] = 1;
                        repaint();
                    }
                }
            }
            
        });
        
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0;i<=mCountY;i++)
            g.drawLine(0, i * MainFrame.CELLSIZE, MainFrame.WIDTH, i * MainFrame.CELLSIZE);

        for(int j = 0;j <= mCountX;j++)
            g.drawLine(j * MainFrame.CELLSIZE, 0, j * MainFrame.CELLSIZE, MainFrame.HEIGHT);
        for(int i = 0;i < mCountY;i++)
            for(int j = 0;j < mCountX;j++) {
                if(mMap[i][j] == 1) {
                    g.fillRect(j * MainFrame.CELLSIZE, i * MainFrame.CELLSIZE, MainFrame.CELLSIZE, MainFrame.CELLSIZE);
                    
                }
            }
    }
    
    
    
    public void copyMap() {
        for(int i = 0;i < mCountX;i++)
            for(int j=0;j<mCountY;j++)
                 mMap[j][i] = mNextMap[j][i];
    }
    
    public int countNeighbors(int cellX, int cellY) {
        int count = 0;
        for(int i=cellX-1;i<=cellX+1;i++)
            for(int j=cellY-1;j<=cellY+1;j++) {
                if(i>=0 && i<mCountX &&j>=0 &&j<mCountY) {
                    if(mMap[j][i] == 1)
                        count++;
                }
            }
        if(mMap[cellY][cellX] == 1)
            count--;
        return count;
    }
    
    public void changeCellStatus() {
        for (int row = 0; row < mCountY; row++) {  
            for (int col = 0; col < mCountX; col++) {  
  
                switch (countNeighbors(col, row)) {  
                case 0:
                case 1:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    mNextMap[row][col] = 0;
                    break;
                case 2:
                    mNextMap[row][col] = mMap[row][col];
                    break;
                case 3:
                    mNextMap[row][col] = 1;
                    break;  
                }  
            }  
        }  
        copyMap();
    }
    
    public void setDrawable(boolean b) {
        this.isDrawing = b;
    }
    
    public void setStarting(boolean b) {
        if(b) {
            timer.start();
        }
        else {
            timer.stop();
        }
    }
    
    
    public void setNextMapCell(int x, int y, int value) {
        if(value ==0)
            mMap[x][y] = 0;
        else
            mMap[x][y] = 1;
    }
    
    public void setMapCell(int x, int y, int value) {
        if(value == 0)
            mMap[x][y] = 0;
        else
            mMap[x][y] = 1;
    }
    public void releaseMap() {
        for(int i = 0;i < mCountY;i++)
            for(int j = 0;j < mCountX;j++)
                mMap[i][j] = mNextMap[i][j] = 0;
    }
    public int[][] getMap() {
        return mMap;
    }
    public int[][] getNextMap() {
        return mNextMap;
    }
}
