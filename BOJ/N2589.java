package BOJ.BOJ;

import java.io.*;
import java.util.*;

public class N2589 {
    static int max = -1;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int R, C;
    static char[][] map;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for(int i=0; i<R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j] == 'L') {
                    bfs(i, j);
                }
            }
        }

        System.out.print(max);
    }

    static void bfs(int x, int y) {
        boolean[][] visited = new boolean[R][C];
        visited[x][y] = true;

        Queue<int[]> lands = new ArrayDeque<>();
        lands.add(new int[]{x, y, 0});

        int tmp = 0;
        while(!lands.isEmpty()) {
            int[] n = lands.poll();

            for(int i=0; i<4; i++) {
                int nx = n[0]+dx[i];
                int ny = n[1]+dy[i];

                if(nx<0 || ny<0 || nx>=R || ny>=C) continue;
                if(visited[nx][ny] || map[nx][ny]=='W') continue;

                visited[nx][ny] = true;
                lands.add(new int[]{nx, ny, n[2]+1});
            }

            max = Math.max(max, n[2]);
        }
    }
}
