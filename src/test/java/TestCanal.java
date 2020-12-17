import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestCanal {


    @Test
    public void ecrireMessageOK() throws ActionNonAutoriseeException {
        // Etape 1 : préparation des objets
        Canal c = new Canal();

        Message m = new Message();
        m.setDestinaire(c);
        m.setTexte("Bonne fêtes de fin d'année !");

        Utilisateur utilisateur = new Utilisateur();
        c.getMapping_role_utilisateurs().put(new Rôle("Membre"), Arrays.asList(utilisateur));
        c.getMapping_role_habilitations().put(new Rôle("Membre"), Arrays.asList(Habilitation.ECRITURE));

        // Etape 2 : appel de la méthode testée
        c.ecrireMessage(utilisateur, m);

        // Etape 3 test du retour
        List<Message> historiques = c.getHistoriques();
        Assert.assertEquals(1, historiques.size());
        System.out.println(historiques.get(0).getTexte());
    }

    @Test(expected = ActionNonAutoriseeException.class)
    public void ecrireMessageKO() throws ActionNonAutoriseeException {

        // Etape 1 : préparation des objets
        Canal c = new Canal();

        Message m = new Message();
        m.setDestinaire(c);
        m.setTexte("Bonne année 2021 !");

        Utilisateur utilisateur = new Utilisateur();

        // Etape 2 : appel de la méthode testée

        c.ecrireMessage(utilisateur, m);

        // Etape 3 test du retour
        fail("L'exception aurait du être levée, on ne doit pas passer ici !");

        // Etape 3 test du retour = si on arrive ici, le test est concluant
    }

    @Test
    public void canalCompareToTest(){
        ServeurDiscussion serveur = new ServeurDiscussion();
        Canal canal1 = new Canal();
        canal1.setOrdre(1);
        Canal canal2 = new Canal();
        canal2.setOrdre(2);


        assertEquals(canal1.compareTo(canal1), 0);
        assertTrue(canal1.compareTo(canal2) < 0);
        assertTrue(canal2.compareTo(canal1) > 0);
    }
}
