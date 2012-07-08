import java.io.*;
import java.util.*;

class dotty {
	static int count;
	public static String[] findChild(String []id,String parent){
			int length = parent.length() + 2;
			String []children = new String[10];
			String []validChild = new String[10];
			int i=0;
			for(String str : id)
				if(str.length() == length){
					children[i]=str;
					i++;
				}
				count=0;
			for(int j=0;j<i;j++){
				String sub=children[j].substring(0,parent.length());
				if(parent.equals(sub)){
					validChild[count]=children[j];
					count++;
			}
			
	}
	return validChild;
	}
	public static int findIndex(String child,String []arr) {
		for(int i=0;i<arr.length-1;i++) {
			if(child.equals(arr[i]))
				return i;
		}
		return 0;
	}
	public static void createID(String []id,String []id1,int i){
		int count4=0,count6=0,count8=0;
		for(int j=2;j<i-2;j++){
			if(id[j].length()==4){
				id1[j]=id1[1]+count4++;
			}
			if(id[j].length()==6){
				id1[j]=id1[j-1]+count6++;
			}		
		}
	}
	public static void main(String args[]) {
				try {
				byte []b=new byte[2];
				b[0]=13;b[1]=10;
				FileOutputStream fos=new FileOutputStream(new File("output.dot"));
				BufferedReader in = new BufferedReader(new FileReader("req_data.txt"));
				String str="";
				String toWrite[] = new String[30];
				String []id = new String[30];
				String []id1 = new String[30];
				fos.write("digraph \"graph\"{ node [ shape=record, sides=4, distortion=\"0.0\", orientation=\"0.0\", skew=\"0.0\", color=black, style=bold];".getBytes());
				fos.write(b);
				String aa =new String();
				for(int j=0;j<id.length;j++)
				id[j]=new String();
				int i=0;
				while((str = in.readLine())!=null){
						//while(true)
						
						//System.out.println(aa);
						//System.out.println(str);
						String res[] = str.split("=");
						if("ID ".equals(res[0])) {
								toWrite[i]=aa;
								id[i]=res[1]; 
								id1[i] = res[1];
								i++;
								aa="";
						}
						else {
							aa=aa.concat(str);
							aa=aa.concat("\\l");
						}
				}
				id1[0]= "Main Page";
				id1[1]="Header";
				id1[i-1] = "Footer";
				id1[i-2]="Side Menu";
				createID(id,id1,i);
				for(int n=0 ; n<i;n++){ 	
					if(n==0){
							fos.write(("\"" + id1[n]+"\""+"["+"\n label=\"{"+ id1[n]+"||"+toWrite[n]+"}\""+"\n]\n").getBytes());
						}
					else if(n==1){
							fos.write(("\"" + id1[n]+"\""+"["+"\n label=\"{"+ id1[n]+"||"+toWrite[n]+"}\""+"\n]\n").getBytes());
						}
						else if(n==(i-1)){
							fos.write(("\"" + id1[n]+"\""+"["+"\n label=\"{" + id1[n]+"||"+toWrite[n]+"}\""+"\n]\n").getBytes());
						}
						else if(n==(i-2)){
							fos.write(("\"" + id1[n]+"\""+"["+"\n label=\"{" + id1[n]+"||"+toWrite[n]+"}\""+"\n]\n").getBytes());
						}
						else
					//System.out.println("ID:"+id[n]+"to:" + toWrite[n]+"\n");
					fos.write(("\"" + id[n]+"\""+"["+"\n label=\"{"+ id[n]+"||"+toWrite[n]+"}\""+"\n]\n").getBytes());
				}
				for(int j=0;j<i-1;j++){
					String first = id[j];
					
					String []children = findChild(id,id[j]);
					for(int c=0;c<count;c++) {
						int val = findIndex (children[c],id);
						if(val==1){
							fos.write(("\"" + id1[j] + "\"" + "->\"" + id1[1] + "\"[dir=\"back\"];").getBytes());
						}
						else if(val==(i-1)){
							fos.write(("\"" + id1[j] + "\"" + "->\"" + id1[i-1] + "\"[dir=\"back\"];").getBytes());
						}
						else if(val==(i-2)){
							fos.write(("\"" + id1[j] + "\"" + "->\"" + id1[i-2] + "\"[dir=\"back\"];").getBytes());
						}
						else
						fos.write(("\"" + id1[j] + "\"" + "->\"" + children[c] + "\"[dir=\"back\"];").getBytes());
						fos.write(b);
					}
				}
				fos.write("}".getBytes());
				int j=0;
				
				/*BufferedReader inDot = new BufferedReader(new FileReader("output.dot"));
				PrintWriter pw = new PrintWriter(new FileWriter ("output.dot"));
				while((str = inDot.readLine())!=null){ 
						String res[] = str.split("->");
						if("\" 1\"".equals(res[0])){
							
						}
				}*/
				}catch(Exception e)
				{
				e.printStackTrace();
					
				}
	}

}
