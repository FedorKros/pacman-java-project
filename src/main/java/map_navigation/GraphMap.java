package map_navigation;

import characters.Enemy;
import characters.Player;

import java.util.*;

public class GraphMap {

    public static int[] bfsFromPlayer(List<List<Integer>> adj, Player player, int mapLength) {

        int playerX = player.getX();
        int playerY = player.getY();

        int n = adj.size();
        int[] distance = new int[n];
        Arrays.fill(distance, -1);

        Queue<Integer> queue = new LinkedList<>();
        int playerPos = getCellNum(playerY, playerX, mapLength);
        distance[playerPos] = 0;

        queue.add(playerPos);

        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            for (int neighbor : adj.get(vertex)) {

                if (distance[neighbor] == -1) {
                    distance[neighbor] = distance[vertex] + 1;
                    queue.add(neighbor);
                }
            }
        }

    return distance;
    }

    public static int callDfsFromEnemy(List<List<Integer>> adj, Player player, int mapLength, Enemy enemy) {
        boolean[] used = new boolean[adj.size()];
        List<Integer> track = new ArrayList<>();
        int playerCellNum = getCellNum(player.getY(), player.getX(), mapLength);
        int start = getCellNum(enemy.getY(), enemy.getX(), mapLength);

        boolean playerFound = dfsFromEnemy(adj, start, playerCellNum, used, track);
        if (playerFound && track.size() > 1) {
            return track.get(1);
        }
        return track.get(0);
    }


    static boolean dfsFromEnemy(List<List<Integer>> adj, int start, int playerCellnum, boolean[] used, List<Integer> track) {

        used[start] = true;
        track.add(start);

        if (start == playerCellnum) return true;
        List<Integer> ngh = new ArrayList<>(adj.get(start));
        Collections.shuffle(ngh);

        for (int i = 0; i < ngh.size(); i++) {
            if (!used[ngh.get(i)]) {
                if (dfsFromEnemy(adj, ngh.get(i), playerCellnum, used, track)) return true;
            }
        }

        track.remove(track.size() - 1);
        return false;
    }

    public static List<List<Integer>> createAdjList(int[][] map) {

        int n = map.length*map[0].length;
        // Answer 2d array
        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    int cellNum = getCellNum(i,j,map.length);
                    if (i != map.length-1) {
                        if (map[i+1][j] == 0) {
                            int neighbourCellNum = getCellNum(i+1,j,map.length);
                            adj.get(cellNum).add(neighbourCellNum);
                            adj.get(neighbourCellNum).add(cellNum);
                        }
                    }
                    if (j != map.length-1) {
                        if (map[i][j+1] == 0) {
                            int neighbourCellNum = getCellNum(i,j+1,map.length);
                            adj.get(cellNum).add(neighbourCellNum);
                            adj.get(neighbourCellNum).add(cellNum);
                        }
                    }
                }
            }
        }
        return adj;
    }

    public static int getCellNum(int row, int column, int rowLength) {
        return row*rowLength + column;
    }

}
