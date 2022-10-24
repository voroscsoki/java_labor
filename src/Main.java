public class Main{
    public static void main(String[] args) {
        String asd = "abCdEf";
        System.out.println(caesarCode(asd, -25));

        CaesarFrame cf = new CaesarFrame();
    }

    private static String caesarCode(String input, int offset){
        StringBuilder sb = new StringBuilder(input.toUpperCase());
        for (int i = 0; i < sb.length(); i++){
            char tmp = (char) (sb.charAt(i) + offset);
            while(tmp < 'A')
                tmp += 26;
            while(tmp > 'Z')
                tmp -= 26;
            sb.setCharAt(i, tmp);
        }
        return sb.toString();
    }
}