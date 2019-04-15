package eg.edu.alexu.csd.datastructure.iceHockey;
import java.awt.*;
import java.awt.List;
import java.util.*;
    class comparator implements Comparator<Point>{
        public int compare(Point p1, Point p2) {
            if(p1.x != p2.x) {
                return p1.x - p2.x;
            }
            return p1.y - p2.y;
        }

    }
    public class findPlayer implements{
        private String[] photo;
        private int team;
        private int threshold;
        public static Point answer1 = new Point();
        public static Point answer2 = new Point();
        public static int count;
        public findPlayer(String[] p, int t, int thres) {
            photo = p;
            team = t;
            threshold = thres;
        }

        public static Point [] findPlayers (String[] photo, int team, int threshold){
            if(photo.length == 0) {
                Point s [] = new Point[0];
                return s;
            }
            boolean array[][] = new boolean[photo.length][photo[0].length()];
            for (int i = 0; i < photo.length; ++i) {
                for(int j = 0; j < photo[i].length(); ++j) {
                    if(photo[i].charAt(j)-'0' == team)
                        array[i][j] = true;
                }
            }
            ArrayList<Point> ans = new ArrayList<Point>();
            for(int i = 0; i < photo.length; ++i) {
                for(int j = 0; j < photo[0].length(); ++j) {
                    count = 0;
                    if(array[i][j] == true) {
                        array[i][j] = false;
                        answer1.move(i, j);
                        answer2.move(i, j);
                        count ++;
                        search(array, i, j);

                        if(count*4 >= threshold) {
                            int r= answer1.x + answer2.x + 1;
                            int w = answer1.y + answer2.y + 1;
                            Point b = new Point(w, r);
                            ans.add(b);
                        }
                    }
                }
            }
            Point answer[] = new Point[ans.size()];
            ans.toArray(answer);
            Arrays.sort(answer, new comparator());
            return answer;
        }
        public static int search(boolean arr[][], int x, int y) {

            if(x+1 < arr.length && arr[x+1][y] == true) {
                arr[x+1][y]=false;
                count++;
                int z = Math.max(answer1.x, x+1);
                answer1.x=z;
                search(arr, x+1, y);
            }
            if(x-1 >= 0 && arr[x-1][y] == true) {
                arr[x-1][y]=false;
                count ++;
                int z = Math.min(answer2.x, x-1);
                answer2.x=z;
                search(arr, x-1, y);
            }
            if(y+1 < arr[0].length && arr[x][y+1] == true) {
                arr[x][y+1]=false;
                count ++;
                int z = Math.max(answer1.y, y+1);
                answer1.y = z;
                search(arr, x, y+1);
            }
            if(y-1 >= 0 && arr[x][y-1] == true) {
                arr[x][y-1]=false;
                count ++;
                int z = Math.min(answer2.y, y-1);
                answer2.y = z;
                search(arr, x, y-1);
            }
            return count;
        }
    }
}
