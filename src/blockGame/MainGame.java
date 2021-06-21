package blockGame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainGame extends JFrame {
	static int state = 0;
	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("./image/엑스2.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("./image/엑스.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("./image/시작버튼1.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("./image/시작버튼2.png"));
	private ImageIcon soloButtonEnteredImage = new ImageIcon(Main.class.getResource("./image/솔로2.png"));
	private ImageIcon soloButtonBasicImage = new ImageIcon(Main.class.getResource("./image/솔로1.png"));
	private ImageIcon duoButtonEnteredImage = new ImageIcon(Main.class.getResource("./image/듀오1.png"));
	private ImageIcon duoButtonBasicImage = new ImageIcon(Main.class.getResource("./image/듀오2.png"));
	private JPanel img = new JPanel() {
		Image background = new ImageIcon(Main.class.getResource("./image/설명2.png")).getImage();

		public void paint(Graphics g) {// 그리는 함수
			g.drawImage(background, 0, 0, null);// 이미지를 그려줌
		}
	};
	private JPanel img2 = new JPanel() {
		Image background = new ImageIcon(Main.class.getResource("./image/설명.png")).getImage();

		public void paint(Graphics g) {// 그리는 함수
			g.drawImage(background, 0, 0, null);// 이미지를 그려줌
		}
	};

	private JTextField text = new JTextField(15);

	private Image Background = new ImageIcon(Main.class.getResource("./image/메인화면.png")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("./image/상단바2.png")));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);

	private JButton soloButton = new JButton(soloButtonEnteredImage);
	private JButton duoButton = new JButton(duoButtonBasicImage);

	private int mouseX, mouseY;

	public static String userName;

	public MainGame() {

		setUndecorated(true);
		setTitle("Game");// 타이틀
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);// 프레임의 크기
		setResizable(false);// 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);// 창이 가운데 나오게
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame이 정상적으로 종료되게
		setVisible(true);// 창이 보이게
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);// 레이아웃을 내맘대로 설정가능하게 해줌.

		Music introMusic = new Music("배경음악.mp3", true);
		introMusic.start(); // 배경음악

		add(text);

		add(soloButton);
		add(duoButton);
		add(img);
		add(img2); // 필요한 이미지들을 여기서 다 추가한다.
		add(startButton);
		add(exitButton);
		add(menuBar);

		img.setLayout(null);
		img.setBounds(280, 130, 939, 544);
		img.setVisible(false);

		img2.setLayout(null);
		img2.setBounds(50, 130, 939, 544);
		img2.setVisible(false); // 게임 설명의 사이즈 위치 지정

		soloButton.setVisible(false);
		duoButton.setVisible(false);

		text.setBounds(492, 365, 292, 67);
		text.setFont(new Font("맗은고딕", Font.BOLD, 35)); // 텍스트 필드 위치 글꼴등 설정
		text.setForeground(Color.white);
		text.setBackground(new Color(0, 0, 0, 122));
		text.setHorizontalAlignment(JTextField.CENTER);

		exitButton.setBounds(1245, 0, 30, 30); // 나가기버튼 위치
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스이벤트로 마우스손을 대면 이미지 변경
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// 마우스이벤트로 마우스손을 대면 이미지가 다시 돌아오도록
			}

			@Override
			public void mousePressed(MouseEvent e) {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}

				System.exit(0); // 버튼을 누를경우 클레스 중료
			}
		}); // 나가기버튼

		startButton.setBounds(515, 458, 230, 130); // 시작버튼 위치
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("금속버튼.mp3", false);
				ButtonEnteredMusic.start();// 버튼에 손을대면 소리가나도록적용 소리가 배경음악보다 작아서 잘안들릴수도 있다.
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {

				if (text.getText().length() <= 0) {

					JOptionPane.showMessageDialog(null, "닉네임 길이가 너무 짧습니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

				} else if (text.getText().length() >= 6) {
					JOptionPane.showMessageDialog(null, "닉네임 길이가 너무 깁니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				} else {
					// 만약 텍스트필드에 적는 닉네임 길이가 너무적거나 길면 오류창이 발생
					Music ButtonEnteredMusic = new Music("금속버튼.mp3", false);
					ButtonEnteredMusic.start();

					userName = text.getText(); // 텍스트 필드에 입력 된 값은 userName에 저장이된다.

					startButton.setVisible(false);
					text.setVisible(false);
					duoButton.setVisible(true);
					soloButton.setVisible(true); // 버튼이 눌리면 버튼이 사라지고 새로운버튼들이 추가
					Background = new ImageIcon(Main.class.getResource("./image/화면2.png")).getImage();

				}
			}
		});// 시작버튼

		soloButton.setBounds(50, 150, 500, 450); // 솔로 위치

		soloButton.setBorderPainted(false);
		soloButton.setContentAreaFilled(false);
		soloButton.setFocusPainted(false);
		soloButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				img.setVisible(true);
				duoButton.setVisible(false);

				soloButton.setIcon(soloButtonEnteredImage);
				soloButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("금속버튼.mp3", false);
				ButtonEnteredMusic.start();

			} // 솔로 모드 버튼을 누르면 듀오모드이미지가 사라지고 게임설명이미지가 나타난다

			@Override
			public void mouseExited(MouseEvent e) {

				img.setVisible(false);
				duoButton.setVisible(true);

				soloButton.setIcon(soloButtonBasicImage);
				soloButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}// 솔로 모드 버튼에서 손을 때면 듀오모드이미지가 다시 나타나고 게임설명이미지가 사라진다

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic = new Music("금속버튼.mp3", false);
				ButtonEnteredMusic.start();

				new JTest();
				dispose(); // 지금 프레임종료

				// 버튼이벤트 솔로 버튼을 누르면 JTest라는 프래임이 실행되면서 MainGame창 화면은 사라진다
			}
		});

		duoButton.setBounds(780, 150, 500, 450); // 듀오모드

		duoButton.setBorderPainted(false);
		duoButton.setContentAreaFilled(false);
		duoButton.setFocusPainted(false);
		duoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				duoButton.setIcon(duoButtonEnteredImage);
				duoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("금속버튼.mp3", false);
				ButtonEnteredMusic.start();
				img2.setVisible(true);
				soloButton.setVisible(false);
			}// 솔로 모드 버튼 이미지을 누르면 듀오모드이미지가 사라지고 게임설명이미지가 나타난다

			@Override
			public void mouseExited(MouseEvent e) {
				duoButton.setIcon(duoButtonBasicImage);
				duoButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				img2.setVisible(false);
				soloButton.setVisible(true);
			}// 솔로 모드 버튼에서 손을 때면 듀오모드이미지가 다시 나타나고 게임설명이미지가 사라진다

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic = new Music("금속버튼.mp3", false);
				ButtonEnteredMusic.start();

				new LookCode();
				dispose(); // 지금 프레임종료

				// 버튼이벤트
			}
			// 버튼이벤트 듀오 버튼 이미지을 누르면 LookCode라는 프래임이 실행되면서 MainGame창 화면은 사라진다
		});

		menuBar.setBounds(0, 0, 1280, 30);// 상단바의 위치와 크기지정
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});// 상단바 상단바를 누르면 마우스위치대로 x좌표 y좌표로 이동할수 있다.

	}

	@Override
	public void paint(Graphics g) {// 그리는 함수
		super.paint(g);

		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);

	}

	public void paint2(Graphics g) {// 그리는 함수

		g.drawString(text.getText(), 30, 30);

	}

	public void screenDraw(Graphics g) {
		g.drawImage(Background, 0, 0, null);
		paintComponents(g);
		this.repaint();

	}

}