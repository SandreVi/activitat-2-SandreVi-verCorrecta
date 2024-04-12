package cat.urv.deim;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.PosicioForaRang;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LlistaPersones {

    private LlistaAbstracta<Persona> llista;

    // Constructor que crea una llista de persones buida del tipus especificat en el boolea (ordenada o no)
    public LlistaPersones(boolean ordenada) {
        if (ordenada) this.llista = new LlistaOrdenada<>();
        else this.llista = new LlistaNoOrdenada<>();
    }

    //Constructor que crea una llista del tipus especificat i hi carrega totes les dades del fitxer
    public LlistaPersones(boolean ordenada, String filename) {
        this(ordenada);

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] camps = line.split(",");

                Persona persona = new Persona(
                    Integer.parseInt(camps[0].trim()),  // id_persona
                    Integer.parseInt(camps[1].trim()),  // edat
                    camps[2].trim(),                     // nom
                    camps[3].trim(),                     // cognom
                    Integer.parseInt(camps[4].trim()),  // pes
                    Integer.parseInt(camps[5].trim())   // alçada
                );

                llista.inserir(persona);
            }
        } catch (IOException e) {
        } catch (NumberFormatException e) {}
    }

    //Afegeix una nova persona a la llista que tenim inicialitzada
    public void inserir(Persona p) {
        llista.inserir(p);
    }

    //Metode per a consultar una persona a partir de la seva posicio
    public Persona consultar(int pos) throws PosicioForaRang {
        return llista.consultar(pos);
    }

    //Metode per a saber si una persona existeix a l'estructura
    public boolean existeix(Persona p) {
        return llista.existeix(p);
    }

    //Metode per a esborrar una persona de l'estructura
    public void esborrar(Persona e) throws ElementNoTrobat {
        llista.esborrar(e);
    }

    //Metode que ens indica en quina posicio de la llista hi ha la persona que es passa per parametre
    public int posicioPersona(Persona persona) throws ElementNoTrobat {
        return llista.buscar(persona);
    }

    //Metode per a saber si la llista esta buida
    public boolean esBuida() {
        return llista.esBuida();
    }

    //Metode per a saber el nombre d'elements de la llista
    public int numElements() {
        return llista.numElements();
    }

   //Metode per a obtenir un array amb tots els elements de la llista
    public Persona[] elements() {
        Object[] objs = llista.elements();
        Persona[] personesLlista = new Persona[llista.numElements()];
        for (int pos = 0; pos < llista.numElements(); pos++) personesLlista[pos] = (Persona) objs[pos];

        return personesLlista;
    }

    //Metode per a obtenir una persona a partir del seu id
    public Persona buscarPerId(int id) throws ElementNoTrobat {
        for (Persona p : elements()) {
            if (p != null) {
                if (p.getId_persona() == id) return p;
            }
        }

        throw new ElementNoTrobat();
    }

    //Metode per a obtenir un array amb totes les persones que tenen un pes inferior al valor que es passa per parametre
    public Persona[] personesPesInferior(int pes) {
        int comptador = 0;
        Persona[] llistaPersones = new Persona[llista.numElements()];

        for (Persona p : elements()) {
            if (p != null && p.getPes() < pes) {
                llistaPersones[comptador] = p;
                comptador++;
            }
        }

        Persona[] personesPesInferior = new Persona[comptador];
        System.arraycopy(llistaPersones, 0, personesPesInferior, 0, comptador);

        return personesPesInferior;
    }
}
