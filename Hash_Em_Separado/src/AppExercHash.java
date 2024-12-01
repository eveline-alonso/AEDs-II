import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class AppExercHash {
    
    static TabelaHash<LocalDate, ABB<Evento>> eventosPorData = new TabelaHash<>(40);

    public static void lerMedalhistas(String arquivo) throws FileNotFoundException{
        Scanner arquivoDados = new Scanner(new File(arquivo));
        arquivoDados.nextLine();
        //name,medal_type,medal_date,gender,birth_date,country,discipline,event
        //0   , 1        , 2        , 3    , 4        , 5     , 6        , 7
        while (arquivoDados.hasNextLine()) {
            String[] linha = arquivoDados.nextLine().split(",");
            
    
            LocalDate dataEvento = LocalDate.parse(linha[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
            Evento novoEvento = new Evento(linha[7], linha[6], dataEvento);
            
            ABB<Evento> eventosDeUmaData; 
           
            try {
                eventosDeUmaData = eventosPorData.pesquisar(dataEvento);    
            } catch (IllegalStateException  | NoSuchElementException nex) {
                eventosDeUmaData = new ABB<>();
                eventosPorData.inserir(dataEvento, eventosDeUmaData);
            }
            

            try{
                novoEvento = eventosDeUmaData.pesquisar(novoEvento);
            }
            catch(NoSuchElementException re){
                eventosDeUmaData.adicionar(novoEvento);
            }
    
            
           
            


            // try{
            //     novoEvento = eventosDaData.pesquisar(novoEvento);
            //     novoEvento.inserirMedalhista(novoMedalhista);
            // }catch(NoSuchElementException nex){
            //     eventosDaData.adicionar(novoEvento);
            //     novoEvento.inserirMedalhista(novoMedalhista);
            // }

            // eventosDaData.adicionar(novoEvento);

            

        }
        arquivoDados.close();
    }
    
    public static void main(String[] args) throws IOException {
        LocalDate dataBase = LocalDate.of(2024,07,27);
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        LinkedList<LocalDate> datas = new LinkedList<>();
        String entraPub = "pub.in";
        String saidaPub = "pub.out";

        String entraPri = "pri.in";
        String saidaPri = "pri.out";

        FileWriter arqEntra = new FileWriter(entraPri);
        FileWriter arqSaida = new FileWriter(saidaPri);

        Random rand = new Random(32);

        for (int i = 0; i < 15; i++) {
            datas.add(dataBase);
            dataBase = dataBase.plusDays(1);
        }
        

        try {
            lerMedalhistas("medallists.csv");
            int i=0;
            Set<LocalDate> datasConj = new HashSet<>();
            while(i<10){
                int pos = rand.nextInt(datas.size());
                LocalDate dataArq = datas.get(pos);
                if(!datasConj.contains(dataArq)){
                    arqEntra.append(formatoData.format(dataArq)+"\n");    
                    ABB<Evento> arvore = eventosPorData.pesquisar(dataArq);
                    System.out.println("Eventos do dia "+formatoData.format(dataArq));
                    arvore.caminhamentoEmOrdem();
                    System.out.println();
                    i++;
                    datasConj.add(dataArq);
                }
                
            }    
            arqEntra.close();
        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
