package org.swea.eclipse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Pos {
	int y;
	int x;
	
	Pos (int y, int x) {
		this.y=y;
		this.x=x;
	}
}

public class Solution {

	public static final int N=16;
	
	public static final int dy[]= {0,0,1,-1};
	public static final int dx[]= {1,-1,0,0};
	
	public static final int PATH=0;
	public static final int WALL=1;
	public static final int START=2;
	public static final int END=3;
	
	static int sy,sx;
	static int ey,ex;
	
	static int[][]map =new int[N][N];
	static boolean[][] visited=new boolean[N][N];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		
		for (int tc=1; tc<=10; tc++) {
			
			int T=Integer.parseInt(br.readLine());
			
			for (int i=0; i<N; i++)
				Arrays.fill(visited[i], false);
			
			for (int i=0; i<N; i++) {
				String[] line=br.readLine().split("");
				for (int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(line[j]);
					
					if (map[i][j]==START) {
						sy=i; sx=j;
					}
					if (map[i][j]==END) {
						ey=i; ex=j;
					}
				}
			}
			
			boolean result=bfs();
			if (result) System.out.println("#"+tc+" 1");
			else System.out.println("#"+tc+" 0");
		}
		
	}
	
	private static boolean bfs () {
		Queue<Pos> q=new LinkedList<>();
		q.add(new Pos(sy,sx));
		visited[sy][sx]=true;
		
		while (!q.isEmpty()) {
			Pos p=q.poll();
			int y=p.y;
			int x=p.x;
			
			for (int i=0; i<4; i++) {
				int ny=y+dy[i];
				int nx=x+dx[i];
				
				if (ny<=0 || nx<=0 || ny>=14 || nx>=14) continue;
				
				if (map[ny][nx]==END) return true;
				if (!visited[ny][nx] && map[ny][nx]==PATH) {
					visited[ny][nx]=true;
					q.add(new Pos(ny, nx));
				}
			}
		}
		return false;
	}

}
