package BOJ.BOJ;

import java.util.*;
import java.io.*;

public class N11559 {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static char[][] game = new char[12][6];
    static char[][] copy = new char[12][6];

    static int count = 0;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            game[i] = br.readLine().toCharArray();
        }

        while(true) {
            copyGame();
            boolean[][] visited = new boolean[12][6];

            //1. 4개 이상 동일한 알파벳 찾기
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if(game[i][j] != '.' && !visited[i][j]) {
                        bfs(i, j, visited);
                    }
                }
            }

            //2. 1번에서 찾은 것들 터트리기
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if(visited[i][j]) {
                        game[i][j] = '.';
                    }
                }
            }

            //while문 탈출조건
            if(isSame()) break;

            //3. 빈공간 내리기
            down();
        }

        System.out.println(count);
    }

    static void bfs(int x, int y, boolean[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();
        Queue<int[]> nearby = new ArrayDeque<>();

        q.add(new int[]{x, y});
        visited[x][y] = true;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            nearby.add(now);

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx<0 || nx>=12 || ny<0 || ny>=6) continue;
                if(visited[nx][ny] || game[x][y] != game[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }

        if (nearby.size() < 4) {
            while(!nearby.isEmpty()) {
                int[] now = nearby.poll();
                visited[now[0]][now[1]] = false;
            }
        }
    }

    static void down() {
        count++;
        for (int i = 0; i < 6; i++) {
            Queue<Character> queue = new ArrayDeque<>();

            for (int x = 11; x >= 0; x--) {
                if(game[x][i] != '.') {
                    queue.add(game[x][i]);
                    game[x][i] = '.';
                }
            }

            int x = 11;
            while(!queue.isEmpty()) {
                game[x--][i] = queue.poll();
            }
        }
    }

    static void copyGame() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                copy[i][j] = game[i][j];
            }
        }
    }

    static boolean isSame() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if(copy[i][j] != game[i][j]) return false;
            }
        }
        return true;
    }
}
