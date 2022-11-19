public class Main {
    public static void main(String[] args) {
        //массив с буквами
        char[] alf = new char[]{'А','Б','В','Г','Д','Е','Ж','З','И','К'};
        //создание поля
        String[][] pole = new String[11][11];
        for (int i = 0; i<11;i++){
            for (int l = 0; l<11;l++){
                if (i==0 & l!=0){
                    pole[0][l] = alf[l-1] + "";
                } else if (l==0 & i!=0) {
                    if (i<10){
                        pole[i][0] = i + " ";
                    } else {
                        pole[i][0] = i + "";
                    }
                } else {
                    pole[i][l] = "◯";
                }
            }
        }
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
}