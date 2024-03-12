package trabalhoflavio;

import javax.swing.JOptionPane;
import com.db4o.ObjectContainer;
import com.db4o.Db4o;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.util.Scanner;


public class TrabalhoFlavio {
    
    public static void main(String[] args) {
        ObjectContainer db;
        db = Db4o.openFile("meubanco.db0");
        
        Lista lista = new Lista();
        Scanner scanner = new Scanner(System.in);
        
        boolean continuar;   
        String escolha;
        int escolhaC;
        continuar = true;
        
        while (continuar == true) {
            
            
            escolha = JOptionPane.showInputDialog(null, ""
                    + "---------- Menu --------------"
                    + "\n1 - Adicionar Anime "
                + "\n2 - Adicionar Filme"
                + "\n3 - Atualizar"
                + "\n4 - Deletar"
                + "\n5 - Visualizar a Lista"
                + "\n6 - Sair do Programa"
                + "");
            
            
            escolhaC = Integer.parseInt(escolha);
            System.out.println("");
        
        switch (escolhaC) {
            case 1:
                cadastrarAnime(db, scanner, lista);
                break;
            case 2:
                cadastrarFilme(db, scanner, lista);
                break;
                
            case 3:
                atualizarLista(db, scanner, lista);
                break;
                
            case 4:
                deletarLista(db, scanner, lista);
                break;
                
            case 5:
                visualizarLista(db, scanner);
                break;
                
            case 6:
                continuar = false;
                break;
        }          
        }
    }
    
    public static void cadastrarAnime (ObjectContainer db, Scanner scanner, Lista lista) 
    { 
            String nota;
            int notaTemporaria;
            
            lista.setopcao("Anime");
            
            lista.setnomeLista(JOptionPane.showInputDialog(null, "Digite o nome do Anime :" ));
            lista.setGenero(JOptionPane.showInputDialog(null, "Digite o genêro do Anime :" ));
            lista.setAutor(JOptionPane.showInputDialog(null, "Digite o nome do Autor :" ));

            nota = JOptionPane.showInputDialog(null, "Digite a nota do Anime :" );
            notaTemporaria = Integer.parseInt(nota);    
            
            lista.setNota(notaTemporaria);
      
            db.store(lista);
    }
    
    public static void cadastrarFilme (ObjectContainer db, Scanner scanner, Lista lista) 
    {
            String nota;
            int notaTemporaria;
            
            lista.setopcao("Filme");
            
            lista.setnomeLista(JOptionPane.showInputDialog(null, "Digite o nome do Filme :" ));
            lista.setGenero(JOptionPane.showInputDialog(null, "Digite o genêro do Filme :" ));
            lista.setAutor(JOptionPane.showInputDialog(null, "Digite o nome do Autor :" ));
            
            nota = JOptionPane.showInputDialog(null, "Digite a nota do Filme :" );
            notaTemporaria = Integer.parseInt(nota); 
            
            lista.setNota(notaTemporaria);
            
            db.store(lista);  
    }
    
    public static void atualizarLista (ObjectContainer db, Scanner scanner, Lista lista) 
    {
        String nome;
        
        nome = JOptionPane.showInputDialog(null, "Digite o nome da Obra a ser atualizada");
       
        String nota;
        int notaTemporaria;
        
        Query query = db.query();
        query.constrain(Lista.class);
        query.descend("nomeLista").constrain(nome);
        ObjectSet<Lista> result = query.execute();
        
        if (result.hasNext()) 
        {
            Lista conteudo = result.next();
            
            lista.setopcao(JOptionPane.showInputDialog(null, "Digite se é um Anime ou Filme :" ));
            lista.setnomeLista(JOptionPane.showInputDialog(null, "Digite o nome da Obra :" ));
            lista.setGenero(JOptionPane.showInputDialog(null, "Digite o genêro da Obra :" ));
            lista.setAutor(JOptionPane.showInputDialog(null, "Digite o nome do Autor :" ));

            nota = JOptionPane.showInputDialog(null, "Digite a nota da Obra :" );
            notaTemporaria = Integer.parseInt(nota); 
            
            lista.setNota(notaTemporaria);
            
            db.store(lista);
            
            JOptionPane.showMessageDialog(null, "Dados da Obra atualizada com sucesso");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Obra não encontrada");
        }
    }
    
    public static void deletarLista (ObjectContainer db, Scanner scanner, Lista lista)
    {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da Obra a ser atualizada");
        
        Query query = db.query();
        query.constrain(Lista.class);
        query.descend("nomeLista").constrain(nome);
        ObjectSet<Lista> result = query.execute();
        
        if (result.hasNext())
        {
            lista = result.next();
            db.delete(lista);
            JOptionPane.showMessageDialog(null, "Obra deletada com sucesso");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Obra não encontrada");
        }
    }

    public static void visualizarLista (ObjectContainer db, Scanner scanner) 
    {
        ObjectSet<Lista> lista = db.query(Lista.class);
        
        while (lista.hasNext())
        {
            Lista umConteudo = lista.next();
            
            JOptionPane.showMessageDialog(null, "-----Um Contéudo------"
                    + "\nNome do Contéudo : " + umConteudo.getnomeLista()
                    + "\nO contéudo é um : " + umConteudo.getopcao()
                    + "\nO género do contéudo é : " + umConteudo.getGenero()
                    + "\nO autor do contéudo é : " + umConteudo.getAutor()
                    + "\nA nota do contéudo é : " + umConteudo.getNota());
          
        }   
    }
}   
