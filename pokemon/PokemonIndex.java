import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PokemonIndex extends JFrame{
    private ArrayList<Pokemon> pokemon;

    public PokemonIndex(ArrayList<Pokemon> pokemon){
        super("All Pokemon Index");
        this.pokemon = pokemon;

        setBounds(100, 100, 432, 195);
        JPanel panel = new JPanel();

        try{
            int i = 0;
            for(i = 0; i < pokemon.size(); i++){
                ImageIcon image = new ImageIcon(pokemon.get(i).getIMAGE());
                Image img = image.getImage().getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
                ImageIcon pokeImg = new ImageIcon(img);

                panel.add(new JLabel(pokeImg));
                panel.add(new JLabel("Name: " + pokemon.get(i).getName()));
                panel.add(new JLabel(", HP: Random"));
                panel.add(new JLabel(", Type: " + pokemon.get(i).getType()));
                panel.add(new JLabel(", Gender: " + pokemon.get(i).getGender()));
                panel.add(new JLabel("   "));
            }
            
        }catch(java.lang.NullPointerException exception){
            System.out.println("Null Pointer Exception (ERROR)");
        }

        JScrollPane scroll = new JScrollPane(panel);
        getContentPane().add(scroll, BorderLayout.CENTER);
        setLocationRelativeTo(panel);
        setVisible(true);
    }
}