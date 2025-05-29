package map_navigation;

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
