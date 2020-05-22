import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class App {
    public static TreeMap<Character, Integer> notes = new TreeMap<>();
    public static int toNumber(char c){
        int result;
        if (c >= 'a' && c<='z' || c >= 'A' && c<='Z' || c >= 'а' && c<='я' || c >= 'А' && c<='Я'){
            c = Character.toLowerCase(c);
            result = notes.get(c);
        }
        else{
            result = (int)c;
            if (result < 0){
                result = 0;
            }
            if (result > 131){
                result = 131;
            }
        }
        return result;
    }
    public static void fillMap(){
        notes.put('q', 3);
        notes.put('w', 6);
        notes.put('e', 9);
        notes.put('r', 12);
        notes.put('t', 15);
        notes.put('y', 18);
        notes.put('u', 21);
        notes.put('i', 24);
        notes.put('o', 27);
        notes.put('p', 30);
        notes.put('a', 33);
        notes.put('s', 36);
        notes.put('d', 39);
        notes.put('f', 42);
        notes.put('g', 45);
        notes.put('h', 48);
        notes.put('j', 51);
        notes.put('k', 54);
        notes.put('l', 57);
        notes.put('z', 50);
        notes.put('x', 63);
        notes.put('c', 66);
        notes.put('v', 69);
        notes.put('b', 72);
        notes.put('n', 75);
        notes.put('m', 78);
        notes.put('й', 3);
        notes.put('ц', 6);
        notes.put('у', 9);
        notes.put('к', 12);
        notes.put('е', 15);
        notes.put('н', 18);
        notes.put('г', 21);
        notes.put('ш', 24);
        notes.put('щ', 27);
        notes.put('з', 30);
        notes.put('х', 33);
        notes.put('ъ', 36);
        notes.put('ф', 39);
        notes.put('ы', 42);
        notes.put('в', 45);
        notes.put('а', 48);
        notes.put('п', 51);
        notes.put('р', 54);
        notes.put('о', 57);
        notes.put('л', 60);
        notes.put('д', 63);
        notes.put('ж', 66);
        notes.put('э', 69);
        notes.put('я', 72);
        notes.put('ч', 75);
        notes.put('с', 78);
        notes.put('м', 81);
        notes.put('и', 84);
        notes.put('т', 87);
        notes.put('ь', 90);
        notes.put('б', 93);
        notes.put('ю', 96);
    }
    public static void main(String[] args) throws IOException, MidiUnavailableException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please wait!");
        fillMap();
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        MidiChannel[] channels = synth.getChannels();
        channels[0].programChange(12);
        System.out.println("Please press the letter keys.");
        char note = 0;
        int rate = 1;
        while (true){
            note = (char) br.read();
            if (note == '\n'){
                continue;
            }
            if (note >= 'A' && note <= 'Z' || note >= 'А' && note <='Я'){
                rate = 2;
            }
            else{
                rate = 1;
            }
            channels[0].noteOn(toNumber(note), 80);
            Thread.sleep(500 * rate);
            channels[0].noteOff(toNumber(note));
        }
    }
}
