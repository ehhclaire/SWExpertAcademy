package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_4615 {
   public static void main(String[] args) throws Exception {
      System.setIn(new FileInputStream("res/swea_4615.txt"));
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      // 상, 하, 좌, 우, 상좌, 상우, 하좌, 하우
      int[][] d = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};
      int T = Integer.parseInt(br.readLine());   // 테스트케이스 수
      
      for(int testcase=1; testcase<=T; testcase++) {
         StringTokenizer st = new StringTokenizer(br.readLine());
         int N = Integer.parseInt(st.nextToken());   // 보드 한 변의 길이
         int M = Integer.parseInt(st.nextToken());   // 돌을 놓는 횟수
         int[][] map = new int[N][N];            // 게임 보드

         int blackCnt=0;   // 흑돌 개수
         int whiteCnt=0;   // 백돌 개수
         
         // 보드 초기화
         map[N/2-1][N/2-1] = 2;   // 중간 위쪽 좌측
         map[N/2-1][N/2] = 1;   // 중간 위쪽 우측
         map[N/2][N/2-1] = 1;   // 중간 아래쪽 좌측
         map[N/2][N/2] = 2;      // 중간 아래쪽 우측
         
         for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int color = Integer.parseInt(st.nextToken());   
            map[r][c] = color;      // 좌표에 입력 돌 색깔 지정
            
            int nr=0, nc=0; 
            for(int j=0; j<8; j++) {
               nr = r + d[j][0];
               nc = c + d[j][1];
               
               boolean flag = false;
               while(true) {
            	   if(nr<0 || nr>=N || nc<0 || nc>=N) break;
            	   if(map[nr][nc]==0) break;
            	   if(map[nr][nc]==color) {
            		   flag = true;
            		   break;
            	   }
            	   nr += d[j][0];
            	   nc += d[j][1]; 
               }
               if(flag) {
            	   while(nr!=r || nc!=c) {
            		   nr -= d[j][0];
                	   nc -= d[j][1];
                	   map[nr][nc] = color;
            	   }
               }
            }
         }
         for(int[] ma : map) {
        	 for(int m : ma) {
        		 if(m==1) blackCnt++;
        		 else if(m==2) whiteCnt++;
        	 }
         }
         System.out.println("#" + testcase + " " + blackCnt + " " + whiteCnt);   
      }
   }
}