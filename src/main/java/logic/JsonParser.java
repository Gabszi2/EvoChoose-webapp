package logic;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JsonParser {

    private String path;
    private String arrayname;

    public JsonParser(String path,String arrayname)
    {
        this.path=path;
        this.arrayname=arrayname;
    }

    public JSONArray jsonArrayParser()
    {
        JSONParser parser = new JSONParser();
        JSONArray array=new JSONArray();
        try {
            Object obj = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;

            array = (JSONArray) jsonObject.get(arrayname);
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (
                ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public int getSize() {
        return jsonArrayParser().size();
    }
    public String answerParser(boolean[] answers,int size)
    {
        JSONArray array=jsonArrayParser();


        Iterator i=array.iterator();

        while (i.hasNext()) {
            {
                int tmpIterator = 0;
                int good = 0;

                JSONObject answer = (JSONObject) i.next();
                JSONArray answerArray = (JSONArray) answer.get("answer");

                Iterator j = answerArray.iterator();
                boolean[] testAnswers = new boolean[size];

                while (j.hasNext()) {
                    testAnswers[tmpIterator] = (Boolean) j.next();
                    tmpIterator++;
                }

                for (int k = 0; k < answers.length; k++) {
                    if (testAnswers[k] == answers[k]) {
                        good++;
                    }
                }

                if (good == size) {
                    String q = (String) answer.get("name");
                    return q;

                }
            }
        }

            return "Unfortunetly there isn't any food to your likeing in our system.";

    }
}
