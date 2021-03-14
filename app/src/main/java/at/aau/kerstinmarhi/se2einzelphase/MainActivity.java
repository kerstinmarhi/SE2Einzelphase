package at.aau.kerstinmarhi.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText matrNr;
    private Button send;
    private TextView printMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matrNr = findViewById(R.id.edtxt_number);
        send=findViewById(R.id.btn_send);
        printMessage=findViewById(R.id.txtview_serverreply);

    }

    public void sendData(View view){
        String data=matrNr.getText().toString();
        TCP_Client client=new TCP_Client(data);
        Thread thread=new Thread(client);
        thread.start();
        try {
            thread.join();      //wartet bis aufgerufener Thread terminiert.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String serverReply=client.getMessage();
        printMessage.setText(serverReply);
    }

    public void calc(View view){
        String enteredNumber = matrNr.getText().toString();
        int number;
        int sum=0;

        if (enteredNumber.matches("0[0-9]*")){     //wenn Matrikelnummer mit 0 beginnt
            enteredNumber=enteredNumber.substring(1);    //0 am Anfang wird entfernt
            number=Integer.parseInt(enteredNumber);
        }else {
            number=Integer.parseInt(enteredNumber);
        }

        while (number!=0){
            sum += (number%10);
            number = number/10;
        }

        if (sum%2==0){
            printMessage.setText(R.string.main_calc_even);
        }else {
            printMessage.setText(R.string.main_calc_odd);
        }
    }
}