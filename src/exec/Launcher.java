package exec;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import game.Board;

@SuppressWarnings("serial")
public class Launcher extends JFrame {

	private ImageIcon originalBackgroundImage;
	private ImageIcon resizedBackgroundImage;

	private JLabel actualBackground;

	private int width;
	private int height;

	private Board board;

	public Launcher() {
		board = new Board();

		loadBackground();

		setTitle("Chess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		setFullScreen();
		// setSpecificSize(1280, 720);

		addInput();

		JLayeredPane mainPanel = new JLayeredPane();
		add(mainPanel);

		mainPanel.add(actualBackground, new Integer(0), 0);
		mainPanel.add(board, new Integer(1), 0);

		setVisible(true);
	}

	public void loadBackground() {
		try {
			originalBackgroundImage = new ImageIcon(ImageIO.read(new File("res/background.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		actualBackground = new JLabel(originalBackgroundImage);
	}

	public void setFullScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int) screenSize.getWidth();
		height = (int) screenSize.getHeight();
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		organizeComponents();
	}

	public void setSpecificSize(int width, int height) {
		this.width = width;
		this.height = height;
		getContentPane().setPreferredSize(new Dimension(width, height));
		organizeComponents();
	}

	public void organizeComponents() {
		if (width == 1920 && height == 1080)
			actualBackground.setIcon(originalBackgroundImage);
		else {
			resizeBackground(width, height);
			actualBackground.setIcon(resizedBackgroundImage);
		}
		actualBackground.setSize(width, height);
		actualBackground.setBounds(0, 0, width, height);
		actualBackground.setFocusable(false);

		board.setSize(height, height);
		int widthBorder = (width - height) / 2;
		board.setBounds(widthBorder, 0, width - 2 * widthBorder, height);
		pack();

		resizeImages();
	}

	public void resizeBackground(int width, int height) {
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) resizedImage.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(originalBackgroundImage.getImage(), 0, 0, width, height, null);
		resizedBackgroundImage = new ImageIcon(resizedImage);
	}

	public void resizeImages() {
		board.loadImages();
		HashMap<String, ImageIcon> resizedImages = new HashMap<>();

		Set<String> keys = board.getPiecesImages().keySet();

		int sizeSquare = height / 8;

		for (String key : keys) {
			BufferedImage resizedImage = new BufferedImage(sizeSquare, sizeSquare, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = (Graphics2D) resizedImage.createGraphics();
			g2d.setBackground(new Color(0, 0, 0, 0));
			g2d.clearRect(0, 0, sizeSquare, sizeSquare);
			g2d.addRenderingHints(
					new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(board.getPiecesImages().get(key).getImage(), 0, 0, sizeSquare, sizeSquare, null);
			resizedImages.put(key, new ImageIcon(resizedImage));
		}

		board.setPiecesImages(resizedImages);

		board.updateGrid();

	}

	public void addInput() {
		addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ESCAPE) {
					System.exit(1);
				}

			}

		});

		addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				setSpecificSize(getContentPane().getWidth(), getContentPane().getHeight());
			}
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Launcher();
			}
		});
	}

}
