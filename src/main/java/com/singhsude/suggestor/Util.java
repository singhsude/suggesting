import com.singhsude.suggestor;

public class Util{
private static String[] localStorage = new String[256];
private static index = -1;
public static String getString(int i) {
  if(i <= index)
    return localStorage[i];
    
    return null;
}

public static int putString(String str) {
  if(index >= 256)
    index = -1;
    index++;
    
    localStorage[index] = str;
    return index;
}
}
