package Words;

public class WordPlay {

    /*This method returns true if character is vowel
    * Given character may be upper case or lowercase */
    public boolean isVowel(char ch){
        String vowels = "aeiou";
        char input = Character.toLowerCase(ch);
        int index = vowels.indexOf(input);
        if(index != -1){
            return true;
        }
        return  false;
    }

    public void testIsVowel(){
        boolean output = isVowel('a');
        System.out.println(output);
    }

    /*This method replaces all the vowels with given character
    * in given phrase and returns the obtained string */
    public String replaceVowels(StringBuilder phrase, char ch){
        for(int i=0; i<phrase.length(); i++){
            char currChar = phrase.charAt(i);
            if(isVowel(currChar)){
                phrase.setCharAt(i, ch);
            }
        }
        return phrase.toString();
    }

    public void testReplaceVowels(){
        String phrase = replaceVowels(new StringBuilder("Hello World"), '*');
        System.out.println(phrase);
    }

    /*In the given string, given character will be replaced by
    * '*' if character is in odd position
    * '+' if character is in even position */
    public String emphasize(StringBuilder phrase, char ch){
        for(int i=0; i<phrase.length(); i++){
            if(Character.toLowerCase(ch) == Character.toLowerCase(phrase.charAt(i))) {
                if (i % 2 == 0) {
                    phrase.setCharAt(i, '*');
                } else {
                    phrase.setCharAt(i, '+');
                }
            }
        }
        return phrase.toString();
    }

    public void testEmphasize(){
        String phrase = emphasize(new StringBuilder("Mary Bella Abracadabra"),'a');
        System.out.println(phrase);
    }

    public static void main(String[] args) {
        WordPlay play = new WordPlay();
        //play.testIsVowel();
        //play.testReplaceVowels();
        play.testEmphasize();
    }
}
