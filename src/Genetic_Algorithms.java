import java.lang.Math;
import java.util.*;
public class Genetic_Algorithms {
	static int range=200;//+-20
	static int top=10;
	static int round=0;
	static double minx=0;
	static double miny=0;
	public static void main(String[] args) {
		System.out.println();
		create();
	}
	public static void create(){
		HashMap<Float, Float> data =new HashMap<Float, Float>();  
		for(int i=0;i<20;i++){
			float x=random();
			if(data.containsKey(x)){
				x=(float) (x*1.1);
			}
			data.put(x, calculate(x));
		}
		System.out.println("起始參數="+data);
		sort(data);
	}
	public static void sort(HashMap<Float, Float> data){
		List<Map.Entry<Float, Float>> Sortlist =new ArrayList<Map.Entry<Float, Float>>(data.entrySet());
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		Collections.sort(Sortlist, new Comparator<Map.Entry<Float, Float>>(){
            public int compare(Map.Entry<Float, Float> entry1,
                               Map.Entry<Float, Float> entry2){
                return (int)(entry1.getValue()*10000-entry2.getValue()*10000);
            }
        });
		if((Sortlist.get(0).getValue()-miny)<0){
			miny=Sortlist.get(0).getValue();
			minx=Sortlist.get(0).getKey();
		}
		round++;
		//System.out.println(Sortlist.size());
		if(round==100){
			System.out.println();
			System.out.println("題目 y=x^4+x^3-3x^2+3x-27");
			System.out.println("疊代次數="+round);
			System.out.println("範圍="+range+" ~ -"+range);
			System.out.println("minx="+Sortlist.get(0).getKey()+"	miny="+Sortlist.get(0).getValue());
			return;
		}
		System.out.println("x="+Sortlist.get(0).getKey()+"	y="+Sortlist.get(0).getValue());
		float [] topx=new float[top];
		for(int i=0;i<top;i++){
			topx[i]=Sortlist.get(i).getKey();
		}
		copulation(topx);

		
	}
	public static void copulation(float [] topx){
		HashMap<Float, Float> data =new HashMap<Float, Float>(); 
		float child;
		for(int i=0;i<topx.length;i++){
			for(int j = i+1;j<topx.length;j++){
				child=(float) (topx[i]*0.7+topx[j]*0.3);
				if(data.containsKey(child)){
					child=(float) (child+random()*2);
				}
				data.put(child, calculate(child));
			}
		}
		sort(data);
	}
	public static float random(){
		return (float) Math.random()*range*2-range;
	}
	public static float calculate(float x){
		return (float)(Math.pow(x,4)+Math.pow(x,3)-3*Math.pow(x,2)+3*x-27);
	}

}
