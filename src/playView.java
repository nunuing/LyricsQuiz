
import java.io.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public class playView {
    static BufferedReader br;
    static musicController musicController;
    static memberController memberController;
    static String[] t = {"3", "2", "1", "Start!!"};
    static  PrintPretty pp;
    private volatile static boolean shouldStop = false;

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        br = new BufferedReader(new InputStreamReader(System.in));
        pp = new PrintPretty();
        musicController = new musicController();
        memberController = new memberController();
           pp.printOpening();
        while (true) {
            pp.printmainmenu();
            int input = -1;
            try {
                input = Integer.parseInt(br.readLine().trim());
            }
            catch (IOException | NumberFormatException e) {

            }
            if (input == 1) {
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("\t\t\t\t\t    로그인");
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.print("\t\t\t\t          ID: ");
                String id = br.readLine();
                System.out.print("\t\t\t\t          PW: ");
                String pw = br.readLine();

                memberVO memberVO = new memberVO(id, pw);
                memberVO resultmemberVO = memberController.login(memberVO);
                if (resultmemberVO == null) {
                    System.out.println("\t\t\t\t\t   아이디 비밀번호를 확인해주세요.");
                    continue;

                }
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println(" WELCOM\t    WELCOM\t    WELCOM\t    WELCOM\t    WELCOM\t    WELCOM\t    WELCOM");
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("\t\t\t\t\t  " + resultmemberVO.getId() + "님 환영합니다!");
                for(int i = 0; i < 1; i++) {
                	
                System.out.println();
                System.out.printf("%2s%-50s%n", " ", "----------------------------------------------------------------------------------------------");
                System.out.printf("%2s%-3s%-3s%-86s%2s%n", " ", "|", " ", " ", "|");
                System.out.printf("%2s%-3s%-3s%36s%-47s%1s%n", " ", "|", " ", " ","게임 설명", "|");
                System.out.printf("%2s%-3s%-3s%-86s%2s%n", " ", "|", " ", " ", "|");
                System.out.printf("%2s%-3s%-3s%-49s%1s%n", " ", "|", "1.", "음악 가사가 AI 음성으로 낭독되며, 플레이어는 가사를 듣고 음악 제목과 가수를 입력합니다.", "|");
                System.out.printf("%2s%-3s%-3s%-58s%2s%n", " ", "|", "2.", "문제마다 총 3번의 기회가 주어지며 처음 정답을 맞추면 10점을 획득하며,             ", "|");
                System.out.printf("%2s%-3s%-3s%-60s%2s%n", " ", "|", " ", "틀릴 때마다 3점씩 줄어들어 마지막에는 4점을 획득할 수 있습니다.                  ", "|");
                System.out.printf("%2s%-3s%-3s%-57s%2s%n", " ", "|",  "3.", "힌트를 요청할 경우, 입력란에 'H' 또는 'h'를 입력하면 힌트를 받을 수 있습니다.      ", "|");
                System.out.printf("%2s%-3s%-3s%-53s%2s%n", " ", "|", " ", "총 3회의 힌트가 제공되며, 사용 가능한 힌트가 소진되면 더 이상 사용할 수 없습니다.     ", "|");
                System.out.printf("%2s%-3s%-3s%-61s%2s%n", " ", "|", "4.", "게임이 끝난 후 최종 점수가 표시되며, 점수가 업데이트됩니다.                      ", "|");
                System.out.printf("%2s%-3s%-3s%-66s%2s%n", " ", "|", "5.", "최종 점수에 따라 플레이어의 랭킹이 결정됩니다.                                ", "|");
                System.out.printf("%2s%-3s%-3s%-86s%2s%n", " ", "|", " ", " ", "|");
                System.out.printf("%2s%-50s%n", " ", "----------------------------------------------------------------------------------------------");
                System.out.println();
                
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                
                }
                
                System.out.println("\t\t\t\t\t  이제 게임을 시작해볼까요?");
                
                
                Thread childThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
        				sc.nextLine();
                        
                        // 메인 스레드의 반복문 종료 요청
                        shouldStop = true;
                    }
                });

                // 자식 스레드 시작
                childThread.start();

                // 메인 스레드의 반복문 실행
                while (!shouldStop) {
                	System.out.println("\t\t\t\t       Press Enter to Start");
        			Thread.sleep(600);
        			clearConsole();
        			Thread.sleep(600);
        			}

//                System.out.println("메인 스레드 반복문 종료");
                GameStart(resultmemberVO);
            
//                
//                
////                System.out.println("\t\t\t\t       Press Enter to Start");
////                sc.nextLine();

