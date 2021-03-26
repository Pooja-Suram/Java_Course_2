import edu.duke.FileResource;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Locale;

public class ImprovingGladLib {

    private HashMap<String, Integer> map;


    public ImprovingGladLib(){
        map = new HashMap<String, Integer>();
    }


    //this method will build new map of codons mapped
    //to their counts from dna string, by reading frame
    // from stat position
    public void buildCodonMap(int start, String dna){
        map = new HashMap<String, Integer>();
            for(int j=start; j<dna.length()-3; j+=3){
                String codon = dna.substring(j, j+3);
                if(map.containsKey(codon)){
                    int count = map.get(codon);
                    map.put(codon, count+1);
                }
                else{
                    map.put(codon, 1);
                }
            }
    }


    //this method returns the codon that
    //has largest count
    public String getMostCommonCodon(){
        int largestCount = 0;
        String commonCodon = null;
        for(String codon: map.keySet()){
            int count = map.get(codon);
            if(count > largestCount){
                largestCount = count;
                commonCodon = codon;
            }
        }
        return commonCodon;
    }

    //this method prints all the codons
    //from HashMap where their count is between start
    //and end
    public void printCodonCounts(int start, int end){
        for(String codon: map.keySet()){
            int count = map.get(codon);
            if(count >= start && count <= end){
                System.out.println("Codon: "+ codon + " count: " + count);
            }
        }
    }

    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase(Locale.ROOT);
        for(int i=0; i<3; i++){
            System.out.println("Reading frame with starting " + i);
            int unique =0;
            buildCodonMap(i, dna);
            for(String word: map.keySet()){
                if(map.get(word) == 1){
                    unique+=1;
                }
            }
            System.out.println("It has " + unique + " unique codons");
            String commonCodon = getMostCommonCodon();
            System.out.println("Most common codon is: "+ commonCodon +" with count "+ map.get(commonCodon));
            printCodonCounts(6, 7);

        }
    }

    public static void main(String[] args) {
        ImprovingGladLib gladLib = new ImprovingGladLib();
        gladLib.tester();
    }
}
