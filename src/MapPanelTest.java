import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class MapPanelTest {

	private MapPanel mapPanel = new MapPanel();
	@Before
	public void setUp() throws Exception {
		mapPanel.releaseMap();
	}

	@Test
	public void testCountNeighbors() {
		mapPanel.setMapCell(2, 2, 1);
		mapPanel.setMapCell(2, 4, 1);
		mapPanel.setMapCell(3, 3, 1);
		mapPanel.setMapCell(4, 2, 1);
		mapPanel.setMapCell(4, 3, 1);
		mapPanel.countMap();
		assertEquals(4, mapPanel.countNeighbors(3, 3));
	}

	@Test
	public void testChangeCellStatus() {
		mapPanel.setMapCell(2, 2, 1);
		mapPanel.setMapCell(2, 4, 1);
		mapPanel.setMapCell(3, 3, 1);
		mapPanel.setMapCell(4, 2, 1);
		mapPanel.setMapCell(4, 3, 1);
		int[][] map = new int[MainFrame.HEIGHT / MainFrame.CELLSIZE][MainFrame.WIDTH /MainFrame.CELLSIZE];
		map[2][3] = 1;
		map[3][4] = 1;
		map[4][2] = 1;
		map[4][3] = 1;
		mapPanel.changeCellStatus();
		assertEquals(map, mapPanel.getMap());
	}
	
	@Test
	public void testCopyMap() {
		mapPanel.setNextMapCell(7, 3, 1);
		mapPanel.setNextMapCell(2, 4, 1);
		mapPanel.setNextMapCell(6, 2, 1);
		mapPanel.setNextMapCell(12, 3, 1);
		mapPanel.setNextMapCell(4, 3, 1);
		
		mapPanel.copyMap();
		assertEquals(mapPanel.getMap(), mapPanel.getNextMap());
	}

}