//                게임 시작

            } else if (input == 2) {
               System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("\t\t\t\t\t  회원가입");
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.print("\t\t\t\t\t  사용할 ID: ");
                String id = br.readLine().trim();
                System.out.print("\t\t\t\t\t  사용할 PW: ");
                String pw = br.readLine().trim();

                memberVO memberVO = new memberVO(id, pw);
                int result = memberController.insert(memberVO);

                if (result > 0) {
                	System.out.println();
                    System.out.println("\t\t\t\t\t  회원 가입 완료");
                    System.out.println();
                } else {
                    System.out.println("\t\t\t\t\t   회원 가입 실패");
                    System.out.println("\t\t\t\t   아이디, 비밀번호를 확인해주세요!");
                    System.out.println();
                }

            } else if (input == 3) {
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("\t\t\t\t\t  회원탈퇴");
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.print("\t\t\t\t\t  탈퇴할 ID: ");
                String id = br.readLine();
                System.out.print("\t\t\t\t\t  PW: ");
                String pw = br.readLine();

                memberVO memberVO = new memberVO(id, pw);
                int result = memberController.delete(memberVO);
                if (result > 0) {
                	System.out.println();
                    System.out.println("\t\t\t\t\t  회원 탈퇴 완료");
                    System.out.println();
                } else {
                	System.out.println();
                    System.out.println("\t\t\t\t\t  회원 탈퇴 실패");
                    System.out.println();
                }


            } else if (input == 4) {
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-10s%n", "RANKING", "RANKING",  "RANKING",  "RANKING",  "RANKING",  "RANKING",  "RANKING");
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("\t\t\t\t\t    랭킹 확인");
                System.out.println("--------------------------------------------------------------------------------------------------");
//                pp.printRanking();
                ArrayList<memberVO> result = memberController.getRank();

                System.out.println();
                System.out.println("    \t\t\t\t순위\t아이디\t\t 점수");
                System.out.println();
                int len = result.size() < 5 ? result.size() : 5;
                for (int i = 0; i < len; i++) {
                    System.out.printf("    \t\t\t\t%3s\t%-15s%5s%n", i+1, result.get(i).getId(), result.get(i).getScore());
                }
                System.out.println();
                System.out.println();
            } else if (input == 5) {
            	System.out.println();
            	  for (int j = 0; j < 1; j++) {

            		  System.out.println("\t\t\t\t\t   게임을 종료합니다.");
                      
                      try {
                          Thread.sleep(3000);
                      } catch (InterruptedException e) {
                      }
                  }
//                System.out.println("\t\t\t\t\t   종료되었습니다.");
                break;
            }
            else {
            	System.out.println();
                System.out.println("\t\t\t\t     1-5 사이 숫자를 입력해주세요!");
                System.out.println();
            }
        }
