public class q3 {
    public static void main(String[] args) {
        Integer[] example = {4,2,9,1,5};
        sort(example);
        System.out.println();
        findMax(example);
        findAverage(example);
    }

    public static void sort(Integer[] exmaple){
        int firstNum;
        int secondNum;
        for(int i=0;i<exmaple.length-1;i++){
            for(int j=i+1;j<exmaple.length;j++){
                firstNum = exmaple[i];
                secondNum = exmaple[j];
                //System.out.println(firstNum + " " + secondNum);
                if(secondNum < firstNum){
                    exmaple[i] = secondNum;
                    exmaple[j] = firstNum;
                }
            }
        }
        System.out.print("Sort: ");
        for(int k=0;k<exmaple.length;k++){
            System.out.print(exmaple[k] + " ");
        }
    }

    public static void findMax(Integer[] example){
        int num = example[0];
        for(int i=1;i<example.length;i++){
            if(num < example[i]){
                num = example[i];
            }
        }
        System.out.println("The max number is " + num);
    }

    public static void findAverage(Integer[] example){
        double sum = 0;
        for(int i=0;i<example.length;i++){
            sum += example[i];
        }
        //System.out.println(sum);
        double avg = sum/example.length;
        System.out.println("The average is " + String.format("%.2f", avg));
    }
}