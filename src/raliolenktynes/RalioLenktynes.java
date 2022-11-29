package raliolenktynes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;


class Masina{
    private String marke;
    private String modelis;
    private int variklioTuris;
    private int greitis;
    
    public Masina(String marke, String modelis, int variklioTuris, int greitis){
        this.marke = marke;
        this.modelis = modelis;
        this.variklioTuris = variklioTuris;
        this.greitis = greitis;
    }
    public String getMarke(){
        return marke;
    }
    public String getModelis (){
        return modelis;
    }
    public int getVariklioTuris(){
        return variklioTuris;
    }
    public int getGreitis(){
        return greitis;
    }
    public void getDuomenys(){
        System.out.println("\t"+marke+" "+modelis+" "+variklioTuris+" "+greitis);
    }
    public void setAutomobilioMarke (String marke){
        this.marke = marke;
    }
    public void setModelis (String modelis){
        this.modelis = modelis;
    }
    public void setVariklioTuris(int variklioTuris){
        this.variklioTuris = variklioTuris;
    }
    public void setMaksimalusGreitis(int greitis){
        this.greitis = greitis;
    }
}

//=========================//

class Ralis{
    private ArrayList<Masina>sarasas = new ArrayList();
    
    public void getSarasas(){
        try{
            File file = new File("C:\\Users\\Kig\\Documents\\NetBeansProjects\\RalioLenktynes\\Duomenys.txt");
            //---//
            String marke = null;
            String modelis = null;
            int variklioTuris = 0;
            int greitis = 0;
            //---//
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = "";
            while((s = br.readLine()) != null){
                StringTokenizer token = new StringTokenizer(s);
                int tokenNr = 1;
                while(token.hasMoreTokens()){
                    switch (tokenNr){
                        case 1:
                            marke = token.nextToken();
                            tokenNr++;
                            break;
                        case 2:
                            modelis = token.nextToken();
                            tokenNr++;
                            break;
                        case 3:
                            variklioTuris = Integer.parseInt(token.nextToken());
                            tokenNr++;
                            break;
                        case 4:
                            greitis = Integer.parseInt(token.nextToken());
                            tokenNr++;
                            break;
                    }
                }
                sarasas.add(new Masina(marke,modelis,variklioTuris,greitis));
            }
        } catch(IOException e){
            System.out.println("Kazkas negerai su failu");
        }
        System.out.println("Masinu sarasas:");
        sarasas.forEach((n) -> n.getDuomenys()); //Isveda visu masinu sarasa
    }
    
    public void var1(){ //Vidutinio visu automobiliu varikliu turio skaiciavimas
        int sk = sarasas.size();
        double suma = 0;
        for(int i = 0; i < sk; i++)
            suma += sarasas.get(i).getVariklioTuris();
        System.out.println("\nVidutinis visu automobiliu varikliu turis: "+suma / sk);
    }
    
    public void var2(){ //Paieska nurodzius marke ir modeli
        Scanner klaviatura = new Scanner(System.in);
        String marke,modelis;
        int sk = sarasas.size();
        System.out.println("Nurodykite automobilio marke:");
        marke = klaviatura.nextLine();
        System.out.println("Nurodykite automobilio modeli:");
        modelis = klaviatura.nextLine();
        for(int i = 0; i < sk; i++)
            if(sarasas.get(i).getMarke().equals(marke) && sarasas.get(i).getModelis().equals(modelis))
                sarasas.get(i).getDuomenys();
    }
    
    public void var3(){ //Paieska nurodzius marke, modeli ir maksimalu greiti
        Scanner klaviatura = new Scanner(System.in);
        String marke,modelis;
        int greitis;
        int sk = sarasas.size();
        System.out.println("Nurodykite automobilio marke:");
        marke = klaviatura.nextLine();
        System.out.println("Nurodykite automobilio modeli:");
        modelis = klaviatura.nextLine();
        System.out.println("Nurodykite automobilio maksimalu greiti:");
        greitis = klaviatura.nextInt();
        for(int i = 0; i < sk; i++)
            if(sarasas.get(i).getMarke().equals(marke) && sarasas.get(i).getModelis().equals(modelis) && sarasas.get(i).getGreitis() == greitis)
                sarasas.get(i).getDuomenys();
    }
    
    public void var4(){ //Automobiliu saraso rikiavimas pagal marke ir maksimalu greiti
        Collections.sort(sarasas, Comparator.comparing(Masina::getModelis).thenComparing(Masina::getGreitis)); //Pirma palygina modelius ir surikuoja, o poto palygina ir surikuoja pagal greiti
        
        System.out.println("Masinu sarasas po rikiavimo:");
        sarasas.forEach((n) -> n.getDuomenys()); //Isveda visu masinu sarasa
    }
    
}

public class RalioLenktynes {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        Ralis r = new Ralis();
        r.getSarasas();
        while(true){
            System.out.println("\n1. Vidutinio visu automobiliu varikliu turio skaiciavimas\n2. Paieska nurodzius marke ir modeli\n3. Paieska nurodzius marke, modeli ir maksimalu greiti\n4. Automobiliu saraso rikiavimas pagal marke ir maksimalu greiti\n5. Baigti darba");
            Scanner klaviatura = new Scanner(System.in);
            int punktas = klaviatura.nextInt();
            switch(punktas){
                case 1: 
                    r.var1();
                    break;
                case 2:
                    r.var2();
                    break;
                case 3:
                    r.var3();
                    break;
                case 4:
                    r.var4();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\nNetinkamas pasirinkimas");
                    break;
            }
        }
    }
}