import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner input = new Scanner(System.in);
        //массив с буквами
        char[] alf = new char[]{'А','Б','В','Г','Д','Е','Ж','З','И','К'};
        //создание поля
        String[][] pole = new String[12][12];
        String[][] polevra = new String[12][12];
        String[][] koravra = new String[12][12];
        for (int i = 0; i<11;i++){
            for (int l = 0; l<11;l++){
                if (i==0 & l!=0){
                    pole[0][l] = alf[l-1] + "";
                    polevra[0][l] = alf[l-1] + "";
                    koravra[0][l] = alf[l-1] + "";
                } else if (l==0 & i!=0) {
                    if (i<10){
                        pole[i][0] = i + " ";
                        polevra[i][0] = i + " ";
                        koravra[i][0] = i + " ";
                    } else {
                        pole[i][0] = i + "";
                        polevra[i][0] = i + "";
                        koravra[i][0] = i + "";
                    }
                } else {
                    pole[i][l] = "◯";
                    polevra[i][l] = "◯";
                    koravra[i][l] = "◯";
                }
            }
        }
        for (int i = 0;i<12;i++){
            pole[11][i] = " ";
            pole[i][11] = " ";
            polevra[11][i] = " ";
            polevra[i][11] = " ";
            koravra[11][i] = " ";
            koravra[i][11] = " ";
        }
        koravra = botkor(polevra,alf);
        //первый вывод
        for (int i = 0; i<11;i++){
            for (int l = 0; l<11;l++){
                if (l==1 & i==0){
                    System.out.print(" "+pole[i][l]+" ");
                } else {
                    System.out.print(pole[i][l]+" ");
                }

            }
            System.out.println();
        }
        viv(koravra);
        for (int i=1;i<5;i++) {
            //распологаем однопалубные корабли
            System.out.print("Выберите, где расположить " + i + "-й однопалубный корабль (Пример ввода: А1): ");
            String qwe = input.next();
            char[] kor1 = new char[3];
            kor1 = qwe.toCharArray();
            int temp;
            if (kor1.length == 2){
                temp = kor1[1]-'0';
            } else {
                temp = 10;
            }
            if (pole[temp][wheAlf(alf, kor1[0])] != "□" &&  mainprov(kor1[0],kor1[0],temp,temp,alf,pole)) {
                pole[temp][wheAlf(alf, kor1[0])] = "□";
                viv(pole);
            } else {
                i--;
                System.out.println("Корабль расположен неправильно");
            }
        }
        for (int i=1;i<4;i++){
            //распологаем двупалубные корабли
            System.out.print("Выберите, где расположить " + i + "-й двупалубный корабль (Пример ввода: А1 А2): ");
            int temp=0;
            int temp1=0;
            String qwe = input.next();
            String qwe1 = input.next();
            char[] kor1 = new char[3];
            char[] kor2 = new char[3];
            kor1 = qwe.toCharArray();
            kor2 = qwe1.toCharArray();
            if (kor1.length == 2){
                temp = kor1[1]-'0';
            } else {
                temp = 10;
            }
            if (kor2.length == 2){
                temp1 = kor2[1]-'0';
            } else {
                temp1 = 10;
            }
            boolean why = false;
            if (pole[temp][wheAlf(alf, kor1[0])] != "□"    &&    pole[temp1][wheAlf(alf, kor2[0])] != "□"   &&      prov2(kor1[0],kor2[0],temp,temp1,alf)  &&   mainprov(kor1[0],kor2[0],temp,temp1,alf,pole)) {
                pole[temp][wheAlf(alf, kor1[0])] = "□";
                pole[temp1][wheAlf(alf, kor2[0])] = "□";
                viv(pole);
            }   else {
                i--;
                System.out.println("Корабль расположен неправильно");
            }
        }
        for (int i=1;i<3;i++){
            //распологаем трёхпалубный корабль
            System.out.print("Выберите, где расположить " + i + "-й трёхпалубный корабль (Пример ввода: А1 А3): ");
            int temp=0;
            int temp1=0;
            String qwe = input.next();
            String qwe1 = input.next();
            char[] kor1 = new char[3];
            char[] kor2 = new char[3];
            kor1 = qwe.toCharArray();
            kor2 = qwe1.toCharArray();
            if (kor1.length == 2){
                temp = kor1[1]-'0';
            } else {
                temp = 10;
            }
            if (kor2.length == 2){
                temp1 = kor2[1]-'0';
            } else {
                temp1 = 10;
            }
            boolean why = false;
            if (pole[temp][wheAlf(alf, kor1[0])] != "□"    &&    pole[temp1][wheAlf(alf, kor2[0])] != "□"   &&      prov3(kor1[0],kor2[0],temp,temp1,alf)  &&   mainprov(kor1[0],kor2[0],temp,temp1,alf,pole)) {
                pole[temp][wheAlf(alf, kor1[0])] = "□";
                if (kor1[0]==kor2[0]){
                    pole[(temp1+temp)/2][wheAlf(alf, kor1[0])] = "□";
                } else {
                    pole[temp][(wheAlf(alf, kor1[0])+wheAlf(alf, kor2[0]))/2] = "□";
                }
                pole[temp1][wheAlf(alf, kor2[0])] = "□";
                viv(pole);
            }   else {
                i--;
                System.out.println("Корабль расположен неправильно");
            }
        }
        for (int i=1;i<2;i++){
            //распологаем четырёхпалубный корабль
            System.out.print("Выберите, где расположить четырёхпалубный корабль (Пример ввода: А1 А4): ");
            int temp=0;
            int temp1=0;
            String qwe = input.next();
            String qwe1 = input.next();
            char[] kor1 = new char[3];
            char[] kor2 = new char[3];
            kor1 = qwe.toCharArray();
            kor2 = qwe1.toCharArray();
            if (kor1.length == 2){
                temp = kor1[1]-'0';
            } else {
                temp = 10;
            }
            if (kor2.length == 2){
                temp1 = kor2[1]-'0';
            } else {
                temp1 = 10;
            }
            boolean why = false;
            if (pole[temp][wheAlf(alf, kor1[0])] != "□"    &&    pole[temp1][wheAlf(alf, kor2[0])] != "□"   &&      prov4(kor1[0],kor2[0],temp,temp1,alf)  &&   mainprov(kor1[0],kor2[0],temp,temp1,alf,pole)) {
                pole[temp][wheAlf(alf, kor1[0])] = "□";
                if (kor1[0]==kor2[0]){
                    pole[(temp1+temp)/2][wheAlf(alf, kor1[0])] = "□";
                    pole[(temp1+temp)/2+1][wheAlf(alf, kor1[0])] = "□";
                } else {
                    pole[temp][(wheAlf(alf, kor1[0])+wheAlf(alf, kor2[0]))/2] = "□";
                    pole[temp][(wheAlf(alf, kor1[0])+wheAlf(alf, kor2[0]))/2+1] = "□";
                }
                pole[temp1][wheAlf(alf, kor2[0])] = "□";
                viv(pole);
            }   else {
                i--;
                System.out.println("Корабль расположен неправильно");
            }
        }
        System.out.print("Игра начинается через... ");
        TimeUnit.SECONDS.sleep(3);
        System.out.print("3.. ");
        TimeUnit.SECONDS.sleep(3);
        System.out.print("2... ");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("1... ");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Игра начата... ");

    }
    public static int wheAlf(char[] a,char b){
        int temp = 0;
        for (int i=0;i<10;i++){
            if (a[i]==b){
                temp = i+1;
            }
        }
        return (temp);
    }
    public static void viv(String[][] pole) {
        //первый вывод
        for (int i = 0; i<11;i++){
            for (int l = 0; l<11;l++){
                if (l==1 & i==0){
                    System.out.print(" "+pole[i][l]+" ");
                } else {
                    System.out.print(pole[i][l]+" ");
                }

            }
            System.out.println();
        }
    }
    public static boolean prov2(char a,char b,int a1, int b1,char[] c){
        if (a == b  &&  (a1-b1) * (a1-b1) == 1){
            return true;
        } else {
            return  (a1 == b1  &&  (wheAlf(c,a)-wheAlf(c,b)) * (wheAlf(c,a)-wheAlf(c,b)) == 1);
        }

    }
    public static boolean mainprov(char a, char b, int a1, int b1, char[] alf, String[][] pole){
        boolean lo = true;
        if (a1>b1){
            if (pole[b1-1][wheAlf(alf,a)].equals("□")){
                lo = false;
            }
            for (int i = b1; i < a1+1;i++){
                if (pole[i][wheAlf(alf,a)-1].equals("□")  ||  pole[i][wheAlf(alf,a)+1].equals("□")){
                    lo = false;
                }
            }
            if (pole[a1+1][wheAlf(alf,a)].equals("□")){
                lo = false;
            }
        } else if (a1<b1){
            if (pole[a1-1][wheAlf(alf,a)].equals("□")){
                lo = false;
            }
            for (int i = a1; i < b1+1;i++){
                if (pole[i][wheAlf(alf,a)-1].equals("□")  ||  pole[i][wheAlf(alf,a)+1].equals("□")){
                    lo = false;
                }
            }
            if (pole[b1+1][wheAlf(alf,a)].equals("□")){
                lo = false;
            }
        } else if (wheAlf(alf,a)<wheAlf(alf,b)){
            if (pole[a1][wheAlf(alf,a)-1].equals("□")){
                lo = false;
            }
            for (int i = wheAlf(alf,a); i < wheAlf(alf,b)+1;i++){
                if (pole[a1-1][i].equals("□")  ||  pole[a1+1][i].equals("□")){
                    lo = false;
                }
            }
            if (pole[a1][wheAlf(alf,b)+1].equals("□")){
                lo = false;
            }
        } else {
            if (pole[a1][wheAlf(alf,b)-1].equals("□")){
                lo = false;
            }
            for (int i = wheAlf(alf,b); i < wheAlf(alf,a)+1;i++){
                if (pole[a1-1][i].equals("□")  ||  pole[a1+1][i].equals("□")){
                    lo = false;
                }
            }
            if (pole[a1][wheAlf(alf,a)+1].equals("□")){
                lo = false;
            }
        }
        return lo;
    }
    public static boolean prov3(char a,char b,int a1, int b1,char[] c){
        if (a == b  && ( a1-b1==2 || b1-a1==2 )){
            return true;
        } else {
            return  (a1 == b1  && ( wheAlf(c,a)-wheAlf(c,b) ==2 || wheAlf(c,b)-wheAlf(c,a) == 2 ));
        }

    }
    public static boolean prov4(char a,char b,int a1, int b1,char[] c){
        if (a == b  && ( a1-b1==3 || b1-a1==3 )){
            return true;
        } else {
            return  (a1 == b1  && ( wheAlf(c,a)-wheAlf(c,b) ==3 || wheAlf(c,b)-wheAlf(c,a) == 3 ));
        }

    }
    public static String[][] botkor(String[][] a,char[] alf){
        /*polev[1][1]="□";
        polev[3][1]="□"; polev[3][2]="□";
        polev[1][5]="□";
        polev[1][9]="□"; polev[2][9]="□"; polev[3][9]="□"; polev[4][9]="□";
        polev[8][3]="□";
        polev[10][3]="□"; polev[10][4]="□"; polev[10][5]="□";
        polev[3][6]="□"; polev[4][6]="□"; polev[5][6]="□";
        polev[10][10]="□";
        polev[8][9]="□"; polev[9][9]="□";
        polev[7][1]="□"; polev[8][1]="□";*/
        for (int i=0;i<4;i++){
            char bukva = alf[(int) (Math.random()*10)];
            int zifra = (int) (Math.random()*10+1);
            if (!a[zifra][wheAlf(alf,bukva)].equals("□") & mainprov(bukva,bukva,zifra,zifra,alf,a)){
                a[zifra][wheAlf(alf,bukva)] = "□";
            } else {
                i--;
            }
        }
        for (int i=0;i<3;i++){
            char bukva = alf[(int) (Math.random()*10)];
            int zifra = (int) (Math.random()*10+1);
            int zifra1=-1; char bukva1='Р';
            while(zifra1<1 | zifra1>10 | bukva1=='Р'){
                bukva = alf[(int) (Math.random()*10)];
                zifra = (int) (Math.random()*10+1);
                int temp = (int) (Math.random()*4+1);
                switch (temp){
                    case 1 -> {
                        if(zifra>1 & zifra<10) {
                            zifra1 = zifra - 1;
                            bukva1 = bukva;
                        }
                    }
                    case 2 -> {
                        if(wheAlf(alf, bukva)>0 & wheAlf(alf, bukva)<10) {
                            zifra1 = zifra;
                            bukva1 = alf[wheAlf(alf, bukva)];
                        }
                    }
                    case 3 -> {
                        if (zifra<10 & zifra>1){
                            zifra1 = zifra + 1;
                            bukva1 = bukva;
                        }
                    }
                    default -> {
                        if (wheAlf(alf, bukva)>2 & wheAlf(alf, bukva)<11){
                            zifra1 = zifra;
                            bukva1 = alf[wheAlf(alf, bukva) - 2];
                        }
                    }
                }
            }
            if (!a[zifra][wheAlf(alf,bukva)].equals("□") & !a[zifra1][wheAlf(alf,bukva1)].equals("□") & mainprov(bukva,bukva1,zifra,zifra1,alf,a)){
                a[zifra][wheAlf(alf,bukva)] = "□";
                a[zifra1][wheAlf(alf,bukva1)] = "□";
            } else {
                i--;
            }
        }

        for (int i=0;i<2;i++){
            char bukva = alf[(int) (Math.random()*10)];
            int zifra = (int) (Math.random()*10+1);
            int zifra1=-1; char bukva1='P';
            while(zifra1<1 | zifra1>10 | bukva1=='Р') {
                bukva = alf[(int) (Math.random()*10)];
                zifra = (int) (Math.random()*10+1);
                int temp = (int) (Math.random()*4+1);
                switch (temp) {
                    case 1 -> {
                        if(zifra>2 & zifra<11) {
                            zifra1 = zifra - 2;
                            bukva1 = bukva;
                        }
                    }
                    case 2 -> {
                        if(wheAlf(alf, bukva)<9 & wheAlf(alf, bukva)>0) {
                            zifra1 = zifra;
                            bukva1 = alf[wheAlf(alf, bukva) + 1];
                        }
                    }
                    case 3 -> {
                        if(zifra<9 & zifra>0) {
                            zifra1 = zifra + 2;
                            bukva1 = bukva;
                        }
                    }
                    default -> {
                        if (wheAlf(alf, bukva)>3 & wheAlf(alf, bukva)<11) {
                            zifra1 = zifra;
                            bukva1 = alf[wheAlf(alf, bukva) - 3];
                        }
                    }
                }
            }
            if (!a[zifra][wheAlf(alf,bukva)].equals("□") & !a[zifra1][wheAlf(alf,bukva1)].equals("□") & !a[(zifra+zifra1)/2][(wheAlf(alf,bukva)+wheAlf(alf,bukva1))/2].equals("□") & mainprov(bukva,bukva1,zifra,zifra1,alf,a)){
                a[zifra][wheAlf(alf,bukva)] = "□";
                a[(zifra+zifra1)/2][(wheAlf(alf,bukva)+wheAlf(alf,bukva1))/2] = "□";
                a[zifra1][wheAlf(alf,bukva1)] = "□";
            } else {
                i--;
            }
        }
        for (int i=0;i<1;i++){
            char bukva=' ';
            int zifra=0;
            int zifra1=-1; char bukva1='P';
            while(zifra1<1 | zifra1>10 | bukva1=='Р') {
                bukva = alf[(int) (Math.random()*10)];
                zifra = (int) (Math.random()*10+1);
                int temp = (int) (Math.random()*4+1);
                switch (temp) {
                    case 1 -> {
                        if (zifra>3 & zifra<11) {
                            zifra1 = zifra - 3;
                            bukva1 = bukva;
                        }
                    }
                    case 2 -> {
                        if (wheAlf(alf, bukva)<8 & wheAlf(alf, bukva)>0) {
                            zifra1 = zifra;
                            bukva1 = alf[wheAlf(alf, bukva) + 2];
                        }
                    }
                    case 3 -> {
                        if(zifra<8 & zifra>0) {
                            zifra1 = zifra + 3;
                            bukva1 = bukva;
                        }
                    }
                    default -> {
                        if (wheAlf(alf, bukva)>4 & wheAlf(alf, bukva)<11) {
                            zifra1 = zifra;
                            bukva1 = alf[wheAlf(alf, bukva) - 4];
                        }
                    }
                }
            }
            if (!a[zifra][wheAlf(alf,bukva)].equals("□") & !a[zifra1][wheAlf(alf,bukva1)].equals("□") & !a[(zifra+zifra1)/2][(wheAlf(alf,bukva)+wheAlf(alf,bukva1))/2].equals("□") & !a[(zifra+zifra1)/2+1][(wheAlf(alf,bukva)+wheAlf(alf,bukva1))/2+1].equals("□") & mainprov(bukva,bukva1,zifra,zifra1,alf,a)){
                System.out.println(zifra + "  " + bukva1 + zifra1 + "  " + bukva);
                System.out.println(mainprov(bukva,bukva1,zifra,zifra1,alf,a));
                a[zifra][wheAlf(alf,bukva)] = "□";
                a[(zifra+zifra1)/2][(wheAlf(alf,bukva)+wheAlf(alf,bukva1))/2] = "□";
                if (bukva == bukva1){
                    a[(zifra+zifra1)/2+1][wheAlf(alf,bukva)] = "□";
                } else {
                    a[zifra][(wheAlf(alf,bukva)+wheAlf(alf,bukva1))/2+1] = "□";
                }
                a[zifra1][wheAlf(alf,bukva1)] = "□";
            } else {
                i--;
            }
        }
        return a;
    }

}