import java.util.*;

public class Trainer{
    private ArrayList<Pokemon> bagOfPokemon;
    private ArrayList<Berry> bagOfBerry;
    private Scanner sc;
    private String name;

    public Trainer(String name){
        bagOfPokemon = new ArrayList<Pokemon>();
        bagOfPokemon.add(new Koiking("My Koiking"));

        bagOfBerry = new ArrayList<Berry>();
        bagOfBerry.add(new LeppaBerry("Leppa Berry"));
        bagOfBerry.add(new LeppaBerry("Leppa Berry"));

        sc = new Scanner(System.in);
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    public void play(){
        String cmd = "";

        do{
            System.out.print("\n\nEnter cmd: ");
            cmd = sc.nextLine();

            if(cmd.equals("print")){
                System.out.println("\n\nPokemon or Berry ?");
                System.out.print("Enter cmd: ");
                cmd = sc.nextLine();

                if(cmd.equals("pokemon")){
                    System.out.println("\n\nPokemon in bag: ");
                    printPokemon(bagOfPokemon);
                }else if(cmd.equals("berry")){
                    System.out.println("\n\nBerry in bag: ");
                    printBerry(bagOfBerry);
                }
                
            }else if(cmd.equals("catch")){
                catchPokemon();
            }else if(cmd.equals("feed")){
                feedPokemon();
            }else if(cmd.equals("harvest")){
                harvestBerry();
            }
        }
        while(!cmd.equals("q"));
    }

    public void catchPokemon(){
        System.out.println("Catch pokemons");
        ArrayList<Pokemon> pokemons = PokemonRandomizer.getPokemons(5);
        
        System.out.println("\n\nPokemons around you: ");
        int no = 0;
        printPokemon(pokemons);

        System.out.println("\n\nSelect Pokemon around you(num) or run away(-1): ");
        no = sc.nextInt();
        if(no < 0){
            sc.nextLine();
            return;
        }

        Pokemon wildPokemon = pokemons.get(no);

        System.out.println("\n\nPokemon in bag: ");
        printPokemon(bagOfPokemon);
        System.out.println("Select Pokemon in bag(num): ");

        no = sc.nextInt();
        Pokemon myPokemon = bagOfPokemon.get(no);

        boolean isWin = false;
        do{
            myPokemon.attack(wildPokemon);
            if(wildPokemon.getHp() == 0){
                isWin = true;
                break;
            }

            wildPokemon.attack(myPokemon);
            if(myPokemon.getHp() == 0){
                isWin = false;
                break;
            }
        }while(true);

        if(isWin){
            bagOfPokemon.add(wildPokemon);
            System.out.println("You catch " + wildPokemon.getName() + " !");
        }else{
            System.out.println(wildPokemon.getName() + " survive your catch !");
        }
        sc.nextLine();
        
    }
    
    public void printPokemon(ArrayList<Pokemon> pokemons){
        int number = 0;
        for(Pokemon p: pokemons){
            System.out.println("" + number + " " + p + " HP: " + p.getHp() + ", Type: " + p.getType() + ", Gender: " + p.getGender());
            number++;
        }
    }

    public void printBerry(ArrayList<Berry> berries){
        int number = 0;
        for(Berry b: berries){
            System.out.println("" + number + " " + b + ": HP +" + b.getHealPoints());
            number++;
        }
        
    }

    public void feedPokemon(){
        int BerryNo = 0;
        int PokeNo = 0;

        System.out.println("\n\nFeed pokemons");
        System.out.println("Select Berry in bag(num): ");

        if(bagOfBerry.isEmpty()){
            System.out.println("There's no berry in your bag !");
            return;
        }

        printBerry(bagOfBerry);
        BerryNo = sc.nextInt();
        if(BerryNo < 0){
            sc.nextLine();
            return;
        }
        Berry myBerry = bagOfBerry.get(BerryNo);

        System.out.println("\n\nPokemon in bag: ");
        printPokemon(bagOfPokemon);
        System.out.println("Select Pokemon in bag(num): ");

        PokeNo = sc.nextInt();
        sc.nextLine();
        Pokemon myPokemon = bagOfPokemon.get(PokeNo);

        myBerry.heal(myPokemon);
        bagOfBerry.remove(BerryNo);
    }

    public void harvestBerry(){
        System.out.println("Harvest berries");
        ArrayList<Pokemon> berries = BerryRandomizer.getBerries(3);
        

    }

    public ArrayList<Pokemon> getBagOfPokemon(){
        return bagOfPokemon;
    }

    public ArrayList<Berry> getBagOfBerry(){
        return bagOfBerry;
    }
}
