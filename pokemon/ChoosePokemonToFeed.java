import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChoosePokemonToFeed extends JFrame{
    private ArrayList<Pokemon> pokemon;
    private Berry berry;

    public ChoosePokemonToFeed(ArrayList<Pokemon> pokemon, Berry berry){
        super("Choose Pokemon");
        this.pokemon = pokemon;
        this.berry = berry;
        
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));

        try{
            int i = 0;
            for(i = 0; i < pokemon.size(); i++){
                JLabel hp = new JLabel("HP: " + pokemon.get(i).getHp());

                ImageIcon image = new ImageIcon(pokemon.get(i).getIMAGE());
                Image img = image.getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
                ImageIcon pokemonImg = new ImageIcon(img);

                p1.add(hp);
                p3.add(new JLabel(pokemonImg));
            }

            JButton feedButton1 = new JButton("Feed");
            p2.add(feedButton1);

            c.add(p3, BorderLayout.WEST);
            c.add(p1, BorderLayout.CENTER);
            c.add(p2, BorderLayout.EAST);

            feedButton1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int currentHP = pokemon.get(0).getHp() + berry.getHealPoints();
                    pokemon.get(0).setHp(currentHP);
                }
            });

        }catch(java.lang.NullPointerException exception){
            System.out.println("Null Pointer Exception (ERROR)");
        }
        
        setSize(350, 250);
        setLocationRelativeTo(c);
        setVisible(true);
    }
}