/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package whatsappmessage;
import java.util.Scanner;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.io.IOException;
import java.net.http.HttpResponse;
import org.json.*;
/**
 *
 * @author saravana
 */
public class WhatsappMessage {

    /**
     * @param args the command line arguments
     */
    static Scanner console;
    static final String TOKEN = ""; //refere ultramsg.com to get your own token
    public static void main(String[] args) {
       console = new Scanner ( System.in );
       int choice;
       System.out.print ( "\nTo number: " );
       String number = console.next ( );
       while ( true ) {
         console.nextLine ( );
         System.out.print ( "\nMessage: " );
         String message = console.nextLine ( );
        try {    
            System.out.println ( sendMessageThroughWhatsapp ( number , message ) ? "\nsent successfully!" : "failed to send!" );       
        }
        catch ( JSONException | IOException | InterruptedException exceptions ) { 
            System.out.println ( "error check token or to_number format!" );
                    
        }
        System.out.print ( "\nPress 1 to continue! any other key to terminate chat!: " );
        choice = console.nextInt ( );
        if ( choice != 1 )
            break;
       }
    }
    public static boolean sendMessageThroughWhatsapp ( String toNumber , String message ) throws JSONException , IOException , InterruptedException {
        message = message.replaceAll ( " " , "%20" );
        HttpClient client = HttpClient.newHttpClient ( );
        String uri = "https://api.ultramsg.com/instance6529/messages/chat?token="+TOKEN+"&to=+91"+toNumber+"&body="+message+"&priority=10";
        HttpRequest request = HttpRequest.newBuilder ( ).GET ( ).uri ( URI.create ( uri ) ).build ( );
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString ( ));
        return new JSONObject ( response.body ( ) ).getString ( "sent" ).equals ( "true" );
    }
} 
