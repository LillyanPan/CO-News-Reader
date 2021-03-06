import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class Framework {
    int n;
    int v[];
    int w[];
    int W;
    boolean picked[];
    void input(String input_name){
        File file = new File(input_name);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));

            String text = reader.readLine();
            String parts[];
            parts=text.split(" ");
            n=Integer.parseInt(parts[0]);
            W=Integer.parseInt(parts[1]);
            v=new int[n];
            w=new int[n];
            picked=new boolean[n];
            for (int i=0;i<n;i++){
                text=reader.readLine();
                parts=text.split(" ");
                v[i]=Integer.parseInt(parts[0]);
                w[i]=Integer.parseInt(parts[1]);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //writing the output
    void output(String output_name)
    {
        try{
            PrintWriter writer = new PrintWriter(output_name, "UTF-8");

            int total_v=0;
            for (int i=0;i<n;i++)
              if (picked[i])
                total_v += v[i];
            writer.println(total_v);
            for (int i=0;i<n;i++)
              if (picked[i])
                writer.println("1");
              else 
                writer.println("0");

            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Framework(String []Args){
        input(Args[0]);
        // choose be decreasing weight density
        int[] pickVal = new int[n];
        int[] pickDensity = new int[n];
        
        // Initialize picked array //
        for (int p = 0; p < n; p++) {
        	picked[p] = false;
        	pickVal[p] = -1;
        	pickDensity[p] = -1;
        }
        int maxValue = -1;
        double maxDensity = -1;
        int maxValInd = -1;
        int maxDenInd = -1;
        int newDenWeight, newValWeight;
        int denSum = 0;
        int valSum = 0;
        int weight = W;
        
        /* Greedy Algorithm choose max value */
        // 0 : picked; 1 : incorrect
        
        while (weight > 0) {
        	maxValue = -1;
        	for (int i = 0; i < n; i++) {
        		if (v[i] > maxValue && pickVal[i] != 0 && pickVal[i] != 1) {
        			maxValInd = i;
        			maxValue = v[i];
        		}
        	}
        	pickVal[maxValInd] = 1;
        	newValWeight = weight - w[maxValInd];
        	if (newValWeight >= 0) {
        		pickVal[maxValInd] = 0;
        	}
        	weight = newValWeight;
        	
        	
        }
        /* Greedy Algorithm choose max density */
        weight = W;
        while (weight >= 0) {
        	maxDensity = -1;
        	for (int i = 0; i < n; i++) {
        		double den = (double)(v[i])/w[i];
        		if (den > maxDensity  && pickDensity[i] != 0 && pickDensity[i] != 1) {
        			maxDenInd = i;
        			maxDensity = den;
        		}
        	}
        	pickDensity[maxDenInd] = 1;
        	newDenWeight = weight - w[maxDenInd];
        	if (newDenWeight >= 0) {
        		pickDensity[maxDenInd] = 0;
        	}
        	weight = newDenWeight;
        }
        
        /* Check which algorithm returns greater value */
        for (int i = 0; i < n; i++) {
        	if (pickVal[i] == 0) {
        		valSum += v[i];
        	}
        	if (pickDensity[i] == 0) {
        		denSum += v[i];
        	}
        }
        
        /* Set picked array */
        if (valSum >= denSum) {
        	for (int i = 0; i < n; i++) {
        		if (pickVal[i] == 0) {
        			picked[i] = true;
        		}
        	}
        }
        else {
        	for (int i = 0; i < n; i++) {
        		if (pickDensity[i] == 0) {
        			picked[i] = true;
        		}
        	}
        }
        

        output(Args[1]);
    }

    public static void main(String [] Args) //Strings in Args are the name of the input file followed by the name of the output file
    {
        new Framework(Args);
    }
}
