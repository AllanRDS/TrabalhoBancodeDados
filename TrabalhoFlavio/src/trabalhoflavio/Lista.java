package trabalhoflavio;

public class Lista {   
    private String nomeLista;
    private String opcao;
    private String genero;
    private String autor;
    private int nota;
    
    public Lista () {
    }

    public String getnomeLista() {
        return nomeLista;
    }

    public void setnomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public String getopcao() {
        return opcao;
    }

    public void setopcao(String opcao) {
        this.opcao = opcao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
