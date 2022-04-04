package utils;

public class PayloadUtil {
    public static String getPetPayload(int id,String petname,String petStatus){
        String petPayload="{\n" +
                "  \"id\": "+id+",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": "+petname+"\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": "+petStatus+"\"\n" +
                "}";

        return petPayload;
    }
public  static String getSlackMessagePayload(String message){
        String payload = "{\n" +
                "    \"channel\":\"C0397J4JY3T\",\n" +
                "    \"text\":\"jojo"+message+"\"\n" +
                "}";
        return payload;
}

}
