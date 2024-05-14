
public class ConvertMoney {
    public static void main(String[] args) {
        String amout = "52,856,421.15";
        //test data : 00 01 10 11 15 20 21 25 48 60 100 101 121 2600 4000 4201 5555110243221000001

        String[] texts = { "ศูนย์", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า" };
        String[] details = { "", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน" }; 

        String bahtReplace = amout.replace(",", "");
        String[] bahtSplit = bahtReplace.split("[.]");
        String baht = bahtSplit[0];

        if(baht.length()>6){
            if (amout.contains(".")) {
                String satung = bahtSplit[1];
                CalculateMillion(baht, texts, details);
                System.out.print("บาท");
                CalculateSaTung(satung, texts, details);
            } else {
                CalculateMillion(baht, texts, details);
                System.out.print("บาทถ้วน");
            } 
        }else if(baht.length()>0){
            if (amout.contains(".")) {
                String satung = bahtSplit[1];
                if(Integer.parseInt(baht) == 0 || Integer.parseInt(baht) == 00){
                    System.out.print("ศูนย์บาท");
                    CalculateSaTung(satung, texts, details);
                }else{
                    CalculateBaht(baht, texts, details);
                    System.out.print("บาท");
                    CalculateSaTung(satung, texts, details);
                }
            } else {
                if(Integer.parseInt(baht) == 0 || Integer.parseInt(baht) == 00){
                    System.out.print("ศูนย์บาทถ้วน");
                }else{
                    CalculateBaht(baht, texts, details);
                    System.out.print("บาทถ้วน");
                }
                
            } 
        }
    }

    private static void CalculateMillion(String baht, String[] texts, String[] details) {
        int len = baht.length();
        String firstPart = "";
        String secondPart = "";

        firstPart = baht.substring(0, len - 6);
        secondPart = baht.substring(len - 6);

        if(firstPart.length()>6){ //handle มากกว่า ล้านล้าน
            int len2 = firstPart.length();
            String firstPart2_1 = "";
            String firstPart2_2 = "";

            firstPart2_1 = firstPart.substring(0, len2 - 6);
            firstPart2_2 = firstPart.substring(len2 - 6);

            CalculateBaht(firstPart2_1, texts, details);
            System.out.print("ล้าน");
            CalculateBaht(firstPart2_2, texts, details);
            System.out.print("ล้าน");
            CalculateBaht(secondPart, texts, details);
        }else{
            CalculateBaht(firstPart, texts, details);
            System.out.print("ล้าน");
            CalculateBaht(secondPart, texts, details);
        } 
    }

    private static void CalculateBaht(String baht, String[] texts, String[] details) {
        String[] eachNumInBaht = baht.split("");
        String txtBaht = "";
        int d = eachNumInBaht.length - 1;
            for (int i = 0; i < eachNumInBaht.length; i++) {
                int n = Integer.parseInt(eachNumInBaht[i]);
                if (i == eachNumInBaht.length - 1 && n == 1 && baht.length() >1) { // Handle 1 กับ เอ็ด
                    if (Integer.parseInt(eachNumInBaht[i - 1]) == 0) {
                        txtBaht += texts[n];
                    } else {
                        txtBaht += "เอ็ด";
                    }
                } else if (n == 0) { // Handle 0 ต่อหลัง เช่น 30, 200, 4000, ...

                } else if (eachNumInBaht.length - i == 2) { // Handle สอง กับ ยี่
                    if (n == 2) {
                        txtBaht += "ยี่";
                        txtBaht += details[d];
                    } else if (n == 1) {
                        txtBaht += details[d];
                    } else if (n == 0) { // Handle 0 ต่อหลัง เช่น 30, 200, 4000, ...

                    } else {
                        txtBaht += texts[n];
                        txtBaht += details[d];
                        //break;
                    }
                } else if (Integer.parseInt(baht) % 10 == 0) { // Handle 10, 20, 30, ...
                    if (Integer.parseInt(baht) == 10) {
                        txtBaht += details[d];
                    } else if (n == 0) { // Handle 0 ต่อหลัง เช่น 30, 200, 4000, ...

                    }else {
                        txtBaht += texts[n];
                        txtBaht += details[d];
                    }
                } else { // เลขทั่วไปที่ไม่ใช้ ยี่(25) เอ็ด(51) สิบ(18)
                    txtBaht += texts[n];
                    txtBaht += details[d];
                }
                d--;
            }
        // }
        System.out.print(txtBaht);
    }

    private static void CalculateSaTung(String satung, String[] texts, String[] details) {
        if (Integer.parseInt(satung) == 0 || Integer.parseInt(satung) == 00) { // handle 0/00
            System.out.print("ถ้วน");
        } else {
            CalculateBaht(satung, texts, details);
            System.out.print("สตางค์");
        }
    }
}
