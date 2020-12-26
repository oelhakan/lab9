package task9;

import java.util.Arrays;

public class Words {
    protected String arg = "";

    public Words(String arg){
        if (!(arg == null)){
            this.arg = arg;
            setString(cleanWords(arg));
        }
    }	//if arg == null use ""

    public boolean equals(Object other){
        if (other == this) return true;
        if (!(other instanceof Words) || (other instanceof UniqueWords)) return false;
        Words otherWords = (Words) other;
        String[] thisArr = getSortedWordsArray(arg);
        String[] otherArr = getSortedWordsArray(otherWords.arg);
        if (thisArr.length != otherArr.length) return false;
        for (int i = 0; i < thisArr.length; i++){
            if (!thisArr[i].equals(otherArr[i])) return false;
        }
        return true;
    }	//return true only of of is of the type Words and both objects contain the same SET of words

    public void clear(){
        setString("");
    }	//sets internal string to ""

    public void append(String s){
        setString(arg + " " + s);
    }	// append a string to the existing one

    public void append(Words wrd){
        setString(arg + " " + wrd.arg);
    }	// append a string from wOrd

    public String[] maxLenWords(){
        return minMaxWords(true);
    }	//returns an array of words with maximal length

    public String[] minLenWords(){
        return minMaxWords(false);
    }	//Returns an array with words with minimal length

    private String[] minMaxWords(boolean max){
        String[] arrArg = toArray(arg);
        int len = max ? 0 : Integer.MAX_VALUE;
        int howMany = 0;
        for (String s : arrArg){
            if ((max ? s.length() > len : s.length() < len))  len = s.length();
        }
        for (String s : arrArg){
            if (s.length() == len) howMany++;
        }

        String[] arr = new String[howMany];
        int index = 0;
        for (String s : arrArg){
            if (s.length() == len) {
                arr[index] = s;
                index++;
            }
        }
        return arr;
    }

    public String[] getSortedWordsArray(String words){
        String[] other = toArray(words);
        Arrays.sort(other);
        return other;
    }

    public String[] toArray(String words){
        return words.split("[\\s\\.?!:,]+");
    }

    protected String cleanWords(String words){
        String[] other = toArray(words);
        String clean = other[0];
        for (int i = 1; i < other.length; i++)
            clean = clean + " " + other[i];
        return clean;
    }

    public void setString(String s){
        arg = s;
    }

    @Override
    public String toString(){
        return Arrays.toString(toArray(arg));
    }
}
