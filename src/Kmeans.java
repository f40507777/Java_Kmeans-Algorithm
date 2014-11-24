import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;	//亂數
import java.util.Scanner;	//輸入
public class Kmeans {
	
	static int range=500;
	static int point_count;
	static int k;
	static int[][] pointarray;
	static int[][] CenterPoint;
	private static Scanner scanner;
	public static void main(String[] args) {
		CreatePoint();
	}

	
	public static void CreatePoint()
	{
        scanner = new Scanner(System.in);
        System.out.print("座標的數量=");
        point_count=Integer.parseInt(scanner.next());   
        System.out.print("k=");
        k=Integer.parseInt(scanner.next());
        pointarray = new int[2][point_count];
        CenterPoint=new int[2][k];
        int[][] CenterPoint = new int[2][k];
        Random ran = new Random();
        for(int i=0;i<point_count;i++){
        	pointarray[0][i]=(ran.nextInt(range)+1);
        	pointarray[1][i]=(ran.nextInt(range)+1);
        }
        for(int i=0;i<k;i++){//CenterPoint
        	CenterPoint[0][i]=(ran.nextInt(range)+1);
        	CenterPoint[1][i]=(ran.nextInt(range)+1);
        }
        Classification(CenterPoint);
	}
	
	public static void Classification(int[][] CenterPoint)//算距離+分類
	{
		HashMap<Integer, ArrayList<Integer>> kGroup =new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> kPoint = new ArrayList<Integer>();
		double distance;
		int minPoint=0;
		for(int i=0;i<point_count;i++){
			double mindistance=Double.POSITIVE_INFINITY;
			for(int j=0;j<k;j++){
				distance=Math.sqrt(((pointarray[0][i]-CenterPoint[0][j])*(pointarray[0][i]-CenterPoint[0][j]))+((pointarray[1][i]-CenterPoint[1][j])*(pointarray[1][i]-CenterPoint[1][j])));
				if(distance<mindistance){
					mindistance=distance;
					minPoint=j;
				}
			}
			kPoint=kGroup.get(minPoint);
			if (kPoint == null) {
				kPoint = new ArrayList<Integer>();
			}
			kPoint.add(i);
			kGroup.put(minPoint,kPoint);
		}
		System.out.println(kGroup);
		Average(kGroup);
	}
	
	public static void Average(HashMap<Integer, ArrayList<Integer>> group)//平均中心點
	{
		int count=0;
		for(int i=0;i<group.size();i++){
			int average_x=0,average_y=0;
			for(int j=0;j<group.get(i).size();j++){
				average_x=average_x+pointarray[0][group.get(i).get(j)];
				average_y=average_y+pointarray[1][group.get(i).get(j)];
			}
			if(CenterPoint[0][i]==average_x/group.get(i).size() && CenterPoint[1][i]==average_y/group.get(i).size()){
				count++;
			}
			CenterPoint[0][i]=average_x/group.get(i).size();
			CenterPoint[1][i]=average_y/group.get(i).size();
		}
		if(count==group.size()){
			System.out.println("final="+group);
		}else{
			Classification(CenterPoint);
		}	
	}
}
