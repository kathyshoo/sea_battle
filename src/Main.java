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
                } else if (l==0 & i!=0) {
                    if (i<10){
                        pole[i][0] = i + " ";
                        polevra[i][0] = i + " ";
                    } else {
                        pole[i][0] = i + "";
                        polevra[i][0] = i + "";
                    }
                } else {
                    pole[i][l] = "◯";
                    polevra[i][l] = "◯";
                }
            }
        }
        for (int i = 0;i<12;i++){
            pole[11][i] = " ";
            pole[i][11] = " ";
            polevra[11][i] = " ";
            polevra[i][11] = " ";
        }
        koravra = botkor(polevra);
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
    public static String[][] botkor(String[][] polev){
        polev[1][1]="□";
        polev[3][1]="□"; polev[3][2]="□";
        polev[1][5]="□";
        polev[1][9]="□"; polev[2][9]="□"; polev[3][9]="□"; polev[4][9]="□";
        polev[8][3]="□";
        polev[10][3]="□"; polev[10][4]="□"; polev[10][5]="□";
        polev[3][6]="□"; polev[4][6]="□"; polev[5][6]="□";
        polev[10][10]="□";
        polev[8][9]="□"; polev[9][9]="□";
        polev[7][1]="□"; polev[8][1]="□";
        return polev;
    }
}