//        pp.printGameover();
        br.close();
        sc.close();
    }

    static void GameStart(memberVO member) throws IOException {
        int cur = 0;
        int score = 0;
        int hcnt = 0;
        ArrayList<musicVO> list = musicController.getList();
        int slen = list.size();
        boolean[] check = new boolean[slen + 1];

        while (cur++ < 10) {
            // 게임 시작 준비 물어보기
//            System.out.println("3초 후에 노래가 시작됩니다!");
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 1; j++) {
                    System.out.println("\t\t\t\t\t3초 후에 노래가 시작됩니다!");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                }
                for (int j = 0; j < 1; j++) {

                    System.out.println("\t\t\t\t\t              \r\n"
                            + "\t\t\t\t\t       ____   \r\n"
                            + "\t\t\t\t\t      |___ \\  \r\n"
                            + "\t\t\t\t\t        __) | \r\n"
                            + "\t\t\t\t\t       |__ <  \r\n"
                            + "\t\t\t\t\t       ___) | \r\n"
                            + "\t\t\t\t\t      |____/  \r\n"
                            + "\t\t\t\t\t              \r\n"
                            + "\t\t\t\t\t              ");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
                for (int j = 0; j < 1; j++) {
                    System.out.println("\t\t\t\t\t              \r\n"
                            + "\t\t\t\t\t        ___   \r\n"
                            + "\t\t\t\t\t       |__ \\  \r\n"
                            + "\t\t\t\t\t          ) | \r\n"
                            + "\t\t\t\t\t         / /  \r\n"
                            + "\t\t\t\t\t        / /_  \r\n"
                            + "\t\t\t\t\t       |____| \r\n"
                            + "\t\t\t\t\t              \r\n"
                            + "\t\t\t\t\t              ");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
                for (int j = 0; j < 1; j++) {
                    System.out.println("\t\t\t\t\t             \r\n"
                            + "\t\t\t\t\t         __  \r\n"
                            + "\t\t\t\t\t        /_ | \r\n"
                            + "\t\t\t\t\t         | | \r\n"
                            + "\t\t\t\t\t         | | \r\n"
                            + "\t\t\t\t\t         | | \r\n"
                            + "\t\t\t\t\t         |_| \r\n"
                            + "\t\t\t\t\t             \r\n"
                            + "\t\t\t\t\t             ");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
                for (int j = 0; j < 1; j++) {
                    System.out.println("\t\t     _______..___________.     ___      .______      .___________. __   __  \r\n"
                            + "\t\t    /       ||           |    /   \\     |   _  \\     |           ||  | |  | \r\n"
                            + "\t\t   |   (----``---|  |----`   /  ^  \\    |  |_)  |    `---|  |----`|  | |  | \r\n"
                            + "\t\t    \\   \\        |  |       /  /_\\  \\   |      /         |  |     |  | |  | \r\n"
                            + "\t\t.----)   |       |  |      /  _____  \\  |  |\\  \\----.    |  |     |__| |__| \r\n"
                            + "\t\t|_______/        |__|     /__/     \\__\\ | _| `._____|    |__|     (__) (__) \r\n"
                            + "\t\t                                                                            ");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }

//            랜덤으로 노래 인덱스 뽑기 -> 중복 빼고
            int idx = -1;
            while (true) {
                idx = (int) (Math.random() * slen);
                if (!check[idx]) {
                    check[idx] = true;
                    break;
                }
            }
            musicVO answer = list.get(idx);
            Music playing = new Music("/music/" + answer.getMfile(), true);
            playing.start();

            int wrong = 0;
            while (wrong <= 3) {
            	System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("\t\t\t\t\t\t" + cur + "번 문제");
            	System.out.print("\t\t\t\t\t   제목을 입력하세요 : ");
                String aTitle = br.readLine().toLowerCase().replaceAll(" ", "");

                if (aTitle.equals("h")) {
                    if (hcnt < 3) {
                        String[] hints = answer.getHints();
                        System.out.println();
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        System.out.println("\t\t\t\t\t   힌트: " + hints[answer.hidx]);
                        answer.hidx++;
                        hcnt++;
                        System.out.println();
                        continue;
                    } else {
                    	System.out.println("--------------------------------------------------------------------------------------------------");
                        System.out.println("\t\t\t\t\t   힌트를 다 소진하였습니다.");
                        continue;
                    }
                } else if (aTitle.equals("ㅗ")) {
                	System.out.println();
                	System.out.println("--------------------------------------------------------------------------------------------------");
                    System.out.println("\t\t\t\t\t   잘못 입력하셨습니다.");
                    System.out.println();
                    continue;
                }
                System.out.print("\t\t\t\t\t   가수를 입력하세요 : ");
                String aSinger = br.readLine().toLowerCase().replaceAll(" ", "");

                if (aTitle.equals("") || aSinger.equals("")) {
                	System.out.println("--------------------------------------------------------------------------------------------------");
                    System.out.println("\t\t\t\t\t답을 입력해주세요!");
                    continue;
                }
                
//                만약 입력된게 영어라면? -> 선우 + 정현
                if ( (answer.getTitle().equals(aTitle) || answer.getTitle_eng().equals(aTitle))
                        && (answer.getSinger().equals(aSinger) || answer.getSinger_eng().equals(aSinger))) {
//                    정답 효과음 재생 -> 동수 + 주원
                    playing.close();
                    ringPlay("띠리링.mp3");
                    int earned = 10 - (wrong * 3);
                    score += earned;
                    System.out.println();
                    System.out.println("--------------------------------------------------------------------------------------------------");
                    System.out.println("\t\t\t\t\t   정답입니다!!");
                    System.out.println("\t\t\t\t\t   " + earned + "점을 획득합니다!");
                    System.out.println("\t\t\t\t\t   현재 점수 " + score + "점");
                    System.out.println("--------------------------------------------------------------------------------------------------");
                    System.out.println();
                    break;
                }
                wrong++;
                if (wrong == 3) {
//                    실패 효과음 재생 -> 동수 + 주원
                    ringPlay("패배.mp3");
                    System.out.println();
                    System.out.println("\t\t\t\t틀렸습니다! 모든 기회가 소진되었습니다. 다음 문제로 넘어갑니다.");
                    System.out.println();
                    playing.close();
                    break;
                } else {
                	System.out.println();
                    System.out.println("\t\t\t\t\t틀렸습니다! 다시 한 번 생각해볼까요?");
                    System.out.println();
                }
            }
        }
//      게임 오버 효과음 재생 -> 동수 + 주원
        ringPlay("성공나팔.mp3");
        System.out.println();
        System.out.println("\t\t\t\t\t     GAME CLEAR!");
        System.out.println();
        System.out.println("\t\t\t\t\t     최종 점수 : " + score);
        System.out.println();
//      회원 점수 업데이트 하기
        if (member.getScore() <= score) {
            member.setScore(score);
            member.setUpdated(Timestamp.from(Instant.now()));
            memberController.update(member);
        }
//      랭킹확인하기
    }

    static public void ringPlay(String mFile) {
        Music playing = new Music("/music/" + mFile, false);
        playing.start();
    }

//		깜빡거림
	public static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[h\033[2J");
				System.out.flush();
			}
		} catch (Exception e) {
			System.out.println("콘솔을 지울 수 없습니다.");
		}
	}
}

