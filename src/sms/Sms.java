package sms;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
public class Sms {
    private static Map<String, Felhasznalok> felh = new TreeMap<>();
    private static int Status = 0;
    private static String asd = "";

    public static void main(String[] args) {
        felh.put("dsa", new Felhasznalok("dsa@dsa.com", "dsa"));
        log(0);
    }
    private static void log(int menu){
        Scanner scanner = new Scanner(System.in);
        if (Status == 0){   //Login
            if(menu == 0){
                System.out.println("1. Bejelentkezés\n2. Regisztráció\n3. Kilépés");
                int a = 0;
                try{
                    a = scanner.nextInt();
                }
                catch(Exception e){
                    System.out.println("ÉRVÉNYTELEN érték");
                }
                switch(a){
                        case 1: log(1); break;
                        case 2: log(2); break;
                        case 3: System.exit(0); break;
                    }
            }
            else if(menu== 1){
                System.out.println("Írd be az e-mail címed a bejelentkezéshez\n Email:");
                String email = scanner.nextLine();           
                    System.out.println(belep(email) ? "" : "Ez az email cím még nincs regisztrálva\n");
                log(0);
            }
            else if(menu == 2){
                System.out.println("Adj meg egy email címet a regisztrációhoz:\nEmail:");
                reg();
                log(1);
            }
        }
        else if(Status == 1){
            //clear
            if(menu == 0){
                System.out.println("Válaszd ki, hogy mit szeretnél tenni:\n1. Üzenetek olvasása\n2. Új üzenet küldése\n3. Kijelentkezés");
                    int a = scanner.nextInt();
                    log(a);
            }
            else if(menu == 1){
                felh.get("asd").ujFogadott(new Uzenet("asd", "asdasd", "dsa"));
                felh.get("asd").getUzenetek();
                log(0);
            }
            else if(menu == 2){
                System.out.println("Add meg a címzett email címét: ");
                String cimzett = scanner.next();
                if(!felh.containsKey(cimzett)){
                    System.out.println("Nem létező e-mail");
                    log(2);
                }
                System.out.println("Írj 1 üzenetet: ");
                String uzenet = scanner.next();
                felh.get("asd").ujKuldott(new Uzenet(cimzett, uzenet, asd));
                log(0);
            }
            else if(menu == 3){
                Status = 0;
                System.out.println("Sikeresen kijelentkeztél!\n");
                log(0);
            }
        }
    }
    
    private static void reg(){
        Scanner scanner = new Scanner(System.in);
        String email = scanner.next();
        System.out.println("Adj meg egy becenevet: ");
        String nick = scanner.next();
        felh.put(email, new Felhasznalok(email, nick));
    }
    
    private static boolean belep(String _email){
        if(felh.containsKey(_email)){
            asd = _email;
            Status = 1;
            return true;
        }
      return false;
    }
}

class Felhasznalok{
    private String email;
    private String name;
    private Set<Uzenet> bejovoUzenetek = new HashSet<>();
    private Set<Uzenet> kimenoUzenetek = new HashSet<>();
    
    public Felhasznalok( String email, String name) {
        this.name = name;
        this.email = email;
    }
    
    public void ujFogadott(Uzenet m){
        bejovoUzenetek.add(m);
    }
    
    public void ujKuldott(Uzenet m){
        kimenoUzenetek.add(m);
    }
    
    public void getUzenetek(){
        System.out.println("Bejövőüzenet");
        for(Uzenet elem: bejovoUzenetek){
            System.out.println(elem);
        }
        System.out.println("\nKimenőüzenet\n1");
        for(Uzenet elem: kimenoUzenetek){
            System.out.println(elem);
        }
    }
    
    @Override
    public String toString(){
        return "Email: " + email + " | Név: "+ name;
    }
}

class Uzenet{
    private String felado;
    private String uzenet;
    private String cimzett;

    public Uzenet(String felado, String uzenet, String cimzett) {
        this.felado = felado;
        this.uzenet = uzenet;
        this.cimzett = cimzett;
    }

    public String getFelado() {
        return felado;
    }

    public String getCimzett() {
        return cimzett;
    }
    
    public String getUzenet() {
        return uzenet;
    }

    @Override
    public String toString(){
        return "Felado: " + felado + "\nCímzett: " + cimzett + "\nÜzenet: "+uzenet;
    }
}