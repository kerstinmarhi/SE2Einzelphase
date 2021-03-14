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

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
    }

    public void sendData(){
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
}