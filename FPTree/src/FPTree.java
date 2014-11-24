import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;


public class FPTree {
	static long time,lastime=0;
	static FileReader fr;
	static BufferedReader br;
	static DefaultMutableTreeNode FPTree=new DefaultMutableTreeNode();
	static int filesize=1;
	static int support=5*filesize;
	//static ArrayList<Map.Entry<Integer, Integer>> sortlist;
	public static void main(String[] args)throws IOException {
		clockstart();
		DefaultMutableTreeNode node=new DefaultMutableTreeNode("root");
		FPTree=node;
		readline();
		clockend();
	}

	public static void readline()throws IOException{

		fr=new FileReader("./T15I6N0.5KD"+Integer.toString(filesize)+"K.txt");
	    br = new BufferedReader(fr);
	    String s;
	    String[] stringarray;
	    int [] arraycount =new int[500];
	    while((s=br.readLine())!=null){
	    	stringarray= s.split(", ");
	    	for(int i=0;i<stringarray.length;i++){	
	    		arraycount[Integer.parseInt(stringarray[i])]++;
	    	}
	    }
	    System.out.print("統計次數完成");
	    spendclock();
	    fr.close();
	    sortItem(arraycount);
	    
	}
	
	public static void sortItem(int[] arraycount) throws IOException{
		
	    String s;
	    String[] stringarray;
	    fr=new FileReader("./T15I6N0.5KD"+Integer.toString(filesize)+"K.txt");
	    br = new BufferedReader(fr);
	    int tempnum,enter=0;
	    while((s=br.readLine())!=null){
	    	stringarray= s.split(", ");
	    	Map<Integer, Integer> dataCount = new HashMap<Integer, Integer>();
	    	for(int i=0;i<stringarray.length;i++){
	    		tempnum=Integer.parseInt(stringarray[i]);
	    		if(arraycount[tempnum]<support){
	    			continue;
	    		}
	    		dataCount.put(tempnum, arraycount[tempnum]);
	    	}
	    	ArrayList<Map.Entry<Integer, Integer>> sortlist = new ArrayList<Map.Entry<Integer, Integer>>(dataCount.entrySet());
			Collections.sort(sortlist, new Comparator<Map.Entry<Integer, Integer>>() {
	            public int compare(Map.Entry<Integer, Integer> o1,Map.Entry<Integer, Integer> o2) {
	                return (o2.getValue() - o1.getValue());
	            }
	        });
			//System.out.println(sortlist.get(0).getKey());
			int[] sortarray=new int[sortlist.size()];
			for(int i=0;i<sortlist.size();i++){
				sortarray[i]=sortlist.get(i).getKey();
			}
			//System.out.println(sortlist);
			//FPTree.getRoot();
			FPTree=(DefaultMutableTreeNode) FPTree.getRoot();
			//System.out.println(enter++);
			Tree(sortarray);
	    }

	    System.out.print("排序次數完成 ");
	    spendclock();
	    fr.close();
	}
	
	public static void Tree(int[] array){
		//System.out.println(FPTree.getChildAt(0));
		
		for(int i=0;i<array.length;i++){
			DefaultMutableTreeNode childnode=new DefaultMutableTreeNode(array[i]);
			if(FPTree.getChildCount()==0){
				FPTree.add(childnode);
				FPTree=childnode;
			}else{
				boolean tf=false;
				//System.out.println(FPTree.getChildCount());
				for(int search=0;search<FPTree.getChildCount();search++){
					if(FPTree.getChildAt(search).toString().equals(Integer.toString(array[i]))){
						FPTree=(DefaultMutableTreeNode) FPTree.getChildAt(search);
						tf=true;
						break;
					}

				}
				if(!tf){
					FPTree.add(childnode);
					FPTree=childnode;
				}
			}
		}
	}
	
	public static void clockstart(){
		time=System.currentTimeMillis();//計時開始
	}
	public static void spendclock(){
		System.out.println("	耗時= "+((System.currentTimeMillis()-time)-lastime)/1000f+" 秒 ");//結束的時間
		lastime=System.currentTimeMillis()-time;
	}
	public static void clockend(){
		System.out.println("執行耗時= "+(System.currentTimeMillis()-time)/1000f+" 秒 ");//結束的時間	
	}
}
