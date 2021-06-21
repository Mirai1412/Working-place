package blockGame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JTest extends JFrame {

   TetrisPanel TP = new TetrisPanel();
   JLabel scoreInt = new JLabel();
   TetrisThread tThread;
   JLabel userName = new JLabel(MainGame.userName);
   JPanel GameOver = new JPanel();

   private ImageIcon restartButtonImage = new ImageIcon("./src/blockGame/image/reStartBlack.png");
   private ImageIcon restartButtonBasicImage = new ImageIcon("./src/blockGame/image/reStart.png");

   private ImageIcon homeButtonImage = new ImageIcon("./src/blockGame/image/home.png");
   private ImageIcon homeButtonBasicImage = new ImageIcon("./src/blockGame/image/home2.png");

   private JButton home = new JButton(homeButtonImage);

   private JButton RBtn = new JButton(restartButtonBasicImage);

   private Image background = new ImageIcon("./src/blockGame/image/new.png").getImage();
   private Image gameover = new ImageIcon("./src/blockGame/image/gameover.png").getImage();
   static int blocksize = 30;
   static int garoKan = 15;
   static int seroKan = 19;
   static int scoreSum = 0;

   int endCheck = 0;
   int test = 0;

   int randomColor;

   int End = 0;
   int random = 0, random2 = (int) (Math.random() * 7);

   int score = 0;

   int wid = 100;
   int hgt = 0;
   int rotation = 0;

   boolean limit = false;

   // 블록들의 좌표 저장
   int curX[] = new int[4], curY[] = new int[4];

   int blocks[][][][] = { {
         // ■
         // ■■■
         { { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
         { { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
         { { 1, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } },
         { { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } } },

         {

               // ■
               // ■■■
               { { 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
               { { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
               { { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 1, 0, 0, 0 }, { 0, 0, 0, 0 } },
               { { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }, },

         {
               // ■■
               // ■■
               { { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
               { { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
               { { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
               { { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } } },

         {
               // ■■■■
               { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } },
               { { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } },
               { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } },
               { { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } } },

         {
               // ■
               // ■■■
               { { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
               { { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
               { { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
               { { 0, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } } },

         {
               // ■■
               // ■■
               { { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
               { { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
               { { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
               { { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } } },

         {
               // ■■
               // ■■
               { { 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
               { { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } },
               { { 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
               { { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } } } };

   int[][] gameboard = {

         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
         { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

         { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }

   };

   JTest() {
      setTitle("테트리스");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(null);
      setResizable(false);
      setSize(1280, 720);
      setLocationRelativeTo(null);
      setVisible(true);

      TP.add(RBtn);
      TP.add(home);

      tThread = new TetrisThread();

      RBtn.setVisible(false);
      home.setVisible(false);

      // 패널 사이즈
      TP.setSize(1280, 720);
      add(TP);

      // 패널 사이즈 수정해라 돼지야
      GameOver.setSize(1280, 281);

      TP.add(userName);

      userName.setForeground(Color.white);
      Font font = new Font("맑은고득", Font.BOLD, 40);
      userName.setFont(font);

      scoreInt.setFont(new Font("arial", Font.PLAIN, 40));
      scoreInt.setForeground(Color.white);

      // 키 입력
      TP.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent e) {

            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_UP) {
               TP.moveUp();
            }

            if (keyCode == KeyEvent.VK_DOWN) {
               TP.moveDown();
            }

            if (keyCode == KeyEvent.VK_LEFT) {
               TP.moveLeft();
            }

            if (keyCode == KeyEvent.VK_RIGHT) {
               TP.moveRight();
            }

         }
      });

      RBtn.setBounds(440, 470, 400, 100); // 시작버튼 위치
      RBtn.setBorderPainted(false);
      RBtn.setContentAreaFilled(false);
      RBtn.setFocusPainted(false);
      RBtn.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            RBtn.setIcon(restartButtonImage);
            RBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            Music ButtonEnteredMusic = new Music("금속버튼.mp3", false);
            ButtonEnteredMusic.start();// 버튼에 손을대면 소리가나도록적용 소리가 배경음악보다 작아서 잘안들릴수도 있다.

         }

         @Override
         public void mouseExited(MouseEvent e) {

            RBtn.setIcon(restartButtonBasicImage);
            RBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

         }// 솔로 모드 버튼에서 손을 때면 듀오모드이미지가 다시 나타나고 게임설명이미지가 사라진다

         @Override
         public void mousePressed(MouseEvent e) {
            Music ButtonEnteredMusic = new Music("금속버튼.mp3", false);
            ButtonEnteredMusic.start();

            home.setVisible(false);
            limit = false;
            for (int y = 0; y < 19; y++)
               for (int x = 1; x < 16; x++)
                  gameboard[y][x] = 0;
            score = 0;
            test = 0;
            endCheck = 0;
            scoreSum = 0;
            if (endCheck == 0) {
               TP.remove(RBtn);

            }
         }
      });

      home.setBounds(440, 560, 400, 100); // 시작버튼 위치
      home.setBorderPainted(false);
      home.setContentAreaFilled(false);
      home.setFocusPainted(false);
      home.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            home.setIcon(homeButtonBasicImage);
            home.setCursor(new Cursor(Cursor.HAND_CURSOR));
            Music ButtonEnteredMusic = new Music("금속버튼.mp3", false);
            ButtonEnteredMusic.start();// 버튼에 손을대면 소리가나도록적용 소리가 배경음악보다 작아서 잘안들릴수도 있다.

         }

         @Override
         public void mouseExited(MouseEvent e) {

            home.setIcon(homeButtonImage);
            home.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

         }// 솔로 모드 버튼에서 손을 때면 듀오모드이미지가 다시 나타나고 게임설명이미지가 사라진다

         @Override
         public void mousePressed(MouseEvent e) {
            Music ButtonEnteredMusic = new Music("금속버튼.mp3", false);
            ButtonEnteredMusic.start();

            new MainGame();
            dispose(); // 지금 프레임종료

         }
      });

      // 패널이 여러개 일 경우, 키 입력을 먼저 받도록 설정
      TP.requestFocus(true);

      // 스레드 시작
      tThread.start();
   }

   class TetrisPanel extends JPanel {

      public void paintComponent(Graphics g) {

         int cnt = 0, cnt2 = 0;
         TP.requestFocus(true);
         userName.setLocation(135, 130); // 위치
         super.paintComponent(g);
         
         home.setLocation(150, 500);
         RBtn.setLocation(700, 500);
         
         scoreInt.setLocation(1050, 190);
         TP.add(scoreInt);

         scoreInt.setText(Integer.toString(scoreSum + (score * 20)));
         setLocationRelativeTo(null);// 창이 가운데 나오게

         switch (randomColor) {

         case 0: {
            g.setColor(Color.decode("#FF5675")); // 새로 떨어지는 블럭,미리보기 블럭 색깔
            break;
         }
         case 1: {
            g.setColor(Color.decode("#3DFF92"));
            break;
         }
         case 2: {
            g.setColor(Color.decode("#FFEB5A"));
            break;
         }
         case 3: {
            g.setColor(Color.decode("#B2FA5C"));
            break;
         }
         case 4: {
            g.setColor(Color.decode("#00D7FF"));
            break;
         }
         case 5: {
            g.setColor(Color.decode("#FFA98F"));
            break;
         }
         case 6: {
            g.setColor(Color.decode("#00FFFF"));
            break;
         }

         }

         g.drawImage(background, 0, 0, null);

         // 다음 나올 도형 출력
         blockLookAhead(g);

         // 벽이 천장에 닿으면 게임 오버
         gameOverCheck();

         // 한 행이 모두 블록으로 채워진 경우 블록들 제거(채워지지않은 경우 블록 떨어지도록)
         removeBlock(cnt, cnt2, g);

         // 블록이 벽에 착지하면 블록->벽으로 변환(떨어지는 블록 초기화)
         blockToWall();

         // 벽들 생성
         makeBlock(g);

         // 테두리 생성
         makeBorder(g);

         if (endCheck == 1) {
            // 패널 이미지

            RBtn.setVisible(true);
            home.setVisible(true);
            TP.add(RBtn);
            TP.add(home);

            g.drawImage(gameover, 0, 200, null);

         }

         if (End == 1) {
            random2 = (int) (Math.random() * 7);
            End = 0;
         }
      }

      // 다음 나올 도형 출력
      public void blockLookAhead(Graphics g) {
         for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
               if (blocks[random2][0][a][b] == 1) {
                  g.fill3DRect(b * blocksize + 1050, a * blocksize + 425, blocksize, blocksize, true);
                  randomColor = (int) (Math.random() * 7);
               }
            }
         }
      }

      // 벽이 천장에 닿으면 게임 오버 이부분 수상
      public void gameOverCheck() {
         for (int x = 1; x < 15; x++) {

            if (gameboard[2][x] == 1) {
               limit = true;
               endCheck = 1;
            }
         }

      }

      // 한 행이 모두 블록으로 채워진 경우 블록들 제거(채워지지않은 경우 블록 떨어지도록)
      public void removeBlock(int cnt, int cnt2, Graphics g) {
         for (int y = 0; y < 19; y++) {
            for (int x = 1; x < 16; x++) {
               if (gameboard[y][x] == 1) {
                  cnt2++;
               }
            }
            if (cnt2 == 15) {
               for (int i = y; i > 1; i--)
                  for (int j = 1; j < 16; j++) {
                     gameboard[i][j] = 0;
                     gameboard[i][j] = gameboard[i - 1][j];
                  }
               scoreSum += 300;

            } else {
               blockDown(cnt, g); // 한 행이 모두 블록으로 채워지지 않을 때만 블록이 내려가도록 함
            }
            cnt2 = 0;
         }
      }

      // 떨어 진 블록
      public void makeBlock(Graphics g) {

         g.setColor(Color.GRAY);
         for (int y = 0; y < 19; y++) {
            for (int x = 1; x <= 15; x++) {
               if (gameboard[y][x] == 1) {
                  g.fill3DRect((x * blocksize) + 380, y * blocksize + 75, blocksize, blocksize, true);
               }
            }
         }

      }

      // 떨어지는 블록
      public void blockDown(int cnt, Graphics g) {
         for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
               if (blocks[random][rotation][j][k] == 1) {
                  curX[cnt] = ((k * blocksize) + wid) / blocksize;
                  curY[cnt] = ((j * blocksize) + hgt) / blocksize;// curX,Y[0][1][2][3]에 좌표 4개 저장

                  // 위치 수정

                  g.fill3DRect((curX[cnt] * blocksize) + 380, curY[cnt] * blocksize + 75, blocksize, blocksize,
                        true);

                  cnt++;
               }
            }
         }
      }

      public void addScore() {
         score++;
      }

      // 블록이 벽에 착지하면 블록->벽으로 변환(떨어지는 블록 초기화)
      // 떨어지던 블록이 벽이 되는지 검사
      // 벽이 되면 wid=120, hgt=0 으로 블록 초기화, rotation도 초기화
      public void blockToWall() {
         try {
            for (int z = 0; z < 4; z++)
               if (gameboard[curY[z] + 1][curX[z]] == 1)
                  for (int j = 0; j < 4; j++) {

                     gameboard[curY[j]][curX[j]] = 1;
                     wid = 100;
                     hgt = 0;
                     End = 1;
                     rotation = 0;
                     random = random2;

                  }

         } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error");

            for (int i = 0; i < 4; i++) {
               System.out.print("(" + curY[i] + "," + curX[i] + ")");
            }

         }

      }

      // 왼쪽 벽에 충돌하면 못움직이도록
      public int collision_LEFT() {
         for (int i = 0; i < 4; i++) {
            if (gameboard[curY[i]][curX[i] - 1] == 1) // 충돌시 1 반환
               return 1;
         }
         return 0; // 충돌하지 않으면 0 반환
      }

      // 오른쪽 벽에 충돌하면 못 움직이도록
      public int collision_RIGHT() {

         for (int i = 0; i < 4; i++) {
            if (gameboard[curY[i]][curX[i] + 1] == 1) // 충돌시 1반환
               return 1;
         }
         return 0; // 충돌하지 않으면 0반환
      }

      // curX,Y에 다음 회전 도형의 절대좌표를 모두 기록해두고, 만약 오른쪽이나 왼쪽 X좌표1,2칸 안에 벽이 있으면 그만큼 오른쪽 혹은
      // 왼쪽으로 밀어서 배치
      public void rotationCheck() {
         // curX,Y에 다음 회전 도형의 절대좌표를 모두 기록해두고, 밑에 구문에서 그 절대좌표의 값이 벽에 닿는지 판단
         int cnt2 = 0;
         for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
               int rotation2 = (rotation % 4) + 1;
               if (rotation2 == 4)
                  rotation2 = 0;
               if (blocks[random][rotation2][j][k] == 1) {
                  curX[cnt2] = ((k * blocksize) + wid) / blocksize;
                  curY[cnt2] = ((j * blocksize) + hgt) / blocksize;
                  cnt2++;
               }
            }
         }

         // curX,Y에 저장된 좌표를 이용
         int chk = 0;
         int blank = 0;
         int error = 0;

         // 왼쪽 벽
         if (gameboard[curY[0]][curX[0]] == 1 || (random == 6 && gameboard[curY[2]][curX[2]] == 1)
               || (random == 1 && gameboard[curY[1]][curX[1]] == 1)) {
            chk = 1; // 만약 다음 회전한 도형의 위치가 벽과 겹친다면 chk=1로 표시함
            error++;
            System.out.println("chk1");

            if (random == 3) { // 일자막대의 경우 회전할 여유가 있는 공백이 없으면 회전막음
               for (int i = 1; i < 5; i++) {
                  if (gameboard[curY[0]][curX[0] + i] == 0) {
                     blank++;
                  }
               }

               if (blank < 4) {
                  chk = 4;
               }

               System.out.println(blank);
            } else { // 그 외의 경우 회전할 여유가 없는 공백이 없으면 회전 막음
               for (int i = 1; i < 4; i++)
                  if (gameboard[curY[0]][curX[0] + i] == 0)
                     blank++;
               if (blank < 3)
                  chk = 4;
               System.out.println("blank2");
               System.out.println(blank);
            }

         }

         // 오른쪽 벽
         else if (gameboard[curY[2]][curX[2]] == 1) {
            error++;
            chk = 2; // 만약 다음 회전한 도형의 위치가 벽과 겹친다면 chk=2로 표시함
            System.out.println("chk2");

            for (int i = 1; i < 5; i++)
               if (gameboard[curY[2]][curX[2] - i] == 0)
                  blank++;
            if (blank < 4)
               chk = 4;
            System.out.println("blank2");
            System.out.println(blank);

         } else if (gameboard[curY[3]][curX[3]] == 1) {
            error++;
            chk = 3; // 만약 다음 회전한 도형의 위치가 벽과 겹친다면 chk=3로 표시함
            System.out.println("chk3");
            for (int i = 0; i < 5; i++)
               if (gameboard[curY[3]][curX[3] - i] == 0)
                  blank++;
            if (blank < 4)
               chk = 4;
            System.out.println(blank);

         }

         if (chk == 1) {
            // chk = 1(다음 회전한 도형의 위치가 벽과 중복되면)면 wid(가로)로 30이동
            wid += blocksize;
            rotation++;
            rotation = rotation % 4;
         } else if (chk == 2) {
            wid -= blocksize * 2;
            rotation++;
            rotation = rotation % 4;
         } else if (chk == 3) {
            wid -= blocksize;
            rotation++;
            rotation = rotation % 4;
         } else if (chk == 4) {
            System.out.println("ban");
         } else {
            rotation++;
            rotation = rotation % 4;
         }

      }

      // 위치 조절 다시해야함

      // board 테두리
      public void makeBorder(Graphics g) {
         g.setColor(Color.GRAY);
         for (int row = 0; row < seroKan; row++) { // 가로줄 x 30 부터 시작 (블럭1개 분)
            g.drawLine(410, blocksize * row + 135, blocksize * 15 + 410, blocksize * row + 135);
         }

         for (int col = 0; col < garoKan + 1; col++) { // 세로줄, y 180 부터 시작 (블럭6개 분)
            g.drawLine(col * blocksize + 410, 135, col * blocksize + 410, blocksize * 17 + 135);
         }
      }

      // 가로로 블럭 몇 개 인지 15칸
      // 세로로 블럭 몇 개 인지 19 칸

      void down() {
         hgt += blocksize;
         TP.repaint();
      }

      void moveUp() {
         rotationCheck();
         if (limit == false) {
            repaint();
         }
      }

      void moveDown() {
         if (limit == false) {
            hgt += blocksize;
            TP.repaint();
         }
      }

      void moveLeft() {
         int sel = collision_LEFT();

         // sel이 1이면 충돌, 0이면 충돌X
         if (sel == 0 && limit == false) {
            wid -= blocksize;
            TP.repaint();
         }
      }

      void moveRight() {
         int sel = collision_RIGHT();

         // sel이 1이면 충돌, 0이면 충돌X
         if (sel == 0 && limit == false) {
            wid += blocksize;
            TP.repaint();
         }
      }
   }

   class TetrisThread extends Thread {
      // TetrisPanel TP = new TetrisPanel();

      public void run() {
         while (true) {
            try {
               sleep(200);
               if (limit == false) // limit이 false일 경우에만 작동. true가 되면 테트리스 작동중지
                  TP.down();
            } catch (InterruptedException e) {
               return;
            }
         }
      }
   }

   public static void main(String[] args) {
      new JTest();

   }
}